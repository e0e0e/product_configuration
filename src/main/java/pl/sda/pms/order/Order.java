package pl.sda.pms.order;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;

import pl.sda.pms.feature.Feature;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
public class Order {
	

@Id	
@GeneratedValue(strategy = GenerationType.AUTO)	
private Long id;
@Column(unique=true)
private String orderName;



@ColumnDefault("0.0")
private Double price;
private String client;
private Integer unitsToProduce;

@ManyToMany
private List<OrderFeature> orderFeatures;

public Order() {
	super();
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

public String getClient() {
	return client;
}

public void setClient(String client) {
	this.client = client;
}

public Integer getUnitsToProduce() {
	return unitsToProduce;
}

public void setUnitsToProduce(Integer unitsToProduce) {
	this.unitsToProduce = unitsToProduce;
}

public List<OrderFeature> getOrderFeatures() {
	return orderFeatures;
}

public void setOrderFeatures(List<OrderFeature> orderFeatures) {
	this.orderFeatures = orderFeatures;
};

public String getOrderName() {
	return orderName;
}

public void setOrderName(String orderName) {
	this.orderName = orderName;
}


}
