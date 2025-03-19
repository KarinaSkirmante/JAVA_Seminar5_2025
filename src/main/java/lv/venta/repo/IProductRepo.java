package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;
// Product - uz kuru modeļu klasi/tabuli sis repo strādās, 
//Long - Product klasē primary key ir ka long datu tips (šeit jāizmanto referenču datu tips)
public interface IProductRepo extends CrudRepository<Product, Long>{

	//tiks izveidots vaicajums
	//SELECT * FROM product_table WHERE title = ?1 and description=?2 and price =?3
	//?1 - pirmais padotais parametrs funcijai
	//?2 - otrais padotais parametrs funcijai
	//?3 - trešais padotais parametrs funcijai
	public abstract Product 
	findByTitleAndDescriptionAndPrice(String title, String description, float price);

	public abstract boolean existsByTitleAndDescriptionAndPrice(String title, String description, float price);

	
	//tiks izveidots vaicājums
	//SELECT * FROM product_table WHERE price < ?1;
	//?1 - pirmais padotais parametrs funcijai
	public abstract ArrayList<Product> findByPriceLessThanEqual(float threshold);
	//tiks izveidots vaicājums
	//SELECT * FROM product_table WHERE quantity > ?1;
	//?1 - pirmais padotais parametrs funcijai
	public abstract ArrayList<Product> findByQuantityGreaterThanEqual(int threshold);

	
	//tiks izveidots vaicājums
	//SELECT * FROM product_table WHERE title LIKE '%?1%' OR description LIKE '%G?2%';
	//?1 - pirmais padotais parametrs funcijai
	//?2 - otrais padotais parametrs funcijai
	public abstract ArrayList<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String text,
			String text2);
	
	@Query(nativeQuery = true, value = "SELECT sum(price * quantity) FROM product_table;")
	public abstract float calculateSumOfProductValues();
	
	

}
