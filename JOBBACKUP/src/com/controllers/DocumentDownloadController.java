package com.controllers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.ProfileUtilisateur;

import hibernate.dao.TableDocumentsHome;
import hibernate.model.TableDocuments;

@Controller
@RequestMapping(value="/documentDownload")
public class DocumentDownloadController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView downloadFile(HttpServletRequest request) {
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		if((pl==null)){
			return new ModelAndView("Login");
		}else {
		
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		Session sessions= sf.openSession();
		Query query=sessions.createSQLQuery("select FILE_NAME from TABLE_DOCUMENTS");

		ArrayList<String> nomsFichiers=new ArrayList<String>();

		if(!query.list().isEmpty()) {
			nomsFichiers.addAll(query.list());


			return new ModelAndView("FileDownload","nomsFichiers",nomsFichiers);
		}
		else {
			return new ModelAndView("FileDownload");
		}
		}
		
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView downloadFile(@RequestParam("string") String filename,HttpServletResponse response){
		String contentType;
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		Session sessions= sf.openSession();
		Query query=sessions.createSQLQuery("select UPLOAD_ID from TABLE_DOCUMENTS where FILE_NAME=:FILENAME").setParameter("FILENAME", filename);
		

		if(!query.list().isEmpty()) {
			BigDecimal id=(BigDecimal) query.list().get(0);
			Transaction transact=sessions.beginTransaction();
			TableDocuments doc=(TableDocuments) sessions.get(TableDocuments.class, id);
			contentType=filename.substring(filename.lastIndexOf(".")).toLowerCase();
			System.out.println(contentType);	
			try {
	            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFileName()+ "\"");
	            OutputStream out = response.getOutputStream();
	            response.setContentType(contentType);
	            IOUtils.copy(doc.getFileData().getBinaryStream(), out);
	            out.flush();
	            out.close();
	            return new ModelAndView("FileDownload");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	
		}
		else {
			return new ModelAndView("FileDownload");
		}
		return new ModelAndView("FileDownload");

	}




}
