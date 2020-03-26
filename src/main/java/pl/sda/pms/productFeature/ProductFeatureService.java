package pl.sda.pms.productFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import javassist.expr.NewArray;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.Row;
import pl.sda.pms.order.Ord;
import pl.sda.pms.productConfiguration.ProductConfiguration;
import pl.sda.pms.productConfiguration.ProductConfigurationService;

@Service
public class ProductFeatureService {

	@PersistenceContext
	EntityManager entityManager;

	private final ProductFeatureRepository productFeatureRepository;
	private final FeatureService featureService;

	public ProductFeatureService(ProductFeatureRepository productFeatureRepository, FeatureService featureService) {

		this.productFeatureRepository = productFeatureRepository;
		this.featureService = featureService;

	}

	public List<ProductFeature> findAll() {

		return productFeatureRepository.findAll();
	}

	public List<List<Row>> getFields() {
		List<ProductFeature> productFeatureList = productFeatureRepository.findAll();

		List<List<Row>> tableValues = new ArrayList<List<Row>>();

		for (ProductFeature feature : productFeatureList) {

			List<Row> rowList = new ArrayList<>();
			rowList.add(new Row("Product Feature Name", feature.getName(), "name"));
			rowList.add(new Row("Description", feature.getDescription(), "description"));
			rowList.add(new Row("Image Path", feature.getImagePath(), "imagePaths"));

			tableValues.add(rowList);

		}

		return tableValues;

	}

	public ProductFeature findById(Long id) {
		return productFeatureRepository.findById(id).get();

	}

	public ProductFeature findByID(Long id) {
		return productFeatureRepository.findById(id).get();

	}

	public boolean edit(Long id, String newName, String description, String imagePath, List<Long> featureList,
			String parent, Boolean color) {

		ProductFeature productFeature = productFeatureRepository.findById(id).get();
		List<ProductFeature> productFeatureSameName = productFeatureRepository.findByName(productFeature.getName());

		// List<ProductFeature> productFeaturesByName=
		// productFeatureRepository.findByName(productFeature.getName());

		Set<Feature> featuresToAddFeatures = new HashSet<>();
		try {
			featuresToAddFeatures.addAll(productFeature.getFeature());
		} catch (Exception e) {
			System.out.println("product feature service edit failur" + e.getMessage());
		}
		try {
			for (Long f : featureList) {
				Feature feature = featureService.findByID(f);
				featuresToAddFeatures.add(feature);
			}
			productFeature.setFeature(featuresToAddFeatures);

		} catch (Exception e) {
			System.out.println(
					"product feature service edit, no new features in feature List, no problem: " + e.getMessage());
		}
		List<Boolean> createdFeature = new ArrayList<>();

		productFeatureSameName.stream().forEach(pf -> {
			pf.setName(newName);
			pf.setDescription(description);
			pf.setImagePath(imagePath);
			pf.setColor(color);
			pf.setParent(parent);

			createdFeature.add(productFeatureRepository.save(pf).getId() == null);
		});

		return createdFeature.contains(false);
	}

	public void save(List<ProductFeature> configurationList, ProductConfiguration productConfiguration) {
		for (int i = 0; i < configurationList.size(); i++) {
			ProductFeature productFeature = configurationList.get(i);
			productFeature.setProductConfiguration(productConfiguration);
			productFeature.setPosition(i);
			productFeatureRepository.save(productFeature);
		}
		// configurationList.forEach(x -> {
		// x.setProductConfiguration(productConfiguration);
		// x.autoPosition();
		// productFeatureRepository.save(x);
		// });

	}

	public ProductFeature save(String name, String description, String imagePath, List<Long> featureList) {

		Set<Feature> featureSet = new HashSet<Feature>();

		for (Long id : featureList) {
			featureSet.add(featureService.findByID(id));
		}

		ProductFeature productFeature = new ProductFeature(name, description, imagePath, featureSet);
		ProductFeature createdProductFeature = productFeatureRepository.save(productFeature);
		return createdProductFeature;

	}

	public void removeFeature(Feature feature, Long productFeatureId) {
		ProductFeature productFeature = productFeatureRepository.findById(productFeatureId).get();
		productFeature.getFeature().remove(feature);
		feature.getProductFeatureList().remove(productFeature);
		// featureService.save(feature);
		productFeatureRepository.save(productFeature);

	}

	public ProductFeature save(ProductFeature productFeature) {
		return productFeatureRepository.save(productFeature);

	}

	public void findByPositionAndProduct(int i, Long productId) {

	}

	public void clone(Long productFeatureId) {
		ProductFeature productFeatureOrgin = productFeatureRepository.findById(productFeatureId).get();

		ProductFeature newProductFeature = new ProductFeature(productFeatureOrgin.getName(),
				productFeatureOrgin.getDescription(), productFeatureOrgin.getImagePath(),
				productFeatureOrgin.getPosition());

		Set<Feature> featureSet = new HashSet<>();
		for (Feature f : productFeatureOrgin.getFeature()) {
			featureSet.add(f);
		}
		newProductFeature.setFeature(featureSet);
		productFeatureRepository.save(newProductFeature);

	}

	public void delete(Long productFeatureId) {
		productFeatureRepository.deleteById(productFeatureId);

	}





	public Collection<Feature> findFeaturesByProductFeatureName(String productFeatureName) {
		List<ProductFeature> productFeatures = productFeatureRepository.findAllByName(productFeatureName);
		Set<Feature> featuresInProductFeature = new HashSet<>();
		productFeatures.stream().forEach(x -> featuresInProductFeature.addAll(x.getFeature()));

		return featuresInProductFeature;
	}

	public Collection<Feature> findWithSameProductFeatue(Long featureId, Ord order) {
		Feature feature = featureService.findByID(featureId);

		try {
			String productFeatureName = order.findOrderFeatureByFeatyre(feature).getProductFeature().getName();
			Collection<Feature> features = findFeaturesByProductFeatureName(productFeatureName);
			return features;
		} catch (Exception e) {

			System.out.println("Feture is not in order Feature: " + e.getMessage());
		}

		return null;
	}

	public Boolean existsById(Long id) {
		return productFeatureRepository.existsById(id);
	}

	public List<String> findOldName(String featureName) {

		List<ProductFeature> productFeaturesList = productFeatureRepository.findByName(featureName);

		ProductFeature pf = productFeaturesList.get(0);
		@SuppressWarnings("unchecked")
		List<ProductFeature> revisions = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(ProductFeature.class, true, true).add(AuditEntity.id().eq(pf.getId()))
				.getResultList();

		Set<String> result = revisions.stream().map(x -> x.getName()).collect(Collectors.toSet());
		result.remove(featureName);

		return null;
	}

	public void remove(ProductFeature p) {
		productFeatureRepository.delete(p);
	}

	public List<ProductFeature> findAllByName(String name) {
		
		return productFeatureRepository.findAllByName(name);
	}

	public List<ProductFeature> findByName(String name) {
		return productFeatureRepository.findByName(name);
	}

}
