package pl.sda.pms.OrderFeature;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productConfiguration.ProductConfigurationService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;

@Controller
public class OrderFeatureController {
	
	private final ProductConfigurationService productConfigurationService;
	private final FeatureService featureService;
	private final ProductFeatureService productFeatureService;
	private final OrderFeatureService orderFeatureService;

	public OrderFeatureController(ProductConfigurationService productConfigurationService,
			FeatureService featureService, ProductFeatureService productFeatureService,
			OrderFeatureService orderFeatureService) {
		this.productConfigurationService = productConfigurationService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.orderFeatureService=orderFeatureService;
	}
	

}
