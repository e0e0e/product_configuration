package pl.sda.pms.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ord, Long> {

	@Query(value = "Select * from ord  where rs<?1 and documentation<?2 and qad<?3 and production<?4 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findByStatus(Integer rs, Integer doc, Integer qad, Integer prod);

	@Query(value = "Select * from ord  where rs<>2 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findRsToMake();

	@Query(value = "Select * from ord  where production=2 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findInProd();
	
	@Query(value = "Select * from ord  where documentation<>2 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findDocToMake();

	@Query(value = "Select * from ord  where qad<>2 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findQadToMake();

	@Query(value = "Select * from ord  where production<>2 ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findToProduction();

	@Query(value = "Select * from ord  where production=2  ORDER BY LAST_MODIFIED_DATE DESC", nativeQuery = true)
	List<Ord> findInProduction();

}
