package pl.sda.pms.productFeature;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(unique = true)
	String name;
	String description;
	Double price;
	String imagePath;
	
	public String getDescription() {
		return description;
	}

	public Feature(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	@ManyToMany
	private Set<ProductFeature> productFeatureList;

	public Set<ProductFeature> getProductFeatureList() {
		return productFeatureList;
	}

	public void setProductFeatureList(Set<ProductFeature> productFeatureList) {
		this.productFeatureList = productFeatureList;
	}

	public Feature(String name) {
		super();
		this.name = name;
	}

	public Feature(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
