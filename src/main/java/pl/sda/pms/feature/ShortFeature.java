package pl.sda.pms.feature;


import pl.sda.pms.productConfiguration.ProductConfiguration;


public class ShortFeature {

	private Long id;

	private String name;
	private String imagePath;
	private String index;

	



	public boolean isIsSelected() {
		return this.isSelected;
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	boolean isSelected=false;

	private ProductConfiguration productConfiguration;

	public ProductConfiguration getProductConfiguration() {
		return this.productConfiguration;
	}

	public void setProductConfiguration(ProductConfiguration productConfiguration) {
		this.productConfiguration = productConfiguration;
	}

	

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
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

	public ShortFeature(Long id, String name, String imagePath, String index) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.index = index;
	}

	public ShortFeature(Long id, String name, String imagePath) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
	}

	public ShortFeature(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		ShortFeature other = (ShortFeature) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShortFeature [id=" + id + ", name=" + name + ", isSelected=" + isSelected + "]";
	}
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
