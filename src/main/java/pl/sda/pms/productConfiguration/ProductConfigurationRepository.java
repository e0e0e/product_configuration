package pl.sda.pms.productConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, Long>{
	
	List<ProductConfiguration> findAll();

	@Query(value = "Select * from product_configuration where name like ?1", nativeQuery = true)
	ProductConfiguration findByName(String name);



}
