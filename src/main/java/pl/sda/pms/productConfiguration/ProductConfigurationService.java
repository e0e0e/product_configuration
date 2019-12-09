package pl.sda.pms.productConfiguration;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductConfigurationService {
	
	private final ProductConfigurationRepository productConfigurationRepository;

	public ProductConfigurationService(ProductConfigurationRepository productConfigurationRepository) {
		this.productConfigurationRepository = productConfigurationRepository;
	}

	public List<ProductConfiguration> findAll() {
		productConfigurationRepository.findAll();
		return null;
	}
	
	

}
