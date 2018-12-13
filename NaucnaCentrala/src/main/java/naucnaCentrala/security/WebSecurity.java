package naucnaCentrala.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import naucnaCentrala.service.UserService;

import static naucnaCentrala.security.SecurityConstants.SIGN_UP_URL;
import static naucnaCentrala.security.SecurityConstants.LOGIN_URL;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//ovo metodom definisemo koji su resursi javni a koji su osigurani
	//SIGN-UP-URL smo postavili kao javno a sve ostalo osigurano
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		System.out.println("POZIV 2");
		
		//ove dve linije sam koristio da promenim url 'login' na 'users/login' za logovanje
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/users/login");
		
		
		
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			//.antMatchers(HttpMethod.POST, "/users/loginn").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(authenticationFilter)
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			// this disables session creation on Spring Security
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	//ova metoda nam omogucava da ukljucimo userService u sigurnosni okvir (valjda zbog metode loadUserByUsername)
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("POZIV 1");
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
	
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
		System.out.println("POZIV 3");
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	
	
	//Metoda za promenu putanju za logovanja, jer je podrazumevana '/login' pa je menjam na 'users/logi'
	/*@Bean
	public JWTAuthenticationFilter getJWTAuthenticationFilter() {
	    final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/users/login");
	    return filter;
	}
	*/
	
}
