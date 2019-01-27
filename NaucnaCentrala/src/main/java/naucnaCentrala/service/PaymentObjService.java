package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.PaymentMembershipFDTOO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObj;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.PaymentObjRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class PaymentObjService {

	@Autowired
	private PaymentObjRepository paymentObjRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	public Long createPaymentObjectMembershipfee(Long idm) {
		
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		User loginuser = userRepository.findByEmail(useremail);
		
		if(loginuser!=null &&magazine !=null) {
			PaymentObj paymentobj = new PaymentObj();
			
			paymentobj.setSeller(magazine);
			paymentobj.setCustomer(loginuser);	
			paymentobj.setAmount(1);
			paymentobj.setTitle("Payment of membership fee");
			PaymentObj po =  paymentObjRepository.save(paymentobj);
			return po.getId();
		}

		return null;
	}
	
	
	public PaymentMembershipFDTOO getPayment(Long id) {
		
		
		PaymentObj po = paymentObjRepository.findByIdEquals(id);
		
		if(po !=null) {
			PaymentMembershipFDTOO obj = new PaymentMembershipFDTOO();
			
			obj.setIdPaymentObj(po.getId());
			obj.setIdCustomer(po.getCustomer().getId());
			obj.setNameCustomer(po.getCustomer().getName());
			obj.setSurnameCustomer(po.getCustomer().getSurname());
			obj.setEmailCustomer(po.getCustomer().getEmail());
			obj.setIdSeller(po.getSeller().getId());
			obj.setNameSeller(po.getSeller().getName());
			obj.setIssnumberSeller(po.getSeller().getIssnnumber());
			obj.setAmount(po.getAmount());
			obj.setMerchant_id(po.getSeller().getMerchant_id());
			obj.setMerchant_password(po.getSeller().getMerchant_password());
			obj.setTitle(po.getTitle());
			return obj;
		}
		else {
			return null;
		}
		
		
	}
	
}
