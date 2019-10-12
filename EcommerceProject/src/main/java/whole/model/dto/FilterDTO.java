package whole.model.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterDTO {
	private long minPrice;
	private long maxPrice;
	private String hasNameLike;
	private List<String> hasSubCategories;
	
	public FilterDTO() {
		minPrice = 0;
		maxPrice = 0;
		hasNameLike = "";
		hasSubCategories = new ArrayList<String>();
	}
	
	public long getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(long minPrice) {
		this.minPrice = minPrice;
	}
	public long getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(long maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getHasNameLike() {
		return hasNameLike;
	}
	public void setHasNameLike(String hasNameLike) {
		this.hasNameLike = hasNameLike;
	}
	public List<String> getHasSubCategories() {
		return hasSubCategories;
	}
	public void setHasSubCategories(List<String> hasSubCategories) {
		this.hasSubCategories = hasSubCategories;
	}
	
	
	
}
