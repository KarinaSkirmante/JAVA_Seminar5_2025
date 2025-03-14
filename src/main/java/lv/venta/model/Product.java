package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z ]{2,15}")
	//@Size(min = 3, max = 16) <- so var izlaist, jo regex izteiksmē tas jau ir ietverts
	private String title;
	
	@Column(name = "Description")
	@NotNull
	@Pattern(regexp = "[A-Za-z :;]{3,30}")
	private String description;
	
	@Column(name = "Price")
	@Min(0)
	@Max(1000)
	private float price;
	
	@Column(name = "Quantity")
	@Min(0)
	@Max(100)
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
