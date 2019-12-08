package pl.sda.pms.productFeature;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductFeatureController{
	
	private final ProductFeatureService productFeatureService;

	public ProductFeatureController(ProductFeatureService productFeatureService) {

		this.productFeatureService = productFeatureService;
	}
	
	
	@GetMapping("/feature/productShow")
	public String showProductFeatures(Model model) {

		model.addAttribute("features", productFeatureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/productFeatureList");
		return "main";
	}

	
	
}