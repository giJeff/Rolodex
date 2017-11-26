import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Person's email
 */
public class Email
{

	public static final String EXAMPLE = "username@email.domain";
	public static final String EMAIL_MESSAGE = "Bad email or no EMAIL entered :(";
	public static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	public final String value;
	private Pattern pattern;
	private Matcher matcher;

	/**
	 * Validates given email.
	 *
	 * @throws BadInput
	 *             if given email address string is invalid.
	 */
	public Email(String email) throws BadInput
	{
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		
		email = email.trim();
		if (!isValidEmail(email))
		{
			throw new BadInput(EMAIL_MESSAGE + "\n" + "Please try somthing like: " + EXAMPLE);
		}
		this.value = email;
	}

	/**
	 * Checks if a given string is a valid person email.
	 */
	public boolean isValidEmail(String test)
	{
		if (test == null) {
			return false;
		}
		matcher = pattern.matcher(test);
		return matcher.matches();
	}
	
	/*
	 * toString - returns a value as a string
	 * 
	 * equals - for comparing 2 objects
	 * 
	 * hashCode - for generating hash values to protect privacy
	 */

	@Override 
	public String toString()
	{
		return value;
	}

	@Override
	public boolean equals(Object other)
	{
		return other == this || (other instanceof Email && this.value.equals(((Email) other).value)); 
	}

	@Override
	public int hashCode()
	{
		return value.hashCode();
	}

}