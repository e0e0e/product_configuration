package pl.sda.pms.OrderFeature;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import pl.sda.pms.color.Color;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.order.Ord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
public class OrderFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;



	@ManyToMany
	@JsonIgnore
	private List<Ord> ord;

	@OneToOne
	private Feature feature;

	@OneToOne
	private ProductFeature productFeature;

	@OneToOne
	private Color color;


	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}




	
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

	

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof OrderFeature)) {
			return false;
		}
		OrderFeature orderFeature = (OrderFeature) o;
		return Objects.equals(id, orderFeature.id) && Objects.equals(ord, orderFeature.ord) && Objects.equals(feature, orderFeature.feature) && Objects.equals(productFeature, orderFeature.productFeature) && Objects.equals(color, orderFeature.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ord, feature, productFeature, color);
	}

}
