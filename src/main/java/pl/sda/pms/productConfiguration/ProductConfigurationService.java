package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import pl.sda.pms.feature.FeatureService;
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

	public ProductConfigurationService(ProductConfigurationRepository productConfigurationRepository,
			ProductFeatureService productFeatureService) {
		this.productConfigurationRepository = productConfigurationRepository;
		this.productFeatureService = productFeatureService;
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
		
		List<ProductFeature> configurationList=new ArrayList<>();
		configurationList.addAll(productConfigurationOrgin.getConfigurationList());
		ProductConfiguration productConfiguration = new ProductConfiguration(
				productConfigurationOrgin.getName() + "copy",configurationList);

		productConfigurationRepository.save(productConfiguration);

	}

}
