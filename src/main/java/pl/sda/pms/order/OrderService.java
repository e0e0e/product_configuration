package pl.sda.pms.order;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.OrderFeature.OrderFeatureController;
import pl.sda.pms.OrderFeature.OrderFeatureService;
import pl.sda.pms.color.Color;
import pl.sda.pms.color.ColorService;
import pl.sda.pms.config.CustomRevisionEntity;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.feature.FeatureService;
import pl.sda.pms.productConfiguration.ProductConfiguration;
import pl.sda.pms.productConfiguration.ProductConfigurationRepository;
import pl.sda.pms.productConfiguration.ProductConfigurationService;
import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.productFeature.ProductFeatureService;
import pl.sda.pms.projects.Project;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class OrderService {

	@PersistenceContext
	EntityManager entityManager;

	private final OrderRepository orderRepository;
	private final OrderFeatureService orderFeatureService;
	private final ProductFeatureService productFeatureService;
	private final FeatureService featureService;
	private final ColorService colorService;
	private final ProductConfigurationService productConfigurationService;

	public OrderService(OrderRepository orderRepository, OrderFeatureService orderFeatureService,
			ProductFeatureService productFeatureService, FeatureService featureService, ColorService colorService,
			ProductConfigurationService productConfigurationService) {
		super();
		this.orderRepository = orderRepository;
		this.orderFeatureService = orderFeatureService;
		this.featureService = featureService;
		this.productFeatureService = productFeatureService;
		this.colorService = colorService;
		this.productConfigurationService = productConfigurationService;
	}

	public void create(Ord order) {
		orderRepository.save(order);

	}

	public List<Ord> findAll() {
		return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));

	}

	public void deleteById(Long orderId) {
		Ord order = orderRepository.findById(orderId).get();

		order.removeAllOrderFeatures();
		orderRepository.deleteById(orderId);

	}

	public Ord findById(Long orderId) {
		return orderRepository.findById(orderId).get();

	}

	public void addMore(Long orderId, String orderName, String price, Integer unitsToProduce, String client) {

		Ord order = orderRepository.findById(orderId).get();
		order.setOrderName(orderName);

		if (OrderFeatureController.isNumeric(price)) {
			order.setPrice(Double.parseDouble(price));

		}

		order.setUnitsToProduce(unitsToProduce);
		order.setClient(client);
		order.revisionUp();
		orderRepository.save(order);

	}

	public void saveProductOrderChanges(Map<String, String> paramMap, String orderId) {
		Ord order = orderRepository.findById(Long.parseLong(orderId)).get();
		List<OrderFeature> orginalOrderFeatures = order.getOrderFeatures();

		Map<ProductFeature, Feature> newOrderFeaturesMapToFilter = paramMap.entrySet().stream()
				.filter(x -> OrderFeatureController.isNumeric(x.getKey()))
				.collect(Collectors.toMap(x -> productFeatureService.findByID(Long.parseLong(x.getKey())),
						x -> featureService.findByID(Long.parseLong(x.getValue()))));

		List<OrderFeature> newOrderFeaturesToFilter = newOrderFeaturesMapToFilter.entrySet().stream()
				.map(x -> new OrderFeature(x.getKey(), x.getValue())).collect(Collectors.toList());

		List<OrderFeature> newOrderFeatures = new ArrayList<>();
		for (OrderFeature of : newOrderFeaturesToFilter) {
			for (OrderFeature orf : orginalOrderFeatures) {
				if (of.getProductFeature().getName().equals(orf.getProductFeature().getName())
						&& !of.getFeature().getName().equals(orf.getFeature().getName())) {

					newOrderFeatures.add(of);
				}

			}
		}

		Map<ProductFeature, Feature> newOrderFeaturesMap = newOrderFeatures.stream()
				.collect(Collectors.toMap(x -> x.getProductFeature(), x -> x.getFeature()));

		for (OrderFeature of : newOrderFeatures) {
			orginalOrderFeatures.stream()
					.filter(x -> x.getProductFeature().getName().equals(of.getProductFeature().getName()))
					.collect(Collectors.toList()).get(0).setFeature(of.getFeature());

		}

		Map<String, Feature> newStringFeaturesMap = newOrderFeaturesMap.entrySet().stream()
				.collect(Collectors.toMap(x -> x.getKey().getName(), x -> x.getValue()));

		List<String> oldOrderFeatureList = newOrderFeaturesMap.entrySet().stream().map(x -> x.getKey().getName())
				.collect(Collectors.toList());

		Map<String, Feature> oldOrderFeatureMap = orginalOrderFeatures.stream()
				.collect(Collectors.toMap(x -> x.getProductFeature().getName(), x -> x.getFeature()));

		// order.setOrderFeaturesStringsMapByOrderFeatures(orginalOrderFeatures);

		try {
			Double priceList = orginalOrderFeatures.stream().mapToDouble(x -> x.getFeature().getPrice()).sum();
			order.setPrice(priceList);
		} catch (Exception e) {
			System.out.println("Cant sum price: " + e.getLocalizedMessage());
		}

		order.setOrderFeatures(orginalOrderFeatures);
		order.setOrderFeaturesStringsMapByOrderFeatures(orginalOrderFeatures);
		order.revisionUp();
		orderRepository.save(order);

		newOrderFeaturesMap.entrySet().stream().forEach(x -> {
			OrderFeature orderFeature = x.getKey().getOrderFeature();
			orderFeature.setFeature(x.getValue());
			orderFeatureService.save(orderFeature);

		});

	}

	public void saveProductOrderChangesAllParamMap(Map<String, String> paramMap, String orderId) {
		Ord order = orderRepository.findById(Long.parseLong(orderId)).get();
		paramMap.remove("orderId");
		List<OrderFeature> orderFeature = order.getOrderFeatures();
		List<OrderFeature> orderFeaturesToAdd = paramMap.entrySet().stream()
				.map(e -> new OrderFeature(productFeatureService.findById(Long.parseLong(e.getKey())),
						featureService.findByID(Long.parseLong(e.getValue()))))
				.map(x -> orderFeatureService.save(x)).collect(Collectors.toList());

				orderFeaturesToAdd.forEach(x->x.addOrd(order));

		order.addAllOrderFeatures(orderFeaturesToAdd);
		order.setOrderFeaturesStringsMapByOrderFeatures(order.getOrderFeatures());
		order.revisionUp();
		Ord newOrder=orderRepository.save(order);

		System.out.println("adf");

	}

	public List<OrderAud> findByIdAud(Long orderId) {

		@SuppressWarnings("unchecked")
		List<Object> revisions = AuditReaderFactory.get(entityManager).createQuery()
				.forRevisionsOfEntity(Ord.class, false, true).add(AuditEntity.id().eq(orderId)).getResultList();

		// revisions.remove(0);

		List<Map<String, String>> mapList = new ArrayList<>();
		List<Date> dateList = new ArrayList<>();
		List<Ord> orderList = new ArrayList<>();
		for (Object o : revisions) {
			Object[] orderAud = (Object[]) o;
			Ord order = (Ord) orderAud[0];

			try {
				Map<String, String> result = new ObjectMapper().readValue(order.getOrderFeaturesStrings(), Map.class);
				mapList.add(result);
				orderList.add(order);
				dateList.add(((CustomRevisionEntity) orderAud[1]).getRevisionDate());
			} catch (Exception e) {

				System.out.println("Error parsing JSON: " + e.getLocalizedMessage());
			}

		}
		Ord currentOrder = orderRepository.findById(orderId).get();
		List<String> nameList = currentOrder.getPositions();
		List<OrderAud> orderAuds = new ArrayList<>();

		// Set<OrderFeature> of = new HashSet<>();
		// for (Ord o : orderList) {
		// Set<OrderFeature> orderPorducts=new HashSet<>();
		// orderPorducts = o.getOrderFeatures().stream()
		// .filter(x ->
		// !nameList.contains(x.getProductFeature().getName())).collect(Collectors.toSet());
		// of.addAll(orderPorducts);
		// }

		for (int i = 1; i < mapList.size(); i++) {

			OrderAud orderAud = new OrderAud();

			for (int n = 0; n < nameList.size(); n++) {

				if (mapList.get(i).get(nameList.get(n)) != null) {
					if (!mapList.get(i).get(nameList.get(n)).equals(mapList.get(i - 1).get(nameList.get(n)))) {

						FeatureAud featureAud = new FeatureAud();
						featureAud.setPfName(nameList.get(n));
						featureAud.setOldFeature(mapList.get(i - 1).get(nameList.get(n)));
						featureAud.setNewFeature(mapList.get(i).get(nameList.get(n)));

						orderAud.addFeatureAud(featureAud);

					}
				} else {
					Set<String> mapKeySet = new HashSet<>();
					// Set<String> oldMapKeySet = new HashSet<>();
					mapKeySet.addAll(mapList.get(i).keySet());
					// oldMapKeySet.addAll(mapList.get(i - 1).keySet());

					mapKeySet.removeAll(nameList);

					for (String oldName : mapKeySet) {
						if (!mapList.get(i).get(oldName).equals(mapList.get(i - 1).get(oldName))) {
							FeatureAud featureAud = new FeatureAud();
							featureAud.setPfName(oldName);
							featureAud.setOldFeature(mapList.get(i - 1).get(oldName));
							featureAud.setNewFeature(mapList.get(i).get(oldName));

							orderAud.addFeatureAud(featureAud);
						}
					}

					// System.out.println("Product feature name was changed between order
					// versions");
				}

			}

			orderAud.setDate(dateList.get(i));

			orderAud.setOrder(orderList.get(i));
			orderAud.setRevision(Long.parseLong(orderList.get(i).getRevision().toString()));
			orderAuds.add(orderAud);

		}

		return orderAuds;
	}

	public void saveColor(Long orderId, Map<String, String> paramMapStart) {

		Ord order = orderRepository.findById(orderId).get();
		Map<String, String> paramMap = paramMapStart.entrySet().stream().filter(x -> !x.getValue().equals(""))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// order.getOrderFeatures().forEach(
		// x -> System.out.println(x.getProductFeature().getId() + " - " +
		// x.getProductFeature().getName()));

		Map<OrderFeature, Color> colorMap = paramMap.entrySet().stream()
				.collect(Collectors.toMap(x -> order.findByProductFeatureID(Long.parseLong(x.getKey())),
						x -> colorService.findById(Long.parseLong(x.getValue()))));

		colorMap.entrySet().stream().forEach(x -> {
			x.getKey().setColor(x.getValue());
			orderFeatureService.save(x.getKey());

		});

	}

	public Object findByIdAndUpdatePrice(Long orderId) {

		Ord order = orderRepository.findById(orderId).get();
		Double price = order.getOrderFeatures().stream().filter(x -> x.getFeature().getPrice() != null)
				.mapToDouble(x -> x.getFeature().getPrice()).sum();
		order.setPrice(price);
		Ord updatedOrder = orderRepository.save(order);

		return updatedOrder;
	}

	public List<ProductConfiguration> checkIfRealyNoStandard(Ord order) {

		List<OrderFeature> orderFeatures = order.getOrderFeatures();
		List<ProductConfiguration> productConfiguration = productConfigurationService.findAll();

		List<ProductConfiguration> matchingProducts = new ArrayList<>();
		List<String> noMatch = new ArrayList<>();

		for (ProductConfiguration pc : productConfiguration) {
			Boolean match = false;
			for (OrderFeature of : orderFeatures) {

				if (pc.findIfMatch(of)) {
					match = true;

				} else {
					match = false;
					break;
				}
			}
			if (match != false) {

				matchingProducts.add(pc);

			}

		}
		System.out.println("Match find in :" + matchingProducts.size());
		matchingProducts.forEach(x -> System.out.println(x.getName()));

		System.out.println("End of matching");
		return matchingProducts;
	}

	public void save(Ord order) {
		order.revisionUp();
		order.setOrderFeaturesStringsMapByOrderFeatures(order.getOrderFeatures());
		orderRepository.save(order);
	}

	public void saveAsProduct(Long orderId) {
		Ord order = orderRepository.findById(orderId).get();

		List<OrderFeature> orderFeatures = order.getOrderFeatures();
		Feature chassis = orderFeatures.stream().filter(x -> x.getProductFeature().getName().equals("Chassis"))
				.map(x -> x.getFeature()).findFirst().get();

		ProductConfiguration productConfiguration = new ProductConfiguration();

		Date date = Calendar.getInstance().getTime();
		productConfiguration.setName(chassis.getName() + " - " + date.toString());
		ProductConfiguration newPc = productConfigurationService.save(productConfiguration);

		List<ProductFeature> productFeatures = new ArrayList<>();

		for (OrderFeature of : orderFeatures) {
			ProductFeature productFeature = new ProductFeature();

			productFeature.setColor(of.getProductFeature().getColor());
			productFeature.setName(of.getProductFeature().getName());
			productFeature.setDescription(of.getProductFeature().getDescription());
			productFeature.setImagePath(of.getProductFeature().getImagePath());
			productFeature.setParent(of.getProductFeature().getParent());
			productFeature.setPosition(of.getProductFeature().getPosition());
			productFeature.addFeatureToCollection(of.getFeature());
			productFeature.setProductConfiguration(newPc);
			ProductFeature newPF = productFeatureService.save(productFeature);

			productFeatures.add(newPF);

		}

		newPc.setConfigurationList(productFeatures);

		productConfigurationService.save(newPc);
		System.out.println("ok");
	}

	public void newFeatureToOrder(Feature newFeature, Feature oldFeature, Long orderId) {
		newFeature.setNoStandard(true);
		Feature nFeature = featureService.save(newFeature);
		Ord orderToChange = findById(orderId);

		OrderFeature orderFeature = orderToChange.findOrderFeatureByFeatyre(oldFeature);
		orderFeature.setFeature(nFeature);
		nFeature.setOrderFeatures(orderFeature);
		featureService.save(nFeature);

		// orderFeatureService.save(orderFeature);

		orderToChange.setNoStandard(true);
		save(orderToChange);

	}

	public void readMatrix(Ord order) {
		String nameToSearch = order.getOrderName();

		try {
			File file = new File("C:\\LD\\tomcat\\webapps\\sp\\LD Orders _MATRIX MASTER v.xlsx");

			FileInputStream fip = new FileInputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook(fip);
			XSSFSheet sheet = workbook.getSheet("MATRIX - MASTER v.2");
			for (int rowIndex = 0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				if (row != null && row.getCell(2).getStringCellValue().trim().equals(nameToSearch.trim())) {
					System.out
							.println(row.getCell(2).getStringCellValue() + " in row " + row.getCell(2).getReference());
					System.out.println("link: " + row.getCell(2).getHyperlink().getAddress());
					order.setLink(row.getCell(2).getHyperlink().getAddress());
					order.setPlOrder(row.getCell(1).getStringCellValue());
					order.setClient(row.getCell(6).getStringCellValue());
					order.setUnitsToProduce(Integer.parseInt(row.getCell(7).getRawValue()));

					orderRepository.save(order);
					break;
				}
			}

			if (file.isFile() && file.exists()) {
				System.out.println("MATRIX open");
			} else {
				System.out.println("MATRIX either not exist" + " or can't open");
			}

		} catch (Exception e) {
			System.out.println("excel file not found: " + e.getLocalizedMessage());
		}

	}

}
