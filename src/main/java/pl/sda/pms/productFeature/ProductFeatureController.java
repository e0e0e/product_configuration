package pl.sda.pms.productFeature;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/feature/delete")
	public String deleteProductFeatures(@RequestParam Long productFeatureId, Model model) {

		productFeatureService.delete(productFeatureId);

		return "redirect:/feature/list";
	}

	@GetMapping("/feature/copy")
	public String copyProductFeatures(@RequestParam Long productFeatureId, Model model) {

		productFeatureService.clone(productFeatureId);
		return "redirect:/feature/list";
	}

	@GetMapping("/feature/addProductFeature")
	public String addProductFeatures(Model model) {

		model.addAttribute("featuresList", featureService.findNotUsed());

		model.addAttribute("title", "Add Product Features");
		model.addAttribute("path", "feature/createProductFeature");
		return "main";
	}

	@PostMapping("/productFeatureCreate")
	public String createProductFeatures(@RequestParam String name, @RequestParam String description,
			@RequestParam String imagePath, @RequestParam(required = false) List<Long> featureList, Model model) {

		List<ProductConfiguration> productsList = productConfigurationService.findAll();
		try {
			productsList.forEach(x -> {

				ProductFeature productFeatureNew = productFeatureService.save(name, description, imagePath,
						featureList);
				productFeatureNew.setProductConfiguration(x);
				productFeatureService.save(productFeatureNew);

			});
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Please choose feature");
			model.addAttribute("featuresList", featureService.findNotUsed());

			model.addAttribute("title", "Add Product Features");
			model.addAttribute("path", "feature/createProductFeature");
			return "main";
		}

		model.addAttribute("featuresList", featureService.findAll());

		return "redirect:/feature/list";
	}

	@PostMapping("/productFeatureChange")
	public String changeProductFeature(@RequestParam Long productFeatureId, @RequestParam String name,
			@RequestParam String description,
			 @RequestParam(required = false) String parent,
			 @RequestParam(required = false) Boolean color,
			@RequestParam String imagePath, @RequestParam(required = false) List<Long> featureList, Model model) {

		productFeatureService.findById(productFeatureId);
		if (parent == null) {
			parent = "";
		}
		if (color == null) {
			color = false;
		}
		productFeatureService.edit(productFeatureId, name, description, imagePath, featureList, parent,color);

		return "redirect:/feature/list";
	}

	@GetMapping("/feature/removeFeature")
	public String removeFeature(@RequestParam Long featureId, @RequestParam Long productFeatureId, Model model) {

		Feature feature = featureService.findByID(featureId);
		Long productId = productFeatureService.findById(productFeatureId).getProductConfiguration().getId();
		productFeatureService.removeFeature(feature, productFeatureId);

		return "redirect:/product/list?productId=" + productId + "&productFeaturesId=" + productFeatureId + "#anchor_"
				+ productFeatureId;

	}

	@GetMapping("/feature/removeProductFeature")
	public String removeProductFeature(@RequestParam Long productFeatureId, @RequestParam Long productId, Model model) {

		ProductFeature productFeature = productFeatureService.findByID(productFeatureId);
		productFeature.setPosition(null);
		productFeature.removeProductConfiguration();
		productFeatureService.save(productFeature);

		return "redirect:/product/list?productId=" + productId;

	}

	@GetMapping("/feature/moveDown")
	public String moveDownProductFeature(@RequestParam Long productFeatureId, @RequestParam Long productId,
			Model model) {

		productFeatureService.moveDown(productFeatureId, productId);

		return "redirect:/product/moveList?productId=" + productId + "#anchor_" + productFeatureId;

	}

	@GetMapping("/feature/moveUp")
	public String moveUpProductFeature(@RequestParam Long productFeatureId, @RequestParam Long productId, Model model) {

		productFeatureService.moveUp(productFeatureId, productId);
		

		return "redirect:/product/moveList?productId=" + productId + "#anchor_" + productFeatureId;

	}

	

	@GetMapping("/productFeature/unify")
	public String unifyPosition() {

		productFeatureService.unifyPosition();

		return null;

	}


	@GetMapping("/product/moveList")
	public String showMoveList(Model model) {

		model.addAttribute("configuration", productConfigurationService.findByName("pattern"));

		model.addAttribute("title", "Mve Product Features");
		model.addAttribute("path", "product/move");
		return "main";
	}
}