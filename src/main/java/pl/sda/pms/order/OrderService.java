package pl.sda.pms.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureController;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;
import pl.sda.pms.projects.Project;

@Service
public class OrderService {

	@PersistenceContext
	EntityManager entityManager;

	private final OrderRepository orderRepository;
	private final OrderFeatureService orderFeatureService;
	private final ProductFeatureService productFeatureService;
	private final FeatureService featureService;

	public OrderService(OrderRepository orderRepository, OrderFeatureService orderFeatureService,
			ProductFeatureService productFeatureService, FeatureService featureService) {
		super();
		this.orderRepository = orderRepository;
		this.orderFeatureService = orderFeatureService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
	}

	public void create(Ord order) {
		orderRepository.save(order);

	}

	public List<Ord> findAll() {
		return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));

	}

	public void deleteById(Long orderId) {
		Ord order = orderRepository.findById(orderId).get();

		order.removeAllOrderFeatures();
		orderRepository.deleteById(orderId);

	}

	public Ord findById(Long orderId) {
		return orderRepository.findById(orderId).get();

	}

	public void addMore(Long orderId, String orderName, String price, Integer unitsToProduce, String client) {

		Ord order = orderRepository.findById(orderId).get();
		order.setOrderName(orderName);

		if (OrderFeatureController.isNumeric(price)) {
			order.setPrice(Double.parseDouble(price));

		}

		order.setUnitsToProduce(unitsToProduce);
		order.setClient(client);
		orderRepository.save(order);

	}

	public void saveProductOrderChanges(Map<String, String> paramMap, String orderId) {
		Ord order = orderRepository.findById(Long.parseLong(orderId)).get();

		Map<ProductFeature, Feature> anotherMap = paramMap.entrySet().stream()
				.filter(x -> OrderFeatureController.isNumeric(x.getKey()))
				.collect(Collectors.toMap(x -> productFeatureService.findByID(Long.parseLong(x.getKey())),
						x -> featureService.findByID(Long.parseLong(x.getValue()))));

		List<String> orderFeatureStringList = anotherMap.entrySet().stream()
				.map(x -> x.getKey().getName() + ", " + x.getValue().getName()+"<br>")
				.collect(Collectors.toList());

		order.setOrderFeaturesStrings(orderFeatureStringList);
		
		order.revisionUp();
		orderRepository.save(order);

		anotherMap.entrySet().stream().forEach(x -> {
			OrderFeature orderFeature = x.getKey().getOrderFeature();
			orderFeature.setFeature(x.getValue());
			orderFeatureService.save(orderFeature);

		});

	}

	public List<Object> findByIdAud(Long orderId) {

		@SuppressWarnings("unchecked")
		List<Object> revisions = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(Ord.class, false, true).add(AuditEntity.id().eq(orderId)).getResultList();

		Ord order = orderRepository.findById(orderId).get();
		List<OrderFeature> orderFeaturesList = order.getOrderFeatures();
		orderFeaturesList.get(0).getFeature().getName();

		@SuppressWarnings("unchecked")
		List<Object> revisions1 = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(Ord.class, false, true).add(AuditEntity.id().eq(orderId)).getResultList();

//		String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(1578178800000L);
////		Ord dt = (Ord) ((Object[]) revisions1.get(0))[0];
////		// String nameString=dt.getLastModifiedDate().toGMTString();
////		// (Object[]) revisions1.get( 0 ))[0];
//		for (Object o : revisions1) {
//			Object[] objects = (Object[]) o;
//			Object ob1 = objects[0];
//			Ord ord = (Ord) ob1;
//
//			if (ord.getCreatedDate() != null) {
//				System.out.println((Date) ord.getCreatedDate());
//			}
//		}
		// .traverseRelation( "ord", JoinType.INNER )
		// .add(AuditEntity.property(“title”).eq(“Hibernate Tips – 64 Tips for your day
		// to day work”))

		// System.out.println(revisions1.get(0).);

		return revisions1;
	}

}
