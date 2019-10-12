package whole.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whole.model.entity.Category;
import whole.model.entity.SubCategory;
import whole.model.repository.CategoryRepository;
import whole.model.repository.SubCategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	SubCategoryRepository subcategoryRepo;
	
	public Category addCategory(String category) throws RuntimeException{
		if(categoryRepo.findByName(category).isPresent()) {
			throw new RuntimeException("Category: " + category + " already exists in database.");
		}else {
			Category newCategory = new Category(category);
			return(categoryRepo.save(newCategory));
		}
	}
	
	public SubCategory addSubCategory(String subCategory) throws RuntimeException{
		if(subcategoryRepo.findByName(subCategory).isPresent()) {
			throw new RuntimeException("SubCategory: " + subCategory + " already exists in database.");
		}else {
			SubCategory newSubCategory = new SubCategory(subCategory);
			return(subcategoryRepo.save(newSubCategory));
		}
	}
}
