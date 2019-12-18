package pl.sda.pms.OrderFeature;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderFeatureService {

	private final OrderFeatureRepository orderFeatureRepository;

	public OrderFeatureService(OrderFeatureRepository orderFeatureRepository) {

		this.orderFeatureRepository = orderFeatureRepository;
	}

	public OrderFeature create(OrderFeature orderFeature) {
		OrderFeature orderFeature2 = orderFeatureRepository.save(orderFeature);
		return orderFeature2;
	}

	public List<OrderFeature> create(List<OrderFeature> orderList) {
		
		List<OrderFeature> result= new ArrayList<>();
		for(OrderFeature f:orderList) {
			result.add(orderFeatureRepository.save(f));
			
		}
	
		return result;
	}

}
