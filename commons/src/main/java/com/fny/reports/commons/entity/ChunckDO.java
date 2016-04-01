package com.fny.reports.commons.entity;

public class ChunckDO {
	public Integer chunckId;
	public String location;
	public ChunckDO(Integer chunckId, String location, String skill, String status) {
		super();
		this.chunckId = chunckId;
		this.location = location;
		this.skill = skill;
		this.status = status;
	}
	public String skill;
	public String status;
	public String createdDate;
	public Integer getChunckId() {
		return chunckId;
	}
	public void setChunckId(Integer chunckId) {
		this.chunckId = chunckId;
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
