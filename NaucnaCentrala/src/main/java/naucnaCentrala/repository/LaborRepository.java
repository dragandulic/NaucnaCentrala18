package naucnaCentrala.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Labor;

@Repository
public interface LaborRepository extends JpaRepository<Labor, Long> {

	
	ArrayList<Labor> findByMagazine_idEquals(Long idm);
	
	Labor findByIdEquals(Long id);
	
}
