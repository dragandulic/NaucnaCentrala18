package naucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class MagazineService {

	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
	public List<Magazine> listofMagazine(){
		
		List<Magazine> magazines = magazineRepository.findAll();
		return magazines;
		
	}
	
}
