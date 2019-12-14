package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;
import pl.sda.pms.projects.Project;

@Service
public class ProductConfigurationService {
	
	@PersistenceContext
    EntityManager entityManager;
	
	
	private final ProductConfigurationRepository productConfigurationRepository;
	private final ProductFeatureService productFeatureService;

	public ProductConfigurationService(ProductConfigurationRepository productConfigurationRepository, ProductFeatureService productFeatureService) {
		this.productConfigurationRepository = productConfigurationRepository;
		this.productFeatureService=productFeatureService;
	}

	public List<ProductConfiguration> findAll() {
		return productConfigurationRepository.findAll();

	}

	public void create(String name, List<Long> feature) {
		ProductConfiguration productConfiguration=new ProductConfiguration(name);
		List<ProductFeature> productFeature= new ArrayList<ProductFeature>();
		for(Long id:feature){
			ProductFeature featureToConfigurator= productFeatureService.findById(id);
			productFeature.add(featureToConfigurator);
		}
		productConfiguration.setConfigurationList(productFeature);
		productConfigurationRepository.save(productConfiguration);

	}
	
	public  List<Object> findAllById(Long id) {
        @SuppressWarnings("unchecked")
        List<Object> revisions = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(ProductConfiguration.class, false, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();

        return revisions;
    }

	public boolean saveChanges(Long id, String name, List<Long> feature) {
		ProductConfiguration productConfiguration=productConfigurationRepository.findById(id).get();
		
		List<ProductFeature> productFeature= new ArrayList<ProductFeature>();
		for(Long fId:feature){
			ProductFeature featureToConfigurator= productFeatureService.findById(fId);
			productFeature.add(featureToConfigurator);
		}
		productConfiguration.setConfigurationList(productFeature);

		productConfiguration.setName(name);
		ProductConfiguration create=productConfigurationRepository.save(productConfiguration);
		
		return create.getId()!=null;
		
	}

	public ProductConfiguration findById(Long id) {
		
		return productConfigurationRepository.findById(id).get();
		
	}
	
	

}
