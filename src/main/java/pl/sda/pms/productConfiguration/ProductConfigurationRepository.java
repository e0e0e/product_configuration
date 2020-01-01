package pl.sda.pms.productConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, Long>, ProductConfigurationRepositoryCustom{
	
	List<ProductConfiguration> findAll();
//Select * from feature left join PRODUCT_FEATURE_FEATURE ON feature.id=PRODUCT_FEATURE_FEATURE.feature_id
	@Query(value = "SELECT DISTINCT pc.id FROM product_configuration pc inner join PRODUCT_FEATURE pf on pc.id=pf.PRODUCT_CONFIGURATION_ID inner"
			+ " join PRODUCT_FEATURE_FEATURE pff on pff.product_feature_id=pf.id"
			+ " inner join feature f on f.id=pff.feature_id WHERE pf.name= :pfName AND f.name=:fName ", nativeQuery = true)
	List<Long> findByProductFeatureNames(@Param("pfName") Collection<String> pfName,@Param("fName") Collection<String> fName);
	

	
   // int findIfProjectNameExistsExceptThis(long projectId, String projectName);
}
