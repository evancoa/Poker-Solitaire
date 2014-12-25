import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Hand Class Keeps track of the information of a Hand including the cards in
 * the hand
 * 
 * @author Evan Cao ICS4U
 * @version October 2013
 */

public class Hand
{
	// Keeps track of the cards in the hand
	protected ArrayList <Card> hand;
	
	/**
	 * Constructs an empty hand
	 *
	 */
	public Hand ()
	{
		hand = new ArrayList<Card>();
	}
	
	/**
	 * Constructs a hand based on a string
	 *
	 */
	public Hand(String handStr)
	{
		// Add a card to the hand
		hand = new ArrayList<Card>();
		while (handStr.indexOf(" ") > 0)
		{
			Card newCard = new Card(handStr.substring(0, 2));
			hand.add(newCard);
			handStr = handStr.substring(3);
		}
		
		Card newCard = new Card(handStr.substring(handStr.length() - 2));
		hand.add(newCard);
	}
	
	/**
	 * Sort the hand by rank, then suit
	 */
	public void sortByRank()
	{
		Collections.sort(hand, Card.RANK_SUIT);
	}
	
	/**
	 * Sort the hand by suit, then rank
	 */
	public void sortBySuit()
	{
		Collections.sort(hand);
	}
	
	/**
	 * Adds a card to the hand
	 */
	public void addCard(Card cardToAdd)
	{
		hand.add(cardToAdd);
	}
	
	/**
	 * Returns the Hand information as a String
	 * 
	 * @return the suit and rank of each card in the hand
	 */
	public String toString()
	{
		// Converts each card into a string
		// and combines them into one string
		if (hand.size() > 0)
		{
			StringBuilder handStr = new StringBuilder(hand.size() * 3 - 1);
			for (Card cardInHand : hand)
			{
				handStr.append(cardInHand.toString());
			
				// Add a space at the end if it is not the last card
				if (hand.indexOf(cardInHand) < (hand.size()))
					handStr.append(" ");		
			}
			return handStr.toString();
		}
		
		// If there are no cards in the hand
		// Return an empty string
		return "";
	}
	
	/**
	 * Returns the blackjack value of the hand
	 * 
	 * @return the blackjack value of the hand
	 */
	public int getValue()
	{
		int handValue = 0;
		int numAces = 0;
		
		// Add up the values in the hand
		for (int index = 0; index < hand.size(); index++)
		{
			// Add 1 to the ace counter if there is an ace
			if (hand.get(index).getRank() == 1)
			{
				numAces ++;
				handValue += hand.get(index).getValue();
			}
			else 
				handValue += hand.get(index).getValue();
		}
		
		// If the value is greater than 21, and there are aces in the hand
		// subtract 10 from the hand
		while (handValue > 21 && numAces > 0)
		{
			handValue -= 10;
			numAces --;
		}
		
		// Return the blackjack value of the hand
		return handValue;
	}
	
	/**
	 * Clears the hand of any cards
	 * 
	 */
	public void clear()
	{
		hand.clear();
	}
	
	/**
	* Displays the Cards in this Hand
	*@param g Graphics context to display the deck
	*/ public void draw(Graphics g)
	{
		for (Card next : hand)
			next.draw(g);
	}
	
	/**
	 * Removes a card from the hand
	 * @param card the card to remove
	 */
	public void removeCard(Card card)
	{
		hand.remove(card);
	}
	
	/**
	 * Finds a selected card at a specific point
	 * @param point the point to find a selected card at
	 * @return null if there is no card
	 * 		   next if there is a card
	 */
	public Card getCardAt(Point point)
	{
		for (Card next : hand)
			if (next.contains(point))
				return next;
		return null;
	}
	
	

}
