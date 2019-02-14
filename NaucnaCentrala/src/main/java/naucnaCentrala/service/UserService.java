package naucnaCentrala.service;

import org.dom4j.dom.DOMNodeHelper.EmptyNodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.PurchasedPropsDTO;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.UserRepository;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
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
			EditorReviewer er = editorReviewerRepository.findByEmail(email);
			if(er == null) {
				throw new UsernameNotFoundException(email);
			}
			return new org.springframework.security.core.userdetails.User(er.getEmail(), er.getPassword(), getAuthorityed(er));
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
		
		
	}
	
	//prvili smo ovde dve iste metode jer su nam editor ireviewre u jednoj klasi a user i author u drugoj
	private Set<SimpleGrantedAuthority> getAuthority(User user){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getAuthorityed(EditorReviewer user){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}
	
	public ArrayList<PurchasedPropsDTO> purchasedprops() {
		
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		User u = userRepository.findByEmail(useremail);
		
		ArrayList<PurchasedPropsDTO> res = new ArrayList<>();
		boolean pom = false;
		for(Labor l : u.getPurchasedlabors()) {
			PurchasedPropsDTO p = new PurchasedPropsDTO();
			p.setType("Labor");
			p.setName(l.getTitle());
			p.setDownloadurl("http://localhost:8038/dbfile/downloadFile=" + l.getDbfile().getId());
			res.add(p);
			pom=true;
		}
		
		for(Magazine m : u.getPurchasedmagazins()) {
			PurchasedPropsDTO p = new PurchasedPropsDTO();
			p.setType("Magazine");
			p.setName(m.getName());
			p.setDownloadurl("http://localhost:8038/dbfile/downloadFile=" + m.getDbfile().getId());
			res.add(p);
			pom=true;
		}
		
		if(pom) {
			return res;
		}
		return null;
	}
	
}






