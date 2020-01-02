package pl.sda.pms.OrderFeature;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.order.Ord;
//import pl.sda.pms.order.Order;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
public class OrderFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;



	@ManyToMany
	private List<Ord> ord;

	@OneToOne
	private Feature feature;

	@OneToOne
	private ProductFeature productFeature;

	
	public OrderFeature() {

	}

	public OrderFeature(ProductFeature productFeature, Feature feature) {
		super();
		this.feature = feature;
		this.productFeature = productFeature;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Feature getFeature() {
		return feature;
	}

	public List<Ord> getOrd() {
		return ord;
	}

	public void setOrd(List<Ord> ord) {
		this.ord = ord;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public ProductFeature getProductFeature() {
		return productFeature;
	}

	public void setProductFeature(ProductFeature productFeature) {
		this.productFeature = productFeature;
	}

}
