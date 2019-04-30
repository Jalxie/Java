public class NotIntegerException extends Exception
{
	public NotIntegerException()
	{
		super("Invalid move. You must enter integer");
		System.out.println();
	}

	public NotIntegerException(String message)
	{
		super(message);
	}
}