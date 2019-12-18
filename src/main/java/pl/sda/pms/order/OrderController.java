package pl.sda.pms.order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productConfiguration.ProductConfigurationService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class OrderController {
	
	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;
	private final OrderService orderService;
	private final OrderFeatureService orderFeatureService;

	public OrderController(ProductConfigurationService productConfigurationService,
			FeatureService featureService, ProductFeatureService productFeatureService,
			OrderService orderService,
			OrderFeatureService orderFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderService=orderService;
		this.orderFeatureService=orderFeatureService;
	}
	
	@PostMapping("/orderCreate")
	public String orderCreation(@RequestParam Map<String, String> paramMap, @RequestParam Long productConfigurationId,
			Model model) {

		List<OrderFeature> orderList = 
				paramMap.entrySet()
				.stream()
				.filter(x -> isNumeric(x.getKey()))
				//.forEach(x->System.out.println(x.getKey()+" - "+x.getValue()));
				.map(e->new OrderFeature(productFeatureService.findById(Long.parseLong(e.getKey())),
						featureService.findByID(Long.parseLong(e.getValue()))))
				.collect(Collectors.toList());
////				.collect(Collectors.toMap(e -> productFeatureService.findById(Long.parseLong(e.getKey())),
////						e -> featureService.findByID(Long.parseLong(e.getValue()))));
//	
//		
//		
//		orderList.stream()
//				.forEach(x -> System.out.println(x.getProductFeature().getName() + " - " + x.getFeature().getName()));

		List<OrderFeature> orderFeatures=orderFeatureService.create(orderList);
		Ord ord=new Ord();
		ord.setOrderFeatures(orderFeatures);
		orderService.create(ord);
		
		
		
		model.addAttribute("title", "Show Products");
		model.addAttribute("path", "product/show");
		return "main";

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
