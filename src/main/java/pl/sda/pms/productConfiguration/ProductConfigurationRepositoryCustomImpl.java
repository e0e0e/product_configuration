package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pl.sda.pms.productFeature.ProductFeature;

public class ProductConfigurationRepositoryCustomImpl implements ProductConfigurationRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProductConfiguration> findProductByChoosenFeatyres(List<String> queryCd) {


		//String baseQueryString="SELECT pc FROM ProductConfiguration pc join pc.configurationList pf join pf.feature f ";
		String baseQueryString="SELECT DISTINCT pc.name FROM product_configuration pc inner join PRODUCT_FEATURE pf on pc.id=pf.PRODUCT_CONFIGURATION_ID inner join PRODUCT_FEATURE_FEATURE pff on pff.product_feature_id=pf.id inner join feature f on f.id=pff.feature_id ";
		//		+ "where pf.name LIKE 'Axle' and f.name LIKE 'SAF drum' ";
		
		//String queryString="SELECT pc FROM "+baseQueryString+" WHERE "+q;
		for (String q : queryCd) {
			System.out.println("query : "+q);
			baseQueryString=String.format("SELECT a.id FROM (%s) a WHERE %s",baseQueryString,q);
		}
		//String q2="Select a from (SELECT pc FROM ProductConfiguration pc join pc.configurationList pf join pf.feature f WHERE (pf.name LIKE 'Axle' and f.name LIKE 'SAF drum')) a";

		System.out.println(String.format("query %s",baseQueryString));
//		TypedQuery<ProductConfiguration> query = entityManager.createQuery(q2,
//				ProductConfiguration.class);
		
		String sqlString="SELECT DISTINCT pc.name FROM product_configuration pc inner join PRODUCT_FEATURE pf on pc.id=pf.PRODUCT_CONFIGURATION_ID inner join PRODUCT_FEATURE_FEATURE pff on pff.product_feature_id=pf.id inner join feature f on f.id=pff.feature_id "
				+ "where pf.name LIKE 'Axle' and f.name LIKE 'SAF drum' ";
		
		Query sqlQuery =entityManager.createNativeQuery(baseQueryString);
		
		List<String> results = sqlQuery.getResultList();
		results.forEach(x->System.out.println("winer :"+x));
		return new ArrayList<ProductConfiguration>();
	}

}
