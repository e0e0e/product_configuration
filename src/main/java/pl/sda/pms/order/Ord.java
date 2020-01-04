package pl.sda.pms.order;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.feature.Feature;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Ord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String orderName;

	private Double price;
	private String client;
	private Integer unitsToProduce;
	private Integer revision;

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public void revisionUp() {
		this.revision++;
	}

	@ManyToMany(mappedBy = "ord")
	private List<OrderFeature> orderFeatures;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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
		List<OrderFeature> orderFeatures = this.getOrderFeatures();
		orderFeatures.forEach(x -> x.getOrd().remove(this));
	}

	@Override
	public String toString() {
		return "Ord [id=" + id + ", orderName=" + orderName + ", price=" + price + ", client=" + client
				+ ", unitsToProduce=" + unitsToProduce + ", orderFeatures=" + orderFeatures + "]";
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
