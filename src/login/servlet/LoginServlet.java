/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Verifies proper data entry of the login form.
 * 						Logs users out of the application.
 * 						
 */

package login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.CaptchaHelper;
import helpers.ValidationHelper;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String username = request.getParameter("InputEmail");
		String password = request.getParameter("InputPassword");
		String logOut = request.getParameter("logOut");
		
		String gRecaptchaResponse = request.getParameter("g-recaptcha");
		String privateKey = "6LclTHQUAAAAALljFQLWxQf2jTI5MQKU0P4KPtSv";
		// ends session if the user clicked log out
		if(logOut == null) {
			logOut = "";
		}
		if(logOut.equals("Log Out")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.html");
			return;
		}
		// captcha check
		boolean validateHumans = CaptchaHelper.isCaptchaValid(privateKey, gRecaptchaResponse);
		if (validateHumans == false) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Confirm you're not a robot.");
			return;
		}
		// make sure form entries are valid
		if(username == null || username.trim().equals("")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Must enter an email");
			return;
		}
		if (!ValidationHelper.validEmail(username)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Must be a properly formatted email address");
			return;
		}
		if(!ValidationHelper.validPassword(password)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password must be possible");
			return;
		}
		
		else {
			// if data passes all checks send it to the authenticate servlet
			getServletContext().getRequestDispatcher("/AuthenticateServlet").forward(request,response);
		}
		
	}

}
