package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService prodService;

	@GetMapping("/all") // localhost:8080/product/crud/all
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

	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public String getControllerGetOneProductById(@RequestParam(name = "id") long id, Model model) {
		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/product/crud/all/1
	public String getControllerGetOneProductById2(@PathVariable(name = "id") long id, Model model) {

		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}

}
