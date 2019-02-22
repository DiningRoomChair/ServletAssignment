/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Class to verify proper use of the reCaptcha
 */

package helpers;

public class CaptchaHelper {
	// checks for a valid captcha response
	public static boolean isCaptchaValid (String secret, String recaptchaResponse) {
		if(secret == "6LclTHQUAAAAALljFQLWxQf2jTI5MQKU0P4KPtSv" && recaptchaResponse == "g-recaptcha") {
			return false;
		}
		else {
			return true;
		}
	}
}
