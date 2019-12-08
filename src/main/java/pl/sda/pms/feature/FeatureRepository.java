package pl.sda.pms.feature;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
	List<Feature> findAll();
}
