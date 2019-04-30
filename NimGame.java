
public class NimGame
{

	private int currentStoneCount;
	private int upperBound;
	private int stoneRemoval;
	private String command;

	//Constants define
	public static final int JUMPOUTOFLOOP = -1;
	public static final int ZERO = 0;
	public static final int FIRST_ELEMENT = 0;
	public static final int SECOND_ELEMENT = 1;
	public static final int THIRD_ELEMENT = 2;
	public static final int FOURTH_ELEMENT = 3;
	public static final int FIFTH_ELEMENT = 4;

	private Nimsys nim = new Nimsys();


	public String startgame(String[] subCommand, NimPlayer[] Players)
	{
		//Initilization
		String player1 = subCommand[FOURTH_ELEMENT];
		String player2 = subCommand[FIFTH_ELEMENT];
		int currentStoneCount = Integer.parseInt(subCommand[SECOND_ELEMENT]);
		int upperBound = Integer.parseInt(subCommand[THIRD_ELEMENT]);
		int stoneRemoval = 0;
		String Name1 = nim.playerExits(player1, Players);
		String Name2 = nim.playerExits(player2, Players);
		String familyName1 = nim.playerExitsFamily(player1, Players);
		String familyName2 = nim.playerExitsFamily(player2, Players);


		//Input initial values
		System.out.println();
		System.out.println("Initial stone count: " + Integer.parseInt(subCommand[SECOND_ELEMENT]));
		System.out.println("Maximum stone removal: " + Integer.parseInt(subCommand[THIRD_ELEMENT]));
		System.out.println("Player 1: " + Name1);
		System.out.println("Player 2: " + Name2);
		System.out.println();

		printStone(currentStoneCount);

		//loop for game playing
		while(currentStoneCount >= ZERO)
		{
			//How many stones to remove in this turn?
			stoneRemoval = removeStone(familyName1);
			System.out.println();
			//Game playing
			currentStoneCount = gamePlay(currentStoneCount, upperBound, stoneRemoval,
										 Name1, Name2);
			if(currentStoneCount <= ZERO)
			{	
				return player2;
			}
				
			stoneRemoval = removeStone(familyName2);
			System.out.println();
			currentStoneCount  = gamePlay(currentStoneCount , upperBound, stoneRemoval,
										 Name2, Name1);
		}
		return player1;
	}

	public int gamePlay(int currentStoneCount, int upperBound, int stoneRemoval,
	 							 String player1, String player2)
	{
		if(stoneRemoval <= upperBound)
		{
			currentStoneCount = currentStoneCount - stoneRemoval;

			if(currentStoneCount <= ZERO)
			{
				//Player remove the last stone, Opponent wins
				System.out.println("Game Over");
				System.out.println(player2 + " wins!");
				//The StonesLeft set to -1, then the while(StonesLeft) break
				currentStoneCount = JUMPOUTOFLOOP;
			} else
			{
				printStone(currentStoneCount);
			}
		}
		//Return how many stones are left
		return currentStoneCount;
	}

	public int removeStone(String Name)//Method return how many stones removed 
	{
		System.out.println(Name + "'s turn - remove how many?");
		int stoneRemoval = Nimsys.keyboard.nextInt();
		return stoneRemoval;
	}

	public void printStone(int StonesLeft)
	{
		System.out.print(StonesLeft + " stones left:");
		for(int i = 0; i <= StonesLeft - 1; i++)
		{
			System.out.print(" *");
		}
		System.out.println();
	}

}