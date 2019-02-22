<%
/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			Placeholder for later dynamic pages
 */
 %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
  if(session.getAttribute("firstName") == null){
	  response.sendRedirect("login.html");
  }
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Coming Soon</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>


    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
      <h5 class="my-0 mr-md-auto font-weight-normal">CURLY BOYS</h5>
      <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#">TAB1</a>
        <a class="p-2 text-dark" href="#">TAB2</a>
        <a class="p-2 text-dark" href="#">TAB3</a>
        <a class="p-2 text-dark" href="#">TAB4</a>
        <a>Hello:  ${firstName}</a>
      </nav>
      <form action="LoginServlet" method="post">
      <button name="logOut" type="submit" class="btn btn-primary" value="Log Out">Log Out</button>
      </form>
    </div>

    <img src="assets/underconstruction.png" style="display: block; width: 90%; margin-left: auto; margin-right: auto;">
    
 <!-- Bootstrap core JavaScript-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


</body>
</html>