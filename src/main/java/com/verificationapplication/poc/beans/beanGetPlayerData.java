package com.verificationapplication.poc.beans;

import java.util.ArrayList;
import java.util.List;

import com.verificationapplication.poc.dataobjects.Player;

public class beanGetPlayerData {
	
	// This is a test line
	
	/**
	 * @param id
	 * 		The id of the player that is used to query the database
	 * 
	 * @return
	 * 		A player object
	 * 
	 */
	public static Player getPlayerFromId(int id) {
		
		Player player = new Player();
		
		// Query the database and extract a player based on Id
		
		return player;
		
	}
	
	
	/**
	 * @param id
	 * 		The id of the team that is used to query the database
	 * 
	 * @return
	 * 		A player object
	 * 
	 */
	public static List<Player> getAllPlayerInTeamFromId(int id) {
		
		Player player = new Player();
		
		List<Player> listOfPlayersOnTeam = new ArrayList();
		
/*		Query the database and get a result set of raw Players data
		Loop over each item of the resultSet and build up each individual player object
		Add each player to the list
		This loop is probably not needed .. JPA has some shortcut i would say
		
		 		while(resultSet.hasMoreData){
		 			create new player object
					add player object to the list
				}
				
					
*/

		return listOfPlayersOnTeam;
		
	}

}
