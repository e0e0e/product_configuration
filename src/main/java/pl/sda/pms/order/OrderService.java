package pl.sda.pms.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeature;

import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.feature.Feature;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderFeatureService orderFeatureService;

	public OrderService(OrderRepository orderRepository, OrderFeatureService orderFeatureService) {
		super();
		this.orderRepository = orderRepository;
		this.orderFeatureService = orderFeatureService;
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
		Ord order=orderRepository.findById(orderId).get();
		
		order.removeAllOrderFeatures();
		orderRepository.deleteById(orderId);
		
	}

	public Ord findById(Long orderId) {
		return orderRepository.findById(orderId).get();
		
	}

	public void addMore(Long orderId, String orderName, String price, Integer unitsToProduce, String client) {
		
	Ord order=orderRepository.findById(orderId).get();
	order.setOrderName(orderName);
	order.setPrice(Double.parseDouble(price));
	order.setUnitsToProduce(unitsToProduce);
	order.setClient(client);
	orderRepository.save(order);
	
		
	}



}
