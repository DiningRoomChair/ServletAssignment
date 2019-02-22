/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Sends an email to a specified address
 */

package helpers;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailHelper {
	// sends an email to a user
	public static void Send(String to, String subject, String msg) {
		
		final String user = "curlyboys.java@gmail.com";
		 final String pass = "curlyboysJ@va";
		
		//Create an instance of the properties Class
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		
		props.put("mail.smtp.port", "587"); //ssl protocol 465 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable", "true"); //ssl protocol 465 
		
		//We need to pass the props to the session instance
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(user, pass);
			}
			
		});
		
		// Set uP the MIme Object instance
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);
			
			// Transport is the class responsible for delivering the actual mail
			Transport.send(message);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
