package pl.sda.pms.productConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public List<ProductConfiguration> findProductByChoosenFeatyres(List<String> pfNames, List<String> fNames) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductConfiguration> query = cb.createQuery(ProductConfiguration.class);
        
        Root<ProductConfiguration> productConfiguration = query.from(ProductConfiguration.class);
        
        Join<Object, Object> pf = productConfiguration.join("configurationList");
        Join<Object, Object> f = pf.join("feature");
        List<Predicate> predicates = new ArrayList<>();
        for (int i=0;i<fNames.size();i++) {
//        	Predicate predicateForProductFeatureName
//        	  = cb.like(pf.get("name"), pfNames.get(i));
//        	Predicate predicateForFeatureName
//      	  = cb.like(f.get("name"), fNames.get(i));
//        	
        	predicates.add(cb.and(cb.like(pf.get("name"), pfNames.get(i)),cb.like(f.get("name"), fNames.get(i))));
           // predicates.add(cb.like(f.get("name"), fNames.get(i)));
        }
        //cb.and(predicates.toArray(new Predicate[predicates.size()]));
       // predicates.size()
        query.where(predicates.toArray(new Predicate[predicates.size()]));
       // query.select(productConfiguration).where(cb.equal(f.get("name"),"1250"));
        return entityManager.createQuery(query)
            .getResultList();
        
   
	}

}
