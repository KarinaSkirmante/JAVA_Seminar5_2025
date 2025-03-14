package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;


@Service
public class ProductCRUDServiceImpl implements IProductCRUDService{

	@Autowired
	private IProductRepo prodRepo;
		
	
	@Override
	public void createProduct(String title, String description, float price, int quantity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(prodRepo.count() == 0)
		{
			throw new Exception("Product table is empty");
		}
		
		ArrayList<Product> result = (ArrayList<Product>) prodRepo.findAll();
		return result;
		
	}

	@Override
	public Product retrieveById(long id) throws Exception {
		if(id <= 0)
		{
			throw new Exception("Id can not be negative");
		}
		
		if(!prodRepo.existsById(id))
		{
			throw new Exception("Product with id " + id + " doesn't exist");
		}
		
		Product retreivedproduct = prodRepo.findById(id).get();
		return retreivedproduct;
	}

	@Override
	public void updateProductById(long id, String description, float price, int quantity) throws Exception {
		Product productForUpdating = retrieveById(id);
		
		if(description == null || !description.matches("[A-Za-z :;]{3,30}")
				|| price < 0 || price > 1000
				|| quantity < 0 || quantity > 100)
		{
			throw new Exception("Incorrect input params");
		}
		
		if(!productForUpdating.getDescription().equals(description)) {
			productForUpdating.setDescription(description);
		}
		
		
		if(productForUpdating.getPrice() != price) {
			productForUpdating.setPrice(price);
		}
		
		if(productForUpdating.getQuantity() != quantity) {
			productForUpdating.setQuantity(quantity);
		}
				
		prodRepo.save(productForUpdating);//te nostrādās kā UPDATE SQL vaicājums
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
