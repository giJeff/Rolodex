/**
 * this is a phone number
 */
public class Phone
{

	public static final String EXAMPLE = "123456789";
	public static final String PHONE_MESSAGE = "Bad PHONE or no PHONE entered :(";
	public static final String PHONE_REGEX = "\\d+";

	public final String value;

	/**
	 * Is this digits?.
	 *
	 * @throws IllegalValueException
	 *             if given phone number is invalid.
	 */
	public Phone(String phone) throws BadInput
	{
		phone = phone.trim();
		if (!isValidPhone(phone))
		{
			throw new BadInput(PHONE_MESSAGE + "\n" + "Please try somthing like: " + EXAMPLE);
		}
		this.value = phone;
	}

	/**
	 * Checks the phone number.
	 */
	public boolean isValidPhone(String test)
	{
		return test.matches(PHONE_REGEX);
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
		return other == this || (other instanceof Phone && this.value.equals(((Phone) other).value));
	}

	@Override
	public int hashCode()
	{
		return value.hashCode();
	}

}