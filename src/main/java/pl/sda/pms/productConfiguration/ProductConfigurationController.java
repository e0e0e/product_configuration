package pl.sda.pms.productConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.h2.tools.RunScript;
import org.h2.util.IOUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@Value("${spring.datasource.url}")
	private String h2Url;
	@Value("${spring.datasource.username}")
	private String h2Username;
	@Value("${spring.datasource.password}")
	private String h2Password;

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
		List<ProductConfiguration> pc = productConfigurationService.findAll();
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

	@GetMapping("/product/search")
	public String productSearch(Model model) {

		model.addAttribute("configuration", productConfigurationService.findByName("pattern"));

		model.addAttribute("title", "Search in products");
		model.addAttribute("path", "product/search");
		return "main";

	}

	// @PostMapping("/product/search")
	// public String productSearch(@RequestParam Map<String, String> paramMap, Model
	// model) {

	// model.addAttribute("configurations",
	// productConfigurationService.findByForm(paramMap));

	// model.addAttribute("title", "Show Features");
	// model.addAttribute("path", "product/show");
	// return "main";

	// }

	@PostMapping(value = "/product/matching", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, List<ShortFeature>> productLiveSearch(@RequestBody String features, Model model) {

		Map<String, List<ShortFeature>> productMap = productConfigurationService.productLiveSearch(features);

		return productMap;

	}

	@PostMapping("/search/products")
	public String searchProducts(@RequestParam Map<String, String> paramMap, Model model) {
		List<ProductConfiguration> products = productConfigurationService.productSearch(paramMap);
		List<String> productFeaturesNames=products.get(0).getConfigurationList().stream().sorted((o1,o2)->o1.getPosition().compareTo(o2.getPosition())).map(x->x.getName()).collect(Collectors.toList());
		model.addAttribute("columntitles", productFeaturesNames);
		model.addAttribute("configurations", products);
		model.addAttribute("title", "Filtered products");
		model.addAttribute("path", "product/listfiltered");
		return "main";

	}

	@GetMapping("/product/delete")
	public String productDelete(@RequestParam Long productId, Model model) {

		productConfigurationService.delete(productId);
		productConfigurationService.updatePattern();

		return "redirect:/product/show";

	}

	@GetMapping("/product/update")
	public String paternUpdate() {

		productConfigurationService.updatePattern();

		return "redirect:/product/show";

	}

	@GetMapping("/product/copy")
	public String productClone(@RequestParam Long productId, Model model) {

		productConfigurationService.clone(productId);

		return "redirect:/product/show";

	}

	@GetMapping("/export/products")
	public String exportProducts(HttpServletResponse response, HttpServletRequest request, Model model) {

		try {
			Charset charset = Charset.forName("UTF-8");
			RunScript.execute(h2Url, h2Username, h2Password, request.getServletContext().getRealPath("/") + "query.sql",
					charset, false);
			System.out.println("File Exported to: " + request.getServletContext().getRealPath("/") + "query.sql");
		} catch (Exception e) {
			System.out.println(
					"Export failed: " + request.getServletContext().getRealPath("/") + e.getLocalizedMessage());
			// model.addAttribute("errorMessage", respons+e.getLocalizedMessage());
			model.addAttribute("title", "Show Error");
			model.addAttribute("path", "error/show");
			return "main";
		}

		try {
			String filePathToBeServed = "db-dump.sql";
			File fileToDownload = new File(filePathToBeServed);
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=db-dump.sql");
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
			inputStream.close();
		} catch (Exception e) {
			System.out
					.println("Request could not be completed at this moment. File not saved. Please try again. Error: "
							+ e.getLocalizedMessage());
			model.addAttribute("errorMessage", e.getLocalizedMessage());
			model.addAttribute("title", "Show Error");
			model.addAttribute("path", "error/show");
			return "main";

		}

		return null;
	}

	@GetMapping("/import/products")
	public String importProducts(Model model) {
		try {
			// importSql();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}
		return "redirect:/product/show";
	}

	@PostMapping("/uploadFile")
	public String uploadDbFileToServer(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			Model model) {

		try {
			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
			String fileName = date + file.getOriginalFilename();

			String filePath = request.getServletContext().getRealPath("/");

			File newFile = new File(filePath + "db-dump.sql");

			file.transferTo(newFile);

			System.out.println("File saved on server.");

			try {
				importSql(request);
			} catch (Exception e) {
				System.out.println("error uplading file: " + e.getLocalizedMessage());
				model.addAttribute("errorMessage", e.getLocalizedMessage());
				model.addAttribute("title", "Show Error");
				model.addAttribute("path", "error/show");
				return "main";
			}

			model.addAttribute("file", file);
			model.addAttribute("title", "file details");
			model.addAttribute("path", "product/fileUploadView");
			return "main";
		} catch (Exception e) {
			System.out.println("File saved on server error: " + e.getMessage());
			return "redirect:/upload/db";
		}

	}

	public void importSql(HttpServletRequest request) throws IOException {
		try {
			Charset charset = Charset.forName("UTF-8");
			productConfigurationService.dropAllObjects();
			System.out.println("Databased droped.");
			String filePath = request.getServletContext().getRealPath("/") + "db-dump.sql";
			RunScript.execute(h2Url, h2Username, h2Password, filePath, charset, false);
			System.out.println("File imported.");
		} catch (Exception e) {
			System.out.println("Import failed: " + e.getLocalizedMessage());

		}
	}

	@GetMapping("/upload/db")
	public String uploadDb(Model model) {

		model.addAttribute("title", "Upload DB");
		model.addAttribute("path", "product/upload");
		return "main";
	}

}
