package pl.sda.pms.OrderFeature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFeatureRepository extends JpaRepository<OrderFeature, Long> {

	
	
}
