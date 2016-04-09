package com.fny.reports.service.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fny.reports.commons.entity.SubscriptionDO;
@Service
public class SubscriptionDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertIntoSubscriptionTable(SubscriptionDO subscription)
	{
	
	 String query="insert into Gossiphgirl.subscription (character_id,character_name,subscribed_character,subscribed_character_id,field_subscribed) values"
			  +"('"+subscription.getCharacterId()+"','"+subscription.getCharacterName()+"','"+subscription.getSubscriptionId()+"','"+subscription.getSubscriptionName()+"','"+subscription.getField()+"')";  
			    return jdbcTemplate.update(query);  
	
     }
}
