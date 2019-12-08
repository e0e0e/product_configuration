package pl.sda.pms.feature;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.productFeature.ProductFeatureService;



@Controller
public class FeatureController {
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;

	public FeatureController(FeatureService featureService, ProductFeatureService productFeatureService) {
		this.featureService = featureService;
		this.productFeatureService=productFeatureService;
	}

//	@GetMapping("/feature")
//    public String showFeature(Model model) {
//
//        return "featurelist";
//    }

	@GetMapping("/feature/show")
	public String showFeatures(Model model) {
		
		
		
		model.addAttribute("fields", featureService.getFields());
		model.addAttribute("features", featureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/featureList");
		return "main";
	}
	


}
