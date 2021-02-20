package com.verificationapplication.poc.dataobjects;

public class VerificationInput {

	private 	int 	membershipId;
	private 	String 	image;

	public VerificationInput() {
	}
	
	public VerificationInput(int membershipId, String image) {
		this.membershipId = membershipId;
		this.image = image;
	}
	
	public int getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
