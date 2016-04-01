package com.fny.reports.commons.entity;

public class NateDO {
	
	public Integer nateId;
	public String location;
	public String skill;
	public String status;
	public String createdDate;
	public NateDO(Integer nateId, String location, String skill, String status) {
		super();
		this.nateId = nateId;
		this.location = location;
		this.skill = skill;
		this.status = status;
	}
	public Integer getNateId() {
		return nateId;
	}
	public void setNateId(Integer nateId) {
		this.nateId = nateId;
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
