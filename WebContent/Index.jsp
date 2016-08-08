<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, SQL.ForumDAO, SQL.Application"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


	<meta charset="utf-8">
	<meta name="Forum" content="Comment box for clients to submit and respond">
	<meta name="keywords" content="HTML,XML,Java">
  	<meta name="Andy Ball" content="Loyalty One Project">

    <!-- Mobile-friendly viewport -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" href="css/styles.css?v=1.0">

    <style>

		header, footer {
    		padding: 1em;
    		color: white;
		    background-color: black;
		    clear: left;
		    text-align: center;
		}

	</style>

	<title> Client-Forum </title>
</head>

<body> 

	<header> <h2> LoyaltyOne Forum </h2></header>
	<div>
		<form action="/WEB-INF/classes/servletclass/display.jsp" method=POST>
			 <h4> Your comments: </h4> 
			 	 
			<textarea name="comments" cols=40 rows=10></textarea>
			<p>
			<input type="submit" submit value="Done" name="submit" style="background-color:yellowgreen;color:white;padding:5px;font-size:18px;border:none;padding:8px;">
		</form>
	</div>
		<%
		ForumDAO forum = new ForumDAO();
		List<Application> applications = forum.selectAll(); 
	%> 
	<ul>
	<%	for(Application app:applications){
		%> <li>
			<span><%= app.getInput() %> </span>
			<span><%= app.getUser() %> </span>
			</li>
			
			
		<%
		}
		%>
	 </ul>
	<footer></footer>
</body>

</html>

