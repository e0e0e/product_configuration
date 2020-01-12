package pl.sda.pms.OrderFeature;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productConfiguration.ProductConfigurationService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class OrderFeatureController {
	
	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;
	private final OrderFeatureService orderFeatureService;

	public OrderFeatureController(ProductConfigurationService productConfigurationService,
			FeatureService featureService, ProductFeatureService productFeatureService,
			OrderFeatureService orderFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderFeatureService=orderFeatureService;
	}
	@PostMapping("/orderCreate")
	public String orderCreation(@RequestParam Map<String, String> paramMap, @RequestParam Long productConfigurationId,
			Model model) {

		List<OrderFeature> orderList = paramMap.entrySet().stream().filter(x -> isNumeric(x.getKey()))
				.map(e -> new OrderFeature(productFeatureService.findById(Long.parseLong(e.getKey())),
						featureService.findByID(Long.parseLong(e.getValue()))))
				.collect(Collectors.toList());
		

		List<OrderFeature> orderFeatures = orderFeatureService.create(orderList);

		return "redirect:/orders/list";

	}
	
	@PostMapping("/filter/orderCreate")
	public String orderCreationByFilter(@RequestParam Map<String, String> paramMap,
			Model model) {

		List<OrderFeature> orderList = paramMap.entrySet().stream().filter(x -> isNumeric(x.getKey()))
				.map(e -> new OrderFeature(productFeatureService.findById(Long.parseLong(e.getKey())),
						featureService.findByID(Long.parseLong(e.getValue()))))
				.collect(Collectors.toList());
		

		List<OrderFeature> orderFeatures = orderFeatureService.create(orderList);

		return "redirect:/orders/list";

	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
