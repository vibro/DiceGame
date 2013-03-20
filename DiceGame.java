public class DiceGame {

  // FILE NAME: DiceGame.java
  // WRITTEN BY:Tori Brown
  // WHEN:9/15/12
  
  // PURPOSE: Simulates a Dice Poker game played between the computer and
  // user. This class definition contains a main() method that assumes 
  // that the user enters a name in the command line, for example: 
  //        java PlayDice Dave


/**********************************/
  private void printArray (int[] arr) {
  
    // prints the contents of the input array. 
    for (int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
  }


/**********************************/
	
  private int[] throw5Dice(int [] dArray) {
  
	//Throws the dice 5 times
	// fills the input array with five random integers between 1 and 6,
	// simulating a rolling of 5 dice
	// returns the updated input array
         
     for (int i = 0; i < 5; i++)
        dArray[i] = (int)(Math.random() * 6) + 1;
     
     printArray(dArray);
     System.out.print(": ");
     return dArray;
  
  }	


/**********************************/

  private void accumulateValues(int[] input, int[] diceResults) {
  
  // Counts how many distinct values appear in the input array
  // and stores each count onto the diceResults array.
  // CONDITION: diceResults should have enough length to accomodate the values
  // found in the input array. 
      for (int i=0; i<5; i++) 
	  for (int j=1; j<7; j++)
	      if (input[i]==j){
		  diceResults[j]++;
	      }
	        
  }
  
/**********************************/
    
  public int getRank (int[] input) {
  
    // given an input array storing five dice values, determines the rank
    // (i.e. an integer between 0 and 6) of the set of values
    
    // first determine how many of each dice value are stored in input
	// by calling the accumulateValues() method
    int[] DiceResults = new int[7];
	accumulateValues(input, DiceResults);
	
	// Then, determine the rank based on the accumulated values
	int rank = 0;
	for (int i=1; i<7; i++)
	    if (DiceResults[i] == 2 && rank != 1){
		rank = 1;
	    } else if (DiceResults[i] == 3 && rank !=1){
		rank = 3;
	    } else if (DiceResults[i] == 2 && rank ==1){
		rank = 2;
	    } else if (DiceResults[i] == 3 && rank ==1){
		rank = 4;
	    } else if (DiceResults[i] == 4){
		rank = 5;
	    } else if (DiceResults[i] == 5){
		rank = 6;
	    }
	

	//print out rank
	if (rank ==0) System.out.println("Nothing");
    	if (rank ==1) System.out.println("One Pair");
	if (rank ==2) System.out.println("Two Pair");
	if (rank ==3) System.out.println("Three of a Kind");
	if (rank ==4) System.out.println("Full House");
	if (rank ==5) System.out.println("Four of a Kind");
	if (rank ==6) System.out.println("Five of a Kind");
	
	return rank;
  }
  

/**********************************/
  public int playOneRound() {
  // returns 0 if computer wins the round
  // returns 1 if player wins the round
  // returns 2 if a tie
  
	// declare the array that will hold the 5 dice
	int[] DiceArray = new int[5];
	// First simulate the computer's play in the round & find its rank
	System.out.print("HAL: ");
	throw5Dice(DiceArray);
	int comp = getRank(DiceArray);
	//then simulate the player's play in the round & find its rank
	System.out.print("You: ");
	throw5Dice(DiceArray);
	int player = getRank(DiceArray);
	// finally determine the winner based on their ranks and return the correct integer
	if(comp>player){
	    return 0;
	}if(player>comp){
	    return 1;
	}else{
	    return 2;
	}
  }
  

/**********************************/
  public void playDiceGame (String name, int numRounds) {
    // simulates the playing of numRounds of the Dice Poker game between 
    // HAL and the input name, and prints the winner at the end
   
    System.out.println("Hello " + name + ". I'm completely operational and all my circuits are functioning perfectly.");
	System.out.println("Would you like to play a game of Dice Poker? I play very well.");
 
    // declare variables for storing the score of all the rounds
	int comp = 0;
	int player =0;

    // for each round of the game:
       // play the round, determine the winner of the round
	for(int i = 1; i < (numRounds+1); i++){
	    System.out.println("***Round "+(i));
	    int winner = playOneRound();
	    if (winner == 0){
	       comp++;
	   } else if (winner == 1){
	       player++;
	   }
	}
  	
    // After all rounds played, determine the final winner of the game and print the results
	if(comp>player){
	    System.out.println("The game was won by the HAL with a score of " + comp+ " to " +player+ " in " +numRounds+ " rounds.");
	}else if (player>comp){
	    System.out.println("The game was won by " +name+" with a score of " + player+ " to " +comp+ " in " +numRounds+ " rounds.");
	} else if (player==comp) {
	     System.out.println("The game was tied with a score of " + comp+ " to " +player+ " in " +numRounds+ " rounds.");
	}
  }


/**********************************/

  public static void main (String args[]) {

	// Create an instance of a new game
	DiceGame game = new DiceGame();
	
	
	
	// and then play the game if they entered a player's name
	if (args.length > 0)
	    game.playDiceGame(args[0], 5);
	else
	    System.out.println("Please enter a name in the command line");
    // when you have working code, increment the number of rounds of play
    
  }

}
