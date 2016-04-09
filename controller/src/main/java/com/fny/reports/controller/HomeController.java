package com.fny.reports.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fny.reports.commons.entity.SubscriptionDO;
import com.fny.reports.service.persistence.dao.SubscriptionDao;

@Controller
@RequestMapping("/api")
public class HomeController {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

   @Autowired
   SubscriptionDao subscriptionDao;
                                                   
	public HomeController() {                      
		System.out.println("Bean created");
	}

	@RequestMapping(method = RequestMethod.GET) //Default subscription page
	public String index() {
		System.out.println("Invoked home");
		return "index";
	}

	@RequestMapping(value = "/countHome", method = RequestMethod.POST)     //It is invoked from frontEnd Used for subsribing one charcter to another
	public int countHome(@RequestParam("character_name") String character_name, @RequestParam("character_id") Integer character_id,
			@RequestParam("subscribed_character_id") Integer subscribed_character_id,
			@RequestParam("subscribed_character") String subscribed_character,
			@RequestParam("field") String field)throws ParseException {
		
		SubscriptionDO subscription=new SubscriptionDO();
		
		subscription.setCharacterId(character_id);
		subscription.setCharacterName(character_name);
		subscription.setField(field);
		subscription.setSubscriptionId(subscribed_character_id);
		subscription.setSubscriptionName(subscribed_character);
		return subscriptionDao.insertIntoSubscriptionTable(subscription);
		 
		
       
	}
	// return list;

}
