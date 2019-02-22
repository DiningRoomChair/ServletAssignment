/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Emails a user who has forgotten their password with that information.
 */

package login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.DatabaseHelper;
import helpers.MailHelper;

@WebServlet("/ForgottenServlet")
public class ForgottenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ForgottenServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("InputEmail");
		
		// make it actually send an email
		try {
			if(DatabaseHelper.checkIfEmailIsTakenDB(email)) {
				
				String[] userInfo = DatabaseHelper.getUserInfo(email);
				
				pw.println("<h3>Ok! An email with your password is on the way.</h3>");
				
				String message = "Hello " + userInfo[0] + " " + userInfo[1]
						+ "\n\nRemember your password!\n\nYour password is: " + userInfo[3]
						+ "\nYour email is: " + email + "\nThank you ";
				
				MailHelper.Send(email, "PASSWORD RECOVERY", message);
				
				pw.println("<a href=\"login.html\">To Login</a>");
			}
			else {
				pw.println("That is not a valid registered email.");
				pw.println("<a href=\"registration.html\">To Register</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
