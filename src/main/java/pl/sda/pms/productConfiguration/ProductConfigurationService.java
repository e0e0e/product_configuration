package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.ShortFeature;
import pl.sda.pms.order.Ord;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

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

	public ProductConfiguration save(ProductConfiguration productConfiguration) {
		return productConfigurationRepository.save(productConfiguration);

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

	public List<ProductConfiguration> productSearch(Map<String, String> paramMap) {
		Map<String, Feature> filterMap = paramMap.entrySet().stream().filter(x -> !x.getKey().startsWith("nst-"))
				.collect(Collectors.toMap(x -> productFeatureService.findByID(Long.parseLong(x.getKey())).getName(),
						x -> featureService.findByID(Long.parseLong(x.getValue()))));

		List<ProductConfiguration> matchingProducts = getFilteredProducts(filterMap);

		return matchingProducts;
	}

	public Map<String, List<ShortFeature>> productLiveSearch(String features) {
		JSONObject obj = new JSONObject(features);

		Map<String, Feature> filterMap = obj.toMap().entrySet().stream().filter(x -> !x.getKey().startsWith("NS-"))
				.collect(Collectors.toMap(x -> x.getKey(),
						x -> featureService.findByID(Long.parseLong((String) x.getValue()))));

		List<ProductConfiguration> productList = getFilteredProducts(filterMap);


		Map<String, List<ShortFeature>> formMap = getMApOfResultsForForm(productList);

		for (Entry<String, Feature> m : filterMap.entrySet()) {
			List<ShortFeature> shortFeatureList = formMap.get(m.getKey());

			if (shortFeatureList != null) {
				for (ShortFeature sf : shortFeatureList) {
					if (sf.getName().equals(m.getValue().getName())) {
						sf.setSelected(true);
					}

				}
			}

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

		List<ProductConfiguration> productList = new ArrayList<>();

		for (ProductConfiguration pC : productConfigurations) {
			if (!pC.getName().equals("pattern")) {
				Integer resultNumber = 0;

				for (ProductFeature pF : pC.getConfigurationList()) {
					Set<String> pFNameSet = new HashSet<>();
					pFNameSet.addAll(pF.getFeature().stream().map(x -> x.getName()).collect(Collectors.toSet()));

					if (filterMap.get(pF.getName()) != null) {
						if (pFNameSet.contains(filterMap.get(pF.getName()).getName())) {
							resultNumber++;
						}
					}
				}
				if (resultNumber == filterMap.size()) {

					productList.add(pC);
				}
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
								pF.getFeature().stream().map(
										f -> new ShortFeature(f.getId(), f.getName(), f.getImagePath(), f.getIndex()))
										.collect(Collectors.toList()));
					} else {
						List<ShortFeature> featureNames = formMap.get(pF.getName());

						pF.getFeature().stream()
								.filter(f -> !featureNames.contains(
										new ShortFeature(f.getId(), f.getName(), f.getImagePath(), f.getIndex())))
								.forEach(f -> featureNames.add(new ShortFeature(f.getId(), f.getName())));
					}

				}

			}
		}
		return formMap;
	}

	public ProductConfiguration findByName(String name) {
		return productConfigurationRepository.findByName(name);
	}

	public void removeProductFeatureByName(String productFeatureName) {

		List<ProductFeature> pfs = productFeatureService.findAll();

		for (ProductFeature p : pfs) {
			if (p.getName().equals(productFeatureName)) {
				p.removeProductConfiguration();
				productFeatureService.save(p);
			}
		}

	}

	public List<ProductConfiguration> findAllContainingFeatureById(Long featureId) {
		return productConfigurationRepository.findAllContainingFeatureById(featureId);
	}

	public void updatePattern() {

		ProductConfiguration productConfiguration = findByName("pattern");

		List<ProductConfiguration> productConfigurations = productConfigurationRepository.findAll();

		for (ProductConfiguration pC : productConfigurations) {
			if (!pC.getName().equals("pattern")) {

				for (ProductFeature pF : pC.getConfigurationList()) {
					try {
						ProductFeature productFeature = productConfiguration.findProductFeatureByName(pF.getName());
						if (productFeature.getFeature() == null) {
							productFeature.setFeature(new HashSet<>());
						}
						productFeature.getFeature().addAll(pF.getFeature());

						productFeatureService.save(productFeature);
					} catch (Exception e) {
						System.out.println("Update pattern error: " + e.getLocalizedMessage()
								+ " ther is no Product feature with name: " + pF.getName() + " in pattern: "
								+ pC.getName());
					}

				}
			} else {
				pC.removeAllFeaturesFromList();

			}
		}

	}

	public void importPorductConfigurations(List<ProductConfiguration> productConfigurations) {
		for (ProductConfiguration pC : productConfigurations) {
			Boolean existsProduct = productConfigurationRepository.existsById(pC.getId());
			if (!existsProduct) {
				List<ProductFeature> productFeatures = pC.getConfigurationList();
				for (ProductFeature pf : productFeatures) {
					Set<Feature> features = pf.getFeature();

					for (Feature f : features) {
						try {
							Feature existingFeature = featureService.findFeatureByNameAndIndex(f);
							if (existingFeature != null) {
								f.setId(existingFeature.getId());
								f = existingFeature;

							} else {
								Feature newFeature = featureService.save(f);
								f.setId(newFeature.getId());
								f = newFeature;

							}
						} catch (Exception e) {
							System.out.println("did not saved feature" + " error: " + e.getLocalizedMessage());
						}
					}

					if (!productFeatureService.existsById(pf.getId())) {
						try {
							ProductFeature newProductFeature = productFeatureService.save(pf);
							pf.setId(newProductFeature.getId());
							pf = newProductFeature;

							for (Feature newf : pf.getFeature()) {

								featureService.save(newf);
							}
						} catch (Exception e) {
							System.out.println("did not saved product feature: " + pf.getName() + " error: "
									+ e.getLocalizedMessage());
						}
					}

				}
				try {
					ProductConfiguration newProductConfiguration = productConfigurationRepository.save(pC);
					pC.setId(newProductConfiguration.getId());
					for (ProductFeature newPf : newProductConfiguration.getConfigurationList()) {
						newPf.setProductConfiguration(newProductConfiguration);
						ProductFeature nextPf = productFeatureService.save(newPf);

					}

					System.out.println("saved product configuration: " + pC.getName());
				} catch (Exception e) {
					System.out.println("did not saved product Configuration: " + pC.getName() + " error: "
							+ e.getLocalizedMessage());
				}

			}

		}

	}

	public void dropAllObjects() {

		productConfigurationRepository.dropAllObjects();
	}

	public void addFeatureToProducts(String productsAndPfName) {

		JSONObject obj = new JSONObject(productsAndPfName);
		Long featureId = Long.parseLong(obj.get("featureId").toString());
		JSONArray idArray = (JSONArray) obj.get("productsId");
		List<Long> idList = idArray.toList().stream().map(x -> Long.parseLong(x.toString()))
				.collect(Collectors.toList());
		String productFeatureName = obj.get("productFeatureName").toString();
		List<ProductConfiguration> products = productConfigurationRepository.findAllById(idList);

		Feature feature = featureService.findByID(featureId);

		addFeatureToProductsByPf(feature, products, productFeatureName);

		System.out.println("added feature: " + feature.getName() + " to: " + productFeatureName);

	}

	private void addFeatureToProductsByPf(Feature feature, List<ProductConfiguration> products,
			String productFeatureName) {

		products.stream().forEach(x -> {
			x.getConfigurationList().stream().filter(p -> p.getName().equals(productFeatureName)).forEach(f -> {

				f.addFeatureToFeatureSet(feature);
				productFeatureService.save(f);

			});

		});

	}

	public void deleteFeatureFromProducts(String productsAndPfName) {

		JSONObject obj = new JSONObject(productsAndPfName);
		Long featureId = Long.parseLong(obj.get("featureId").toString());
		JSONArray idArray = (JSONArray) obj.get("productsId");
		List<Long> idList = idArray.toList().stream().map(x -> Long.parseLong(x.toString()))
				.collect(Collectors.toList());
		String productFeatureName = obj.get("productFeatureName").toString();
		List<ProductConfiguration> products = productConfigurationRepository.findAllById(idList);

		Feature feature = featureService.findByID(featureId);

		removeFeatureFromProductsByPf(feature, products, productFeatureName);

		System.out.println("Deleted feature: " + feature.getName() + " to: " + productFeatureName);
	}

	private void removeFeatureFromProductsByPf(Feature feature, List<ProductConfiguration> products,
			String productFeatureName) {

		products.stream().forEach(x -> {
			x.getConfigurationList().stream().filter(p -> p.getName().equals(productFeatureName)).forEach(f -> {

				f.removeFeatureToFeatureSet(feature);
				productFeatureService.save(f);

			});

		});
	}

	public void removeProductFeatureByNameIfNotUsed(String productFeatureName) {
		List<ProductFeature> pfs = productFeatureService.findAll();

		for (ProductFeature p : pfs) {
			if (p.getName().equals(productFeatureName)) {
				if (p.getProductConfiguration() == null) {
					try {
						productFeatureService.remove(p);

					} catch (Exception e) {
						System.out.println("Deleted product features failed: " + e.getLocalizedMessage());
					}

				}

			}
		}

	}

	public void removeProductFeatureFromAll(Long productFeatureId) {
		List<ProductConfiguration> product = productConfigurationRepository.findAll();
		String pF=productFeatureService.findByID(productFeatureId).getName();

		product.stream().forEach(x->{
			ProductFeature productFeature=x.findPFByName(pF);
			productFeature.removeProductConfiguration();
			
			
			productConfigurationRepository.save(x);
		});
		

	}


	public void moveDown(Long productFeatureId, Long productId) {

		ProductFeature productFeature = productFeatureService.findById(productFeatureId);
		List<ProductFeature> productFeaturesByOrgynalName = productFeatureService
				.findAllByName(productFeature.getName());
		try {
			for (ProductFeature pf : productFeaturesByOrgynalName) {
				try {
					ProductConfiguration productConfiguration = pf.getProductConfiguration();
					int position = pf.findPositionInProduct();

					ProductFeature nextProductFeature = productConfiguration.getConfigurationList().stream()
							.sorted(Comparator.comparing(ProductFeature::findPositionInProduct))
							.filter(x -> x.findPositionInProduct() > position).findFirst().get();

					pf.setPosition(nextProductFeature.findPositionInProduct());
					nextProductFeature.setPosition(position);

					productFeatureService.save(nextProductFeature);
					productFeatureService.save(pf);
				} catch (Exception e) {
					System.out.println("product feature Service productFeature last in product: " + e.getMessage());
				}

			}
		} catch (Exception e) {
			System.out.println("move down: " + e.getMessage());
		}

	}

	public void moveUp(Long productFeatureId, Long productId) {
		
		ProductFeature productFeature = productFeatureService.findById(productFeatureId);
		List<ProductFeature> productFeaturesByOrgynalName = productFeatureService
				.findAllByName(productFeature.getName());
		try {
			for (ProductFeature pf : productFeaturesByOrgynalName) {
				try {
					ProductConfiguration productConfiguration = pf.getProductConfiguration();
					int position = pf.findPositionInProduct();

					ProductFeature nextProductFeature = productConfiguration.getConfigurationList().stream()
							.sorted(Comparator.comparing(ProductFeature::findPositionInProduct).reversed())
							.filter(x -> x.findPositionInProduct() < position).findFirst().get();

					pf.setPosition(nextProductFeature.findPositionInProduct());
					nextProductFeature.setPosition(position);

					productFeatureService.save(nextProductFeature);
					productFeatureService.save(pf);
				} catch (Exception e) {
					System.out.println("product feature Service productFeature last in product: " + e.getMessage());
				}

			}
		} catch (Exception e) {
			System.out.println("move Up: " + e.getMessage());
		}

	}

	public void unifyPosition() {
		ProductConfiguration pattern=productConfigurationRepository.findByName("pattern");

		List<ProductFeature> productFeatures = productFeatureService.findAll();

		Set<String> nameSet = productFeatures.stream().map(pf -> pf.getName()).collect(Collectors.toSet());

		for (String name : nameSet) {
			List<ProductFeature> pfList = productFeatureService.findAllByName(name);

			try {

				if(pfList.stream().filter(x -> x.getProductConfiguration() != null).findFirst().isPresent()){
				Integer positionDefault = pfList.stream().filter(x -> x.getProductConfiguration() != null).findFirst()
						.get().findPositionInProduct();
				Integer positionDefaultInPc=pattern.findPFPositionByName(name);
					if(name.equals("Configurator")){

						System.out.println("af");
					}
				pfList.stream().forEach(pf -> {
					pf.setPosition(positionDefault);
					productFeatureService.save(pf);
				});
			}else{
				System.out.println("Product Feature Service probably not used in product: err0 ");

			}
			} catch (Exception e) {
				System.out.println("Product Feature Service probably not used in product " + e.getMessage());
			}

		}
	}

}
