package pl.sda.pms.productConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, Long>{

	
}
