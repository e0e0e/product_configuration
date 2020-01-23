package pl.sda.pms.color;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ColorService {

	private final ColorRepository colorRepository;


	public ColorService(ColorRepository colorRepository) {
		this.colorRepository = colorRepository;
	}


	public void create(String name, String ral, String hex) {
		Color color=new Color();

		color.setName(name);
		color.setRal(ral);
		color.setHex(hex);

		colorRepository.save(color);

	}


	public List<Color> findAll() {
		return colorRepository.findAll();
	}

}
