/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Used to check users have properly filled out the available forms in the application
 */

package helpers;

import java.util.regex.Pattern;

public class ValidationHelper {
	// check for lower and caps
	public static boolean containsChar(String word) {
		return Pattern.matches("[a-zA-z]+", word);
	}
	// check if email is a reasonable format
	public static boolean validEmail(String email) {
		return Pattern.matches(".+@.+[.].+", email);
	}
	// makes sure password meets requirements
	public static boolean validPassword (String password) {
		boolean hasUpper = password.equals(password.toUpperCase());
		boolean hasLower = password.equals(password.toLowerCase());
		String[] specialChars = new String[]
				{"@","%","+","/", "\\","'","!","#","$","^","?",":",",","(",")",
						"{","}","[","]","~","-","_","."};
		
		if(password.length() >= 6 && password.length() <= 12) {
			if(!hasUpper && !hasLower) {
				for(int i = 0; i < specialChars.length; i++) {
					if(password.contains(specialChars[i]))
						return true;
				}
			}
		}
			return false;
	}
}
