package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Long>{

	Reviewer findByEmailEquals(String s);
	
	Reviewer findByUsernameEquals(String s);
	
	Reviewer findByIdEquals(Long id);
}
