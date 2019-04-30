/*
 * ELEN90041 Project A
 * Name: Xiaoqian Xie, Student ID: 709716
 * Date: 22/03/2018
 */
import java.util.Scanner;
public class Nimsys
{
	//Constants define
	public static final int JUMPOUTOFLOOP = -1;
	//Public Scanner object define
	public static final Scanner keyboard = new Scanner(System.in);

	//Define a method for stones printing
	public static void printStone(int StonesLeft)
	{
		System.out.print(StonesLeft + " stones left:");
		for(int i = 0; i <= StonesLeft - 1; i++)
		{
			System.out.print(" *");
		}
		System.out.println();
	}
	/* 
	 * Method of game play:
	 * 'Player' is the player who will play this turn, 'Opponent' is the rest player
	 */
	public static int gamePlaying(int StonesLeft, int UpperBound, int NumStoneToRemove,
	 							 String Player, String Opponent)
	{
		if(NumStoneToRemove <= UpperBound)
		{
			StonesLeft = StonesLeft - NumStoneToRemove;

			if(StonesLeft <= 0)
			{
				//Player remove the last stone, Opponent wins
				System.out.println("Game Over");
				System.out.println(Opponent + " wins!");
				System.out.println();
				//The StonesLeft set to -1, then the while(StonesLeft) break
				StonesLeft = JUMPOUTOFLOOP;
			} else
			{
				printStone(StonesLeft);
			}
		}
		//Return how many stones are left
		return StonesLeft;
	}

	//Main function
	public static void main(String[] args)
	{
		//Initilization
		NimPlayer Player1 = new NimPlayer();
		NimPlayer Player2 = new NimPlayer();
		int InitialNumOfStone = 0;
		int UpperBound = 0;
		int NumStoneToRemove = 0;
		int StonesLeft = 0;
		String YesOrNo = 'Y';		//Quit the game or not?

		System.out.println("Welcome to Nim");
		System.out.println();
		System.out.println("Please enter Player 1's name:");
		Player1.setName();
		System.out.println("Please enter Player 2's name:");
		Player2.setName();

		//Main loop
		MainLoop:		//Name the main loop as Mainloop
		while(true)
		{
			//Input initial values
			System.out.println("Please enter upper bound of stone removal:");
			UpperBound = keyboard.nextInt();
			System.out.println();
			System.out.println("Please enter initial number of stones:");
			InitialNumOfStone = keyboard.nextInt();
			System.out.println();

			printStone(InitialNumOfStone);
			//Initialize StonesLeft
			StonesLeft = InitialNumOfStone;

			//loop for game playing
			while(true)
			{
				//How many stones to remove in this turn?
				NumStoneToRemove = Player1.removeStone();
				System.out.println();
				//Game playing
				StonesLeft = gamePlaying(StonesLeft, UpperBound, NumStoneToRemove,
										 Player1.getName(), Player2.getName());
				//If no stone is left, break the loop
				if(StonesLeft <= 0)
					break;

				NumStoneToRemove = Player2.removeStone();
				System.out.println();
				StonesLeft = gamePlaying(StonesLeft, UpperBound, NumStoneToRemove,
										 Player2.getName(), Player1.getName());

				if(StonesLeft <= 0)
					break;
			}
			//Ask players try again or not?
			System.out.print("Do you want to play again (Y/N):");
			while(true)
			{
				YesOrNo = keyboard.next();
				if(YesOrNo.equals("Y"))
				{
					System.out.println();
					break;
				} else
				{
					break MainLoop;
				}
			}
		}
	}
}
