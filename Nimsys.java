import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

public class Nimsys
{
	//Constants define
	public static final int MAX_NUMBER_OF_PLAYER = 100;
	public static final int FIRST_ELEMENT = 0;
	public static final int SECOND_ELEMENT = 1;
	public static final int THIRD_ELEMENT = 2;
	public static final int FOURTH_ELEMENT = 3;
	public static final int FIFTH_ELEMENT = 4;
	public static final int MAX_NUMBER_OF_DISPLAY = 9;
	public static final int INITIAL_INDEX = 0;
	public static final int FOR_ONE_PLAYER = 2;
	public static final int FOR_ALL_PLAYER = 1;

	//Public Scanner object define
	public static Scanner keyboard = new Scanner(System.in);
	private static Nimsys nim = new Nimsys();
	/*Actual index of space that is used in Players[] array,
	  when adding one player into the list the index start from 0*/
	private static int index = INITIAL_INDEX - 1;


	public static void main(String[] args) 
	{
		//To store type in commands
		String command;
		//Build an array of Nimplayer class and initialize it  
		NimPlayer[] Players = new NimPlayer[MAX_NUMBER_OF_PLAYER];
		nim.initiaPlayerList(Players);

		System.out.println("Welcome to Nim");
		//Main loop
		while(true)
		{
			//Wait for command input
			command = nim.waitForCommand();
			//run corresponding command
			nim.runCommand(command, Players);
		}
	}


	//Methods that are used in main methods

	public String waitForCommand()
	{
		String command;
		System.out.println();
		System.out.printf("$");
		command = keyboard.nextLine();
		return command;
	}

	//Method running command
	public void runCommand(String command, NimPlayer[] playerList)
	{
 		
		//Deviding the command by space or ",", put the tokens into an subCommand[] array
		StringTokenizer tokens = new StringTokenizer(command, " ,");
		String[] subCommand = new String[tokens.countTokens()];
		int i = 0;

		while(tokens.hasMoreTokens())
		{
			subCommand[i] = tokens.nextToken();
			i = i + 1;
		}

		/*Determine what is the command based on the first element of subCommand[] array, 
		  then execute the command*/
		if(subCommand[FIRST_ELEMENT].equals("addplayer"))
		{
			nim.addplayer(subCommand, playerList);
		}
		if(subCommand[FIRST_ELEMENT].equals("removeplayer"))
		{
			nim.removeplayer(subCommand, playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("editplayer"))
		{
			nim.editplayer(subCommand,playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("resetstats"))
		{
			nim.resetstats(subCommand, playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("displayplayer"))
		{
			nim.displayplayer(subCommand, playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("rankings"))
		{
			nim.rankings(subCommand, playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("startgame"))
		{
			nim.startgame(subCommand, playerList);
		}

		if(subCommand[FIRST_ELEMENT].equals("exit"))
		{	
			nim.exit();
		}
	}


	//addplayer command
	public void addplayer(String[] subCommand, NimPlayer[] Players)
	{
		if(!playerExits(subCommand, Players))//if the player not exists
		{
			index = index + 1;
			Players[index].setName(subCommand[SECOND_ELEMENT], subCommand[THIRD_ELEMENT],
								   subCommand[FOURTH_ELEMENT]);
		} else
		{
			System.out.println("The player already exists.");
		}
	}

	//removeplayer command
	public void removeplayer(String[] subCommand, NimPlayer[] Players)
	{
		if(subCommand.length == FOR_ALL_PLAYER)//remove all player
		{
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String answer = keyboard.nextLine();
			if(answer.equals("y"))
			{
				nim.initiaPlayerList(Players);
			}
		} else //remove a playe based on the username
		{
			if(playerExits(subCommand, Players))//first make sure the player exists
			{
				for(int i = 0; i <= index; i++)
				{
					if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
					{
						for(int j = 1; i+j <= index; j++)
						{
							//delete the player, the players after the player move forward
							Players[i+j-1].setName(Players[i+j].getUserName(),
							Players[i+j].getGivenName(), Players[i+j].getFamilyName());
						}
						//empty the last element
						Players[index] = new NimPlayer();
						//index reduce 1
						index = index - 1;
					}
				}
			} else
			{
				System.out.println("The player does not exist.");
			}
		}
		
	}

	//editplayer command
	public void editplayer(String[] subCommand, NimPlayer[] Players)
	{
		if(playerExits(subCommand, Players))//first make sure the player exists
		{
			for(int i = 0; i <= index; i++)
				{
					if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
					{
						//assign new information to the player
						Players[i].setName(subCommand[THIRD_ELEMENT],subCommand[FOURTH_ELEMENT]);
						break;
					}
				}
		} else
		{
			System.out.println("The player does not exist.");
		}
	}

	//resetstats command
	public void resetstats(String[] subCommand, NimPlayer[] Players)
	{
		if(subCommand.length == FOR_ALL_PLAYER)//reset all player
		{
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String answer = keyboard.nextLine();
			if(answer.equals("y"))
			{
				for(int i = 0; i <= index; i++)
				{
					//reset stats
					Players[i].reset();
				}
			}
		} else if(playerExits(subCommand, Players))//reset a certain player based on username
		{
			for(int i = 0; i <= index; i++)
			{
				if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
				{
					Players[i].reset();
					break;
				}
			}
		} else
		{
				System.out.println("The player does not exist.");
		}
	}

	//displayplayer command
	public void displayplayer(String[] subCommand, NimPlayer[] Players)
	{

		//an array store the displayer order 
		int[] maxIndex = new int[index+1];

		//Initialization from 0 to index
		for(int i = 0; i <= index; i++)
		{
			maxIndex[i] = i;
		}

		//First do the alphabetic sorting
		alphabeticSorting(maxIndex, Players);

		if(subCommand.length == FOR_ALL_PLAYER)//Display all player based on alphabetic order
		{
			for(int i = 0; i <= index; i++)
			{
				System.out.println(Players[maxIndex[i]].getFullInformation());
			}

		} else if(playerExits(subCommand, Players))//If the player exists then displayer 
		{
				for(int i = 0; i <= index; i++)
				{
					if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
					{
						System.out.println(Players[i].getFullInformation());
					}
				}

		} else
		{
			System.out.println("The player does not exist.");
		}
		

	}

	//rankings command 
	public void rankings(String[] subCommand, NimPlayer[] Players)
	{
		
		//Sorting
		//store the index of the Players
		int[] maxIndex = new int[index+1];
		int tempMax = 0;
		//Initialization
		for(int i = 0; i <= index; i++)
		{
			maxIndex[i] = i;
		}

		//Sorting based on winning ratio
		for(int k = 0; k <= index; k++)
		{
			int max = Players[maxIndex[k]].getWinRatio();

			for(int i = k+1; i <= index; i++)
			{
				String maxUserName = Players[maxIndex[k]].getUserName();
				if(Players[maxIndex[i]].getWinRatio() > max)
				{
					max = Players[maxIndex[i]].getWinRatio();
					tempMax = i;
					nim.interchange(tempMax, k, maxIndex);
				}
				//if the ratio is same, sort based on alphabetic order
				if(Players[maxIndex[i]].getWinRatio() == max &&
				   Players[maxIndex[i]].getUserName().compareToIgnoreCase(maxUserName) < 0)
				{
					max = Players[maxIndex[i]].getWinRatio();
					tempMax = i;
					nim.interchange(tempMax, k, maxIndex);
				}
			}
		}

		if(subCommand.length == FOR_ONE_PLAYER && subCommand[1].equals("asc"))
		{			
			//Only display 10 players
			int rankingIndex = 0;
			if(index > MAX_NUMBER_OF_DISPLAY)
			{
				rankingIndex = MAX_NUMBER_OF_DISPLAY;
			}else
			{
				rankingIndex = index;
			}

			for(int i = rankingIndex; i >= 0; i--)
			{
				rankingsDisplay(maxIndex, Players, i);
			}

		}else 
		{
			//Only display 10 players
			int rankingIndex = 0;
			if(index > MAX_NUMBER_OF_DISPLAY)
			{
				rankingIndex = MAX_NUMBER_OF_DISPLAY;
			}else
			{
				rankingIndex = index;
			}

			for(int i = 0; i <= rankingIndex; i++)
			{
				rankingsDisplay(maxIndex, Players, i);
			}
		}
		

	}

	//startgame command
	public void startgame(String[] subCommand, NimPlayer[] Players)
	{
		//instance of NimGame
		NimGame gamePlay = new NimGame();
		String winner = " ";

		//make sure both of player exist
		if(playerExits(subCommand[FOURTH_ELEMENT], subCommand[FIFTH_ELEMENT], Players))
		{
			winner = gamePlay.startgame(subCommand, Players);
			for(int i = 0; i <= index; i++)
			{
				//increase the number of game played by 1
				if(subCommand[FOURTH_ELEMENT].equals(Players[i].getUserName()))
				{
					Players[i].gamePlayed();
				}
				if(subCommand[FIFTH_ELEMENT].equals(Players[i].getUserName()))
				{
					Players[i].gamePlayed();
				}
			}
			//eat the %n
			keyboard.nextLine();
		}
		else
		{
			System.out.println("One of the players does not exist.");
		}

		for(int i = 0; i <= index; i++)
		{
			if(winner.equals(Players[i].getUserName()))
			{
				//increase the number of game won of the winner
				Players[i].gameWon();
			}
		}

	}

	//exit command
	public void exit()
	{
		System.out.println();
		System.exit(0);
	}

	
	public Boolean playerExits(String[] subCommand, NimPlayer[] Players)
	{
		int i;
		for(i = 0; i <= index; i++)
		{
			if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
				return true;
		}

		return false;
	}

	public Boolean playerExits(String userName1, String userName2, NimPlayer[] Players)
	{
		int i;
		for(i = 0; i <= index; i++)
		{
			if(userName1.equals(Players[i].getUserName()))
			{
				for(int j = 0; j <= index; j++)
				{
					if(userName2.equals(Players[j].getUserName()))
					return true;
				}
			}
		}

		return false;
	}

	//return the family name and given name
	public String playerExits(String username, NimPlayer[] Players)
	{
		int i;
		for(i = 0; i <= index; i++)
		{
			if(username.equals(Players[i].getUserName()))
				return Players[i].getFamilyName()  + " " + Players[i].getGivenName();
		}

		return "not found";
	}

	//return the family name 
	public String playerExitsFamily(String username, NimPlayer[] Players)
	{
		int i;
		for(i = 0; i <= index; i++)
		{
			if(username.equals(Players[i].getUserName()))
				return Players[i].getFamilyName();
		}

		return "not found";
	}


 	public void initiaPlayerList(NimPlayer[] Players)
 	{
 		//initialization 
		for(int i = 0; i < Players.length; i++)
		{
			Players[i] = new NimPlayer();
		}

		index = -1;
 	}

 	//exchange the value of two array element
 	public void interchange(int i, int j, int[] a)
 	{
 		int temp;
  		temp = a[i];
  		a[i] = a[j];
  		a[j] = temp;
 	}

 	public void alphabeticSorting(int[] maxIndex, NimPlayer[] Players)
 	{
 		int tempMax = 0;
		for(int k = 0; k <= index; k++)
		{
			String maxUserName = Players[maxIndex[k]].getUserName();

			for(int i = k+1; i <= index; i++)
			{

				if(Players[maxIndex[i]].getUserName().compareToIgnoreCase(maxUserName) < 0)
				{
					maxUserName = Players[maxIndex[i]].getUserName();
					tempMax = i;
					nim.interchange(tempMax, k, maxIndex);
				}
				
			}
		}

 	}

 	public void rankingsDisplay(int[] maxIndex, NimPlayer[] Players, int i)
 	{
 		System.out.printf("%-4s | %s games | %s %s",
					 new DecimalFormat("###%").format(Players[maxIndex[i]].getWinRatio()/100.0),
					 new DecimalFormat("00").format(Players[maxIndex[i]].getGamePlayed()),
					 Players[maxIndex[i]].getFamilyName(), Players[maxIndex[i]].getGivenName());
				System.out.println();
 	}



}
