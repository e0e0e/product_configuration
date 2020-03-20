package pl.sda.pms.feature;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.sda.pms.OrderFeature.OrderFeature;
import pl.sda.pms.productFeature.ProductFeature;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// @Column(unique = true)
	private String name;
	private String description;
	private Double price;
	private String imagePath;
	private String index;
	private String mIndex;
	private Boolean noStandard;
	private String konfigurator;

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

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public String getKonfigurator() {
		return konfigurator;
	}

	public void setKonfigurator(String konfigurator) {
		this.konfigurator = konfigurator;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Boolean isNoStandard() {
		return this.noStandard;
	}

	public Boolean getNoStandard() {
		return this.noStandard;
	}

	public void setNoStandard(Boolean noStandard) {
		this.noStandard = noStandard;
	}


	public String getMIndex() {
		return this.mIndex;
	}

	public void setMIndex(String mIndex) {
		this.mIndex = mIndex;
	}



	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", price='" + getPrice() + "'" +
			", imagePath='" + getImagePath() + "'" +
			", index='" + getIndex() + "'" +
			", mIndex='" + getMIndex() + "'" +
			", noStandard='" + isNoStandard() + "'" +
			", createdBy='" + getCreatedBy() + "'" +
			", createdDate='" + getCreatedDate() + "'" +
			", lastModifiedBy='" + getLastModifiedBy() + "'" +
			", productFeatureList='" + getProductFeatureList() + "'" +
			", orderFeatures='" + getOrderFeatures() + "'" +
			"}";
	}




	@ManyToMany
	@JsonIgnore
	private Set<ProductFeature> productFeatureList;

	@OneToOne
	@JsonIgnore
	private OrderFeature orderFeatures;
	

	public OrderFeature getOrderFeatures() {
		return orderFeatures;
	}

	public void setOrderFeatures(OrderFeature orderFeatures) {
		this.orderFeatures = orderFeatures;
	}

	public String getDescription() {
		return description;
	}

	public Feature() {

	}

	


	public Feature(String name, String description, Double price, String imagePath, String index, String mIndex) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
		this.index = index;
		this.mIndex = mIndex;
	}



	public Feature(Long id, String name, String description, Double price, String imagePath, String index, String mIndex, Boolean noStandard, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, Set<ProductFeature> productFeatureList, OrderFeature orderFeatures) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
		this.index = index;
		this.mIndex = mIndex;
		this.noStandard = noStandard;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.productFeatureList = productFeatureList;
		this.orderFeatures = orderFeatures;
	}

	public Feature(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProductFeature> getProductFeatureList() {
		return productFeatureList;
	}

	public void setProductFeatureList(Set<ProductFeature> productFeatureList) {
		this.productFeatureList = productFeatureList;
	}

	public Feature(String name) {
		super();
		this.name = name;
	}

	public Feature(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productFeatureList == null) ? 0 : productFeatureList.hashCode());
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
		Feature other = (Feature) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productFeatureList == null) {
			if (other.productFeatureList != null)
				return false;
		} else if (!productFeatureList.equals(other.productFeatureList))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getmIndex() {
		return mIndex;
	}

	public void setmIndex(String mIndex) {
		this.mIndex = mIndex;
	}


	public Feature(String name, Boolean noStandard) {
		this.name = name;
		this.noStandard = noStandard;
	}

}
