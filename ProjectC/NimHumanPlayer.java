public class NimHumanPlayer extends NimPlayer
{	
	//constructors
	public NimHumanPlayer(String username, String givenName, String familyName, int gamePlayed,
	 int gameWon, String humanOrAI) 
	{
		super(username, givenName, familyName, gamePlayed, gameWon, humanOrAI);
	}
	
	public NimHumanPlayer(String username, String givenName, String familyName) 
	{
		super(username, givenName, familyName);
		humanOrAI = "Human";
	}

	public int removeStone(int Bound, int StonesLeft)//Method return how many stones removed 
	{
		int stoneRemoval;
		String stringStoneRemoval;
		while(true)
		{
			try
			{
				System.out.println(getFamilyName() + "'s turn - remove how many?");
				stringStoneRemoval = Nimsys.keyboard.nextLine();
				if(!isNumeric(stringStoneRemoval))
				{
					throw new NotIntegerException();
				}else
				{
					stoneRemoval = Integer.parseInt(stringStoneRemoval);
				}

				if(stoneRemoval <= Bound && stoneRemoval <= StonesLeft && stoneRemoval >0)
				{
					return stoneRemoval;
				} else
				{
					throw new OutOfBoundException();
				}
			}

			catch(NotIntegerException e)
			{
				String message = e.getMessage();
      			System.out.println(message);
				System.out.println();
				printStone(StonesLeft);
			}

			catch(OutOfBoundException e)
			{
				String message = e.getMessage();
      			System.out.print(message);
      			if(Bound > StonesLeft)
      			{
      				System.out.println("You must remove between 1 and "+ StonesLeft +" stones.");
      			}else 
      			{
      				System.out.println("You must remove between 1 and "+ Bound +" stones.");

      			}
				System.out.println();
				printStone(StonesLeft);
			}
			
		}
	}


	//Testing if a string is an integer or not
	public static boolean isNumeric(String str)
 	{  
	   for(int i=str.length();--i>=0;)
	   {  
	      int chr=str.charAt(i);  
	      if(chr<48 || chr>57)  
	      return false;  
   		}  
   		return true;  
	}   
	
}
