package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ProductTable")
@Entity
public class Product {
	//1. mainīgie
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Price")
	private float price;
	
	@Column(name = "Quantity")
	private int quantity;
	//2. getters - no lombok
	//3. setters - no lombok
	//4. bez argumenta konstruktors - no lombok
	//5. argumenta konstruktors
	public Product(String inputTitle, String inputDescription, float inputPrice, int inputQuantity) {
		setTitle(inputTitle);
		setDescription(inputDescription);
		setPrice(inputPrice);
		setQuantity(inputQuantity);
	}
	//6. toString funkcija - no lombok
	//7. citas funkcijas (ja nepieciešams)
	
}
