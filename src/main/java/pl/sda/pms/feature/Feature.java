package pl.sda.pms.feature;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

import pl.sda.pms.productFeature.ProductFeature;

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
	String index;
	
	@ManyToMany
	private Set<ProductFeature> productFeatureList;
	
	public String getDescription() {
		return description;
	}
	public Feature() {
		
	}
	public Feature(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	

	

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productFeatureList == null) ? 0 : productFeatureList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productFeatureList == null) {
			if (other.productFeatureList != null)
				return false;
		} else if (!productFeatureList.equals(other.productFeatureList))
			return false;
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
