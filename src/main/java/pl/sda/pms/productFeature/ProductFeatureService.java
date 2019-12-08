package pl.sda.pms.productFeature;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ProductFeatureService {
	
	private final ProductFeatureRepository productFeatureRepository;

	public ProductFeatureService(ProductFeatureRepository productFeatureRepository) {

		this.productFeatureRepository = productFeatureRepository;
	}

	public List<ProductFeature> findAll(){
		
		return productFeatureRepository.findAll();
	}



	
	
	
	

}
