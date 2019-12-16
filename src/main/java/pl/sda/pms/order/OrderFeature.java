//package pl.sda.pms.order;
//
//import java.util.List;
//
//import javax.management.loading.PrivateClassLoader;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import pl.sda.pms.feature.Feature;
//import pl.sda.pms.productFeature.ProductFeature;
//
//@Entity
//public class OrderFeature{
//
//@Id	
//@GeneratedValue(strategy = GenerationType.AUTO)
//private Long id;
//
//
///OneToMany
//private Feature feature;
//
////@OneToMany
//private ProductFeature productFeature;
//
//public OrderFeature() {
//	super();
//}
//
//public Long getId() {
//	return id;
//}
//
//public void setId(Long id) {
//	this.id = id;
//}
//
//public Feature getFeature() {
//	return feature;
//}
//
//public void setFeature(Feature feature) {
//	this.feature = feature;
//}
//
//public ProductFeature getProductFeature() {
//	return productFeature;
//}
//
//public void setProductFeature(ProductFeature productFeature) {
//	this.productFeature = productFeature;
//}
//
//
//
//	
//}
