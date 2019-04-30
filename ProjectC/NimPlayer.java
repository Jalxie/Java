public abstract class NimPlayer
{
	//Define constants
	public static final String NULL = "NULL";
	public static final int RESET_VALUE = 0;
	public static final int INITIAL_VALUE = 0;

	private int gamePlayed;
	private int gameWon;
	//String identify if the player is human or machine
	String humanOrAI = "Unknow";

	//Object of the Name information(Username, familyname, givenname)
	private NimName idAndName = new NimName();

	//Abstract removeStone() Method
	public abstract int removeStone(int Bound, int StonesLeft);

	//Constructors
	public NimPlayer()
	{
		idAndName.setName(NULL, NULL, NULL);
		gamePlayed = INITIAL_VALUE;
		gameWon = INITIAL_VALUE;
	}
	public NimPlayer(String username, String givenName, String familyName, int gamePlayed,
	 int gameWon, String humanOrAI)
	{
		idAndName.setName(username, givenName, familyName);
		this.gamePlayed = gamePlayed;
		this.gameWon = gameWon;
		this.humanOrAI = humanOrAI;
	}
	public NimPlayer(String username, String givenName, String familyName)
	{
		idAndName.setName(username, givenName, familyName);
		gamePlayed = INITIAL_VALUE;
		gameWon = INITIAL_VALUE;
	}


	//reset game data to 0
	public void reset()
	{
		gamePlayed = RESET_VALUE;
		gameWon = RESET_VALUE;
	}


	//increase number of game won by 1
	public void gameWon()
	{
		gameWon = gameWon + 1;
	}
	//increase the number of game played by 1
	public void gamePlayed()
	{
		gamePlayed = gamePlayed + 1;
	}

	//increase number of game won by 1
	public void gameWon(int gameWon)
	{
		this.gameWon = gameWon;
	}
	//increase the number of game played by 1
	public void gamePlayed(int gamePlayed)
	{
		this.gamePlayed = gamePlayed;
	}

	//Mutators
	//Setting the name of the player
	public void setName(String username, String givenName, String familyName)
	{
		idAndName.setName(username, givenName, familyName);
	}
	
	public void setName(String givenName, String familyName)
	{
		idAndName.setName(givenName, familyName);
	}

	//Setting all player information
	public void setAll(String username, String givenName, String familyName, int gamePlayed,
	 int gameWon)
	{
		idAndName.setName(username, givenName, familyName);
		this.gamePlayed = gamePlayed;
		this.gameWon = gameWon;
	}

	//Initialization
	public void initialization()
	{
		setName(NULL, NULL, NULL);
		gamePlayed(INITIAL_VALUE);
		gameWon(INITIAL_VALUE);
	}

	//Accessors
	//Get information
	public String getUserName()
	{
		return idAndName.getUserName();
	}

	public String getGivenName()
	{
		return idAndName.getGivenName();
	}

	public String getFamilyName()
	{
		return idAndName.getFamilyName();
	}
	//return the full name of player
	public String getName()
	{
		return idAndName.getName();
	}
	//return number of game played
	public int getGamePlayed()
	{
		return gamePlayed;
	}

	public String getFullInformation()
	{
		return idAndName.getNameReverse()+","+gamePlayed+" games,"+gameWon+" wins";
	}

	public String getFullInformationWithState()
	{
		return idAndName.getName()+","+gamePlayed+" games,"+gameWon+" wins," + humanOrAI;
	}

	//return of the ratio of winning
	public double getWinRatio()
	{
		//If not play any game, return 0
		if(gamePlayed == 0)
		{
			return 0;
		}else
		{
			return (double)gameWon/gamePlayed;
		}
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
