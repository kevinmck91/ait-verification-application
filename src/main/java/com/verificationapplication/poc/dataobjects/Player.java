package com.verificationapplication.poc.dataobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private 	int 	id;
	private 	int 	membershipId;
	private 	String 	firstname;
	private 	String 	lastname;
	private 	Date dateOfBirth;
	private 	String 	image;
	private 	String 	qrCode;
	private 	int 	clubId;
	private 	boolean activeMembership;
	private 	int 	trustScore;

	
	//TODO look up linter
	public Player(){

	}
	
	public Player(int membershipId, String firstname, String lastname, Date dateOfBirth, String image,
			String qrCode, int clubId, boolean activeMembership, int trustScore) {
		this.membershipId = membershipId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.qrCode = qrCode;
		this.clubId = clubId;
		this.activeMembership = activeMembership;
		this.trustScore = trustScore;
	}
	public int getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public boolean isActiveMembership() {
		return activeMembership;
	}
	public void setActiveMembership(boolean activeMembership) {
		this.activeMembership = activeMembership;
	}
	public int getTrustScore() {
		return trustScore;
	}
	public void setTrustScore(int trustScore) {
		this.trustScore = trustScore;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Player [membershipId=" + membershipId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dateOfBirth=" + dateOfBirth + ", image=" + image + ", qrCode=" + qrCode + ", clubId=" + clubId
				+ ", activeMembership=" + activeMembership + ", trustScore=" + trustScore + "]";
	}

	
	
	
	
}
