package com.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.BinaryStream;
import org.hibernate.type.SerializationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beans.ProfileUtilisateur;

import hibernate.model.TableDocuments;

@Controller
@RequestMapping(value="/documentUpload")
public class DocumentUploadController {
	
	private static Session session;

	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView uploadDocument (@RequestParam("file") MultipartFile multiFile) throws IOException, SerialException, SQLException{
		 if (!multiFile.isEmpty()) {
			 		
	            	SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
	            	Session sessions= sf.openSession();
	            	
	            	byte[] bytes=multiFile.getBytes();
	            	Query query=sessions.createSQLQuery("Select * From TABLE_DOCUMENTS where FILE_NAME=:FILENAME").setParameter("FILENAME", multiFile.getOriginalFilename());
	            	if (query.list().isEmpty()) {
			        	Connection test;
						try {
							test = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JOBEISTI","JOBEISTI");
							PreparedStatement stmt=test.prepareStatement("insert into TABLE_DOCUMENTS(UPLOAD_ID, FILE_NAME, FILE_DATA) values (SQ_DOC.NEXTVAL,?,?)");
							stmt.setString(1, multiFile.getOriginalFilename());
							stmt.setBytes(2, bytes);
							sessions.getTransaction().begin();
							stmt.executeUpdate();
							sessions.getTransaction().commit();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        
		                System.out.println(multiFile.getOriginalFilename());
	            	}
		 }
		
		return new ModelAndView("FileUpload");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView uploadDocument(HttpServletRequest request) {
	ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
	if((pl==null)||((!pl.isAdmin())&&(!pl.isEnterprise()))){
		return new ModelAndView("Login");
	}
	else {
		return new ModelAndView("FileUpload");
	}
	}
}
