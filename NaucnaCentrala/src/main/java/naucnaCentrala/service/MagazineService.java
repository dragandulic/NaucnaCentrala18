package naucnaCentrala.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import naucnaCentrala.config.TokenProvider;
import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.MembershipfeeRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class MagazineService {

	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private TokenProvider tokenProvider; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MembershipfeeRepository membershipfeeRepository;
	
	public List<MagazineDTO> listofMagazine(){
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		User u = userRepository.findByEmail(useremail);
		
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
				magazinedto.setUrldownload("http://localhost:8083/dbfile/downloadFile=" + magazines.get(i).getDbfile().getId());
				magazinedto.setUserrole(u.getRoles().get(0).getName());
				if(magazines.get(i).isMethodpayment()) {
					magazinedto.setType("openaccess");
				}
				else {
					magazinedto.setType("noopenaccess");
				}
				
				
				
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				MembershipFee membershipfee = membershipfeeRepository.findByMagazine_idEqualsAndUser_idEquals(magazines.get(i).getId(), u.getId());
				
				if(membershipfee != null) {
					Date now=null;
					try {
						now = formatter.parse(timeStamp);
						} catch (ParseException e) {
						  e.printStackTrace();
						}
					
					if(now.compareTo(membershipfee.getEnddate())<=0  && now.compareTo(membershipfee.getStartdate())>=0){
						magazinedto.setActivemembership("validmembershipf");
					}
					else {
						magazinedto.setActivemembership("novalidmembershipf");
					}
				}
				else {
					magazinedto.setActivemembership("novalidmembershipf");
				}
			
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
				/*if(loginuser.getMembershipfee() != null){
					if(datum.after(loginuser.getMembershipfee().getStartdate()) && datum.before(loginuser.getMembershipfee().getEnddate())) {
						return "membershipfeetrue"; //ima aktivnu clanarinu
					}
					else{
						return "membershipfeefalse"; //nema aktivnu clanarinu					
					}					
				}   */				
			}
			return "membershipfeeinvalid";
		}
		
		return "noopenaccess";
	}
	
	
}
