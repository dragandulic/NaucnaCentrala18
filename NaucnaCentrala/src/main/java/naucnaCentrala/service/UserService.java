package naucnaCentrala.service;

import org.dom4j.dom.DOMNodeHelper.EmptyNodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.User;
import naucnaCentrala.repository.UserRepository;

import static java.util.Collections.emptyList;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String singup(User user) {
		
		if(user.getPassword().equals(user.getConfirmpassword())) {
			User use = userRepository.findByEmail(user.getEmail());
			
			if(use==null) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				User u = userRepository.save(user);
				return "Successful, please sign in";
			}
			else {
				return "Email exist";
			}
			
		}
		else {
			return "Password incorrect";
		}
		
	}

	
	//ova metoda se koristi prilikom logovanja. Ovaj metod primi koriscnicko ime, pretrazi bazu i vraca usera ako 
	// ga nadje.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		System.out.println("POZIV 8");
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
		
		
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}
	
}






