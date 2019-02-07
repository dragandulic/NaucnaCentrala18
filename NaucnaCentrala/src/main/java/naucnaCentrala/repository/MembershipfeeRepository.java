package naucnaCentrala.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.MembershipFee;

@Repository
public interface MembershipfeeRepository extends JpaRepository<MembershipFee, Long>{

	
	MembershipFee findByMagazine_idEqualsAndUser_idEquals(Long idm, Long idu);
	
	ArrayList<MembershipFee> findByUser_idEquals(Long idu);
}
