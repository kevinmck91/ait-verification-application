package com.verificationapplication.poc.dataobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private		Integer 	id;
	private 	String 	name;
	private 	String 	location;
	private 	int clubId;

	public Team() {

	}
	
	public Team(String name, String location, int clubId) {
		this.name = name;
		this.location = location;
		this.clubId = clubId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", location=" + location + ", clubId=" + clubId + "]";
	}

}
