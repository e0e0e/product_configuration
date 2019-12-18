package pl.sda.pms.order;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeature;

import pl.sda.pms.OrderFeature.OrderFeatureService;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderFeatureService orderFeatureService; 



	public OrderService(OrderRepository orderRepository, OrderFeatureService orderFeatureService) {
		super();
		this.orderRepository = orderRepository;
		this.orderFeatureService = orderFeatureService;
	}



	public void create(List<OrderFeature> orderList) {
		Order order = new Order();
		
		orderList.forEach(x->orderFeatureService.create(x));

		order.setOrderFeatures(orderList);

		//orderRepository.save(order);
		// TODO Auto-generated method stub

	}

}
