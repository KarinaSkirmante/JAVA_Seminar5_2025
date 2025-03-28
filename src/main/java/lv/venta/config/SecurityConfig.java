package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsManager createInMemoryUsers() {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails ud1 = User.builder().username("karina").password(encoder.encode("qwerty123")).authorities("USER")
				.build();

		UserDetails ud2 = User.builder().username("admin").password(encoder.encode("1234")).authorities("ADMIN")
				.build();

		UserDetails ud3 = User.builder().username("peteris").password(encoder.encode("0987")).authorities("USER")
				.build();

		InMemoryUserDetailsManager imUserDetailsMan = new InMemoryUserDetailsManager(ud1, ud2, ud3);

		return imUserDetailsMan;

	}

	@Bean
	public SecurityFilterChain createConfigForEndpoints(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/simple").hasAuthority("USER")
				.requestMatchers("/getdata").hasAuthority("ADMIN")
				.requestMatchers("/getproduct").hasAuthority("ADMIN")
				.requestMatchers("/getmultipleproducts").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/all").permitAll()
				.requestMatchers("/product/crud/one**").permitAll()
				.requestMatchers("/product/crud/all/**").permitAll()
				.requestMatchers("/product/crud/create").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/update/**").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/product/process/price/**").hasAnyAuthority("ADMIN", "USER")

		);
		// TODO un parējie endpointi jeb adreses

		http.formLogin(auth -> auth.permitAll());

		return http.build();

	}

}
