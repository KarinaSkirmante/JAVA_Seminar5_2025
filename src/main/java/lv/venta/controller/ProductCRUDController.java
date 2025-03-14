package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService prodService;
	
	
	@GetMapping("/all") //localhost:8080/product/crud/all
	public String getControllerGetAllProducts(Model model) {
		
		try {
			ArrayList<Product> allProducts = prodService.retrieveAll();
			model.addAttribute("package", allProducts);
			return "show-multiple-products";
			
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	//kas ir iegūti no servisa
	//parādīt html lapu (izmantojam jau eksistējošō show-multiple-products.htmlt)
	//nostestēt kontrolieri
}
