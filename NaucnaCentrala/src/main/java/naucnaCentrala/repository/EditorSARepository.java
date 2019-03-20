package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.EditorSA;


@Repository
public interface EditorSARepository extends JpaRepository<naucnaCentrala.model.EditorSA, Long> {

	EditorSA findByEmailEquals(String s);
	
	EditorSA findByUsernameEquals(String s);
}
