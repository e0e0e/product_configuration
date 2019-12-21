package pl.sda.pms.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.catalina.startup.LifecycleListenerRule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureRepository;
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

	public OrderController(ProductConfigurationService productConfigurationService, FeatureService featureService,
			ProductFeatureService productFeatureService, OrderService orderService,
			OrderFeatureService orderFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderService = orderService;
		this.orderFeatureService = orderFeatureService;
	}

	@GetMapping("/orders/list")
	public String listOrders(Model model) {
		
		model.addAttribute("orders",orderService.findAll());
		model.addAttribute("title", "List Orders");
		model.addAttribute("path", "order/list");
		return "main";
	}
	
	@GetMapping("/order/delete")
	public String deleteOrder(@RequestParam Long orderId,
			Model model) {
		
		orderService.deleteById(orderId);
	
		return "redirect:/orders/list";
	}
	
	@GetMapping("/order/addMore")
	public String addDetailsToOrder(@RequestParam Long orderId,
			Model model) {
		
		model.addAttribute("order",orderService.findById(orderId));
	
		model.addAttribute("title", "Add details to Order");
		model.addAttribute("path", "order/addMore");
		return "main";
	}
	
	@PostMapping("/orderToChange")
	public String orderToChange(@RequestParam Long orderId,
			@RequestParam String orderName,
			@RequestParam String price,
			@RequestParam Integer unitsToProduce,
			@RequestParam String client,
			Model model) {
		
		
		orderService.addMore(orderId,orderName,price,unitsToProduce,client);
	
		return "redirect:/orders/list";
	}

}
