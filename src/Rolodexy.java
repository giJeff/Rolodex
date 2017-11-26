
import	java.io.*;
import	java.util.*;


public class Rolodex
{
	public	static	final	int	MAX_CARDS = 100;

	private	int	nCards = 0;
	private	boolean	modified = false;
	private	Card[]	cards = new Card[MAX_CARDS];
	private	Scanner	console = new Scanner(System.in);
	private	File	inputFile = new File("rolodex.txt");


	public Rolodex()
	{
		if (!inputFile.exists())
		{
			System.err.println("rolodex.txt Does not exist");
			return;
		}

		try
		{
			Scanner	input = new Scanner(inputFile);
			while (input.hasNextLine())
			{
				String	entry = input.nextLine();
				if (entry.length() == 0)
					continue;
				cards[nCards++] = new Card(entry);
			}
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
		}
	}



	public void menu()
	{
		boolean	done = false;

		while (!done)
		{
			System.out.println("A\tAdd");
			System.out.println("L\tList");
			System.out.println("S\tSave");
			System.out.println("E\tExit");

			System.out.print("> ");

			char	c = console.nextLine().charAt(0);

			switch (c)
			{
				case 'A' :
				case 'a' :
					add();
					break;

				case 'L' :
				case 'l' :
					list();
					break;

				case 'S' :
				case 's' :
					save();
					break;

				case 'E' :
				case 'e' :
					if (modified)
					{
						System.out.print("exit without saving (y/n)? ");
						c = console.nextLine().charAt(0);
						if (c == 'y')
							done = true;
					}
					else
						done = true;
					break;
			}
		}
	}



	public void add()
	{
		System.out.print("Name: ");
		String	name = console.nextLine();

		System.out.print("Address: ");
		String	address = console.nextLine();

		System.out.print("Phone: ");
		String	phone = console.nextLine();

		cards[nCards++] = new Card(name, address, phone);
		modified = true;
	}


	public void list()
	{
		for (int i = 0; i < nCards; i++)
			System.out.printf("%-20s%-30s%-12s%n",
					cards[i].getName(),
					cards[i].getAddress(),
					cards[i].getPhone());
	}


	public void save()
	{
		try
		{
			PrintStream 	out = new PrintStream(inputFile);
			for (int i = 0; i < nCards; i++)
				out.println(cards[i]);
			out.close();
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
		}

		modified = false;
	}



	public static void main(String[] args) 
	{
		Rolodex	rolo = new Rolodex();
		rolo.menu();
	}
}


