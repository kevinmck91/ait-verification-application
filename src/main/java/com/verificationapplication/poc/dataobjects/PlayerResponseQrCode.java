package com.verificationapplication.poc.dataobjects;

import java.util.Date;

public class PlayerResponseQrCode {

	private String firstname;
	private String lastname;
	private int yearOfBirth;
	private int clubId;

	public PlayerResponseQrCode() {
	}

	public PlayerResponseQrCode(String firstname, String lastname, int yearOfBirth, int clubId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.yearOfBirth = yearOfBirth;
		this.clubId = clubId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

}
