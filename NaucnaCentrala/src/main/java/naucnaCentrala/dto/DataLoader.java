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
		
		
		
		File file = new File(fpp + "Analiza magnetopobudnih sila u multifaznim mašinama u sklopu integrisanih punjača za električna vozila.pdf");
	    FileInputStream input = new FileInputStream(file);
	    MultipartFile multipartFile = new MockMultipartFile("file",
	            file.getName(), "application/pdf", IOUtils.toByteArray(input));
	     
	    DBFile db = dbFileService.storeFile(multipartFile);
		
	    
	    File file1 = new File(fpp + "Međulaboratorijska ispitivanja kod širokopojasnih merenja elektromagnetnih polja.pdf");
	    FileInputStream input1 = new FileInputStream(file1);
	    MultipartFile multipartFile1 = new MockMultipartFile("file",
	            file1.getName(), "application/pdf", IOUtils.toByteArray(input1));
	     
	    DBFile db1 = dbFileService.storeFile(multipartFile1);
	    
	    
	    File file2 = new File(fpp + "Sistemi emitovanja i standardi satelitske televizijske transmisije u Evropi.pdf");
	    FileInputStream input2 = new FileInputStream(file2);
	    MultipartFile multipartFile2 = new MockMultipartFile("file",
	            file2.getName(), "application/pdf", IOUtils.toByteArray(input2));
	     
	    DBFile db2 = dbFileService.storeFile(multipartFile2);
	    
	    
	    File file4 = new File(fpp + "Uticaj niskotarifnog prevozioca na cene karata tradicionalnog prevozioca na interkontinentalnim letovima.pdf");
	    FileInputStream input4 = new FileInputStream(file4);
	    MultipartFile multipartFile4 = new MockMultipartFile("file",
	            file4.getName(), "application/pdf", IOUtils.toByteArray(input4));
	     
	    DBFile db4 = dbFileService.storeFile(multipartFile4);
	    
	    File file5 = new File(fpp + "Izmena putnika na međumesnim linijama.pdf");
	    FileInputStream input5 = new FileInputStream(file5);
	    MultipartFile multipartFile5 = new MockMultipartFile("file",
	            file5.getName(), "application/pdf", IOUtils.toByteArray(input5));
	     
	    DBFile db5 = dbFileService.storeFile(multipartFile5);
	    
	    
	    File file6 = new File(fpp + "Savremena uloga i značaj profesionalnih kvalifikacija u sektoru bankarstva.pdf");
	    FileInputStream input6 = new FileInputStream(file6);
	    MultipartFile multipartFile6 = new MockMultipartFile("file",
	            file6.getName(), "application/pdf", IOUtils.toByteArray(input6));
	     
	    DBFile db6 = dbFileService.storeFile(multipartFile6);
	    
   
	    File file7 = new File(fpp + "Tradicionalni i novi poslovni modeli u bankarskoj industriji.pdf");
	    FileInputStream input7 = new FileInputStream(file7);
	    MultipartFile multipartFile7 = new MockMultipartFile("file",
	            file7.getName(), "application/pdf", IOUtils.toByteArray(input7));
	     
	    DBFile db7 = dbFileService.storeFile(multipartFile7);
	    
	    File file8 = new File(fpp + "Magazin Tehnika.pdf");
	    FileInputStream input8 = new FileInputStream(file8);
	    MultipartFile multipartFile8 = new MockMultipartFile("file",
	            file8.getName(), "application/pdf", IOUtils.toByteArray(input8));
	     
	    DBFile db8 = dbFileService.storeFile(multipartFile8);
	    
	    
	    File file9 = new File(fpp + "Magazin Bankarstvo.pdf");
	    FileInputStream input9 = new FileInputStream(file9);
	    MultipartFile multipartFile9 = new MockMultipartFile("file",
	            file9.getName(), "application/pdf", IOUtils.toByteArray(input9));
	     
	    DBFile db9 = dbFileService.storeFile(multipartFile9);
	    
	    
	    File file10 = new File(fpp + "Nelinearna seizmička analiza stubova kontinualnog AB mosta.pdf");
	    FileInputStream input10 = new FileInputStream(file10);
	    MultipartFile multipartFile10 = new MockMultipartFile("file",
	            file10.getName(), "application/pdf", IOUtils.toByteArray(input10));
	     
	    DBFile db10 = dbFileService.storeFile(multipartFile10);
	    
	    File file11 = new File(fpp + "Odvajanje grede konačne dužine od Vinklerove nezatežuće podloge pri dejstvu sile na kraju grede.pdf");
	    FileInputStream input11 = new FileInputStream(file11);
	    MultipartFile multipartFile11 = new MockMultipartFile("file",
	            file11.getName(), "application/pdf", IOUtils.toByteArray(input11));
	     
	    DBFile db11 = dbFileService.storeFile(multipartFile11);
	    
	    
	    File file12 = new File(fpp + "Magazin Gradjevinarstvo.pdf");
	    FileInputStream input12 = new FileInputStream(file12);
	    MultipartFile multipartFile12 = new MockMultipartFile("file",
	            file12.getName(), "application/pdf", IOUtils.toByteArray(input12));
	     
	    DBFile db12 = dbFileService.storeFile(multipartFile12);
	    
	    
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
