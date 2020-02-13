package pl.sda.pms.feature;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.sda.pms.users.User;



@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
	List<Feature> findAll();

	
	 @Query(value = "Select * from feature left join PRODUCT_FEATURE_FEATURE ON feature.id=PRODUCT_FEATURE_FEATURE.feature_id where PRODUCT_FEATURE_FEATURE.feature_id is null", nativeQuery = true)
    List<Feature> findNotUsed();

	@Query(value = "Select 1 from feature where name like ?1 and index like ?2 limit 1", nativeQuery = true)
	Integer findByNameAndIndex(String name, String index);
	
	
}
