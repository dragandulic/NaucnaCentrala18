package naucnaCentrala.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import naucnaCentrala.config.TokenProvider;
import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class MagazineService {

	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private TokenProvider tokenProvider; 
	
	@Autowired
	private UserRepository userRepository;
	
	public List<MagazineDTO> listofMagazine(){
		
		List<Magazine> magazines = magazineRepository.findAll();
		List<MagazineDTO> retlist = new ArrayList<>();
		if(magazines != null) {
			
			for(int i=0; i<magazines.size(); i++) {
				MagazineDTO magazinedto = new MagazineDTO();
				magazinedto.setId(magazines.get(i).getId());
				magazinedto.setIssnnumber(magazines.get(i).getIssnnumber());
				magazinedto.setName(magazines.get(i).getName());
				magazinedto.setChifeditor(magazines.get(i).getMaineditor().getName() + " " + magazines.get(i).getMaineditor().getSurname());
				magazinedto.setAmountmag(magazines.get(i).getAmountMag());
				retlist.add(magazinedto);
				
			}
			return retlist;
		}
		
		return null;
		
	}
	
	
	public String chechmembershipfee(Long id) {
		
		Magazine magazine = magazineRepository.findByIdEquals(id);
		
		if(magazine.isMethodpayment()) {
			String useremail = "";
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				useremail = ((UserDetails)principal).getUsername();
			} else {
				useremail = principal.toString();
			}
			
			User loginuser = userRepository.findByEmail(useremail);
			
			Date datum = new Date();
			if(loginuser != null) {
				if(loginuser.getMembershipfee() != null){
					if(datum.after(loginuser.getMembershipfee().getStartdate()) && datum.before(loginuser.getMembershipfee().getEnddate())) {
						return "membershipfeetrue"; //ima aktivnu clanarinu
					}
					else{
						return "membershipfeefalse"; //nema aktivnu clanarinu					
					}					
				}   				
			}
			return "membershipfeeinvalid";
		}
		
		return "noopenaccess";
	}
	
	
}
