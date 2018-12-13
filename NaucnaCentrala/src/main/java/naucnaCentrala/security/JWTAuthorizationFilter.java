package naucnaCentrala.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import static naucnaCentrala.security.SecurityConstants.HEADER_STRING;
import static naucnaCentrala.security.SecurityConstants.TOKEN_PREFIX;
import static naucnaCentrala.security.SecurityConstants.SECRET;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	
	
	//ova metoda se poziva pre svakog zahtev, valjda :D
	//valjda proverava da li je korisnik poslao JWT, nisam siguran za ovu metodu
	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain) throws IOException, ServletException{
		
		System.out.println("POZIV 4");
		String header = req.getHeader(HEADER_STRING);
		
		
		if(header==null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
		
		
		
	}
	
	
	
	
	//Ova metoda cita JWT iz authorization zaglavlja, a zatim koristi JWT za potvrdjivanje tokena. 
	//Ako je sve na mestu postavili smo korisnika u SecurityContext i dozvolili zahtevu da se nastavi dalje. 
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		
		System.out.println("POZIV 5");
		String token = req.getHeader(HEADER_STRING);
		
		
		if(token != null) {
			System.out.println("TOKEN U 5: " + token);
			//parse the token
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
						.build()
						.verify(token.replace(TOKEN_PREFIX, ""))
						.getSubject();
		
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		
		}
		
		return null;
		
		
		
	}																 
	
	
	
	
	
	
	
}
