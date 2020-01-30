package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.ShortFeature;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureRepository;
import pl.sda.pms.productFeature.ProductFeatureService;
import pl.sda.pms.projects.Project;

@Service
public class ProductConfigurationService {

	@PersistenceContext
	EntityManager entityManager;

	private final ProductConfigurationRepository productConfigurationRepository;
	private final ProductFeatureService productFeatureService;
	private final FeatureService featureService;

	public ProductConfigurationService(ProductConfigurationRepository productConfigurationRepository,
			ProductFeatureService productFeatureService, FeatureService featureService) {
		this.productConfigurationRepository = productConfigurationRepository;
		this.productFeatureService = productFeatureService;
		this.featureService = featureService;
	}

	public List<ProductConfiguration> findAll() {
		return productConfigurationRepository.findAll();

	}

	public List<Object> findAllById(Long id) {
		@SuppressWarnings("unchecked")
		List<Object> revisions = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(ProductConfiguration.class, false, true).add(AuditEntity.id().eq(id))
				.getResultList();

		return revisions;
	}

	public void create(String name, List<Long> productFeatures) {
		ProductConfiguration productConfiguration = new ProductConfiguration();
		productConfiguration.setName(name);

		LinkedList<ProductFeature> configurationList = new LinkedList<ProductFeature>();
		for (Long f : productFeatures) {
			ProductFeature featureToConfigurator = productFeatureService.findById(f);
			featureToConfigurator.setProductConfiguration(productConfiguration);

			configurationList.add(featureToConfigurator);

		}

		productConfiguration.setConfigurationList(configurationList);
		ProductConfiguration createdProductConfiguration = productConfigurationRepository.save(productConfiguration);

		productFeatureService.save(configurationList, createdProductConfiguration);
	}

	public boolean saveChanges(Long id, String name, List<Long> productFeatures) {

		ProductConfiguration productConfiguration = productConfigurationRepository.findById(id).get();
		productConfiguration.setName(name);
		List<ProductFeature> configurationList = new ArrayList<ProductFeature>();
		int maxPosition = 1;
		try {
			configurationList = productConfiguration.getConfigurationList();
			maxPosition = configurationList.stream().mapToInt(x -> x.getPosition()).max().getAsInt() + 1;

		} catch (Exception e) {
			System.out.println("No product Feature in this configurator.");
			System.out.println(e.getMessage());
		}

		try {

			for (int i = 0; i < productFeatures.size(); i++) {
				ProductFeature productFeatureTempFeature = productFeatureService.findById(productFeatures.get(i));
				productFeatureTempFeature.setProductConfiguration(productConfiguration);
				productFeatureTempFeature.setPosition(maxPosition + i);
				configurationList.add(productFeatureTempFeature);
			}

		} catch (Exception e) {
			System.out.println("list of product feature is empty, no problem:" + e.getMessage());
		}

		productConfiguration.setConfigurationList(configurationList);
		ProductConfiguration createdProductConfiguration = productConfigurationRepository.save(productConfiguration);

		productFeatureService.save(configurationList, createdProductConfiguration);

		return createdProductConfiguration.getId() != null;

	}

	public ProductConfiguration findById(Long id) {

		return productConfigurationRepository.findById(id).get();

	}

	public void delete(Long productId) {

		productConfigurationRepository.deleteById(productId);

	}

	public void save(ProductConfiguration productConfiguration) {
		productConfigurationRepository.save(productConfiguration);

	}

	public void clone(Long productId) {

		ProductConfiguration productConfigurationOrgin = productConfigurationRepository.findById(productId).get();
		ProductConfiguration productConfiguration = new ProductConfiguration();
		List<ProductFeature> configurationListOrgin = productConfigurationOrgin.getConfigurationList();

		List<ProductFeature> configurationList = cloneProductFeatures(configurationListOrgin);

		// configurationList.addAll(configurationList);
		for (ProductFeature p : configurationList) {
			p.setProductConfiguration(productConfiguration);

		}

		productConfiguration.setName(productConfigurationOrgin.getName() + "copy");
		productConfiguration.setConfigurationList(configurationList);

		productConfigurationRepository.save(productConfiguration);

	}

	private List<ProductFeature> cloneProductFeatures(List<ProductFeature> configurationListOrgin) {
		List<ProductFeature> configurationList = new ArrayList<>();
		for (ProductFeature productFeature : configurationListOrgin) {

			ProductFeature newProductFeature = new ProductFeature(productFeature.getName(),
					productFeature.getDescription(), productFeature.getImagePath(), productFeature.getPosition());

			Set<Feature> featureSet = new HashSet<>();
			for (Feature f : productFeature.getFeature()) {
				featureSet.add(f);
			}
			newProductFeature.setFeature(featureSet);
			productFeatureService.save(newProductFeature);
			configurationList.add(newProductFeature);
		}
		return configurationList;
	}

	public List<ProductConfiguration> findByForm(Map<String, String> paramMap) {

		List<ProductConfiguration> productsConfigurations = productConfigurationRepository.findAll();

		Map<String, String> filterMap = paramMap.entrySet().stream()
				.collect(Collectors.toMap(x -> productFeatureService.findByID(Long.parseLong(x.getKey())).getName(),
						x -> featureService.findByID(Long.parseLong(x.getValue())).getName()));
		List<String> fNames = filterMap.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());

		List<ProductConfiguration> filteredProductsConfigurations = new ArrayList<ProductConfiguration>();
		for (ProductConfiguration pc : productsConfigurations) {

			Integer isAvalible = 0;
			for (ProductFeature pf : pc.getConfigurationList()) {
				for (Feature f : pf.getFeature()) {
					if (fNames.contains(f.getName())) {
						isAvalible++;
					}

				}

			}

			if (isAvalible == fNames.size()) {
				filteredProductsConfigurations.add(pc);

			}

		}

		return filteredProductsConfigurations;
	}

	public Map<String, List<ShortFeature>> productLiveSearch(String features) {
		JSONObject obj = new JSONObject(features);
		// Map<String, Feature> notStandardsMap=obj.toMap().entrySet().stream().filter(x->x.getKey().startsWith("NS-"))
		// .collect(
		// 		Collectors.toMap(x -> x.getKey().replace("NS-", ""), x -> featureService.createWithName(x.getValue().toString())));


		Map<String, Feature> filterMap = obj.toMap().entrySet().stream()
		.filter(x->!x.getKey().startsWith("NS-"))
		.collect(
				Collectors.toMap(x -> x.getKey(), x -> featureService.findByID(Long.parseLong((String) x.getValue()))));

		List<ProductConfiguration> productList = getFilteredProducts(filterMap);
		
		
//		System.out.println("---VVV---");
//		productList.forEach(x -> System.out.println(x.getName()));

		Map<String, List<ShortFeature>> formMap = getMApOfResultsForForm(productList);
		
		for(Entry<String, Feature> m:filterMap.entrySet()) {
			List<ShortFeature> shortFeatureList=formMap.get(m.getKey());
			
			if(shortFeatureList!=null) {
			for(ShortFeature sf:shortFeatureList) {
				if(sf.getName().equals(m.getValue().getName())) {
					sf.setSelected(true);
				//	System.out.println(sf);
				}
				
			}}
			
			
		}
				

		return formMap;
	}

	public List<ProductConfiguration> getProductList(String features) {
		JSONObject obj = new JSONObject(features);

		Map<String, Feature> filterMap = obj.toMap().entrySet().stream().collect(
				Collectors.toMap(x -> x.getKey(), x -> featureService.findByID(Long.parseLong((String) x.getValue()))));

		List<ProductConfiguration> productList = getFilteredProducts(filterMap);

		return productList;

	}

	private List<ProductConfiguration> getFilteredProducts(Map<String, Feature> filterMap) {
		List<String> productFeatureNameList = filterMap.entrySet().stream().map(x -> x.getKey())
				.collect(Collectors.toList());

		List<ProductConfiguration> productConfigurations = productConfigurationRepository.findAll().stream()
				.filter(x -> {
					if (x.getConfigurationList().stream().filter(p -> productFeatureNameList.contains(p.getName()))
							.count() > 0) {
						return true;
					}
					return false;
				}).collect(Collectors.toList());

		//productConfigurations.forEach(x -> System.out.println(x.getName()));
		List<ProductConfiguration> productList = new ArrayList<>();

		for (ProductConfiguration pC : productConfigurations) {
			Integer resultNumber = 0;

			for (ProductFeature pF : pC.getConfigurationList()) {
				if (pF.getFeature().contains(filterMap.get(pF.getName()))) {
					resultNumber++;
				}
			}
			if (resultNumber == filterMap.size()) {

				productList.add(pC);
			}
		}
		return productList;
	}

	private Map<String, List<ShortFeature>> getMApOfResultsForForm(List<ProductConfiguration> productList) {
		Map<String, List<ShortFeature>> formMap = new HashMap<String, List<ShortFeature>>();

		for (ProductConfiguration pC : productList) {

			if (!pC.getName().equals("pattern")) {

				for (ProductFeature pF : pC.getConfigurationList()) {

					if (!formMap.containsKey(pF.getName())) {
						formMap.put(pF.getName(),
								pF.getFeature().stream().map(f ->new ShortFeature(f.getId(), f.getName(),f.getImagePath())).collect(Collectors.toList()));
					} else {
						List<ShortFeature> featureNames = formMap.get(pF.getName());

						pF.getFeature().stream().filter(f -> !featureNames.contains(new ShortFeature(f.getId(), f.getName(),f.getImagePath())))
								.forEach(f -> featureNames.add(new ShortFeature(f.getId(), f.getName())));
					//	System.out.println(" ");
					}

				}

			}
		}
		return formMap;
	}

	public ProductConfiguration findByName(String name) {
		return productConfigurationRepository.findByName(name);
	}

}
