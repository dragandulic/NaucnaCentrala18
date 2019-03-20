package naucnaCentrala.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long>{

	
	Magazine findByIdEquals(Long id);
	
	Magazine findByNameEquals(String n);
	
	@Query("select ct from Magazine ct where (ct.merchant_id) = (:id)")
	Magazine findByMerchantIdEquals(@Param("id")String id);
	
	//ArrayList<ScientificArea> findByScientificareaAll();
	
}
