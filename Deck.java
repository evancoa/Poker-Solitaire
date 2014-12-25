/**
 * Deck Class Keeps track of the information of a Deck including the cards in
 * the Deck
 * 
 * @author Evan Cao ICS4U
 * @version November 2013
 */

public class Deck
{
	protected Card[] deck;
	
	// Create the variable responsible for
	// keeping track of the number of cards in the deck
	private int topCard;
	
	/**
	 * Constructs a new deck
	 * that is sorted by suit then rank
	 */
	public Deck()
	{
		deck = new Card[52];
		topCard = 0;
		
		// Add each card from a suit
		for (int suit = 1; suit <= 4; suit ++)
		{
			for (int rank = 1; rank <= 13; rank ++)
			{
			deck[topCard] = new Card(rank, suit);
			topCard ++;
			}
		}
	}

	
	/**
	 * Shuffles the cards in the deck into random order and resets the number of cards
	 * 
	 */
	public void shuffle()
	{
		// Reset the number of cards to 52
		topCard = deck.length;		
	    
	    // Shuffle the deck by swapping elements between 2 different indices
	    for (int deckIndex = deck.length - 1; deckIndex >= 0; deckIndex--)
	    { 
	      // Generates a random index that exists in the deck array
	      int tempIndex = ((int) (deck.length * Math.random())); 
	      
	      // If the indices are different, swap the Cards at the indices
	      if (tempIndex != deckIndex)
	      {
	        Card temp = deck[deckIndex];
	        deck[deckIndex] = deck[tempIndex];
	        deck[tempIndex] = temp;
	      }
	    }
	}
	
	/**
	 * Deals a card from the deck
	 * 
	 */
	public Card deal()
	{
		// Return nothing if the deck is empty
		if (topCard == 0)
			return null;
		else
			return deck[--topCard];
	}
	
	/**
	 * Finds the number of cards left in the deck
	 * 
	 */
	public int noOfCardsLeft()
	{
		return topCard;
	}
	
}
