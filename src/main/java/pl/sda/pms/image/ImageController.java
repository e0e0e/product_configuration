package pl.sda.pms.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.apache.poi.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;

@Controller
public class ImageController {
	private final ImageService imageService;
	private final FeatureService featureService;

	public ImageController(ImageService imageService, FeatureService featureService) {
		this.imageService = imageService;
		this.featureService = featureService;

	}

	@GetMapping("/image/get")
	public String getImage(@RequestParam(required = false) Long featureId, Model model) {

		model.addAttribute("feature", featureService.findByID(featureId));
		model.addAttribute("title", "Get image");
		model.addAttribute("path", "image/getImage");
		return "main";
	}

	@PostMapping("/image/add")
	public String addImage(Model model) throws Exception {

		imageService.importImage();

		model.addAttribute("title", "Get image");
		model.addAttribute("path", "image/getImage");
		return "main";
	}

	@RequestMapping(value = "/image/saveCanvasImage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCanvasImage(
			@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64) {

		Map<String, Object> res = new HashMap<String, Object>();

		File imageFile = new File("/home/grzes/Documents/pliki/canvasImage.png");
		try {
			byte[] decodedBytes = DatatypeConverter
					.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			ImageIO.write(bfi, "png", imageFile);
			bfi.flush();
			res.put("ret", 0);
		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
			return res;
		}

		return res;
	}

	@PostMapping(value = "/image/saveCanvasImageToFtp", produces = "application/json")
	@ResponseBody
	public String saveCanvasImageToFtp(@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64,
			@RequestParam String featureId) {
		Feature feature = featureService.findByID(Long.parseLong(featureId));
		try {
			String imagePath = imageService.saveToFtp(imageBase64, feature.getName());
			featureService.saveImagePath(feature.getId(), imagePath);
			return imagePath;
		} catch (Exception e) {
			System.out.println("Error:" + e.getLocalizedMessage());
		}

		return null;

	}

	@GetMapping("/image/list")
	public String readFtp(Model model) throws Exception {
		FTPFile[] imageList = imageService.readFtp();
		model.addAttribute("imageList", imageList);
		model.addAttribute("title", "List image");
		model.addAttribute("path", "image/listImages");
		return "main";

	}

	// @RequestMapping(value = "/getImage/{imagePath}")
	// @ResponseBody
	// public byte[] getImage(@PathVariable String imagePath, HttpServletRequest
	// request) throws IOException {
	// String rpath = request.getServletContext().getRealPath("/");
	// rpath = rpath + "/" + imagePath; // whatever path you used for storing the
	// file
	// Path path = Paths.get(rpath);
	// byte[] data = Files.readAllBytes(path);
	// return data;
	// }

	// }

	@GetMapping(value = "/im1", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(HttpServletRequest request) throws IOException {
		BufferedImage originalImage = null;
		String filePath0 = request.getServletContext().getRealPath("/");
		String filePath = filePath0.replace("pm\\src\\main\\webapp\\", "");

		originalImage = ImageIO.read(new File(filePath + "imagesLd\\73.png"));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "png", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();


		return imageInByte;
	}

}
