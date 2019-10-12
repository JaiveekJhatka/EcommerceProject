package whole.model.dto;

import java.util.ArrayList;
import java.util.List;

import whole.model.entity.Product;


public class ProductDTO{
	private long id;
	private String name;
	private long price;
	private String details;
	private String category;
	private List<String> subcategory;
	
	
	/* ---------------------------------------------    CONSTRUCTORS    --------------------------------------------*/
	public ProductDTO() {
		id = 0;
		name = "Default name";
		price = 0;
		details = "Default details";
		category = "DefaultCategory";
		subcategory = new ArrayList<String>();
	}
	public ProductDTO(Product product) {
		this.id = product.getProductId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.details = product.getDetails();
		this.category = product.getCategory().getName();
		List<String> subcategories = new ArrayList<String>();
		for(int i=0; i<product.getSubcategories().size(); i++) {
			subcategories.add(product.getSubcategories().get(i).getName());
		}
		this.subcategory = subcategories;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(List<String> subcategory) {
		this.subcategory = subcategory;
	}
	
	
	
}
