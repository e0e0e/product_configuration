package pl.sda.pms.order;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
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

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
public class Ord {
	

@Id	
@GeneratedValue(strategy = GenerationType.AUTO)	
private Long id;

private String orderName;

private Double price;
private String client;
private Integer unitsToProduce;


@ManyToMany(mappedBy = "ord")
private List<OrderFeature> orderFeatures;

public Ord(List<OrderFeature> orderFeatures) {
	super();
	this.orderFeatures = orderFeatures;
}



public List<OrderFeature> getOrderFeatures() {
	return orderFeatures;
}



public void setOrderFeatures(List<OrderFeature> orderFeatures) {
	this.orderFeatures = orderFeatures;
}



public Ord() {
	super();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getOrderName() {
	return orderName;
}
public void setOrderName(String orderName) {
	this.orderName = orderName;
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
public void removeOrderFeatures(OrderFeature orderFeature) {
    this.orderFeatures.remove(orderFeature);
    orderFeature.getOrd().remove(this);
}

public void removeAllOrderFeatures() {
//    this.orderFeatures.clear();
//    orderFeatures.forEach(x->x.getOrd().remove(this));
//    
    List<OrderFeature> orderFeatures=this.getOrderFeatures();
	orderFeatures.forEach(x->x.getOrd().remove(this));
}



@Override
public String toString() {
	return "Ord [id=" + id + ", orderName=" + orderName + ", price=" + price + ", client=" + client
			+ ", unitsToProduce=" + unitsToProduce + ", orderFeatures=" + orderFeatures + "]";
}

}
