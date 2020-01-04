package pl.sda.pms.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
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
//		Order order = new Order(orderList);
//		
//		orderRepository.save(order);
//		for (OrderFeature o : orderList) {
//			
//			
//			
//			if(order.getOrderFeatures()==null) {
//				List<OrderFeature> orderFeatureList=new ArrayList<>();
//				orderFeatureList.add(o);
//				order.setOrderFeatures(orderFeatureList);
//				
//			}else {
//			order.getOrderFeatures().add(o);
//			}
//			if(o.getOrder()==null) {
//				List<Order> orders=new ArrayList<>();
//				o.setOrder(orders);
//			}else {
//			o.getOrder().add(order);
//			}
//			OrderFeature orderFeature=orderFeatureService.create(o);
//		}
		// order.setOrderFeatures(orderList);

//		orderList.forEach(a->a.getOrder().add(order)
//				.forEach(x->orderFeatureService.create(x));

		// order.setOrderFeatures(orderList);

		// TODO Auto-generated method stub

	}

	public List<Ord> findAll() {
		return orderRepository.findAll();

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

		anotherMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "--" + x.getValue()));
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
				.forRevisionsOfEntity(OrderFeature.class, false, true)
				.add(AuditEntity.id().isNotNull())
			.getResultList();
		
	//	.traverseRelation( "ord", JoinType.INNER )
		//.add(AuditEntity.property(“title”).eq(“Hibernate Tips – 64 Tips for your day to day work”))
		
		//System.out.println(revisions1.get(0).);

		return revisions1;
	}

}
