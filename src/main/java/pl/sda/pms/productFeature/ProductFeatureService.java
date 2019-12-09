package pl.sda.pms.productFeature;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.Row;


@Service
public class ProductFeatureService {
	
	private final ProductFeatureRepository productFeatureRepository;

	public ProductFeatureService(ProductFeatureRepository productFeatureRepository) {

		this.productFeatureRepository = productFeatureRepository;
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

	
	
	
	

}
