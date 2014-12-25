/** Checks the type for all possible Poker Hands
 * Works for different size hands (e.g. 5 or 7 card)
 * @author G. Ridout
 * @version October 2013
 */

public class CheckAllPokerHands
{
	static int HAND_SIZE =  5;
	
	public static void main(String[] args)
	{
		PokerDeck myDeck = new PokerDeck(HAND_SIZE);
		myDeck.shuffle();
    	int[] noOfEachType = new int[10];
    	
    	// Correct Frequencies for 5 and 7 card hands
    	int [] correctFreq;
    	if (HAND_SIZE == 5)
    		correctFreq = new int []{1302540, 1098240,123552,54912,10200,5108, 3744, 624,36,4};
    	else
    		correctFreq = new int []{23294460, 58627800,31433400,6461620,6180020,4047644, 3473184, 224848,37260,4324};

		System.out.println("Generating and analysing all possible " + HAND_SIZE + " card hands");

		// Generate each hand and tally the number of each hand's type
		// Also total the number of hands analysed - time this operation
		long startTime = System.currentTimeMillis();
		int noOfHands = 0;
		PokerHand nextHand;
		while ((nextHand = myDeck.getNextHand()) != null )
		{
			noOfEachType[nextHand.getType()]++;
			noOfHands++;
		}
		long endTime = System.currentTimeMillis();
		
		// Display a summary of the results including the run time
		double totalPercent = 0;
		System.out.println("Type of Hand      Number of Hands   Percentage   Difference");

		for (int rank = 0; rank <= 9; rank++)
		{
			double percent = 100.0 * noOfEachType[rank] / noOfHands;
			totalPercent += percent;
			int difference = noOfEachType[rank] - correctFreq[rank];
			System.out.printf("%-18s%,12d%14.4f%,14d%n",PokerHand.TYPES[rank], 
					noOfEachType[rank], percent, difference);
		}
		System.out.printf("Totals    %,20d%14.4f%n", noOfHands, totalPercent);

		long totalTime = (endTime - startTime);
		System.out.println("Total time: " + (totalTime / 1000.0) + " seconds");
		
		System.out.println("Program complete");
	} // main method
} // AllPossiblePokerHands class

