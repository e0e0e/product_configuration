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

	public List<List<Row>> getFields() {
		List<Feature> featureList= featureRepository.findAll();
		
		List<List<Row>> tableValues=new ArrayList<List<Row>>();
		for (Feature feature : featureList) {
			List<Row> rowList=new ArrayList<>();
			rowList.add(new Row("Feature", feature.getName(),"name"));
			rowList.add(new Row("Feature Description", feature.getDescription(),"description"));
			rowList.add(new Row("Price", feature.getPrice().toString(),"price"));
			rowList.add(new Row("Image Path", feature.getImagePath(),"imagePath"));
			tableValues.add(rowList);
		}

		
		return tableValues;

	}

	public Feature findByID(Long id) {
		return featureRepository.findById(id).get();
	}
}
