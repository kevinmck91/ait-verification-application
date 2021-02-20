package com.verificationapplication.poc.dataobjects;

public class Team {
	
	
	private 	String 	name;
	private 	String 	location;
	private 	String 	clubId;

	
	
	public Team(String name, String location, String clubId) {
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

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", location=" + location + ", clubId=" + clubId + "]";
	}
	
	
	

}
