package pl.sda.pms.productConfiguration;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.envers.Audited;

import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
public class ProductConfiguration{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy="productConfiguration", cascade = CascadeType.REMOVE)
	@OrderBy("position")
	private List<ProductFeature> configurationList;
	
	public List<ProductFeature> getConfigurationList() {
		return configurationList;
	}


	public void setConfigurationList(List<ProductFeature> configurationList) {
		this.configurationList = configurationList;
	}
	public void moveDownInConfigurationList(ProductFeature productFeature) {
		List<ProductFeature> configurationList =this.configurationList ;
		int indexOf=configurationList.indexOf(productFeature);
		Collections.swap(this.configurationList,indexOf,indexOf+1);
		this.configurationList= configurationList;
		
	}
	
	

	public ProductConfiguration() {
		super();
	}
	

	public ProductConfiguration(String name) {
		super();
		this.name = name;
	}


	public ProductConfiguration(String name, List<ProductFeature> configurationList) {
		super();
		this.name = name;
		this.configurationList = configurationList;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setConfigurationListByList(List<ProductFeature> feature) {
		
		LinkedList<ProductFeature> configurationSet=new LinkedList<ProductFeature>();
		configurationSet.addAll(feature);
		this.configurationList=configurationSet;
		
	}


	public void removeConfigurationList() {

		this.configurationList=null;
	}

	@Override
	public ProductConfiguration clone() {
		ProductConfiguration user = null;
	    try {
	        user = (ProductConfiguration) super.clone();
	    } catch (CloneNotSupportedException e) {
	        
	    }
	    
	    return user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ProductConfiguration other = (ProductConfiguration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public void removeFromConfigurationListByName(String productFeatureName) {
		ProductFeature productFeature=configurationList.stream().filter(x->x.getName().equals(productFeatureName)).findFirst().get();
		configurationList.remove(productFeature);

	}



	
	

	
	
}