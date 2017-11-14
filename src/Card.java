
import	java.util.*;


public class Card
{
	private	String	name;
	private	String	address;
	private	String	phone;


	public Card(String entry)
	{
		//Scanner	tokenizer = new Scanner(entry);
		//tokenizer.useDelimiter(":");
		//name = tokenizer.next();
		//address = tokenizer.next();
		//phone = tokenizer.next();

		StringTokenizer	tokenizer = new StringTokenizer(entry, ":");
		name = tokenizer.nextToken();
		address = tokenizer.nextToken();
		phone = tokenizer.nextToken();
	}


	public Card(String name, String address, String phone)
	{
		this.name = name;
		this.address = address;
		this.phone = phone;
	}


	public String toString()
	{
		return name + ":" + address + ":" + phone;
	}


	public String getName()
	{
		return name;
	}


	public String getAddress()
	{
		return address;
	}


	public String getPhone()
	{
		return phone;
	}
}

