/**
 * Keeps track of a Poker Hand. Hand size could be 1 to 7 cards. Includes a
 * getType() method that finds the type (e.g. two pair, flush, straight) of this
 * hand. Note: In determining a hand's type you should consider up to the best 5
 * cards in the hand.
 * 
 * @author Evan Cao ICS4U
 * @version November 2013
 */

public class PokerHand extends Hand
{
	// Keeps track the of the number of ranks and suits
	// that appear in the poker hand
	private int[] rank;
	private int[] suit;
	static final int[] SCORES = { 0, 1, 3, 6, 12, 5, 10, 16, 30, 50 };

	// Poker Hand types/categories
	// Use these constants in your getType method
	// e.g. return FULL_HOUSE;
	public final static int ROYAL_FLUSH = 9;
	public final static int STRAIGHT_FLUSH = 8;
	public final static int FOUR_OF_A_KIND = 7;
	public final static int FULL_HOUSE = 6;
	public final static int FLUSH = 5;
	public final static int STRAIGHT = 4;
	public final static int THREE_OF_A_KIND = 3;
	public final static int TWO_PAIR = 2;
	public final static int PAIR = 1;
	public final static int NOTHING = 0;

	public final static String[] TYPES = { "Nothing", "Pair", "Two Pair",
			"Three of a Kind ", "Straight", "Flush", "Full House",
			"Four of a Kind", "Straight Flush", "Royal Flush" };

	/**
	 * Constructs an empty PokerHand
	 */
	public PokerHand()
	{
		super();
	}
	
	/**
	 * Gets the score of a poker hand 
	 * @return the score
	 */
	public int getScore()
	{
		return SCORES[getType()];
	}

	/**
	 * Helper Method Determines whether there is a straight flush or a royal
	 * flush in the hand
	 * 
	 * @param suit the suit to check each card for
	 * @return 9 if the hand has a royal flush 8 if the hand has a straight
	 *         flush 0 if the hand has neither a royal or straight flush
	 */
	public int royalStraight(int suit)
	{
		int[] ranks = new int[15];

		// Add the number of each card with the suit to the array
		for (Card next : hand)
		{
			if (next.getSuit() == suit)
				ranks[next.getRank()]++;

			// Set the number of aces to index 14
			ranks[14] = ranks[1];
		}

		// Initialize the index so that it checks from the back
		int ranksIndex = 14;

		// Variable to keep count of cards with consecutive ranks
		int straightCount = 0;

		// Goes through the array and checks for a straight
		while (ranksIndex >= 1)
		{
			if (ranks[ranksIndex] > 0)
			{
				straightCount++;

				// If there are 5 consecutive ranks with
				// cards, check if it is a royal flush
				// If yes, return 9 to indicate royal flush
				// If not, return 8 to indicate straight flush
				if (straightCount == 5)
				{
					if (ranksIndex == 10)
						return 9;
					return 8;
				}

				// Set counter to check for next rank
				ranksIndex--;
			}
			// If the hand does not contain a card with
			// the next consecutive rank, reset the counter
			else
			{
				ranksIndex--;
				straightCount = 0;
			}
		}
		// If the hand does not contain a royal or straight flush
		// Return 0
		return 0;
	}

	/**
	 * Returns the type of this hand e.g. Flush, Straight, Two Pair
	 * 
	 * @return the poker hand type 0 - NOTHING to 9 - ROYAL_FLUSH
	 */
	public int getType()
	{
		rank = new int[15];
		suit = new int[5];
		
		// Record the information of each card in the hand
		for (Card next : hand)
		{
			rank[next.getRank()]++;
			suit[next.getSuit()]++;

			// Set the number of aces to index 14
			rank[14] = rank[1];
		}

		// Initialize variables that determine the value of the hand
		boolean hasStraight = false;
		boolean hasFlush = false;

		// Checks if there is a straight
		int straightIndex = 1;
		int checkCount = 0;

		// Goes through each index of the rank array
		// and adds to the counter for each consecutive
		// rank that has a card as long as a straight
		// has not been found yet
		while (straightIndex <= 14 && hasStraight == false)
		{
			if (rank[straightIndex] > 0)
			{
				straightIndex++;
				checkCount++;

				// If there are 5 consecutive ranks with
				// cards, set the value to indicate that
				// a straight is possible
				if (checkCount == 5)
					hasStraight = true;
			}

			// If the hand does not contain a card with
			// the next consecutive rank, reset the counter
			else
			{
				straightIndex++;
				checkCount = 0;
			}
		}

		// Goes through each suit and checks
		// for a flush, straight flush and
		// royal flush
		for (int suits = 1; suits <= 4; suits++)
		{
			// If there are more than 5 cards with
			// the same suit, indicate that a flush
			// is possible
			if (suit[suits] >= 5)
			{
				// Indicate that a flush is possible
				hasFlush = true;

				// If a straight and flush is possible
				// Check for a straight/royal flush
				if (hasStraight)
				{
					// Checks to see if there is a royal or straight flush
					int royalStraightCheck = royalStraight(suits);
				
					// Returns the value if there is a royal/straight flush
					if (royalStraightCheck != 0)
						return royalStraightCheck;
				}
			}

		}

		// Initialize variables that determine the value of the hand
		int numQuad = 0;
		int numPair = 0;
		int numTrip = 0;

		// Check for the number of quadruples, triples and pairs
		for (int rankCheck = 0; rankCheck < 14; rankCheck++)
		{
			if (rank[rankCheck] == 4)
				numQuad++;

			if (rank[rankCheck] == 3)
				numTrip++;

			if (rank[rankCheck] == 2)
				numPair++;
		}

		// Return 7 if a 4-of-a-kind is possible
		if (numQuad > 0)
			return 7;

		// Return 6 if a full house is possible
		if (numTrip > 0 && numPair > 0 || numTrip >= 2)
			return 6;

		// Return 5 if a flush is possible
		if (hasFlush)
			return 5;

		// Return 4 if a straight is possible
		if (hasStraight)
			return 4;

		// Return 3 if a 3-of-a-kind is possible
		if (numTrip > 0)
			return 3;

		// Return 2 if there are 2 pairs
		if (numPair >= 2)
			return 2;

		// Return 1 if a pair is possible
		if (numPair > 0)
			return 1;

		// Return nothing if there is no possible type
		return 0;
	}
}
