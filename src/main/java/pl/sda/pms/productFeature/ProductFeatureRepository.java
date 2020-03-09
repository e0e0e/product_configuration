package pl.sda.pms.productFeature;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long> {

	List<ProductFeature> findAll();

	@Query(value = "Select * from product_feature where name=?1 limit 1", nativeQuery = true)
	List<ProductFeature> findByName(String name);




}
