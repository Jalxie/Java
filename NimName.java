public class NimName
{
	private String username;
	private String givenName;
	private String familyName;

	public NimName()
	{
		username = "null";
		givenName = "null";
		familyName = "null";
	}

	//Mutators
	public void setName(String username)
	{
		this.username = username;
	}

	public void setName(String givenName, String familyName)
	{
		this.givenName = givenName;
		this.familyName = familyName;
	}

	public void setName(String username, String givenName, String familyName)
	{
		this.username = username;
		this.givenName = givenName;
		this.familyName = familyName;
	}

	//Accessors
	//givenname familyname order
	public String getName()
	{
		return username + "," + givenName + "," + familyName;
	}
	//familyname givenname order
	public String getNameReverse()
	{
		return username + "," + familyName + "," + givenName;
	}

	public String getUserName()
	{
		return username;
	}

	public String getGivenName()
	{
		return givenName;
	}

	public String getFamilyName()
	{
		return familyName;
	}

}



