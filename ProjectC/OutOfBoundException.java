public class OutOfBoundException extends Exception
{
	public OutOfBoundException()
	{
		super("Invalid move. ");
		System.out.println();
	}

	public OutOfBoundException(String message)
	{
		super(message);
	}
}