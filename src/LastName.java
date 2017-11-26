/**
 * Person objects last name
 */
public class LastName
{

	public static final String EXAMPLE = "Smith";
	public static final String LAST_NAME_MESSAGE = "Bad LAST NAME or no LAST NAME entered :(";
	public static final String NAME_REGEX = "[\\p{Alnum} ]+";

	public final String lastName;

	/**
	 * Check entered last name
	 *
	 * @throws IllegalValueException
	 *             if given last name is valid
	 */
	public LastName(String lastName) throws BadInput
	{
		lastName = lastName.trim();
		if (!isValidName(lastName))
		{
			throw new BadInput(LAST_NAME_MESSAGE + "\n" + "Please try somthing like: " + EXAMPLE);
		}
		this.lastName = lastName;
	}

	/**
	 * Returns true if a given string is a valid person name.
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
		return lastName;
	}

	@Override
	public boolean equals(Object compareTo)
	{
		return compareTo == this
				|| (compareTo instanceof LastName && this.lastName.equals(((LastName) compareTo).lastName));
	}

	@Override
	public int hashCode()
	{
		return lastName.hashCode();
	}

}