package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;
// Product - uz kuru modeļu klasi/tabuli sis repo strādās, 
//Long - Product klasē primary key ir ka long datu tips (šeit jāizmanto referenču datu tips)
public interface IProductRepo extends CrudRepository<Product, Long>{

}
