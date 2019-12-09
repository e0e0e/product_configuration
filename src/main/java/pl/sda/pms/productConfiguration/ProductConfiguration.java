package pl.sda.pms.productConfiguration;

import java.util.List;

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
public class ProductConfiguration{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;
	@ManyToMany
	private List<ProductFeature>Configuration;
	
	public ProductConfiguration() {
		super();
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

	public List<ProductFeature> getConfiguration() {
		return Configuration;
	}

	public void setConfiguration(List<ProductFeature> configuration) {
		Configuration = configuration;
	}
	
	

	
	
}