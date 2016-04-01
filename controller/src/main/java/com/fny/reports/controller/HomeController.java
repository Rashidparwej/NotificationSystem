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

@Controller
@RequestMapping("/api")
public class HomeController {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;


                                                   
	public HomeController() {                      
		System.out.println("Bean created");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		System.out.println("Invoked home");
		return "index";
	}

	@RequestMapping(value = "/countHome", method = RequestMethod.POST)
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
		  String query="insert into Gossiphgirl.subscription (character_id,character_name,subscribed_character,subscribed_character_id,field_subscribed) values"
				  +"('"+subscription.getCharacterId()+"','"+subscription.getCharacterName()+"','"+subscription.getSubscriptionId()+"','"+subscription.getSubscriptionName()+"','"+subscription.getField()+"')";  
				    return jdbcTemplate.update(query);  
		
		
       
	}
	// return list;

}
