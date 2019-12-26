package pl.sda.pms.productConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class ProductConfigurationController {

	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;

	public ProductConfigurationController(ProductConfigurationService productConfigurationService,
			FeatureService featureService, ProductFeatureService productFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
	}

	@GetMapping("/product/add")
	public String showFeature(Model model) {
		List<ProductFeature> productFeature = productFeatureService.findAll();

		System.out.println("ProductConfiguration feature");
		System.out.println(productFeature.toString());

		model.addAttribute("features", productFeature);

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/add");
		return "main";
	}

	@GetMapping("/product/listAll")
	public String showFeatures(Model model) {

		// List<Object> oblistList=productConfigurationService.findAllById(3L);

		model.addAttribute("configurations", productConfigurationService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/listAll");
		return "main";
	}

	@GetMapping("/product/show")
	public String showOneFeature(Model model) {

		// List<Object> oblistList=productConfigurationService.findAllById(3L);

		model.addAttribute("configurations", productConfigurationService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/show");
		return "main";
	}

	@GetMapping("/product/list")
	public String showProduct(@RequestParam Long productId, Model model) {

		// List<Object> oblistList=productConfigurationService.findAllById(3L);

		model.addAttribute("configuration", productConfigurationService.findById(productId));

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/list");
		return "main";
	}

	@PostMapping("/saveProduct")
	public String saveConfiguration(@RequestParam String name, @RequestParam List<Long> feature, Model model) {

		productConfigurationService.create(name, feature);

		return "redirect:/product/show";
	}

	@GetMapping("/product/edit")
	public String editProduct(@RequestParam Long productId, Model model) {

		model.addAttribute("newFeatures", productFeatureService.findAll());
		model.addAttribute("product", productConfigurationService.findById(productId));
		model.addAttribute("title", "Edit Product");
		model.addAttribute("path", "product/edit");
		return "main";
	}

	@PostMapping("/saveChangedProduct")
	public String saveChangedProduct(@RequestParam String name, @RequestParam List<Long> feature,
			@RequestParam Long productId, Model model) {

		productConfigurationService.saveChanges(productId, name, feature);

		return "redirect:/product/show";
	}

	@GetMapping("/product/selection")
	public String productSelectiom(@RequestParam Long productId, Model model) {

		model.addAttribute("configuration", productConfigurationService.findById(productId));

		model.addAttribute("title", "Make new Order");
		model.addAttribute("path", "product/selection");
		return "main";

	}

	@GetMapping("/product/delete")
	public String productDelete(@RequestParam Long productId, Model model) {

		productConfigurationService.delete(productId);
		

		
		return "redirect:/product/show";

	}

}
