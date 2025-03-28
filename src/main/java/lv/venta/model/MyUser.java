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
@Table(name = "MyUserTable")
@Entity
public class MyUser {

	@Setter(value = AccessLevel.NONE) 
	@Column(name = "IDu") 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;
	
	@NotNull
	@Pattern(regexp =  "[A-Za-z.0-9]")
	@Column(name = "Username")
	private String username;
	
	@NotNull
	@Pattern(regexp =  "[A-Za-z0-9,./_-!@#$%^&*()]{2,100}")
	@Column(name = "Password")
	private String password;
	
	public MyUser(String username, String passord) {
		setUsername(username);
		setPassword(passord);
	}
}
