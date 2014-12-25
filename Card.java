import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;

import javax.swing.ImageIcon;

/**
 * Card Class Keeps track of the information of a card including the rank, suit,
 * and whether it is face up or face down
 * 
 * @author Evan Cao ICS4U
 * @version November 2013
 */

public class Card extends Rectangle implements Comparable<Card>
{
	// Keep track of the suit, rank and whether the card is face up or not
	private int suit;
	private int rank;
	private boolean isFaceUp;

	// Constants that keep track of the indices for each rank and suit
	private final String RANK_VALUE = " A23456789TJQK";
	private final String SUIT_VALUE = " DCHS";
	
	// Constant Comparator object for comparing Cards by rank then suit
	public static final Comparator <Card>
	RANK_SUIT = new RankSuit();
	
	private final static Image background = new ImageIcon("images\\redback.png").getImage();
	public final static int WIDTH = background.getWidth(null);
	public final static int HEIGHT = background.getHeight(null);
	private Image image;
	

	/**
	 * Constructs a new card with the given rank and suit
	 * 
	 * @param rank the rank of the card
	 * @param suit the suit of the card
	 */
	public Card(int rank, int suit)
	{
		super(0, 0, 0, 0);
		this.rank = rank;
		this.suit = suit;
		isFaceUp = true;
		
		// Load up the appropriate image file for this card
		String imageFileName = "" + " dchs".charAt(suit) + rank + ".png";
		imageFileName = "images\\" + imageFileName;
		image = new ImageIcon(imageFileName).getImage();
		
		// Set the size of the card based on the image size
		setSize(image.getWidth(null), image.getHeight(null));
		
	}

	/**
	 * Constructs a new card with the given string
	 * 
	 * @param cardStr a string containing rank and suit
	 */
	public Card(String cardStr)
	{
		rank = RANK_VALUE.indexOf(cardStr.charAt(0));
		suit = SUIT_VALUE.indexOf(cardStr.charAt(1));
	}

	/**
	 * Returns the Card information as a String
	 * 
	 * @return the card suit and rank
	 */
	public String toString()
	{
		return "" + RANK_VALUE.charAt(rank) + SUIT_VALUE.charAt(suit);
	}

	/**
	 * Compares this Card to another Card by comparing the suit, then rank
	 * fields in each Card.
	 * 
	 * @param other the Card to compare to this Card
	 * @return a value < 0 if the suit in this Card is lower than the suit in
	 *         the other Card, a value > 0 if this suit comes after the other
	 *         suit, and if they are the same suit, return a value < 0 if the
	 *         rank in this Card is lower than the rank in the other card and a
	 *         value > 0 if the rank in this card is greater than the rank in
	 *         the other
	 */
	public int compareTo(Card other)
	{
		if (this.suit < other.suit)
			return -1;
		else if (this.suit > other.suit)
			return 1;
		else
		// If the suits are the same
		{
			if (this.rank < other.rank)
				return -1;
			else 
				return 1;
		}
	}

	/**
	 * Gets the rank of the card
	 * 
	 * @return the rank of the card
	 */
	public int getRank()
	{
		return rank;
	}

	/**
	 * Gets the suit of the card
	 * 
	 * @return the suit of the card
	 */
	public int getSuit()
	{
		return suit;
	}

	/**
	 * Flips the card face up
	 */
	public void turnFaceUp()
	{
		isFaceUp = true;
	}

	/**
	 * Flips the card face down
	 */
	public void turnFaceDown()
	{
		isFaceUp = false;
	}

	/**
	 * Finds out whether the card is an ace
	 * 
	 * @return true or false
	 */
	public boolean isAce()
	{
		if (rank == 1)
			return true;
		return false;
	}

	/**
	 * Finds out the blackjack value of the card
	 * 
	 * @return the blackjack value of the card
	 */
	public int getValue()
	{
		// Return 11 if it is an Ace
		if (rank == 1)
			return 11;
		// Return 10 if it is a 10, J, Q, or K
		else if (rank > 9)
			return 10;
		else 
			return rank;
	}
	
	/**
	 * Checks to see if the given object is a card and if 
	 * it has the same suit and rank as this card
	 * @param 	other 	the object to compare the card to
	 * @return 	true 	if the given object is a card with the same rank and suit
	 * 			false 	if the given object is not a card or if the given object
	 * 			      	does not have the same rank and suit
	 */
	public boolean equals(Object other)
	{
		// Checks if the object is the same class
		if (this.getClass() != other.getClass())
			return false;
		Card otherCard = (Card) other;
		return ((this.suit == otherCard.suit) && (this.rank == otherCard.rank));
	}
	
	/**
	* Draws a card in a Graphics context
	* @param g Graphics to draw the card in
	*/ 
	public void draw(Graphics g)
	{
		if (isFaceUp)
			g.drawImage(image, x, y, null);
		else
			g.drawImage(background, x, y, null);
	}
	
	/**
	 * Translates the card to another spot
	 * @param initialPos the initial position
	 * @param finalPos the final position
	 */
	public void move (Point initialPos, Point finalPos)
	{
	translate (finalPos.x - initialPos.x, finalPos.y - initialPos.y);
	}
	
	private static class RankSuit implements Comparator<Card>
	{
		/**
		 * Compares the rank, then suit of two Card objects
		 * 
		 * @param first the first Card to compare
		 * @param second the second Card to compare
		 * @return a value < 0 if the first Card has a lower rank, a value > 0
		 *         if first Card has a higher rank and if the ranks are the
		 *         same, a value < 0 if the first Card has a lower suit, a value
		 *         > 0 if first Card has a higher suit
		 */
		public int compare(Card first, Card second)
		{
			if (first.rank < second.rank)
				return -1;
			else if (first.rank > second.rank)
				return 1;
			else
			{
				if (first.suit < second.suit)
					return -1;
				else
					return 1;
			}
		}
	}
	
}