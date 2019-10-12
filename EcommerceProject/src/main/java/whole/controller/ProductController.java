package whole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.FilterDTO;
import whole.model.dto.ProductDTO;
import whole.model.dto.ResultResponse;
import whole.model.service.ProductService;


@RestController
public class ProductController {
	@Autowired
	ProductService productServices;
	
	@RequestMapping(value = "/products/addProduct", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody String productDtoJson) {
		try {
			ObjectMapper objMapper = new ObjectMapper();
			ProductDTO productDto = objMapper.readValue(productDtoJson, ProductDTO.class);
			return new ResponseEntity<>(productServices.addProduct(productDto), HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/products/update", method = RequestMethod.POST)
	public ResponseEntity<Object> modifyProduct(@RequestBody String modifyProductDtoJson) {
		try {
			ObjectMapper objMapper = new ObjectMapper();
			ProductDTO productDto = objMapper.readValue(modifyProductDtoJson, ProductDTO.class);
			return new ResponseEntity<>(productServices.modifyProduct(productDto), HttpStatus.ACCEPTED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/products/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value="id") String productIdStr) {
		long productId=0;
		try {
			productId = Long.parseLong(productIdStr);
			return new ResponseEntity<>(productServices.findById(productId), HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/products/{Category}")
	public ResponseEntity<Object> getProductsByCategory(@PathVariable(value="Category") String categoryName) {
		try {
			return new ResponseEntity<>(productServices.getProductsByCategory(categoryName), HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/products/search/{searchString}")
	public ResponseEntity<Object> getProductsBySearchString(@PathVariable(value="searchString") String searchString){
		try {
			return new ResponseEntity<>(productServices.getProductsBySearchString(searchString), HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/products/{category}/getFilteredProducts", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredProducts(@RequestBody String filterDtoJson) {
		try {
			ObjectMapper objMapper = new ObjectMapper();
			FilterDTO filterDto = objMapper.readValue(filterDtoJson, FilterDTO.class);
			return new ResponseEntity<>(productServices.getFilteredProducts(filterDto), HttpStatus.ACCEPTED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(new ResultResponse("FAILED! " + e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
}
