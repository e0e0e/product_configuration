package pl.sda.pms.productFeature;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductFeatureRepository  extends JpaRepository<ProductFeature, Long>{
	
	List<ProductFeature> findAll();

}
