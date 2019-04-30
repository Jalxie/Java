/*
 * ELEN90041 Project A: Nimplayer class
 * Name: Xiaoqian Xie, Student ID: 709716,
 * Date: 22/03/2018
 */
public class NimPlayer
{

	private String Name;//Name of players

	public void setName()//Setting the name of the player
	{
		Name = Nimsys.keyboard.next();
		System.out.println();
	}

	public String getName()	//return the name of player
	{
		return Name;
	}

	public int removeStone()//Method return how many stones removed 
	{
		System.out.println(Name + "'s turn - remove how many?");
		int NumStoneToRemove = Nimsys.keyboard.nextInt();
		return NumStoneToRemove;
	}
}
