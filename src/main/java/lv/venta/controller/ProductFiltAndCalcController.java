package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductFilterAndCalcService;

@Controller
@RequestMapping("/product/process")
public class ProductFiltAndCalcController {
	
	@Autowired
	private IProductFilterAndCalcService prodService;
	
	@GetMapping("/price/{threshold}")//localhost:8080/product/process/price/2.77
	public String getControllerProductPriceLessThan
	(@PathVariable(name ="threshold") float threshold, Model model)
	{
		try {
			ArrayList<Product> filteredProducts = prodService.getAllProductsWherePriceLessThan(threshold);
			model.addAttribute("package", filteredProducts);
			return "show-multiple-products";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
		
		
		
		
		
	}
	

}
