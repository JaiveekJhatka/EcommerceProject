package whole.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubCategory {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	
	public SubCategory() {
	}
	public SubCategory(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long subCategoryId) {
		this.id = subCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String subCategoryName) {
		this.name = subCategoryName;
	}
	
	
}
