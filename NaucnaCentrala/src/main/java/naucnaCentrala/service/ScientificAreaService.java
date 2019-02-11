package naucnaCentrala.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class ScientificAreaService {

	@Autowired
	private MagazineRepository magazineRepository;
	
	public ArrayList<ScientificArea> getscientificarea(Long idm){
		
		Magazine m = magazineRepository.findByIdEquals(idm);
		
		if(m != null) {
			ArrayList<ScientificArea> res = m.getScientificarea();
			
			return res;
		}
		
		return null;
	}
	
}
