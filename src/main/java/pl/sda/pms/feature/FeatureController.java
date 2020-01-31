package pl.sda.pms.feature;

import java.util.List;

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
	
	@GetMapping("/feature/editFeatureNoStandard")
	public String editFeatureNoStandard(@RequestParam Long featureId,
			@RequestParam(required = false) Long orderId,
			Model model) {
				
		List<Feature> features=featureService.findWithSameProductFeatue(featureId);
		
		model.addAttribute("feature", featureService.findByID(featureId));
		model.addAttribute("orderId", orderId);

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "feature/editFeatureNoStandard");
		return "main";
	}

	@GetMapping("/feature/editFeature")
	public String editFeature(@RequestParam Long featureId,
			@RequestParam(required = false) Long orderId,
			Model model) {
				
		model.addAttribute("feature", featureService.findByID(featureId));
		model.addAttribute("orderId", orderId);

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
			@RequestParam String mIndex,
			@RequestParam(required = false) Long orderId,
			Model model) {
				
		model.addAttribute("feature", featureService.findByID(featureId));

		featureService.saveChanges(name,description,imagePath,index,price,featureId,mIndex);
		if(orderId!=null){
			return "redirect:/order/show?orderId="+orderId;

		}

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
			@RequestParam String mIndex,
			@RequestParam String price
	) {
				

		featureService.create(name,description,imagePath,index,price,mIndex);

		return "redirect:/feature/list";
	}



	@GetMapping("/delete/feature")
	public String saveEditedFeature(@RequestParam Long featureId,
	@RequestParam Long temp,
		Model model) {
				try {
					featureService.deleteById(featureId);
				} catch (Exception e) {
					System.out.println("Delete feature failed, probably it is used in somewere: "+e.getMessage());
				}
		

		return "redirect:/feature/show#anchor_" + temp;
	}
}
