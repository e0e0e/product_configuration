package pl.sda.pms.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import pl.sda.pms.color.Color;
import pl.sda.pms.color.ColorService;
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
	private final ColorService colorService;

	public OrderService(OrderRepository orderRepository, OrderFeatureService orderFeatureService,
			ProductFeatureService productFeatureService, FeatureService featureService, ColorService colorService) {
		super();
		this.orderRepository = orderRepository;
		this.orderFeatureService = orderFeatureService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.colorService = colorService;
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
		order.setOrderFeaturesStrings(null);
		order.revisionUp();
		orderRepository.save(order);

	}

	public void saveProductOrderChanges(Map<String, String> paramMap, String orderId) {
		Ord order = orderRepository.findById(Long.parseLong(orderId)).get();
		List<OrderFeature> orginalOrderFeatures = order.getOrderFeatures();

		Map<ProductFeature, Feature> newOrderFeaturesMapToFilter = paramMap.entrySet().stream()
				.filter(x -> OrderFeatureController.isNumeric(x.getKey()))
				.collect(Collectors.toMap(x -> productFeatureService.findByID(Long.parseLong(x.getKey())),
						x -> featureService.findByID(Long.parseLong(x.getValue()))));

		List<OrderFeature> newOrderFeaturesToFilter = newOrderFeaturesMapToFilter.entrySet().stream()
				.map(x -> new OrderFeature(x.getKey(), x.getValue())).collect(Collectors.toList());

		List<OrderFeature> newOrderFeatures = new ArrayList<>();
		for (OrderFeature of : newOrderFeaturesToFilter) {
			for (OrderFeature orf : orginalOrderFeatures) {
				if (of.getProductFeature().getName().equals(orf.getProductFeature().getName())
						&& !of.getFeature().getName().equals(orf.getFeature().getName())) {
					System.out.println(of.getProductFeature().getName() + " -- " + orf.getProductFeature().getName());
					System.out.println(of.getFeature().getName() + " -- " + orf.getFeature().getName());
					newOrderFeatures.add(of);
				}

			}
		}

		Map<ProductFeature, Feature> newOrderFeaturesMap = newOrderFeatures.stream()
				.collect(Collectors.toMap(x -> x.getProductFeature(), x -> x.getFeature()));

		for (OrderFeature of : newOrderFeatures) {
			orginalOrderFeatures.stream()
					.filter(x -> x.getProductFeature().getName().equals(of.getProductFeature().getName()))
					.collect(Collectors.toList()).get(0).setFeature(of.getFeature());

		}

		Map<String, Feature> newStringFeaturesMap = newOrderFeaturesMap.entrySet().stream()
				.collect(Collectors.toMap(x -> x.getKey().getName(), x -> x.getValue()));

		List<String> oldOrderFeatureList = newOrderFeaturesMap.entrySet().stream().map(x -> x.getKey().getName())
				.collect(Collectors.toList());

		Map<String, Feature> oldOrderFeatureMap = orginalOrderFeatures.stream()
				.collect(Collectors.toMap(x -> x.getProductFeature().getName(), x -> x.getFeature()));

		List<String> orderFeatureStringList = oldOrderFeatureList.stream()
				.map(x -> x + ": " + (oldOrderFeatureMap.get(x).getOrderFeatures()==null? "n/a":oldOrderFeatureMap.get(x).getOrderFeatures().getFeature().getName()) + " -> "
						+ newStringFeaturesMap.get(x).getName())
				.collect(Collectors.toList());

		order.setOrderFeaturesStrings(orderFeatureStringList);

		order.revisionUp();
		Double priceList = orginalOrderFeatures.stream().mapToDouble(x -> x.getFeature().getPrice()).sum();

		order.setPrice(priceList);
		order.setOrderFeatures(orginalOrderFeatures);
		orderRepository.save(order);

		newOrderFeaturesMap.entrySet().stream().forEach(x -> {
			OrderFeature orderFeature = x.getKey().getOrderFeature();
			orderFeature.setFeature(x.getValue());
			orderFeatureService.save(orderFeature);

		});

	}

	public List<Object> findByIdAud(Long orderId) {

		@SuppressWarnings("unchecked")
		List<Object> revisions = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(Ord.class, false, true).add(AuditEntity.id().eq(orderId)).getResultList();
		revisions.remove(0);

		return revisions;
	}

	public void saveColor(Long orderId, Map<String, String> paramMapStart) {

		Ord order = orderRepository.findById(orderId).get();
		Map<String, String> paramMap=paramMapStart.entrySet().stream().filter(x->!x.getValue().equals("")).collect(Collectors.toMap(x->x.getKey(),x->x.getValue()));

		order.getOrderFeatures().forEach(x->System.out.println(x.getProductFeature().getId()+" - "+x.getProductFeature().getName()));

		Map<OrderFeature, Color> colorMap = paramMap.entrySet().stream()
				.collect(Collectors.toMap(x -> order.findByProductFeatureID(Long.parseLong(x.getKey())),
						x -> colorService.findById(Long.parseLong(x.getValue()))));

		colorMap.entrySet().stream().forEach(x -> {
			x.getKey().setColor(x.getValue());
			orderFeatureService.save(x.getKey());

		});


	}

	public Object findByIdAndUpdatePrice(Long orderId) {

		Ord order=orderRepository.findById(orderId).get();
		Double price=order.getOrderFeatures().stream().filter(x->x.getFeature().getPrice()!=null)
		.mapToDouble(x->x.getFeature().getPrice()).sum();
		order.setPrice(price);
		Ord updatedOrder=orderRepository.save(order);

		return updatedOrder;
	}

}
