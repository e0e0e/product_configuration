package pl.sda.pms.order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.color.Color;
import pl.sda.pms.color.ColorService;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.ShortFeature;
import pl.sda.pms.productConfiguration.PorductConfiguration;
import pl.sda.pms.productConfiguration.ProductConfiguration;
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
	public String listOrders(@RequestParam(required = false) String filter, Model model) {

		if (filter != null) {
			switch (filter) {
			case "rs":
				model.addAttribute("orders", orderService.findRsToMake());
				break; 
			case "docToMake":
				model.addAttribute("orders", orderService.findDocToMake());
				break; 
			case "qadToMake":
				model.addAttribute("orders", orderService.findQadToMake());
				break; 
			case "toProduction":
				model.addAttribute("orders", orderService.findToProduction());
				break; 
			case "inProduction":
				model.addAttribute("orders", orderService.findInProduction());
				break; 
			default:
				model.addAttribute("orders", orderService.findAll());

			}
		} else {
			model.addAttribute("orders", orderService.findAll());
		}
		model.addAttribute("title", "List Orders");
		model.addAttribute("path", "order/list");
		return "main";
	}

	@GetMapping("/order/show")
	public String showOrder(@RequestParam Long orderId, @RequestParam(required = false) String errorMessage,
			@RequestParam(required = false) Boolean edit, Model model) {

		model.addAttribute("errorMessage", errorMessage);

		model.addAttribute("order", orderService.findByIdAndUpdatePrice(orderId));
		try {
			model.addAttribute("aud", orderService.findByIdAud(orderId));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		model.addAttribute("edit", edit);
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

	@GetMapping("/order/ifNewProductFeatures")
	public String updateIfNewProductFeatures(@RequestParam Long orderId, Model model) {

		Ord order = orderService.findById(orderId);
		ProductConfiguration productConfiguration = productConfigurationService.findByName("pattern");
		Set<String> orderFeaturesListNames = order.getOrderFeatures().stream()
				.map(x -> x.getProductFeature().getName().toLowerCase()).collect(Collectors.toSet());
		Set<String> productFeaturesListNames = productConfiguration.getConfigurationList().stream()
				.map(x -> x.getName().toLowerCase()).collect(Collectors.toSet());

		productFeaturesListNames.removeAll(orderFeaturesListNames);

		List<ProductFeature> productFeatures = productFeaturesListNames.stream()
				.map(x -> productConfiguration.findProductFeatureByName(x)).collect(Collectors.toList());

		model.addAttribute("orderId", orderId);
		model.addAttribute("productFeatures", productFeatures);
		model.addAttribute("title", "Edit Order PFs");
		model.addAttribute("path", "order/update");
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
		List<Color> colors = colorService.findAll().stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
				.collect(Collectors.toList());

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

	@GetMapping("/save/product/noStandard")
	public String saveNoStandardAsProduct(@RequestParam Long orderId) {
		String errorMessage = null;

		Ord order = orderService.findById(orderId);

		if (!order.hasStandardFeatures()) {
			errorMessage = "No feature can be no standard, change to existing or create new";
		} else {
			List<ProductConfiguration> matchingProducts = orderService.checkIfRealyNoStandard(order);
			try {
				matchingProducts.remove(productConfigurationService.findByName("pattern"));
			} catch (Exception e) {
				errorMessage = "No pattern exists with this feature";
				System.out.println(errorMessage);
			}

			if (matchingProducts.size() > 0) {
				errorMessage = "Configuration exists in '"
						+ matchingProducts.stream().map(x -> x.getName()).collect(Collectors.joining("', '"))
						+ "'<br>No need to save new product Configuration";

				order.setNoStandard(false);

				// order.setOrderFeaturesStringsMapByOrderFeatures(order.getOrderFeatures());
				orderService.save(order);
			} else {
				errorMessage = "Ready to save <br><a class='btn btn-outline-light text-light bg-danger' href='/save/product/noStandardConfirmed?orderId="
						+ orderId + "'>Confirm</a>";
			}

		}

		return "redirect:/order/show?orderId=" + orderId + "&errorMessage=" + errorMessage;
	}

	@GetMapping("/save/product/noStandardConfirmed")
	public String saveAsProduct(@RequestParam Long orderId) {

		orderService.saveAsProduct(orderId);
		productConfigurationService.updatePattern();

		return "redirect:/order/show?orderId=" + orderId;
	}

	@GetMapping("/update/pattern")
	public String updateProductConfigurationPattern() {

		productConfigurationService.updatePattern();

		return "redirect:/product/list?productId=8";
	}

	@GetMapping("/export/orders")
	public String export(Model model) throws IOException {
		FileWriter file = new FileWriter("orderExport.txt");
		List<Ord> productConfigurations = orderService.findAll();

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		String json = mapper.writeValueAsString(productConfigurations);
		file.write(json);
		file.close();
		System.out.println("File writen.");

		return null;
	}

	@GetMapping("/matrix")
	public String getMatrix(@RequestParam Long orderId, Model model) {

		orderService.readMatrix(orderService.findById(orderId));

		return "redirect:/order/show?orderId=" + orderId;
	}

	@PostMapping("/order/newPf")
	public String updateOrderWithPf(@RequestParam Map<String, String> paramMap, @RequestParam String orderId,
			Model model) {

		orderService.saveProductOrderChangesAllParamMap(paramMap, orderId);

		return "redirect:/order/show?orderId=" + orderId;
	}

	@PostMapping(value = "/order/status/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String[] orderStatusChange(@RequestBody String order, Model model) {

		Status status = orderService.changeStatus(order);
		String[] res = new String[2];
		res[0] = status.toString();
		res[1] = status.getKolor();

		return res;
	}

	@PostMapping("/order/matching/status")
	public String productSearchStatus(@RequestParam(required = false) Boolean chechR,
			@RequestParam(required = false) Boolean chechQ, @RequestParam(required = false) Boolean chechD,
			@RequestParam(required = false) Boolean chechP, Model model) {

		Integer rs = chechR != null ? 3 : 2;
		Integer doc = chechD != null ? 3 : 2;
		Integer qad = chechQ != null ? 3 : 2;
		Integer prod = chechP != null ? 3 : 2;

		List<Ord> ordersList = orderService.ordersByStatus(rs, doc, qad, prod);
		Map<String, Boolean> chechboxesMap = new HashMap<String, Boolean>();

		chechboxesMap.put("chechR", chechR != null);
		chechboxesMap.put("chechD", chechD != null);
		chechboxesMap.put("chechQ", chechQ != null);
		chechboxesMap.put("chechP", chechP != null);

		model.addAttribute("orders", ordersList);
		model.addAttribute("boxes", chechboxesMap);
		model.addAttribute("title", "List Orders");
		model.addAttribute("path", "order/list");
		return "main";

	}

}
