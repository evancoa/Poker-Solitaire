/**
 * Player Class Keeps track of the information of a Player such as
 * the name and the score
 * 
 * @author Evan Cao ICS4U
 * @version November 2013
 */


import java.io.Serializable;
import java.util.Comparator;

public class Player implements Comparable<Player>, Serializable
{

	// Variables used to keep track of the player name and score
	private String playerName;
	private int score;	
	
	// Constant Comparator object for comparing Players by name
		public static final Comparator <Player>
		NAME_ORDER = new NameOrder();
		
	/**
	 * Constructs a new player with a name and
	 * a score
	 * @param playerName the name of the player
	 */
	public Player(String playerName, int score)
	{
		this.playerName = playerName;
		this.score = score;
	}

	
	/**
	 * Returns the Player information as a String
	 * 
	 * @return the player name and score
	 */
	public String toString() {
		return String.format("Name: %-20s  Score:%3d", playerName, score);
	}
	
	/**
	 * Compares this Player to another Player by comparing the score
	 * 
	 * @param other the Player to compare to this Player
	 * @return a value < 0 if this player has a lower score than
	 * the other player, a value > 0 if this player has a greater
	 * score than the other player, and a value of 0 if both 
	 * players have an equal score
	 */
	public int compareTo(Player other)
	{
		if (this.score < other.score)
			return -1;
		else if (this.score > other.score)
			return 1;
		else
			return 0;
	}
	
	/**
	 * Returns the score of the player
	 *
	 * @return the score of the player
	 */
	public int getScore()
	{
		return score;
	}
	
	// Private comparator class used to sort Players
	private static class NameOrder implements Comparator<Player>
	{
		/**
		 * Compares the name of the 2 players
		 * 
		 * @param first the first Player to compare
		 * @param second the second Player to compare
		 * @return a value < 0 if the name in of this player comes alphabetically
		 *         before the name of the other player, a value > 0, if this name
		 *         comes after the other name and 0, if the names of the 2 players are
		 *         the same
		 */
		public int compare(Player first, Player second)
		{
			return first.playerName.compareTo(second.playerName);
		}
	}
	
	
}
