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
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureRepository;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.color.Color;
import pl.sda.pms.color.ColorService;
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
	private final ColorService colorService;

	public OrderController(ProductConfigurationService productConfigurationService, FeatureService featureService,
			ProductFeatureService productFeatureService, OrderService orderService, ColorService colorService,
			OrderFeatureService orderFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderService = orderService;
		this.orderFeatureService = orderFeatureService;
		this.colorService = colorService;
	}

	@GetMapping("/orders/list")
	public String listOrders(Model model) {

		model.addAttribute("orders", orderService.findAll());
		model.addAttribute("title", "List Orders");
		model.addAttribute("path", "order/list");
		return "main";
	}

	@GetMapping("/order/show")
	public String listOrders(@RequestParam Long orderId, Model model) {

		model.addAttribute("order", orderService.findById(orderId));
		model.addAttribute("aud", orderService.findByIdAud(orderId));
		model.addAttribute("title", "List Orders");
		model.addAttribute("path", "order/show");
		return "main";
	}

	@GetMapping("/order/edit")
	public String productSelectiom(@RequestParam Long orderId, Model model) {
		Ord order = orderService.findById(orderId);
		Map<String, String> orderMap = order.getOrderFeatures().stream()
				.collect(Collectors.toMap(fp -> fp.getProductFeature().getName(), f -> f.getFeature().getName()));

		model.addAttribute("order", orderMap);
		model.addAttribute("orderId", orderId);

		model.addAttribute("configuration", productConfigurationService.findById(8L));

		model.addAttribute("title", "Edit Order");
		model.addAttribute("path", "product/filterOrder");
		return "main";

	}

	@GetMapping("/order/color/edit")
	public String orderColorEdit(@RequestParam Long orderId, Model model) {
		Ord order = orderService.findById(orderId);

		List<OrderFeature> orderFeatures = order.getOrderFeatures().stream()
				.filter(x -> (x.getProductFeature().getColor() != null && x.getProductFeature().getColor() != false))
				.collect(Collectors.toList());

		Map<String, Feature> orderMap = order.getOrderFeatures().stream()
				.filter(x -> (x.getProductFeature().getColor() != null && x.getProductFeature().getColor() != false))
				.collect(Collectors.toMap(fp -> fp.getProductFeature().getName(), f -> f.getFeature()));
		List<Color> colors = colorService.findAll();

		model.addAttribute("orderFeatures", orderFeatures);
		model.addAttribute("orderMap", orderMap);
		model.addAttribute("order", order);
		model.addAttribute("orderId", orderId);
		model.addAttribute("colors", colors);

		model.addAttribute("configuration", productConfigurationService.findById(8L));// change to findByName 'pattern',
																						// it will crash if somthing
																						// chang

		model.addAttribute("title", "Edit Order");
		model.addAttribute("path", "product/colors");
		return "main";

	}

	@GetMapping("/order/print")
	public String orderPrint(@RequestParam Long orderId, Model model) {

		model.addAttribute("order", orderService.findById(orderId));

		model.addAttribute("title", "Print Order");
		model.addAttribute("path", "order/edit");
		return "order/print";

	}

	@GetMapping("/order/delete")
	public String deleteOrder(@RequestParam Long orderId, Model model) {

		orderService.deleteById(orderId);

		return "redirect:/orders/list";
	}

	@GetMapping("/order/addMore")
	public String addDetailsToOrder(@RequestParam Long orderId, Model model) {

		model.addAttribute("order", orderService.findById(orderId));

		model.addAttribute("title", "Add details to Order");
		model.addAttribute("path", "order/addMore");
		return "main";
	}

	@PostMapping("/orderToChange")
	public String orderToChange(@RequestParam Long orderId, @RequestParam String orderName, @RequestParam String price,
			@RequestParam Integer unitsToProduce, @RequestParam String client, Model model) {

		orderService.addMore(orderId, orderName, price, unitsToProduce, client);

		return "redirect:/orders/list";
	}

	@PostMapping("/order/saveProductChanges")
	public String saveProductOrderChanges(@RequestParam Map<String, String> paramMap, @RequestParam String orderId,
			Model model) {

		orderService.saveProductOrderChanges(paramMap, orderId);

		return "redirect:/orders/list";
	}

	@PostMapping("/filter/orderSaveEdited")
	public String orderSaveEdited(@RequestParam Map<String, String> paramMap, @RequestParam String orderId,
			Model model) {

		orderService.saveProductOrderChanges(paramMap, orderId);

		return "redirect:/orders/list";
	}

	@PostMapping("/order/color/saveChanes")
	public String saveColor(@RequestParam Map<String, String> paramMap, Model model) {

		Long orderId = Long.parseLong(paramMap.get("orderId"));
		paramMap.remove("orderId");

		orderService.saveColor(orderId, paramMap);

		return "redirect:/order/show?orderId=" + orderId;

	}

}
