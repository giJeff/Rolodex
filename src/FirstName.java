/**
 * First name is a first name
 */
public class FirstName
{

	public static final String EXAMPLE = "Jeff";
	public static final String FIRST_NAME_MESSAGE = "Bad FIRST NAME or no FIRST NAME entered :(";
	public static final String NAME_REGEX = "[\\p{Alnum} ]+";

	public final String firstName;

	/**
	 * Validates first name.
	 *
	 * @throws IllegalValueException if given first name string is invalid.
	 */
	public FirstName(String firstName) throws BadInput
	{
		firstName = firstName.trim();
		if (!isValidName(firstName))
		{
			throw new BadInput(FIRST_NAME_MESSAGE + "\n" + "Please try somthing like: " + EXAMPLE);
		}
		this.firstName = firstName;
	}

	/**
	 * Returns true if this is actually a first name.
	 */
	public boolean isValidName(String test)
	{
		return test.matches(NAME_REGEX);
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
		return firstName;
	}

	@Override
	public boolean equals(Object compareTo)
	{
		return compareTo == this || (compareTo instanceof FirstName && this.firstName.equals(((FirstName) compareTo).firstName));
	}

	@Override
	public int hashCode()
	{
		return firstName.hashCode();
	}

}