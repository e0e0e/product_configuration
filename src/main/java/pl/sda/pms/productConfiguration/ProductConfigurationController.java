package pl.sda.pms.productConfiguration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class ProductConfigurationController {

	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;

	public ProductConfigurationController(ProductConfigurationService productConfigurationService,
			FeatureService featureService,
			ProductFeatureService productFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService=productFeatureService;
	}

	@GetMapping("/product/add")
	public String showFeature(Model model) {
		model.addAttribute("features", productFeatureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/add");
		return "main";
	}

	@GetMapping("/product/list")
	public String showFeatures(Model model) {

		model.addAttribute("fields", productConfigurationService.findAll());
		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/list");
		return "main";
	}

}
