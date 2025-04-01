package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Product;
import lv.venta.service.IProductFilterAndCalcService;

@RestController
@RequestMapping("/product/process")
public class ProductFiltAndCalcController {
	
	@Autowired
	private IProductFilterAndCalcService prodService;
	
	@GetMapping("/price/{threshold}")//localhost:8080/product/process/price/2.77
	public ResponseEntity<?> getControllerProductPriceLessThan
	(@PathVariable(name ="threshold") float threshold)
	{
		try {
			ArrayList<Product> filteredProducts = prodService.getAllProductsWherePriceLessThan(threshold);
			ResponseEntity<ArrayList<Product>> response 
			= new ResponseEntity<ArrayList<Product>>(filteredProducts, HttpStatus.OK);
			return response;		
			
			
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		
		
		
		
	}
	

}
