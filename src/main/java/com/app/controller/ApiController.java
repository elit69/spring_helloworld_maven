package com.app.controller;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SmsLog;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountCashIns() {
		Session session = sessionFactory.openSession();
		
		ArrayList<SmsLog> currencyConversionRules = null;
		try {
			 currencyConversionRules = (ArrayList<SmsLog>) session
					.createCriteria(SmsLog.class)
					.setMaxResults(10)
					.setCacheable(true)
					//.setCacheMode(CacheMode.NORMAL)
					//.setCacheRegion("query.frontpages")
					.list();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return new ResponseEntity<ArrayList<SmsLog>>(currencyConversionRules, HttpStatus.OK);
	}
}
