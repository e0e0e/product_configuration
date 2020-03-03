package pl.sda.pms.feature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import pl.sda.pms.OrderFeature.OrderFeatureController;
import pl.sda.pms.users.UsersController;

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
		List<Feature> featureList = featureRepository.findAll();

		List<List<Row>> tableValues = new ArrayList<List<Row>>();
		for (Feature feature : featureList) {
			List<Row> rowList = new ArrayList<>();
			rowList.add(new Row("Feature", feature.getName(), "name"));
			rowList.add(new Row("Feature Description", feature.getDescription(), "description"));
			rowList.add(new Row("Price", feature.getPrice().toString(), "price"));
			rowList.add(new Row("Image Path", feature.getImagePath(), "imagePath"));
			rowList.add(new Row("Index", feature.getIndex(), "index"));
			tableValues.add(rowList);
		}

		return tableValues;

	}

	public Feature findByID(Long id) {
		Feature feature = featureRepository.findById(id).get();
		return feature;
	}

	public boolean saveChanges(String name, String description, String imagePath, String index, String price, Long id,
			String mIndex, Boolean noStandard) {
		Feature feature = featureRepository.findById(id).get();

		// if (featureRepository.findByNameAndIndexDiffId(name,index,id)!=null){
		// throw new RuntimeException("There is allready feature with same name and
		// index and with different id, please use existing");
		// }

		feature.setName(name);
		feature.setDescription(description);
		feature.setImagePath(imagePath);
		feature.setIndex(index);
		feature.setmIndex(mIndex);
		feature.setNoStandard(noStandard);

		if (OrderFeatureController.isNumeric(price)) {
			feature.setPrice(Double.parseDouble(price));
		}
		Feature createdFeature = featureRepository.save(feature);
		return createdFeature.getId() != null;
	}

	public boolean create(String name, String description, String imagePath, String index, String price,
			String mindex) {
		Feature feature = new Feature(name, description, 0.0, imagePath, index, mindex);
		if (OrderFeatureController.isNumeric(price)) {
			feature.setPrice(Double.parseDouble(price));

		}

		Feature createdFeature = featureRepository.save(feature);
		return createdFeature.getId() != null;
	}

	public Feature save(Feature feature) {

		return featureRepository.save(feature);

	}

	public List<Feature> findNotUsed() {
		return featureRepository.findNotUsed();

	}

	public Feature createWithNameNoStandard(String name) {
		Feature feature = new Feature();
		feature.setName(name);
		feature.setNoStandard(true);
		return featureRepository.save(feature);

	}

	public void deleteById(Long featureId) {

		featureRepository.deleteById(featureId);
	}

	public void existsByNameAndIndex(String name, String index) {
		if (featureRepository.findByNameAndIndex(name, index) != null) {
			throw new RuntimeException("There is allready feature with same name and index, please use existing");
		}
	}

	public Boolean existsById(Long id) {
		return featureRepository.existsById(id);

	}

	public Feature findFeatureByNameAndIndex(Feature f) {
		return featureRepository.findFeatureByNameAndIndex(f.getName(), f.getIndex());
	}

	public void saveImagePath(Long featureId, String imagePath) {

		Feature feature = featureRepository.findById(featureId).get();
		feature.setImagePath(imagePath);
		featureRepository.save(feature);
	}

	public List<File> searchPDF(String index) {
		
		// String path="\\wielton.corp\s-it-new\QAD_PDF\pdf";
		try {
			//Set<String> filesList = UsersController.listFilesUsingFileWalk("\\\\wielton.corp\\s-it-new\\QAD_PDF\\pdf");
			index=index.replace("-W6", "");
			index=index.replace("-W2", "");

			String searchIndex=index;
			File dir = new File("\\\\wielton.corp\\s-it-new\\QAD_PDF\\pdf");
			File[] filesArray = dir.listFiles((dir1, name) -> name.endsWith(searchIndex+".pdf"));
			List<File> files=new ArrayList<>(Arrays.asList(filesArray));
			
			return files;
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;
	}

	

}
