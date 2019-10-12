package whole.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;




@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String name;
	private long price;
	private String details;
	@JoinColumn(updatable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	@JoinColumn(insertable = false)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<SubCategory> subcategories;
	
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public Product() {
		category = new Category();
		subcategories = new ArrayList<SubCategory>();
	}

	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<SubCategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", details=" + details
				+ ", category=" + category + ", subcategories=" + subcategories + "]";
	}
	
	
}
