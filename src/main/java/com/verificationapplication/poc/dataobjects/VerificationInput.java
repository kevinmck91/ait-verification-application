package com.verificationapplication.poc.dataobjects;

import java.util.List;

public class VerificationInput {

	private 	int 	ageGroup; //2007 , 2013
	private 	List<Player> players;

	public VerificationInput() {
	}

	public VerificationInput(int ageGroup, List<Player> players) {
		this.ageGroup = ageGroup;
		this.players = players;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "VerificationInput{" +
				"ageGroup=" + ageGroup +
				", players=" + players +
				'}';
	}
}
