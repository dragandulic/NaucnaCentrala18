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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.MembershipfeeRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class LaborService {

	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private MembershipfeeRepository membershipfeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ArrayList<LaborDTO> getLabors(Long idm){
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		User u = userRepository.findByEmail(useremail);
		
		Magazine m = magazineRepository.findByIdEquals(idm);
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		boolean pom = false;
		
		if(m!=null && u!=null) {
			MembershipFee membershipfee = membershipfeeRepository.findByMagazine_idEqualsAndUser_idEquals(m.getId(), u.getId());

			if(membershipfee != null) {
				Date now=null;
				try {
					now = formatter.parse(timeStamp);
					} catch (ParseException e) {
					  e.printStackTrace();
					}
				
				if(now.compareTo(membershipfee.getEnddate())<=0  && now.compareTo(membershipfee.getStartdate())>=0){
					pom = true;
				}
				else {
					pom = false;
				}
			}
			else {
				pom = false;
			}
		}
		
		
		
		if(m != null) {
			ArrayList<Labor> res = laborRepository.findByMagazine_idEquals(idm);
			
			ArrayList<LaborDTO> retlist = new ArrayList<>();
			
			for(Labor l : res) {
				LaborDTO labordto = new LaborDTO();
				if(m.isMethodpayment()) {
					labordto.setMagazintype("openaccess");
				}
				else {
					labordto.setMagazintype("noopenaccess");
				}
				labordto.setTitle(l.getTitle());
				labordto.setPricelabor(l.getPricelabor());
				labordto.setUrldownload("http://localhost:8083/dbfile/downloadFile=" + l.getDbfile().getId());
				if(pom) {
					labordto.setActivemembership("validmembershipf");
				}
				else {
					labordto.setActivemembership("novalidmembershipf");
				}
				retlist.add(labordto);
			}
			
			
			return retlist;
		}
		return null;
	}
	
	
	
}
