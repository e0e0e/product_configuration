package pl.sda.pms.productFeature;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import pl.sda.pms.users.User;

@Entity
@Audited
public class ProductFeature {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(unique = true)
	String name;
	String description;
	String imagePath;
	
	@ManyToMany
    private Set<Feature> feature;
	
	public Set<Feature> getFeature() {
		return feature;
	}

	public void setFeature(Set<Feature> feature) {
		this.feature = feature;
	}

	public ProductFeature(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}