package pl.sda.pms.productFeature;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.productConfiguration.ProductConfiguration;
import pl.sda.pms.users.User;

@Entity
@Audited
public class ProductFeature {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String imagePath;
	private Integer position; 
	

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public void autoPosition() {
		this.position = this.productConfiguration.getConfigurationList().size()+1;
	}
	@ManyToMany
    private Set<Feature> feature;
	

	@ManyToOne
	private ProductConfiguration productConfiguration;
	
	@OneToOne
	private OrderFeature orderFeature;
	
	
	public OrderFeature getOrderFeature() {
		return orderFeature;
	}
	public void setOrderFeature(OrderFeature orderFeature) {
		this.orderFeature = orderFeature;
	}
	@Override
	public String toString() {
		return "ProductFeature [name=" + name + ", feature=" + feature + ", productConfiguration="
				+ productConfiguration + "]";
	}
	public ProductConfiguration getProductConfiguration() {
		return productConfiguration;
	}

	public void setProductConfiguration(ProductConfiguration productConfiguration) {
		this.productConfiguration = productConfiguration;
	}

	public ProductFeature() {
		
	}
	public ProductFeature(Long id, String name, String description, String imagePath, Integer position,
			Set<Feature> feature, ProductConfiguration productConfiguration, OrderFeature orderFeature) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.position = position;
		this.feature = feature;
		this.productConfiguration = productConfiguration;
		this.orderFeature = orderFeature;
	}
	public Set<Feature> getFeature() {
		return feature;
	}

	public void setFeature(Set<Feature> feature) {
		this.feature = feature;
	}

	public ProductFeature(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public ProductFeature(String name, String description, String imagePath, Set<Feature> feature) {
		this.name = name;
		this.description = description;
		this.imagePath=imagePath;
		this.feature=feature;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProductFeature other = (ProductFeature) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
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
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void removeProductConfiguration() {
		//ProductConfiguration productConfiguration=this.productConfiguration;
		this.productConfiguration=null;
//		productFeatureSet.remove(this);
	}
	
	
	
}