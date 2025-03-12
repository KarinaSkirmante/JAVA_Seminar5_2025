package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class MyFirstController {
	
	private Random rand = new Random(); 
	@GetMapping("/simple") //localhost:8080/simple
	public String myFirstGetController() {
		System.out.println("Pirmais kontrolieris nostradaja");
		return "simple-page"; //parādīt simple-page.html lapu
	}
	
	@GetMapping("/getdata")//localhost:8080/getdata
	public String getControllerSendData(Model model)
	{
		System.out.println("Send data kontrolieris nostrādāja");
		String data = "Karina: " + rand.nextInt(0, 101);
		model.addAttribute("package", data);
		return "show-data-page";//parādīs show-data-page.html failu, kurā būs jau ievietots data vērtība
		
	}
	@GetMapping("/getproduct")//localhost:8080/getproduct
	public String getControllerSendProduct(Model model) {
		Product newProduct = new Product("Ābols", "Garšīgs", 0.99f, 4);
		model.addAttribute("package", newProduct);
		return "show-one-product";//parādīs show-one-product.html lapu, kurā būs iekļauts produkts package paciņā
		
	}
	
	@GetMapping("/getmultipleproducts")//localhost:8080/getmultipleproducts
	public String getControllerSendMultipleProducts(Model model) {
		
		ArrayList<Product>newProducts = new ArrayList<>(Arrays.asList(
				new Product("Ābols", "Garšīgs", 0.99f, 4),
				new Product("Gurķis", "Zaļš", 1.99f, 10),
				new Product("Tomāts", "Sarkans", 3.49f, 100)
				
				));
		
		model.addAttribute("package", newProducts);
		return "show-multiple-products";//tiks parādīta show-multiple-products.html lapa ar padoto produktu sarakstu
		
		
	}
	
	

}
