package pl.sda.pms.productFeature;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.typesafe.config.Optional;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productConfiguration.ProductConfiguration;
import pl.sda.pms.productConfiguration.ProductConfigurationService;

@Controller
public class ProductFeatureController {

	private final ProductFeatureService productFeatureService;
	private final FeatureService featureService;
	private final ProductConfigurationService productConfigurationService;

	public ProductFeatureController(ProductFeatureService productFeatureService, FeatureService featureService,
			ProductConfigurationService productConfigurationService) {

		this.productFeatureService = productFeatureService;
		this.featureService = featureService;
		this.productConfigurationService = productConfigurationService;
	}

	@GetMapping("/feature/productShow")
	public String showProductFeatures(Model model) {

		model.addAttribute("fields", productFeatureService.getFields());
		model.addAttribute("features", productFeatureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/productFeatureList");
		return "main";
	}

	@GetMapping("/feature/list")
	public String listProductFeatures(Model model) {

		model.addAttribute("features", productFeatureService.findAll());

		model.addAttribute("title", "List Features");
		model.addAttribute("path", "feature/list");
		return "main";
	}

	@GetMapping("/feature/edit")
	public String listProductFeatures(@RequestParam Long productFeatureId, Model model) {

		model.addAttribute("productFeature", productFeatureService.findByID(productFeatureId));
		model.addAttribute("featuresList", featureService.findAll());

		model.addAttribute("title", "Edit Features");
		model.addAttribute("path", "feature/edit");
		return "main";
	}

	@GetMapping("/feature/addProductFeature")
	public String addProductFeatures(Model model) {

		model.addAttribute("featuresList", featureService.findAll());

		model.addAttribute("title", "Add Product Features");
		model.addAttribute("path", "feature/createProductFeature");
		return "main";
	}

	@PostMapping("/productFeatureCreate")
	public String createProductFeatures(@RequestParam String name, @RequestParam String description,
			@RequestParam String imagePath, @RequestParam List<Long> featureList, Model model) {

		productFeatureService.save(name, description, imagePath, featureList);
		model.addAttribute("featuresList", featureService.findAll());

		return "redirect:/feature/list";
	}

	@PostMapping("/productFeatureChange")
	public String changeProductFeature(@RequestParam Long productFeatureId, @RequestParam String name,
			@RequestParam String description, @RequestParam String imagePath, @RequestParam List<Long> featureList,
			Model model) {

		productFeatureService.findById(productFeatureId);
		productFeatureService.edit(productFeatureId, name, description, imagePath, featureList);
		// featureList.parallelStream().forEach(x->System.out.println(x));

		return "redirect:/product/show";
	}

	@GetMapping("/feature/removeFeature")
	public String removeFeature(@RequestParam Long featureId, @RequestParam Long productFeatureId, Model model) {

		Feature feature = featureService.findByID(featureId);
		productFeatureService.removeFeature(feature, productFeatureId);

		return "redirect:/product/show";

	}

	@GetMapping("/feature/removeProductFeature")
	public String removeProductFeature(@RequestParam Long productFeatureId, Model model) {

		ProductFeature productFeature = productFeatureService.findByID(productFeatureId);
		productFeature.removeProductConfiguration();
		productFeatureService.save(productFeature);

		return "redirect:/product/show";

	}

	@GetMapping("/feature/moveDown")
	public String moveDownProductFeature(@RequestParam Long productFeatureId, @RequestParam Long productId, Model model) {

	
		ProductFeature productFeature = productFeatureService.findByID(productFeatureId);
		ProductConfiguration productConfiguration = productFeature.getProductConfiguration();
		
		int position = productFeature.getPosition();
		ProductFeature nextProductFeature = productConfiguration.getConfigurationList().stream()
				.sorted(Comparator.comparing(ProductFeature::getPosition)).filter(x -> x.getPosition() > position)
				.findFirst().get();
		productFeature.setPosition(nextProductFeature.getPosition());
		nextProductFeature.setPosition(position);

		productFeatureService.save(nextProductFeature);
		productFeatureService.save(productFeature);

		return "redirect:/product/list?productId=" + productId;

	}
	
	@GetMapping("/feature/moveUp")
	public String moveUpProductFeature(@RequestParam Long productFeatureId, @RequestParam Long productId, Model model) {

	
		ProductFeature productFeature = productFeatureService.findByID(productFeatureId);
		ProductConfiguration productConfiguration = productFeature.getProductConfiguration();
		
		int position = productFeature.getPosition();
		ProductFeature nextProductFeature = productConfiguration.getConfigurationList().stream()
				.sorted(Comparator.comparing(ProductFeature::getPosition).reversed()).filter(x -> x.getPosition() < position)
				.findFirst().get();
		
		productFeature.setPosition(nextProductFeature.getPosition());
		nextProductFeature.setPosition(position);

		productFeatureService.save(nextProductFeature);
		productFeatureService.save(productFeature);

		return "redirect:/product/list?productId=" + productId;

	}
}