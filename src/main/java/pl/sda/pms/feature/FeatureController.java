package pl.sda.pms.feature;

import java.util.Collection;
import java.util.List;
import java.util.logging.ErrorManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.order.Ord;
import pl.sda.pms.order.OrderService;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class FeatureController {
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;
	private final OrderFeatureService orderFeatureService;
	private final OrderService orderService;

	public FeatureController(FeatureService featureService, ProductFeatureService productFeatureService,
			OrderFeatureService orderFeatureService, OrderService orderService) {
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderFeatureService = orderFeatureService;
		this.orderService = orderService;
	}

	@GetMapping("/feature/show")
	public String showFeatures(Model model) {

		model.addAttribute("features", featureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/featureList");
		return "main";
	}

	@GetMapping("/feature/editFeatureNoStandard")
	public String editFeatureNoStandard(@RequestParam Long featureId, 
	@RequestParam(required = false) Long orderId,
	@RequestParam(required = false) String errorMessage,
			Model model) {

		model.addAttribute("feature", featureService.findByID(featureId));
		model.addAttribute("existingFeatures",
				productFeatureService.findWithSameProductFeatue(featureId, orderService.findById(orderId)));
		model.addAttribute("orderId", orderId);

		model.addAttribute("title", "Show Features");
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("path", "feature/editFeatureNoStandard");
		return "main";
	}

	@GetMapping("/feature/editFeature")
	public String editFeature(@RequestParam Long featureId, @RequestParam(required = false) Long orderId, Model model) {

		model.addAttribute("feature", featureService.findByID(featureId));
		model.addAttribute("orderId", orderId);

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/editFeature");
		return "main";
	}

	@PostMapping("/featureChange")
	public String saveEditedFeature(@RequestParam String name, @RequestParam String description,
			@RequestParam String imagePath, @RequestParam String index, @RequestParam String price,
			@RequestParam Long featureId, @RequestParam String mIndex,
			@RequestParam(required = false) Boolean noStandard, @RequestParam(required = false) Long orderId,
			@RequestParam(required = false) Boolean edit, Model model) {

		if (edit == null) {
			edit = false;
		}
		if (edit) {
			// price will not be changed becouse 0.0!

			Feature newFeature = new Feature(name, description, 0.0, imagePath, index, mIndex);
			Feature oldFeature = featureService.findByID(featureId);
			orderService.newFeatureToOrder(newFeature, oldFeature, orderId);

			// Feature createdFeature=featureService.save(newFeature);

			return "redirect:/order/show?orderId=" + orderId;
		}
		model.addAttribute("feature", featureService.findByID(featureId));
		if (noStandard == null) {
			noStandard = false;
		}
		 
		try {
			featureService.saveChanges(name, description, imagePath, index, price, featureId, mIndex, noStandard);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return "redirect:/feature/editFeatureNoStandard?featureId="+featureId+"&orderId="+orderId+"&errorMessage="+e.getLocalizedMessage();
		}

		if (orderId != null) {
			return "redirect:/order/show?orderId=" + orderId;

		}

		return "redirect:/feature/list";
	}

	@PostMapping("/feature/existingFeatureChange")
	public String saveExistedFeatureToNoStandardOrder(@RequestParam Long existingFeatureId,
			@RequestParam Long featureId, @RequestParam Long orderId, Model model) {

		Feature feature = featureService.findByID(existingFeatureId);
		Feature noStandardFeature = featureService.findByID(featureId);
		OrderFeature orderFeature = noStandardFeature.getOrderFeatures();
		orderFeature.setFeature(feature);
		List<Ord> order = noStandardFeature.getOrderFeatures().getOrd();
		if (order.size() > 1) {
			model.addAttribute("errorMessage", "Too many orders");
			return "redirect:/feature/editFeatureNoStandard?featureId=" + featureId + "&orderId=" + orderId;
		}
		if (order.size() == 0) {
			Ord orderToChange = orderService.findById(orderId);
			for (OrderFeature of : orderToChange.getOrderFeatures()) {
				if (of.getFeature().equals(noStandardFeature)) {
					of.setFeature(feature);

				}

			}
			orderToChange.setNoStandard(true);
			orderService.save(orderToChange);
			return "redirect:/order/show?orderId=" + orderId;
		}

		orderFeatureService.save(orderFeature);

		return "redirect:/order/show?orderId=" + orderId;

	}

	@GetMapping("/feature/add")
	public String addFeatures(Model model) {

		model.addAttribute("title", "Add Features");
		model.addAttribute("path", "feature/createFeature");
		return "main";
	}

	@PostMapping("/createFeature")
	public String createFeature(@RequestParam String name, @RequestParam String description,
			@RequestParam String imagePath, @RequestParam String index, @RequestParam String mIndex,
			@RequestParam String price) {

		featureService.create(name, description, imagePath, index, price, mIndex);

		return "redirect:/feature/list";
	}

	@GetMapping("/delete/feature")
	public String deleteEditedFeature(@RequestParam Long featureId, @RequestParam Long temp, Model model) {
		try {
			featureService.deleteById(featureId);
		} catch (Exception e) {
			System.out.println("Delete feature failed, probably it is used in somewhere: " + e.getMessage());
		}

		return "redirect:/feature/show#anchor_" + temp;
	}
}
