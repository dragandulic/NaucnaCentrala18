package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.PaymentObject;

@Repository
public interface PaymentObjectRepository extends JpaRepository<PaymentObject, Long>{

}
