import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Person object.  A person has a First Name, Last Name, Phone, and Email
 */
public class Person 
{

	private FirstName firstName;
	private LastName lastName;
	private Phone phone;
	private Email email;
	//private int phoneHash;
	//private int emailHash;

	/**
	 * Assumption: No null field there is an alert set up to ensure this be true
	 */
	public Person(FirstName firstNameObj, LastName lastNameObj, Phone phoneObj, Email emailObj)
	{
		this.firstName = firstNameObj;
		this.lastName = lastNameObj;
		this.phone = phoneObj;
		this.email = emailObj;

	}
	
	public Person(String entry) throws BadInput
	{
		StringTokenizer	tokenizer = new StringTokenizer(entry, ":");
		firstName = new FirstName(tokenizer.nextToken());
		lastName =  new LastName(tokenizer.nextToken());
		phone =  new Phone(tokenizer.nextToken());
		email =  new Email(tokenizer.nextToken());
	}

//	public Person(FirstName firstNameObj, LastName lastNameObj, int phoneHash, int emailHash)
//	{
//		this.firstName = firstNameObj;
//		this.lastName = lastNameObj;
//		this.phoneHash = phoneHash;
//		this.emailHash = emailHash;
//	}

	
	/*
	 * Getters are here 
	 */
	public FirstName getFirstName()
	{
		return firstName;
	}

	public LastName getLastName()
	{
		return lastName;
	}

	public Phone getPhone()
	{
		return phone;
	}

	public Email getEmail()
	{
		return email;
	}
//	public int getPhoneHash()
//	{
//		return phoneHash;
//	}
//
//	public int getEmailHash()
//	{
//		return emailHash;
//	}
	
	
	/*
	 * toString - returns a value as a string
	 * 
	 * equals - for comparing 2 objects
	 * 
	 * hashCode - for generating hash values to protect privacy
	 */

	public boolean equals(Person compareTo)
	{
		return compareTo == this || (compareTo != null && compareTo.getFirstName().equals(this.getFirstName()) && compareTo.getLastName().equals(this.getLastName()) 
				&& compareTo.getPhone().equals(this.getPhone()) && compareTo.getEmail().equals(this.getEmail()));
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(firstName, lastName, phone, email);
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(getFirstName());
		builder.append(" : ").append(getLastName());
		builder.append(" : ").append(getPhone());
		builder.append(" : ").append(getEmail());
		return builder.toString();
	}
//	public String toStringHash()
//	{
//		final StringBuilder builder = new StringBuilder();
//		builder.append(getFirstName());
//		builder.append(" : ").append(getLastName());
//		builder.append(" : ").append(getPhoneHash());
//		builder.append(" : ").append(getEmailHash());
//		return builder.toString();
//	}

}