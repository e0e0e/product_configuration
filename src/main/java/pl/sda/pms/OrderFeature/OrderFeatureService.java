package pl.sda.pms.OrderFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	

	public Ord create(List<OrderFeature> orderList, Boolean noStandard) {

		List<OrderFeature> result = new ArrayList<>();
		for (OrderFeature f : orderList) {
			result.add(orderFeatureRepository.save(f));

			f.getFeature().setOrderFeatures(f);
			f.getProductFeature().setOrderFeature(f);
		}

		Double priceSum = result.stream().filter(x -> x.getFeature().getPrice() != null)
				.mapToDouble(x -> x.getFeature().getPrice()).sum();

		List<String> orderFeatureStringList = orderList.stream()
				.map(x -> x.getFeature().getName() + ", " + x.getProductFeature().getName())
				.collect(Collectors.toList());

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
		ord.setOrderFeaturesStrings(orderFeatureStringList);
		ord.setOrderFeatures(orderList);
		ord.setPrice(priceSum);
		ord.setNoStandard(noStandard);


		Ord order=orderRepository.save(ord);
		return order;

	}

	public OrderFeature findByID(long id) {

		return orderFeatureRepository.findById(id).get();
	}

	public void save(OrderFeature orderFeature) {
		orderFeatureRepository.save(orderFeature);

	}

}
