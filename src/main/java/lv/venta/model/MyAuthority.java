package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyAuthority")
@Entity

public class MyAuthority {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Ida")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ida;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z :;]{3,30}")
	@Column(name = "Title")
	private String title;
	
	public MyAuthority(String inputTitle) {
		
		setTitle(inputTitle);
	}

}
