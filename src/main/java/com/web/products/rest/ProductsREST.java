package com.web.products.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.products.dao.ProductsDAO;
import com.web.products.entitys.Product;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductsREST {
    
	@Autowired
	private ProductsDAO productDAO;
	
   	@GetMapping  //returns a list of products
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value="{productId}") //returns a product by ID 
	public ResponseEntity<Product> getProductById(@PathVariable("productId")Long productId){
		Optional<Product> optionalProduct = productDAO.findById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
           
           return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping     //Insert a new product
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value="{productId}")  // Delete a product
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productoId){
		productDAO.deleteById(productoId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping   //Rewrite a product
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		}else {
           return ResponseEntity.noContent().build();
		}
	}
	
}
