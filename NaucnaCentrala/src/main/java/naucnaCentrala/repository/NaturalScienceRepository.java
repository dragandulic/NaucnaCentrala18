package naucnaCentrala.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.ScientificArea;

@Repository
public interface NaturalScienceRepository extends JpaRepository<ScientificArea, Long>{

}
