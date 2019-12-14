package pl.sda.pms.productFeature;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.feature.FeatureService;

@Controller
public class ProductFeatureController {

	private final ProductFeatureService productFeatureService;
	private final FeatureService featureService;

	public ProductFeatureController(ProductFeatureService productFeatureService,FeatureService featureService) {

		this.productFeatureService = productFeatureService;
		this.featureService=featureService;
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
	
	@PostMapping("/productFeatureChange")
	public String changeProductFeature(@RequestParam Long productFeatureId, 
			@RequestParam String name,
			@RequestParam String description,
			@RequestParam String imagePath,
			@RequestParam List<Long> featureList,
			Model model
			) {
		
		
		productFeatureService.findById(productFeatureId);
		productFeatureService.edit(productFeatureId, name, description, imagePath, featureList);
		featureList.parallelStream().forEach(x->System.out.println(x));
		return "redirect:/product/list";
	}

}