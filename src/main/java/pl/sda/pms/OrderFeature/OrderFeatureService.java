package pl.sda.pms.OrderFeature;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.sda.pms.order.Ord;
import pl.sda.pms.order.OrderRepository;
import pl.sda.pms.order.OrderService;

@Service
public class OrderFeatureService {

	private final OrderFeatureRepository orderFeatureRepository;
	private final OrderRepository orderRepository;

	public OrderFeatureService(OrderFeatureRepository orderFeatureRepository, OrderRepository orderRepository) {

		this.orderFeatureRepository = orderFeatureRepository;
		this.orderRepository = orderRepository;
	}

	public OrderFeature create(OrderFeature orderFeature) {
		OrderFeature orderFeature2 = orderFeatureRepository.save(orderFeature);
		return orderFeature2;
	}

	public List<OrderFeature> create(List<OrderFeature> orderList) {

		List<OrderFeature> result = new ArrayList<>();
		for (OrderFeature f : orderList) {
			result.add(orderFeatureRepository.save(f));

			f.getFeature().setOrderFeatures(f);
			f.getProductFeature().setOrderFeature(f);
		}

		Ord ord = new Ord();

		result.forEach(orderFeature -> {
			if (orderFeature.getOrd() == null) {
				List<Ord> ordListenerRule = new ArrayList<>();
				ordListenerRule.add(ord);
				orderFeature.setOrd(ordListenerRule);

			} else {
				orderFeature.getOrd().add(ord);
			}

		});

		ord.setOrderFeatures(orderList);
		orderRepository.save(ord);
		return result;

	}

	public OrderFeature findByID(long id) {

		return orderFeatureRepository.findById(id).get();
	}

	public void save(OrderFeature orderFeature) {
		orderFeatureRepository.save(orderFeature);

	}

}
