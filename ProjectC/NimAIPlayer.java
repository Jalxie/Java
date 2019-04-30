/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer
{
	// you may further extend a class or implement an interface
	// to accomplish the tasks.	
	public static final int RESET_VALUE = 0;
	public static final int INITIAL_VALUE = 0;
	public static final int REMOVE_ONE = 1;
	//constructor
	public NimAIPlayer(String username, String givenName, String familyName, int gamePlayed,
	 int gameWon, String humanOrAI) 
	{
		super(username, givenName, familyName, gamePlayed, gameWon, humanOrAI);
	}
	
	public NimAIPlayer(String username, String givenName, String familyName) 
	{
		super(username, givenName, familyName);
		humanOrAI = "AI";
	}

	//removeStone() method
	public int removeStone(int Bound, int StonesLeft)//Method return how many stones removed 
	{
		int stoneRemoval = REMOVE_ONE;
		while(true)
		{
			System.out.println(getFamilyName() + "'s turn - remove how many?");
			
			//if the stone left is not k(M+1)+1, then make the k(M+1)+1
			if((StonesLeft - 1)%(Bound + 1) != 0)
			{
				//loop from 1 to bound, find one stoneRemoval makes the left stone is k(M+1)+1
				while(stoneRemoval >= 1 && stoneRemoval <= Bound && stoneRemoval <= StonesLeft)
				{
					if((StonesLeft - stoneRemoval - 1)%(Bound + 1) == 0)
					{
						return stoneRemoval;
					}
					stoneRemoval++;
				}
			}else
			{
				//if the condition does't meet, just remove one stone.
				return REMOVE_ONE;
			}
		}

	}
	
	public String advancedMove(boolean[] available, String lastMove) 
	{
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
