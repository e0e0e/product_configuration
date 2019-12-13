package pl.sda.pms.productFeature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.Row;


@Service
public class ProductFeatureService {
	
	private final ProductFeatureRepository productFeatureRepository;
	private FeatureService featureService;

	public ProductFeatureService(ProductFeatureRepository productFeatureRepository,FeatureService featureService) {

		this.productFeatureRepository = productFeatureRepository;
		this.featureService=featureService;
	}

	public List<ProductFeature> findAll(){
		
		return productFeatureRepository.findAll();
	}

	public List<List<Row>> getFields() {
		List<ProductFeature> productFeatureList= productFeatureRepository.findAll();
		
		List<List<Row>> tableValues=new ArrayList<List<Row>>();
		
		for (ProductFeature feature : productFeatureList) {
			
			List<Row> rowList=new ArrayList<>();
			rowList.add(new Row("Product Feature Name", feature.getName(),"name"));
			rowList.add(new Row("Description", feature.getDescription(),"description"));
			rowList.add(new Row("Image Path", feature.getImagePath(),"imagePaths"));
			
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

	public boolean edit(Long id, String name, String description, String imagePath, List<Long> featureList) {
		ProductFeature productFeature=productFeatureRepository.findById(id).get();
		//Set<Feature> featureSet=productFeature.getFeature();
		Set<Feature> featuresToAddFeatures=new HashSet<>();
		
		for(Long f:featureList) {
			
			featuresToAddFeatures.add(featureService.findByID(f));
		}
		productFeature.setFeature(featuresToAddFeatures);
		productFeature.setName(name);
		productFeature.setDescription(description);
		productFeature.setImagePath(imagePath);
		ProductFeature createdFeature=productFeatureRepository.save(productFeature);
		return createdFeature.getId()!=null;
	}

	
	
	
	

}
