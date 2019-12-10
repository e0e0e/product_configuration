package pl.sda.pms.productConfiguration;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
		//List<Object> oblistList=productConfigurationService.findAllById(3L);

		model.addAttribute("configurations", productConfigurationService.findAll());
		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/list");
		return "main";
	}
	
	@PostMapping("/saveProduct")
	public String saveConfiguration(@RequestParam String name,
			@RequestParam List<Long> feature,
			Model model) {

		productConfigurationService.create(name, feature);

		return "redirect:/product/list";
	}

}
