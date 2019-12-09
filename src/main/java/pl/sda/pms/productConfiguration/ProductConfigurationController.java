package pl.sda.pms.productConfiguration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.sda.pms.feature.FeatureService;

@Controller
public class ProductConfigurationController {

	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;

	public ProductConfigurationController(ProductConfigurationService productConfigurationService,
			FeatureService featureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
	}

	@GetMapping("/product/add")
	public String showFeature(Model model) {
		model.addAttribute("features", featureService.findAll());

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
