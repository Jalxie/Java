public class NimPlayer
{
	private int gamePlayed;
	private int gameWon;

	private NimName idAndName = new NimName();

	//Constructor
	public NimPlayer()
	{
		idAndName.setName("null","null","null");
		gamePlayed = 0;
		gameWon = 0;
	}
	
	//reset game data to 0
	public void reset()
	{
		gamePlayed = 0;
		gameWon = 0;
	}


	//increase number of game won by q
	public void gameWon()
	{
		gameWon = gameWon + 1;
	}
	//increase the number of game played by q
	public void gamePlayed()
	{
		gamePlayed = gamePlayed + 1;
	}

	//Mutators
	//Setting the name of the player
	public void setName(String username, String givenName, String familyName)
	{
		idAndName.setName(username, givenName, familyName);
	}
	//Overload 
	public void setName(String givenName, String familyName)
	{
		idAndName.setName(givenName, familyName);
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
	//return the name of player
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
	//retuer of the ratio of winning
	public int getWinRatio()
	{
		return (int)Math.round(100.0*((double)gameWon/gamePlayed));
	}

}
