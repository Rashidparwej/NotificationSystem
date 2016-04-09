package com.fny.reports.commons.entity;

public class NotificationQueueDO {

	public String skill;
	public String location;
	public String status;
	public Integer characterId;
	
	public NotificationQueueDO(Integer characterId, String location, String status, String skill) {
		super();
		this.skill = skill;
		this.location = location;
		this.status = status;
		this.characterId = characterId;
	}
	public NotificationQueueDO(String skill, String location, String status) {
		super();
		this.skill = skill;
		this.location = location;
		this.status = status;
	}
	public NotificationQueueDO(int int1) {
		// TODO Auto-generated constructor stub
	}
	public String getSkill() {
		
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCharacterId() {
		return characterId;
	}
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}
	
}

