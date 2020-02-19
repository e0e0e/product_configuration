package pl.sda.pms.productConfiguration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.feature.ShortFeature;
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

		model.addAttribute("features", productFeature);

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/add");
		return "main";
	}

	@GetMapping("/product/getAll")
	@ResponseBody
	public List<ProductConfiguration> getAll(Model model) {
		List<ProductConfiguration> productConfigurations = productConfigurationService.findAll();

		return productConfigurations;
	}

	@GetMapping("/product/showAll")
	public String showAll(Model model) {

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/showAll");
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
		List<ProductConfiguration> pc=productConfigurationService.findAll();
		model.addAttribute("configurations", productConfigurationService.findAll());

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/show");
		return "main";
	}

	@GetMapping("/product/list")
	public String showProduct(@RequestParam Long productId,
			// @RequestParam(required = false) Long productFeatureId,
			Model model) {

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
	public String saveChangedProduct(@RequestParam String name, @RequestParam(required = false) List<Long> feature,
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

	@GetMapping("/product/filter")
	public String productFilter(Model model) {

		model.addAttribute("configuration", productConfigurationService.findByName("pattern"));

		model.addAttribute("title", "Make new Order");
		model.addAttribute("path", "product/filter");
		return "main";

	}

	@PostMapping("/product/search")
	public String productSearch(@RequestParam Map<String, String> paramMap, Model model) {

		model.addAttribute("configurations", productConfigurationService.findByForm(paramMap));

		model.addAttribute("title", "Show Features");
		model.addAttribute("path", "product/show");
		return "main";

	}

	@PostMapping(value = "/product/matching", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, List<ShortFeature>> productLiveSearch(@RequestBody String features, Model model) {

		Map<String, List<ShortFeature>> productMap = productConfigurationService.productLiveSearch(features);

		return productMap;

	}

	@GetMapping("/product/delete")
	public String productDelete(@RequestParam Long productId, Model model) {

		productConfigurationService.delete(productId);
		productConfigurationService.updatePattern();

		return "redirect:/product/show";

	}

	@GetMapping("/product/copy")
	public String productClone(@RequestParam Long productId, Model model) {

		productConfigurationService.clone(productId);

		return "redirect:/product/show";

	}

	@GetMapping("/export/products")
	public String exportProducts(Model model) throws IOException {
		 FileWriter file=new FileWriter("productExport.txt");
		List<ProductConfiguration> productConfigurations = productConfigurationService.findAll();

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		String json = mapper.writeValueAsString(productConfigurations);
		file.write(json);
		file.close();
		System.out.println("File writen.");
		return null;
	}

	@GetMapping("/import/products")
	public String importProducts(Model model) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		FileReader fileReader=new FileReader("productExport.txt");
		// JSONObject obj = new JSONObject(features);
        //read json file and convert to customer object
        ProductConfiguration[] pC=objectMapper.readValue(new File("productExport.txt"),ProductConfiguration[].class);
		List<ProductConfiguration> productConfigurations=Arrays.asList(pC);
		productConfigurationService.importPorductConfigurations(productConfigurations);

		
		return null;
	}

}
