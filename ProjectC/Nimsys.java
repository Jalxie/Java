/*
ELEN90041 ProjectC
Author: Xiaoqian Xie
StudentID:709716
*/
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

import java.io.File;//import file class

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.FileNotFoundException;


public class Nimsys
{
	//Constants define
	public static final Boolean NO_EXCEPTION = false;
	public static final Boolean EXCEPTION_CATCHED = true;
	public static final int MAX_NUMBER_OF_PLAYER = 100;
	public static final int FIRST_ELEMENT = 0;
	public static final int SECOND_ELEMENT = 1;
	public static final int THIRD_ELEMENT = 2;
	public static final int FOURTH_ELEMENT = 3;
	public static final int FIFTH_ELEMENT = 4;
	public static final int SIXTH_ELEMENT = 5;
	public static final int SEVENTH_ELEMENT = 6;
	public static final int EIGHTH_ELEMENT = 7;
	public static final int MAX_NUMBER_OF_DISPLAY = 9;
	public static final int INITIAL_INDEX = -1;
	public static final int FOR_ONE_PLAYER = 2;
	public static final int FOR_ALL_PLAYER = 1;
	public static final int COMMAND_LENGTH_TWO = 2;
	public static final int ONE_INDEX = 1;
	public static final int LENGTH_OF_ADDPLAYER = 4;
	public static final int LENGTH_OF_REMOVEPLAYER = 2;
	public static final int LENGTH_OF_EDITPLAYER = 4;
	public static final int LENGTH_OF_RESETSTATES = 2;
	public static final int LENGTH_OF_DISPLAYPLAYER = 2;
	public static final int LENGTH_OF_RANKINGS = 2;
	public static final int LENGTH_OF_STARTGAME = 5;
	public static final String FILENAME = "players.dat";
	public static final String HUMAN = "Human";
	public static final String AI = "AI";
	public static final int LENGHT_OF_INFORMATION = 7;
	public static final int ONE = 1;




	//Public Scanner object define
	public static Scanner keyboard = new Scanner(System.in);
	private static Nimsys nim = new Nimsys();
	/*The variable index is the actual index of space that is used in Players[] array, 
	starting from -1 after adding one player into the list the index start from 0 */
	private static int index = INITIAL_INDEX;

	//Main method
	public static void main(String[] args) 
	{
		//To store typing in commands
		String command;
		//Build an array of Nimplayer class (100) and initialize it using constructor 
		NimPlayer[] Players = new NimPlayer[MAX_NUMBER_OF_PLAYER];

		//Load player data from players.dat, and return the last index(index starts from 0)
		index = nim.loadData(FILENAME, Players);

		//Welcome message
		System.out.println("Welcome to Nim");

		//Main loop
		while(true)
		{
			//Waiting for command input
			command = nim.waitForCommand();
			//run corresponding command
			nim.runCommand(command, Players);
		}
	}




	//Methods that are used in main method

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
		//First set it true, when one command is excute change it to false
		Boolean exceptionIndicator = EXCEPTION_CATCHED;

		while(tokens.hasMoreTokens())
		{
			subCommand[i] = tokens.nextToken();
			i = i + 1;
		}

		//Invalid command exception
		try
		{		
				/*Determine what is the command based on the first element of subCommand[] array, 
				  then execute the command*/
				if(subCommand[FIRST_ELEMENT].equals("addplayer"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.addplayer(subCommand, playerList);
				}
				//New method for addaiplayer
				if(subCommand[FIRST_ELEMENT].equals("addaiplayer"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.addaiplayer(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("removeplayer"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.removeplayer(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("editplayer"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.editplayer(subCommand,playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("resetstats"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.resetstats(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("displayplayer"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.displayplayer(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("rankings"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.rankings(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("startgame"))
				{
					exceptionIndicator = NO_EXCEPTION; 
					nim.startgame(subCommand, playerList);
				}
		
				if(subCommand[FIRST_ELEMENT].equals("exit"))
				{	
					exceptionIndicator = NO_EXCEPTION; 
					nim.exit(FILENAME, playerList);
				}
		
				if(exceptionIndicator)
				{
		
					throw new Exception("\'" + subCommand[FIRST_ELEMENT] + "\' " +
					 "is not a valid command.");
				}
		}

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}

	}


	//addplayer command
	public void addplayer(String[] subCommand, NimPlayer[] Players)
	{
		//Invalid command exception
		try
		{
			if(subCommand.length < LENGTH_OF_ADDPLAYER)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}
			if(!playerExits(subCommand, Players))//if the player not exists, add the player
			{
				//index increase by 1
				index = index + ONE_INDEX;
				Players[index] = new NimHumanPlayer(subCommand[SECOND_ELEMENT],
				 subCommand[THIRD_ELEMENT], subCommand[FOURTH_ELEMENT]);
			} else//If the player already exist
			{
				System.out.println("The player already exists.");
			}
		}
		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}
	}

	//addplayer command
	public void addaiplayer(String[] subCommand, NimPlayer[] Players)
	{
		//Invalid command exception
		try
		{
			if(subCommand.length < LENGTH_OF_ADDPLAYER)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}
			if(!playerExits(subCommand, Players))//if the player not exists, add the player
			{
				//index increase by 1
				index = index + ONE_INDEX;
				Players[index] = new NimAIPlayer(subCommand[SECOND_ELEMENT],
				 subCommand[THIRD_ELEMENT], subCommand[FOURTH_ELEMENT]);
			} else//If the player already exist
			{
				System.out.println("The player already exists.");
			}
		}
		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}
	}

	//removeplayer command
	public void removeplayer(String[] subCommand, NimPlayer[] Players)
	{
		//Invalid command exception
		try
		{
			if(subCommand.length > LENGTH_OF_REMOVEPLAYER)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}


			if(subCommand.length == FOR_ALL_PLAYER)//remove all player
			{
				System.out.println("Are you sure you want to remove all players? (y/n)");
				String answer = keyboard.nextLine();
				if(answer.equals("y"))
				{
					//set index to -1 means no data in system
					index = INITIAL_INDEX;
				}
			} else //remove a player based on the username
			{
				if(playerExits(subCommand, Players))//first make sure the player exists
				{
					for(int i = 0; i <= index; i++)
					{
						//Find the player and remove it
						if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
						{
							for(int j = 1; i+j <= index; j++)
							{
								//delete the player, the players after the player move forward
								Players[i+j-1].setName(Players[i+j].getUserName(),
								Players[i+j].getGivenName(), Players[i+j].getFamilyName());
							}
							//empty the last useless element
							Players[index].initialization();
							//index reduce by 1
							index = index - ONE_INDEX;
						}
					}
				} else
				{
					System.out.println("The player does not exist.");
				}
			}
		}

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}
		
	}

	//editplayer command
	public void editplayer(String[] subCommand, NimPlayer[] Players)
	{
		try
		{
			if(subCommand.length < LENGTH_OF_EDITPLAYER)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}

			if(playerExits(subCommand, Players))//first make sure the player exists
			{
				for(int i = 0; i <= index; i++)
					{
						if(subCommand[SECOND_ELEMENT].equals(Players[i].getUserName()))
						{
							//assign new information to the player
							Players[i].setName(subCommand[THIRD_ELEMENT], 
								subCommand[FOURTH_ELEMENT]);
							break;
						}
					}
			} else
			{
				System.out.println("The player does not exist.");
			}
		}

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}
	}

	//resetstats command
	public void resetstats(String[] subCommand, NimPlayer[] Players)
	{
		try
		{	
			if(subCommand.length > LENGTH_OF_RESETSTATES)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}


			if(subCommand.length == FOR_ALL_PLAYER)//reset all player
			{
				System.out.println("Are you sure you want to reset all player statistics? (y/n)");
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

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}

	}

	//displayplayer command
	public void displayplayer(String[] subCommand, NimPlayer[] Players)
	{
		try
		{
			if(subCommand.length > LENGTH_OF_DISPLAYPLAYER)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}

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

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}

	}

	//rankings command 
	public void rankings(String[] subCommand, NimPlayer[] Players)
	{
		try
		{
			if(subCommand.length > LENGTH_OF_RANKINGS)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}

			//Sorting
			//store the index of the each Player
			int[] maxIndex = new int[index+1];
			int tempMax = 0;
			//Initialization
			for(int i = 0; i <= index; i++)
			{
				maxIndex[i] = i;
			}

			//Ascend sorting
			if(subCommand.length == COMMAND_LENGTH_TWO && subCommand[1].equals("asc"))
			{	
				//Sorting based on winning ratio
				for(int k = 0; k <= index; k++)
				{
					double max = Players[maxIndex[k]].getWinRatio();
					for(int i = k+1; i <= index; i++)
					{
						String maxUserName = Players[maxIndex[k]].getUserName();
						double obj = Players[maxIndex[i]].getWinRatio();

						//Compare the winning ratio
		      			int retval = Double.compare(obj, max);

						if(retval > 0)
						{
							max = Players[maxIndex[i]].getWinRatio();
							tempMax = i;
							nim.interchange(tempMax, k, maxIndex);
						}
						//if the ratio is same, sort based on alphabetic order
						if(retval == 0 &&
						   Players[maxIndex[i]].getUserName().compareToIgnoreCase(maxUserName) > 0)
						{
							max = Players[maxIndex[i]].getWinRatio();
							tempMax = i;
							nim.interchange(tempMax, k, maxIndex);
						}
					}
				}

				//Only display last 10 players
				int rankingIndex = 0;
				if(index > MAX_NUMBER_OF_DISPLAY)
				{
					rankingIndex = index - MAX_NUMBER_OF_DISPLAY;
				}else
				{
					rankingIndex = 0;
				}

				for(int i = index; i >= rankingIndex; i--)
				{
					rankingsDisplay(maxIndex, Players, i);
				}

			}else //Descend sorting
			{
				//Sorting based on winning ratio
				for(int k = 0; k <= index; k++)
				{
					double max = Players[maxIndex[k]].getWinRatio();
					for(int i = k+1; i <= index; i++)
					{
						String maxUserName = Players[maxIndex[k]].getUserName();
						double obj = Players[maxIndex[i]].getWinRatio();

						//Compare the winning ratio
		      			int retval = Double.compare(obj, max);

						if(retval > 0)
						{
							max = Players[maxIndex[i]].getWinRatio();
							tempMax = i;
							nim.interchange(tempMax, k, maxIndex);
						}
						//if the ratio is same, sort based on alphabetic order
						if(retval == 0 &&
						   Players[maxIndex[i]].getUserName().compareToIgnoreCase(maxUserName) < 0)
						{
							max = Players[maxIndex[i]].getWinRatio();
							tempMax = i;
							nim.interchange(tempMax, k, maxIndex);
						}
					}
				}

				//Only display first 10 players
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


		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}

	}

	//startgame command
	public void startgame(String[] subCommand, NimPlayer[] Players)
	{
		try
		{
			if(subCommand.length < LENGTH_OF_STARTGAME)
			{
				throw new Exception("Incorrect number of arguments supplied to command.");
			}


			//instance of NimGame
			NimGame gamePlay = new NimGame();
			String winner = " ";

			//make sure both of players exist
			if(playerExits(subCommand[FOURTH_ELEMENT], subCommand[FIFTH_ELEMENT], Players) )
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

		catch(Exception e)
		{
			String message = e.getMessage();
      		System.out.println(message);
		}


	}

	//exit command
	public void exit(String fileName, NimPlayer[] Players)
	{
		//saveData before exit.
		saveData(fileName, Players);
		System.out.println();
		System.exit(0);
	}


	//return the index of a player
	public int playerIndex(String[] subCommand, NimPlayer[] Players, int playNumber)
	{
		int i;
		for(i = 0; i <= index; i++)
		{
			if(subCommand[THIRD_ELEMENT+playNumber].equals(Players[i].getUserName()))
				return i;
		}

		return INITIAL_INDEX;
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

	public Boolean playerExits(String line, String userName)
	{
		//Deviding the command by space or ",", put the tokens into an subCommand[] array
		StringTokenizer tokens = new StringTokenizer(line, " ,");
		
		if(tokens.nextToken().equals(userName))
		{
			return true;
		} else
		{
			return false;
		}

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


 	//exchange the value of two array element
 	public void interchange(int i, int j, int[] a)
 	{
 		int temp;
  		temp = a[i];
  		a[i] = a[j];
  		a[j] = temp;
 	}

 	//Sorting the index of each object not the actual objects
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
 		//round the ratio to nearest integer
 		//Using DecimalFormat class
 		System.out.printf("%-4s | %s games | %s %s",
					 new DecimalFormat("###%").
					 format((int)Math.round(100.0*Players[maxIndex[i]].getWinRatio())/100.0),
					 new DecimalFormat("00").format(Players[maxIndex[i]].getGamePlayed()),
					 Players[maxIndex[i]].getFamilyName(), Players[maxIndex[i]].getGivenName());
				System.out.println();
 	}



 	//File methods
 	//Creat a file
	public void creatFile(String fileName)
	{
		 PrintWriter outputStream = null;
        try
        {
            outputStream = 
                 new PrintWriter(new FileOutputStream(fileName, true));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file " + fileName + " .");
            System.exit(0);
        }
        outputStream.close( );
	}

	//Delete a file 
	public void deleteFile(String fileName)
	{
		File fileObject = new File(fileName);
		if(fileObject.exists( ))
        {
        	fileObject.delete();
        }
	}

	//write one line of data into a file
 	public void fileInput(String fileName, String textInput)
	{
        PrintWriter outputStream = null;
        try
        {
            outputStream = 
                 new PrintWriter(new FileOutputStream(fileName, true));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file " + fileName + " .");
            System.exit(0);
        }

        outputStream.println(textInput);
        outputStream.close( );        
	}

	//read one line of data from a file
	public String fileReadLine(String fileName)
	{
		try
        {
			BufferedReader inputStream = 
              new BufferedReader(new FileReader(fileName));

            String line = inputStream.readLine( );
            inputStream.close( );
            return line;
        }
		catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " was not found");
            System.out.println("or could not be opened.");
            return "File not found!";
        }
        catch(IOException e)
        {
            System.out.println("Error reading "+ fileName +" .");
            return "Error!";
        }

	}


	//save all the data
	public void saveData(String fileName, NimPlayer[] Players)
	{
		String line;
		Boolean playerFound = false;

		//an array store the displayer order 
		int[] maxIndex = new int[index+1];
		//Initialization from 0 to index
		for(int i = 0; i <= index; i++)
		{
				maxIndex[i] = i;
		}
		//do the alphabetic sorting
		alphabeticSorting(maxIndex, Players);


        File fileObject = new File(fileName);
        //if the players.dat not exists creat one, else delete file creat a empty file
        if(!fileObject.exists( ))
        {
        	creatFile(fileName);
        } else
        {
        	deleteFile(fileName);
        	creatFile(fileName);
        }

		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " was not found");
            System.out.println("or could not be opened.");
        }

        //Right all the player information into the empty file
		for(int i = 0; i <= index; i++)
		{
			playerFound = false;
			while(inputStream.hasNextLine( ))
			{	
				line = inputStream.nextLine( );
				if(playerExits(line, Players[maxIndex[i]].getUserName()))
				{
					playerFound = true;
				}
			}

			if(!playerFound)
			{
				fileInput(fileName, Players[maxIndex[i]].getFullInformationWithState());
			}
		}

	}

	//return number of players stored in the system
	public int loadData(String fileName, NimPlayer[] Players)
	{
		String line;
		int i = 0;
		int numberOfPlayers = 0;
		File fileObject = new File(fileName);


        //if the players.dat not exists
        if(!fileObject.exists( ))
        {
        	creatFile(fileName);
        }

		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " was not found");
            System.out.println("or could not be opened.");
        }


        while(inputStream.hasNextLine( ))
		{	
			line = inputStream.nextLine( );
			//Deviding the command by space or ",", put the tokens into an subCommand[] array
			StringTokenizer tokens = new StringTokenizer(line, " ,");
			String[] playerInformation = new String[tokens.countTokens()];
			i = 0;
			while(tokens.hasMoreTokens())
			{
				playerInformation[i] = tokens.nextToken();
				i = i + 1;
			}


			int gamePlayed = Integer.parseInt(playerInformation[FOURTH_ELEMENT]);
			int gameWon = Integer.parseInt(playerInformation[SIXTH_ELEMENT]);
			String username = playerInformation[FIRST_ELEMENT];
			String givenName = playerInformation[SECOND_ELEMENT];
			String familyName = playerInformation[THIRD_ELEMENT];

			if(playerInformation[EIGHTH_ELEMENT].equals(HUMAN))
			{
				Players[numberOfPlayers] = new NimHumanPlayer(username, givenName, familyName,
				 gamePlayed, gameWon, HUMAN);
			}
			else
			{
				Players[numberOfPlayers] = new NimAIPlayer(username, givenName, familyName,
				 gamePlayed, gameWon, AI);

			}
			numberOfPlayers  = numberOfPlayers + ONE;
		}

		return numberOfPlayers - ONE;


	}


}
