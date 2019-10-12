package whole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import whole.model.dto.ResultResponse;
import whole.model.dto.SignupRequest;
import whole.model.dto.SignupResponse;
import whole.model.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/products/addCategory", method = RequestMethod.POST)
	public ResponseEntity<Object> addCategory(@RequestBody String category) {
		try{
			return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/products/addSubCategory", method = RequestMethod.POST)
	public ResponseEntity<Object> addSubCategory(@RequestBody String subCategory) {
		try{
			return new ResponseEntity<>(categoryService.addSubCategory(subCategory), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ResultResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
