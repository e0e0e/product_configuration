package pl.sda.pms.feature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeatureController;
import pl.sda.pms.order.Ord;
import pl.sda.pms.productFeature.ProductFeatureRepository;
import pl.sda.pms.productFeature.ProductFeatureService;

@Service
public class FeatureService {

	private final FeatureRepository featureRepository;
	private final ProductFeatureService productFeatureService;

	public FeatureService(FeatureRepository featureRepository, ProductFeatureService productFeatureService) {
		super();
		this.featureRepository = featureRepository;
		this.productFeatureService = productFeatureService;
	}

	public List<Feature> findAll() {

		return featureRepository.findAll();

	}

	public List<List<Row>> getFields() {
		List<Feature> featureList = featureRepository.findAll();

		List<List<Row>> tableValues = new ArrayList<List<Row>>();
		for (Feature feature : featureList) {
			List<Row> rowList = new ArrayList<>();
			rowList.add(new Row("Feature", feature.getName(), "name"));
			rowList.add(new Row("Feature Description", feature.getDescription(), "description"));
			rowList.add(new Row("Price", feature.getPrice().toString(), "price"));
			rowList.add(new Row("Image Path", feature.getImagePath(), "imagePath"));
			rowList.add(new Row("Index", feature.getIndex(), "index"));
			tableValues.add(rowList);
		}

		return tableValues;

	}

	public Feature findByID(Long id) {
		return featureRepository.findById(id).get();
	}

	public boolean saveChanges(String name, String description, String imagePath, String index, String price, Long id,
			String mIndex) {
		Feature feature = featureRepository.findById(id).get();
		feature.setName(name);
		feature.setDescription(description);
		feature.setImagePath(imagePath);
		feature.setIndex(index);
		feature.setmIndex(mIndex);

		if (OrderFeatureController.isNumeric(price)) {
			feature.setPrice(Double.parseDouble(price));
		}
		Feature createdFeature = featureRepository.save(feature);
		return createdFeature.getId() != null;
	}

	public boolean create(String name, String description, String imagePath, String index, String price,
			String mindex) {
		Feature feature = new Feature(name, description, 0.0, imagePath, index, mindex);
		if (OrderFeatureController.isNumeric(price)) {
			feature.setPrice(Double.parseDouble(price));

		}

		Feature createdFeature = featureRepository.save(feature);
		return createdFeature.getId() != null;
	}

	public void save(Feature feature) {
		featureRepository.save(feature);

	}

	public List<Feature> findNotUsed() {
		return featureRepository.findNotUsed();

	}

	public Feature createWithNameNoStandard(String name) {
		Feature feature = new Feature();
		feature.setName(name);
		feature.setNoStandard(true);
		return featureRepository.save(feature);

	}

	public void deleteById(Long featureId) {

		featureRepository.deleteById(featureId);
	}

	public Collection<Feature> findWithSameProductFeatue(Long featureId) {
		Feature feature = featureRepository.findById(featureId).get();
		String productFeatureName = feature.getOrderFeatures().getProductFeature().getName();
		Collection<Feature> features = productFeatureService.findFeaturesByProductFeatureName(productFeatureName);

		return features;
	}
}
