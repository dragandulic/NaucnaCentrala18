package naucnaCentrala.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import naucnaCentrala.model.DBFile;
import naucnaCentrala.repository.DBFileRepository;
import naucnaCentrala.service.DBFileService;

@Component
public class DataLoader implements ApplicationRunner{

	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	@Autowired
	private DBFileService dbFileService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		
		final String fp = System.getProperty("user.dir");
		String fpp=new String(fp+"\\Magazines\\");
		
		File file = new File(fpp + "Tehnika.pdf");
		
		
	    FileInputStream input = new FileInputStream(file);
	    MultipartFile multipartFile = new MockMultipartFile("file",
	            file.getName(), "application/pdf", IOUtils.toByteArray(input));
	    
	    
	    DBFile db1 = dbFileService.storeFile(multipartFile);
		
		/*
		InputStream is = getClass().getResourceAsStream("C:\\Users\\dragan\\Documents\\GitHub\\NaucnaCentrala18\\NaucnaCentrala\\Magazines\\" + "Tehnika.pdf");
        MultipartFile multipartFile = new MockMultipartFile("file", "fil", "", is);
		
		/*
		FileItem fileItem = new DiskFileItem("fileData", "application/pdf",true, file.getName(), 100000000, new java.io.File(System.getProperty("java.io.tmpdir")));              
		MultipartFile multipartFile = new MockMultipartFile("file", "aaaaaaaaaa".getBytes());
		
		
		/*

		File file = new File(fpp + "Tehnika.pdf");
	    FileInputStream input = new FileInputStream(file);
	    MultipartFile multipartFile = new MockMultipartFile("file",
	            file.getName(), "application/pdf", IOUtils.toByteArray(input));
		
	   */
		
		
		
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		final String fp = System.getProperty("user.dir");
		String fpp=new String(fp+"\\Magazines\\");
		
		File file = new File("C:\\Users\\dragan\\Documents\\GitHub\\NaucnaCentrala18\\NaucnaCentrala\\Magazines\\" + "Tehnika.pdf");
		System.out.println("AAAAAAAAAaa " + file);
		FileInputStream inputStream = new FileInputStream(file);
		
		
		
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/naucnaCentrala?useSSL=false&createDatabaseIfNotExist=true", "root", "root");

		String sql = "INSERT INTO dbfile (id, file_name, file_type, data  ) VALUES (?,?,?,?)";
		PreparedStatement statement = cn.prepareStatement(sql);
		statement.setLong(1, 1);
		statement.setString(2, "NAZIV");
		statement.setString(3, "application/pdf");
		MultipartFile mpf = (MultipartFile) inputStream;
		statement.setBlob(4, mpf);
		statement.executeUpdate();
		
		/*
		PreparedStatement statement = cn.prepareStatement("INSERT INTO dbfile (id, fileName, fileType, data  ) VALUES (?,?,?,?)");
		statement.setBlob(1, inputStream);
		*/
	}

	
	
	
}
