
import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;

public class Rolodexy
{
	public static final int MAX_CONTACTS = 15000;

	private int numberOnFile = 0;
	private File inputFile = new File("rolodexy.txt");
	
	
	/*
	 * Rolodexy constructor searches for our file rolodexy.txt and if it is not
	 * found then it makes it.  
	 * 
	 * if it is found it will populate the tableview with all the contact entries
	 * 
	 */

	public Rolodexy(ObservableList<Person> tableData)
	{
		if (!inputFile.exists())
		{
			System.err.println("rolodex.txt Does not exist");
			return;
		}

		try
		{
			@SuppressWarnings("resource")
			Scanner input = new Scanner(inputFile);
			while (input.hasNextLine())
			{
				String entryFromFile = input.nextLine();
				if (entryFromFile.length() == 0)
					continue;
				tableData.add(numberOnFile++, new Person(entryFromFile));
			}
		} catch (FileNotFoundException file_not_found)
		{
			System.out.println(file_not_found);
		} catch (BadInput bad_input)
		{
			System.out.println(bad_input);
		}

	}

	/*
	 * when the save button is pressed rolodexy will write to the file
	 * 
	 */
	public void save(ObservableList<Person> tableData)
	{

		try
		{
			PrintStream out = new PrintStream(inputFile);
			for (int i = 0; i < tableData.size(); i++)
			{
				out.println(tableData.get(i));
			}
			out.close();
		} catch (FileNotFoundException file_not_found)
		{
			System.out.println(file_not_found);
		}
	}

}
