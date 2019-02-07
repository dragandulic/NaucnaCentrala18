package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
	//User findByIdEqualsAndMembershipfee_idEquals(Long idu, Long idm);
}
