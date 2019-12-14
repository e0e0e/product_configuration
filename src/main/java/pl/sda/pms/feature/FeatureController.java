package pl.sda.pms.feature;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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



	@GetMapping("/feature/show")
	public String showFeatures(Model model) {
		
		
		model.addAttribute("features", featureService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/featureList");
		return "main";
	}
	
	@GetMapping("/feature/editFeature")
	public String editFeature(@RequestParam Long featureId,
			Model model) {
				
		model.addAttribute("feature", featureService.findByID(featureId));

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/editFeature");
		return "main";
	}
	
	
	@PostMapping("/featureChange")
	public String saveEditedFeature(@RequestParam String name,
			@RequestParam String description,
			@RequestParam String imagePath,
			@RequestParam String index,
			@RequestParam String price,
			@RequestParam Long featureId,
			Model model) {
				
		model.addAttribute("feature", featureService.findByID(featureId));

		featureService.saveChanges(name,description,imagePath,index,price,featureId);

		return "redirect:/feature/list";
	}
	
	@GetMapping("/feature/add")
	public String addFeatures(Model model) {
		
		model.addAttribute("title", "Add Features");
		model.addAttribute("path", "feature/createFeature");
		return "main";
	}
	
	
	@PostMapping("/createFeature")
	public String createFeature(@RequestParam String name,
			@RequestParam String description,
			@RequestParam String imagePath,
			@RequestParam String index,
			@RequestParam String price
	) {
				

		featureService.create(name,description,imagePath,index,price);

		return "redirect:/feature/list";
	}
}
