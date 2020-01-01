package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public List<ProductConfiguration> findProductByChoosenFeatyres(String queryCd) {

//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ProductConfiguration> query = cb.createQuery(ProductConfiguration.class);
//
//		Root<ProductConfiguration> productConfiguration = query.from(ProductConfiguration.class);
//
//		Join<Object, Object> pf = productConfiguration.join("configurationList");
//		Join<Object, Object> f = pf.join("feature");
//		List<Predicate> predicates = new ArrayList<>();
//		List<Predicate> predicatesAND = new ArrayList<>();
//		for (int i = 0; i < fNames.size(); i++) {
//			predicatesAND.add(cb.and(cb.like(pf.get("name"), pfNames.get(i)), cb.like(f.get("name"), fNames.get(i))));
//			//predicates.add(cb.and(cb.like(pf.get("name"), pfNames.get(i)), cb.like(f.get("name"), fNames.get(i))));
//
//		}
////		for(int i = 0; i < predicatesAND.size()-1; i++) {
//			predicates.add(cb.and(predicatesAND.get(0),predicatesAND.get(1)));
////			
////		}
//
//		query.where(predicates.toArray(new Predicate[predicates.size()]));
//
//		return entityManager.createQuery(query).getResultList();
//SELECT DISTINCT pc.id FROM product_configuration pc inner join PRODUCT_FEATURE pf on pc.id=pf.PRODUCT_CONFIGURATION_ID inner join PRODUCT_FEATURE_FEATURE pff on pff.product_feature_id=pf.id inner join feature f on f.id=pff.feature_id
		TypedQuery<ProductConfiguration> query = entityManager.createQuery("SELECT pc FROM ProductConfiguration pc join pc.configurationList pf join pf.feature f WHERE "+queryCd,
				ProductConfiguration.class);
		List<ProductConfiguration> results = query.getResultList();
		return results;
	}

}
