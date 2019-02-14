package naucnaCentrala.elasticSearch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.ScientificAreaRepository;
import naucnaCentrala.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;




import static org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.apache.lucene.search.join.ScoreMode;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Service
public class LaborESService {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	
	@Autowired
	private LaborESRepository laborESRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public String saveLaborES(LaborESDTO labor) {
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		User u = userRepository.findByEmail(useremail);
		
		
		Magazine m = magazineRepository.findByIdEquals(labor.getMagazineid());
		
		ScientificArea sa = scientificAreaRepository.findByIdEquals(labor.getScientificareaid());
		
		if(labor != null) {
			LaborES la = new LaborES();
			//la.setId("1");
			la.setLaborname(labor.getLaborname());
			
			List<KeyTerm> list = new ArrayList<>();
			for(int i=0;i<labor.getKeyterms().size();i++) {
				KeyTerm t = new KeyTerm();
				t.setKeyterm(labor.getKeyterms().get(i));
				list.add(t);
			}			
			la.setKeyterms(list);
			
			if(m != null) {
				la.setMagazinename(m.getName());	
			}
			if(sa != null) {
				la.setScientificarea(sa.getName());	
			}
			if(u != null) {
				la.setAuthor(u.getName() + " " + u.getSurname());
			}
			
			if(!labor.getPdfname().equals("")) {
				
				System.out.println("NAZIV PDF: " + labor.getPdfname());
				String st = labor.getPdfname().split("\\\\")[2];
				
				String fp = System.getProperty("user.dir");
				String fpp=new String(fp+"\\Magazines\\");
				String originalFileName = st;
				byte[] input_file;
				try {
					input_file = Files.readAllBytes(Paths.get(fpp+originalFileName));
					 byte[] encodedBytes = Base64.getEncoder().encode(input_file);
					 la.setPdf(encodedBytes);
				}
				catch(Exception e) {
					
				}
				
				
			}
	
			

			

			LaborES lab = laborESRepository.save(la);
			if(lab != null) {
				return "uspesno";
			}
		}

		
		
		return "neuspesno";
	}
	
	
	public List<LaborES> searchLaborName(String name) {

		List<LaborES> labor = laborESRepository.findByLaborname(name);
		
		return labor;
		
	}
	
	
	
	public List<LaborES> searchMagazineName(String name) {
		
		List<LaborES> labor = laborESRepository.findByMagazinename(name);
		
		return labor;
		
	}
	
	
	
	public List<LaborES> searchScientificArea(String name) {
		
		List<LaborES> labor = laborESRepository.findByScientificarea(name);
		
		return labor;
		
	}
	
	
	public List<LaborES> searchAuthor(String name) {
		
		if(name != null) {
			if(name.contains(" ")) {
				String[] words = name.split("\\s+");
				List<LaborES> res= laborESRepository.findByAuthorContaining(words[0], words[1]);
				return res;
			}
			else {
				List<LaborES> res= laborESRepository.findByAuthorContaining(name);
				return res;
			}
			
		}
		return null;
	}
	
	
	public List<LaborES> searchkeyterm(String name) {
		
		String[] words = name.split("\\s+");
		
		
		if(words.length == 2) {
			List<LaborES> labsa = laborESRepository.findByKeytermsKeytermAndKeytermsKeyterm(words[0], words[1]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		else if(words.length == 1) {
			List<LaborES> labsa = laborESRepository.findByKeytermsKeyterm(words[0]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		else if(words.length == 3) {
			List<LaborES> labsa = laborESRepository.findByKeytermsKeytermAndKeytermsKeytermAndKeytermsKeyterm(words[0], words[1], words[2]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		
		
	
		return null;
	}
	
	
	
	
	public List<LaborES> searchAndOr(SearchDTO searchdto) {
		
		final QueryBuilder builder;
		if(searchdto.getAndor().equals("or")) {
			builder = boolQuery()
					.should(matchQuery("laborname", searchdto.getLaborname()))
					.should(matchQuery("scientificarea", searchdto.getScientificarea()))
					.should(matchQuery("author", searchdto.getAuthor()))
					.should(matchQuery("magazinename", searchdto.getMagazinename()))
					//.should(matchQuery("keyterms", searchdto.getAuthor()))
					;
		}
		else {
			builder = boolQuery()
					.must(matchQuery("laborname", searchdto.getLaborname()))
					.must(matchQuery("scientificarea", searchdto.getScientificarea()))
					.must(matchQuery("author", searchdto.getAuthor()))
					.must(matchQuery("magazinename", searchdto.getMagazinename()))
					//.should(matchQuery("keyterms", searchdto.getAuthor()))
					;
		}

		
		
		
		
		System.out.println("DDDDDDDd " + builder.toString());
		//final QueryBuilder builder =  nestedQuery("labor", boolQuery().must(termQuery("laborname", "RadA")).must(termQuery("scientificarea", "Saobracaj")), ScoreMode.None);

		
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		final List<LaborES> labor = elasticsearchTemplate.queryForList(searchQuery, LaborES.class);
		System.out.println("RESSSSSSSSS " + labor.size());
		return labor;
	}
	
	
	
	
	
	
}
