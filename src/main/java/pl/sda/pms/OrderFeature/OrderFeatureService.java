package pl.sda.pms.OrderFeature;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderFeatureService {

	

	private final OrderFeatureRepository orderFeatureRepository;
	

	public OrderFeatureService(OrderFeatureRepository orderFeatureRepository) {

		this.orderFeatureRepository = orderFeatureRepository;
	}


	public void create(OrderFeature orderFeature) {
		orderFeatureRepository.save(orderFeature);

	}

}
