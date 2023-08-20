/* 
* Stephen Ottaway - stephen.ottaway@bellevuecollege.edu
* Student ID: 202653895
* 2023.06.2 - Spring quarter
* This program reads a file of user ID's using an array list, takes input for
* new ID's from the user, and depending on if all of the constraints for the ID
* are met, the program will either ask the user to try again, meeting the criteria
* that was wrong, or it will accept the ID, add it to the array list of IDs, and 
* update the file by adding the new ID to the list.
*/




package assignment3;

import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.System.*;


public class UserIDs {

	final static double MIN_ID_LENGTH = 6;  // constants for checking
	final static double MAX_ID_LENGTH = 9;  // length method
	public static ArrayList<String> id = new ArrayList<>(); 
	public static ArrayList<String> errors = new ArrayList<>();
	public static boolean right = true;
	
	
	public static boolean checkForDuplicateID(String ID) { // preventing duplicate IDs
		for(int i = 0; i < id.size(); i++) {
			
			if(id.contains(ID)) {
				errors.add("ID already in use.");
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean checkIDLength(String ID) // checking ID length
	{
		
		boolean tooShort = ID.length() < MIN_ID_LENGTH;
		boolean tooLong = ID.length() > MAX_ID_LENGTH;		
		
		if (tooShort)
		{
			errors.add("ID too short.");
			return false;
		}
		else if(tooLong)
		{
			errors.add("ID too long.");
			return false;
		}
			
		
		return true;
	}
	
	
	public static boolean checkForLowerAndUpper(String ID) // checking for both upper and lower case letters
	{
		boolean upper = false;
		boolean lower=false;
		
		for(int i = 0; i < ID.length(); i++) {
			if(Character.isUpperCase(ID.charAt(i))) 
			{
				upper=true;
			}
			else if(Character.isLowerCase(ID.charAt(i)))
			{
				lower=true;
			}
			
		}
		
		if (!upper||!lower)
		{
			errors.add("ID must have lower-case and upper-case.");
			return false;
		}
		
		return true;
	}
	
	public static boolean startsWithLetter(String ID) // checking ID starts with a letter
	{  
		boolean isTrue = Character.isLetter(ID.charAt(0));
		if(!isTrue) {
			errors.add("ID must start with a letter.");
			return false;
		}
		return true;
	}
	
	public static boolean checkForNumber(String ID) // checking ID for numbers
	{ 
		for(int i = 0; i < ID.length(); i++) {
			if(Character.isDigit(ID.charAt(i))) 
			{
				return true;
			}
		}
		errors.add("Id must have at least one number.");
		return false;
	}
	
	public static boolean checkForSpecialChars(String ID) // checking for two or more special chars
	{
		int specialChars = 0;
		for(int i = 0; i < ID.length(); i++) {
			if(!Character.isLetterOrDigit(ID.charAt(i))) {
				specialChars++;
			}
		}
		
		if(specialChars >= 2) {
			return true;
		}
		
		errors.add("ID must have at least two special characters.");
		return false;
	}
	
	
	public static void Update(boolean val) // updating the errors array if any conditions are not met
	{
		if (right)
		{
			if (!val)
			{
				right=val;
				System.out.println("Invalid ID.");
			}
		}
			
	}
	
	
	public static boolean NewUser() throws FileNotFoundException  
	{ // checking ID user passes through all methods, and repeating the
	  // process if user ID is invalid, otherwise updating arraylist and file
	  // with new ID	
		right=true;
		errors.clear();
		
		PrintStream out = new PrintStream(new File("IDs.txt"));

		
		
		Scanner input = new Scanner(System.in);
		System.out.print("\nCreate a new ID: ");
		
		String response=input.next();
		
		Update(checkForDuplicateID(response));
		Update(checkIDLength(response));
		Update(checkForNumber(response));
		Update(checkForSpecialChars(response));
		Update(checkForLowerAndUpper(response));
		Update(startsWithLetter(response));
		
		for (int i=0; i<errors.size(); i++)
		{
			System.out.println(errors.get(i));
		}
		
		
		if (right)
		{
			System.out.printf("ID %s created successfully!\n\n",response);
			
			id.add(response);
			
			System.out.println("List of IDs(" + id.size() + ")");
			for(int k = 0; k < id.size(); k++) {
				System.out.println(id.get(k));
				
			}
			
			for(int j = 0; j < id.size(); j++) {
				out.println(id.get(j));
			}
			
			out.close();
		  }
		else
		{
			NewUser();
		}
		
			
		
		
		
		return false;	
	}
	
	public static boolean readArrayList() throws FileNotFoundException // reading initial file into an arraylist
	{
		
		Scanner scn = new Scanner(new File("IDs.txt"));
		String ID;

		
		while(scn.hasNext()) {
			ID = scn.next();
			id.add(ID);
		}
		
		System.out.print("List of User ID's" + "(" + id.size() + ")\n");
		for(int i = 0; i < id.size(); i++) {
			System.out.println(id.get(i));
		}
		
		
		return true;
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		

		if (readArrayList()); // calling the two methods that read the initial file
			NewUser();        // into an arraylist and that update the file
	}                         // if all the conditions are met
	
}
