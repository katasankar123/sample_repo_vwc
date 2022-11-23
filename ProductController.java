package com.sb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.exception.ResourceNotFoundException;
import com.sb.model.Product1;
import com.sb.repository.ProductRepository;
import com.sb.service.ProductService;

@RestController
@RequestMapping("/prapi")
public class ProductController {
	@Autowired
	ProductService ps;
	
	@Autowired
	ProductRepository pr;
	
	@PostMapping("/pcreate")
	public ResponseEntity<Product1> createProduct(@Valid @RequestBody Product1 pro){
		try{
			Product1 p6=ps.p1(pro);
			return new ResponseEntity<>(p6, HttpStatus.CREATED);
			}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	private int saveProduct(@RequestBody Product1 pro1){
//		ps.p1(pro1);
//		return pro1.getId();
//	}
	
	
	@GetMapping("/prod/{id}")
    public ResponseEntity < Product1 > getProductById
    (@PathVariable(value = "id") Integer productId)
    throws ResourceNotFoundException {
		Product1 pro = pr.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Your Entered Product Number is not available in Database,Could you please try with other Emp Number :: " + productId));
        return ResponseEntity.ok().body(pro);
        }
		/*Rq--2311 Start  changes modified by Sankar*/
		@PutMapping("/pupd")
private Product1 update(@RequestBody Product1 product){
	ps.p1(product);
	return product;
}
/*Rq--2311 end*/
@GetMapping("/pget")
public ResponseEntity<List<Product1>> getAllProducts(@RequestParam (required = false) String name) {
	try {
		List<Product1> prod2 = new ArrayList<Product1>();

		if (name == null)
			pr.findAll().forEach(prod2::add);
		else
			pr.findByNameContaining(name).forEach(prod2::add);

		if (prod2.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(prod2, HttpStatus.OK);
	}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//private List<Product1> p2(){
//	return ps.p2();
//}
@PutMapping("/pupd")
private Product1 update(@RequestBody Product1 product){
	ps.p1(product);
	return product;
}

@DeleteMapping("/pdel/{id}")
public void deleteProd(@PathVariable("id") int id){
	ps.p4(id);
}

}
