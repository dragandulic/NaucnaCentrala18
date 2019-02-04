package naucnaCentrala.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Transaction findByIdEquals(Long id);
	
	ArrayList<Transaction> findByPayermailEquals(String s);
}
