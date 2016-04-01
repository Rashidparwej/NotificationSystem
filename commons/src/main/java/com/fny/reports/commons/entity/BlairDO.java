package com.fny.reports.commons.entity;

public class BlairDO {

	public Integer blairId;
	public String location;
	public String skill;
	public String status;
	public String createdDate;
	public Integer getBlairId() {
		return blairId;
	}
	public BlairDO(Integer blairId, String location, String skill, String status) {
		super();
		this.blairId = blairId;
		this.location = location;
		this.skill = skill;
		this.status = status;
	}
	public void setBlairId(Integer blairId) {
		this.blairId = blairId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}
