/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Verifies properly entered data from users.
 * 						Adds their info to the database and emails them if successful.
 */

package register.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.DatabaseHelper;
import helpers.MailHelper;
import helpers.ValidationHelper;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String firstName = request.getParameter("FirstName").trim();
		String lastName = request.getParameter("LastName").trim();
		String address = request.getParameter("Address").trim();
		String email = request.getParameter("Email").trim();
		String password = request.getParameter("Password").trim();
		String confirm = request.getParameter("ConfirmPassword").trim();
		String check = request.getParameter("checkbox");
		// check all entries are okay
		if(firstName.isEmpty() || !ValidationHelper.containsChar(firstName)){
			response.sendRedirect("errorRegistration.html");
		}else if(!ValidationHelper.containsChar(lastName) || lastName.isEmpty()){
			response.sendRedirect("errorRegistration.html");
		}else if(address.isEmpty() ) {
			response.sendRedirect("errorRegistration.html");
		}else if(email.isEmpty() ) {
			response.sendRedirect("errorRegistration.html");
		}else if(!ValidationHelper.validPassword(password) || password.isEmpty() ) {
			response.sendRedirect("errorRegistration.html");
		}else if(!password.equals(confirm) || confirm.isEmpty() ) {
			response.sendRedirect("errorRegistration.html");
		}else if(check == null) {
			// if entries are bad send to error page
			response.sendRedirect("errorRegistration.html");
		}
		else {
			try {
				if(!DatabaseHelper.checkIfEmailIsTakenDB(email)) {
					// if entries are okay check email, then register them in database and send email confirmation
					DatabaseHelper.addUserToDB(firstName, lastName, address, email, password);
					
					pw.println("<h1>Ok! You Are Registered, but make sure to check your email for confirmation<h1>");
					pw.println("<a href=\"login.html\">To Login</a>");
					
					String message = "Welcome " + firstName + " " + lastName
							+ "\n\nRemember your password!\n\nYour password is: " + password
							+ "\nYour email is: " + email + "\nThank you ";
					MailHelper.Send(email,"CONFIRMATION FROM CURLY BOYS", message);			
				}
				else {
					//if email is taken tell them to try again
					pw.println("That email is already taken. Try a different email.");
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
