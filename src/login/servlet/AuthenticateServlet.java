/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Checks info that is passed from the login servlet against the database of users.
 */

package login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import helpers.DatabaseHelper;

@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public AuthenticateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("InputEmail");
		String password = request.getParameter("InputPassword");
		// validate username and password values
		if(userName == null || userName.trim().equals("") || password == null || password.trim().equals("")) {
			response.sendRedirect("login.html");
		}
		
		try {
			// if user is in database make a session and go to dashboard
			if(DatabaseHelper.checkIfUserIsMemberDB(userName, password)) {
				
				HttpSession session = request.getSession(true);
				// Array: [0]=firstName, [1]=lastName, [2]=email, [3]=password, [4]=role
				String[] userInfo = DatabaseHelper.getUserInfo(userName);
				
				session.setAttribute("firstName", userInfo[0]);
				session.setAttribute("lastName", userInfo[1]);
				session.setAttribute("email", userInfo[2]);
				session.setAttribute("password", userInfo[3]);
				session.setAttribute("role", userInfo[4]);

				response.sendRedirect("dashboard.jsp");
			}
			else {
				// if not send to error page
				response.sendRedirect("errorLogin.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
