package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@RestController
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService prodService;

	@GetMapping("/all") // localhost:8080/product/crud/all
	public ResponseEntity<?> getControllerGetAllProducts() {

		try {
			ArrayList<Product> allProducts = prodService.retrieveAll();
			ResponseEntity<ArrayList<Product>> response = 
			new ResponseEntity<ArrayList<Product>>(allProducts, HttpStatus.OK);//atgriezīs 200 kodu + visus produkttus
			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
			
		}
	}

	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public ResponseEntity<?> getControllerGetOneProductById(@RequestParam(name = "id") long id) {
		try {
			Product productFound = prodService.retrieveById(id);

			ResponseEntity<Product> response 
			= new ResponseEntity<Product>(productFound, HttpStatus.OK);
			return response;
			
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/product/crud/all/1
	public ResponseEntity<?> getControllerGetOneProductById2(@PathVariable(name = "id") long id) {

		try {
			Product productFound = prodService.retrieveById(id);

			ResponseEntity<Product> response 
			= new ResponseEntity<Product>(productFound, HttpStatus.OK);
			return response;
			
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> postControllerCreateNewProduct(@RequestBody @Valid Product product, BindingResult result) {//tiek iegūsts jau aizpildītais produkts
		
		if(result.hasErrors()) {
			ResponseEntity response = new ResponseEntity<>(result.getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		
		try {
			prodService.createProduct(product.getTitle(), product.getDescription(),
					product.getPrice(), product.getQuantity());
			
			ArrayList<Product> allProducts = prodService.retrieveAll();
			ResponseEntity<ArrayList<Product>> response = 
			new ResponseEntity<ArrayList<Product>>(allProducts, HttpStatus.OK);//atgriezīs 200 kodu + visus produkttus
			return response;
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		
		
		
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> postControllerUpdateProductById
	(@PathVariable(name = "id") long id, @RequestBody @Valid Product product, BindingResult result)
	{
		if(result.hasErrors())
		{
			ResponseEntity response = new ResponseEntity<>(result.getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
			
		}
		
		
		try {
			prodService.updateProductById(id, product.getDescription(), product.getPrice(), product.getQuantity());
			Product productFromDB = prodService.retrieveById(id);
			ResponseEntity<Product> response
			= new ResponseEntity<Product>(productFromDB, HttpStatus.OK);
			
			return response;
			
			
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	//izveiudot get mapping prieks dzesanas, kur tiek padots arī id
	//mēģināt dzēst, bet ja ir kļudas, parādīt show-error lapu
	
	@GetMapping("/delete/{id}")//localhost:8080/product/crud/delete/3
	public String getControllerDeleteProductById(@PathVariable(name = "id") long id, Model model)
	{
		try {
			prodService.deleteById(id);
			model.addAttribute("package", prodService.retrieveAll());
			return "show-multiple-products";
			
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
		
	}
	
	
	
	

}
