package pl.sda.pms.order;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.json.JSONObject;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javassist.expr.NewArray;
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
	private Integer revision = 0;
	private Boolean noStandard;
	private String link;
	private String plOrder;

	private Status rs = Status.TO_DO;
	private Status documentation = Status.TO_DO;
	private Status qad = Status.TO_DO;
	private Status production = Status.TO_DO;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPlOrder() {
		return plOrder;
	}

	public void setPlOrder(String plOrder) {
		this.plOrder = plOrder;
	}

	public Boolean getNoStandard() {
		return this.noStandard;
	}

	public void setNoStandard(Boolean noStandard) {
		this.noStandard = noStandard;
	}

	@Column(length = 6000)
	private String orderFeaturesStrings = null;

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

	public Ord(Long id, String orderName, Double price, String client, Integer unitsToProduce, Integer revision,
			String orderFeaturesStrings, List<OrderFeature> orderFeatures, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.price = price;
		this.client = client;
		this.unitsToProduce = unitsToProduce;
		this.revision = revision;
		this.orderFeaturesStrings = orderFeaturesStrings;
		this.orderFeatures = orderFeatures;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
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
		// this.orderFeatures.clear();
		// orderFeatures.forEach(x->x.getOrd().remove(this));
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

	public String getOrderFeaturesStrings() {
		return orderFeaturesStrings;
	}

	public String getMapOfOrderFeaturesStrings() {

		return orderFeaturesStrings;
	}

	public void setOrderFeaturesStrings(String orderFeaturesStrings) {
		this.orderFeaturesStrings = orderFeaturesStrings;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public void revisionUp() {
		if (this.revision == null) {
			this.revision = 1;
		} else {
			this.revision++;
		}
	}

	public OrderFeature findByProductFeatureID(Long id) {
		return orderFeatures.stream().filter(x -> x.getProductFeature().getId().equals(id)).findFirst().get();

	}

	public Boolean hasStandardFeatures() {
		for (OrderFeature of : orderFeatures) {
			if (of.getFeature().getNoStandard() != null && of.getFeature().getNoStandard() == true) {
				return false;
			}

		}

		return true;
	}

	public OrderFeature findOrderFeatureByFeatyre(Feature oldFeature) {

		for (OrderFeature of : this.orderFeatures) {
			if (of.getFeature().equals(oldFeature)) {
				return of;
			}
		}

		return null;
	}

	public void setOrderFeaturesStringsMapByOrderFeatures(List<OrderFeature> orderList) {

		Map<String, String> featuresMap = orderList.stream().sorted(
				(o1, o2) -> o1.getProductFeature().getPosition().compareTo(o2.getProductFeature().getPosition()))
				.collect(Collectors.toMap(x -> x.getProductFeature().getName(), x -> x.getFeature().getName()));

		JSONObject jsonO = new JSONObject(featuresMap);
		this.orderFeaturesStrings = jsonO.toString();
	}

	public List<String> getPositions() {
		List<String> productFeatureNameList = this.orderFeatures.stream().map(x -> x.getProductFeature())
				.sorted((o1, o2) -> o1.getPosition().compareTo(o2.getPosition())).map(x -> x.getName())
				.collect(Collectors.toList());

		return productFeatureNameList;
	}

	public ProductFeature findProductFeatureByPfName(String productFeatureName) {

		for (OrderFeature oF : this.orderFeatures) {
			System.out.println(oF.getProductFeature().getName());
			try {
				if (oF.getProductFeature().getName().equals(productFeatureName)) {
					System.out.println(oF.getProductFeature().getName());
					return oF.getProductFeature();
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}

		}

		return null;
	}

	public void addAllOrderFeatures(List<OrderFeature> orderFeaturesToAdd) {
		if (this.orderFeatures == null) {
			List<OrderFeature> orderFeatures = new ArrayList<>();
			orderFeatures.addAll(orderFeaturesToAdd);

			this.orderFeatures = orderFeatures;
		} else {
			this.orderFeatures.addAll(orderFeaturesToAdd);
		}

	}

	public OrderFeature findOrderFeatureByPfName(String pFName) {
		for (OrderFeature oF : this.orderFeatures) {
			if (oF.getProductFeature().getName().equals(pFName)) {
				return oF;

			}

		}
		return null;

	}

	public Boolean isNoStandard() {
		return this.noStandard;
	}

	public Status getRs() {
		return this.rs;
	}

	public void setRs(Status rs) {
		this.rs = rs;
	}

	public Status getDocumentation() {
		return this.documentation;
	}

	public void setDocumentation(Status documentation) {
		this.documentation = documentation;
	}

	public Status getQad() {
		return this.qad;
	}

	public void setQad(Status qad) {
		this.qad = qad;
	}

	public Status getProduction() {
		return this.production;
	}

	public void setProduction(Status production) {
		this.production = production;
	}

	public Status chengStatusToNext(String task) {

		switch (task) {
		case "rs":
			if (this.rs == null) {
				this.rs=Status.TO_DO;
			}
			this.rs = this.rs.next();
			return this.rs;

		case "documentation":
			if (this.documentation == null) {
				this.documentation=Status.TO_DO;
			}
			this.documentation = this.documentation.next();
			return this.documentation;

		case "qad":
			if (this.qad == null) {
				this.qad=Status.TO_DO;
			}
			this.qad = this.qad.next();
			return this.qad;

		case "production":
			if (this.production == null) {
				this.production=Status.TO_DO;
			}
			this.production = this.production.next();
			return this.production;

		}
		return null;

	}

	public Status chengStatusToPrevious(String task) {

		switch (task) {
		case "rs":
			if (this.rs == null) {
				this.rs=Status.TO_DO;
			}
			this.rs = this.rs.previous();
			return this.rs;

		case "documentation":
			if (this.documentation == null) {
				this.documentation=Status.TO_DO;
			}
			this.documentation = this.documentation.previous();
			return this.documentation;

		case "qad":
			if (this.qad == null) {
				this.qad=Status.TO_DO;
			}
			this.qad = this.qad.previous();
			return this.qad;

		case "production":
			if (this.production == null) {
				this.production=Status.TO_DO;
			}
			this.production = this.production.previous();
			return this.production;

		}
		return null;
	}

}
