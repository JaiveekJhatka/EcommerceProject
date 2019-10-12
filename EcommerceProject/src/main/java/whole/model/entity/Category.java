package whole.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	public Category() {
	}
	public Category(String name) {
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long categoryId) {
		this.id = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setCategoryName(String categoryName) {
		this.name = categoryName;
	}
	
	
}
