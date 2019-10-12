package whole.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whole.model.dto.FilterDTO;
import whole.model.dto.ProductDTO;
import whole.model.entity.Category;
import whole.model.entity.Product;
import whole.model.entity.SubCategory;
import whole.model.repository.CategoryRepository;
import whole.model.repository.ProductRepository;
import whole.model.repository.SubCategoryRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	SubCategoryRepository subCategoryRepo;
	@Autowired
	EntityManager entityManager;
	
	
	//Checks if categoryName given, exists in the database.
	private boolean categoryExists(String categoryName) {
		Optional<Category> category = categoryRepo.findByName(categoryName);
		return category.isPresent();
	}
	
	//Checks if subCategoryName given, exists in the database.
	private boolean subCategoryExists(String subCategoryName) {
		Optional<SubCategory> subCategory = subCategoryRepo.findByName(subCategoryName);
		return subCategory.isPresent();
	}
	
	//Checks if productDto contains valid data, throws exception with all the errors found with productDto
	private void validateProductDTO(ProductDTO productDto) throws RuntimeException{
		String errorsFound = "";
		if(!categoryExists(productDto.getCategory())) {
			errorsFound += "Category provided: " + productDto.getCategory() + " does not exist. ";
		}
		
		for(String subCategoryName : productDto.getSubcategory()) {
			if(!subCategoryExists(subCategoryName)) {
				errorsFound += "Subcategory: " + subCategoryName + " does not exist. ";
			}
		}
		if(!errorsFound.isEmpty()) {
			throw new RuntimeException(errorsFound);
		}
	}
	
	private void productDTOtoProduct(ProductDTO productDto, Product product) {
		product.setName(productDto.getName());
		product.setDetails(productDto.getDetails());
		product.setPrice(productDto.getPrice());
		
		Category category = categoryRepo.findByName(productDto.getCategory()).orElseThrow(() -> new RuntimeException("Category: " + productDto.getCategory() + " does not exist. "));
		product.setCategory(category);
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		for(String subCategoryName : productDto.getSubcategory()) {
			SubCategory subCategory = subCategoryRepo.findByName(subCategoryName).orElseThrow(() -> new RuntimeException("Subcategory: " + subCategoryName + " does not exist. "));
			subCategories.add(subCategory);
		}
		product.setSubcategories(subCategories);
	}

	/* ------------------------------------------------   ADD PRODUCT FUNCTION   ------------------------------------------------- */
	//TODO if a request to add is made with a id, its ignored and new id is assigned.
	public ProductDTO addProduct(ProductDTO productDto) throws RuntimeException{
		//validateProductDTO(productDto);
		if(productDto.getId() != 0) {
			System.out.println("You provided an id for adding a product, did you mean to modify that product? Ignored the id, and added it anyways");
		}
		Product product = new Product();
		productDTOtoProduct(productDto,product);
		product = productRepo.save(product);
		ProductDTO updatedProductDto = new ProductDTO(product);
		return updatedProductDto;
	}
	
	
	/* -----------------------------------------------   MODIFY PRODUCT FUNCTION   ------------------------------------------------ */
	public ProductDTO modifyProduct(ProductDTO productDto) throws RuntimeException{
		Product product = productRepo.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("Given product id:" + productDto.getId() + " doesn't exist in database."));
		productDTOtoProduct(productDto, product);
		productRepo.save(product);
		return productDto;
	}
	
	
	/* ---------------------------------------------   GET PRODUCT BY ID FUNCTION   ---------------------------------------------- */
	public ProductDTO findById(long id) {
		Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("No product found with the id " + id));
		ProductDTO productDto = new ProductDTO(product);
		return productDto;
	}
	
	
	/* ------------------------------------------   GET PRODUCTS BY CATEGORY FUNCTION   ----------------------------------------- */
	public List<ProductDTO> getProductsByCategory(String categoryName){
		if(!categoryExists(categoryName)) {
			throw new RuntimeException("There is no category: '" + categoryName + "'. ");
		}
		List<Product> products = productRepo.findProductsByCategory(categoryName);
		List<ProductDTO> productDtos = new ArrayList<>();
		for(Product product : products) {
			ProductDTO productDto = new ProductDTO(product);
			productDtos.add(productDto);
		}
		return productDtos;
	}
	

	/* ----------------------------------------   GET PRODUCTS BY SEARCH STRING FUNCTION   ------------------------------------- */
	public List<ProductDTO> getProductsBySearchString(String searchString){
		List<Product> products = productRepo.findProductBySearchString(searchString);
		List<ProductDTO> productDtos = new ArrayList<>();
		for(Product product : products) {
			ProductDTO productDto = new ProductDTO(product);
			productDtos.add(productDto);
		}
		return productDtos;
	}
	
	/* --------------------------------------------   GET FILTERED PRODUCTS FUNCTION   ---------------------------------------- */
	public List<ProductDTO> getFilteredProducts(FilterDTO filter){  
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> result = cq.from(Product.class);
        
        List<Predicate> conditionsList = new ArrayList<>();
        
        if(filter.getMinPrice() != 0) {
        	conditionsList.add(cb.greaterThanOrEqualTo(result.get("price"), filter.getMinPrice()));
        }
        if(filter.getMaxPrice() != 0) {
        	conditionsList.add(cb.lessThanOrEqualTo(result.get("price"), filter.getMaxPrice()));
        }
        if(filter.getHasNameLike() != null && filter.getHasNameLike() != "") {
        	conditionsList.add(cb.like(result.get("name"), filter.getHasNameLike()));
        }
        
        cq.select(result).where(conditionsList.toArray(new Predicate[] {}));
        List<Product> productList = entityManager.createQuery(cq).getResultList();
        
        List<ProductDTO> productDtos = new ArrayList<>();
        for(Product product : productList) {
        	productDtos.add(new ProductDTO(product));
        }
        return productDtos;
	}
	
}
