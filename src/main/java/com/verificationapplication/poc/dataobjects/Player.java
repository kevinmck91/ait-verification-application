package com.verificationapplication.poc.dataobjects;

public class Player {

	private 	int 	memberbershipId;
	private 	String 	firstname;
	private 	String 	lastname;
	private 	String 	dateOfBirth; // TODO change to date
	private 	String 	image;
	private 	String 	qrCode;
	private 	int 	clubId;
	private 	boolean activeMembership;
	private 	int 	trustScore;

	
	//TODO look up linter
	
	public Player(int memberbershipId, String firstname, String lastname, String dateOfBirth, String image,
			String qrCode, int clubId, boolean activeMembership, int trustScore) {
		this.memberbershipId = memberbershipId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.qrCode = qrCode;
		this.clubId = clubId;
		this.activeMembership = activeMembership;
		this.trustScore = trustScore;
	}
	public int getMemberbershipId() {
		return memberbershipId;
	}
	public void setMemberbershipId(int memberbershipId) {
		this.memberbershipId = memberbershipId;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
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
	
	
	
	@Override
	public String toString() {
		return "Player [memberbershipId=" + memberbershipId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dateOfBirth=" + dateOfBirth + ", image=" + image + ", qrCode=" + qrCode + ", clubId=" + clubId
				+ ", activeMembership=" + activeMembership + ", trustScore=" + trustScore + "]";
	}

	
	
	
	
}