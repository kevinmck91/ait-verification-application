package com.verificationapplication.poc.dataobjects;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int membershipId;
	private String firstname;
	private String lastname;
	private Date dateOfBirth;
	private int ageGroup;
	@Lob
	private Blob image;
	private int clubId;
	private boolean activeMembership;
	private int trustScore;

	
	//TODO look up linter
	public Player(){

	}

	public Player(int id, int membershipId, String firstname, String lastname, Date dateOfBirth, int ageGroup, Blob image, int clubId, boolean activeMembership, int trustScore) {
		this.id = id;
		this.membershipId = membershipId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.ageGroup = ageGroup;
		this.image = image;
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

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
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

	public int getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", membershipId=" + membershipId +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", ageGroup=" + ageGroup +
				", image=" + image +
				", clubId=" + clubId +
				", activeMembership=" + activeMembership +
				", trustScore=" + trustScore +
				'}';
	}
}
