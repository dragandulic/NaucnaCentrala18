package naucnaCentrala.elasticSearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LaborESRepository extends ElasticsearchRepository<LaborES, String>{

	
	List<LaborES> findByLaborname(String s);

	List<LaborES> findByMagazinename(String s);
	
	List<LaborES> findByScientificarea(String s);
	
	List<LaborES> findByAuthorContaining(String s,String s1);
	
	List<LaborES> findByAuthorContaining(String s);
	
	List<LaborES> findLaborESByKeytermsIn(String s);
	
	List<LaborES> findByKeytermsKeyterm(String name);
	
	List<LaborES> findByKeytermsKeytermAndKeytermsKeyterm(String name,String name1);
	
	List<LaborES> findByKeytermsKeytermAndKeytermsKeytermAndKeytermsKeyterm(String name,String name1,String s2);
}
