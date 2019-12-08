package pl.sda.pms.feature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class FeatureService {

	private final FeatureRepository featureRepository;

	public FeatureService(FeatureRepository featureRepository) {
		super();
		this.featureRepository = featureRepository;
	}

	public List<Feature> findAll() {

		return featureRepository.findAll();

	}

	public List<Row> getFields() {
		List<Feature> featureList= featureRepository.findAll();
		List<Row> rowList=new ArrayList<>();
		for (Feature feature : featureList) {
			
			rowList.add(new Row("Feature", feature.getName()));
			rowList.add(new Row("Feature", feature.getDescription()));
			rowList.add(new Row("Feature", feature.getImagePath()));
		}
//		
//		Field[] fields = Feature.class.getDeclaredFields();
//		List<String> fieldeList=Arrays.stream(fields).map(x -> x.getName()).collect(Collectors.toList());

		
		return rowFeatureList;

	}
}
