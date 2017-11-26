/**
 * alerts if given input is bad.
 */
@SuppressWarnings("serial")
public class BadInput extends Exception
{
	/**
	 * @param message
	 *            should contain failure specific errors
	 */
	public BadInput(String message)
	{
		super(message);
	}
}