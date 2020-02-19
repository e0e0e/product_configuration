package pl.sda.pms.productConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, Long>{
	
	List<ProductConfiguration> findAll();

	@Query(value = "Select * from product_configuration where name like ?1", nativeQuery = true)
	ProductConfiguration findByName(String name);


	@Query(value = "Select * from product_configuration  pc inner join PRODUCT_FEATURE pf ON pc.id=pf.product_configuration_id inner join PRODUCT_FEATURE_FEATURE pff on pff.product_feature_id=pf.id inner join feature f on f.id=pff.feature_id where f.id like ?1", nativeQuery = true)
	List<ProductConfiguration> findAllContainingFeatureById(Long featureId);
	
	@Modifying
	@Transactional
	@Query(value = "Drop All Objects", nativeQuery = true)
	void dropAllObjects();



}
