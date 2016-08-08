<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,SQL.ForumDAO,SQL.Application"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<meta charset="utf-8">
<meta name="Forum"
	content="Comment box for clients to submit and respond">
<meta name="keywords" content="HTML,XML,Java">
<meta name="Andy Ball" content="Loyalty One Project">

<!-- Mobile-friendly viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" href="css/styles.css?v=1.0">

<style>
header,footer {
	padding: 1em;
	color: white;
	background-color: black;
	clear: left;
	text-align: center;
}

user {
font-weight: bold
}
</style>

<title>Client-Forum</title>
</head>

<body>

	<header>
	<h2>LoyaltyOne Forum</h2>
	</header>
	<div>

		<form name="myForm" action="Index.jsp" method=POST>
			<table>
				<tbody>

					<tr>
						<td><h4>Username:</h4></td>
						<td><input type="text" name="user" value="" size="50" /></td>
					</tr>
					<tr>
						<td><h4>Your Comments:</h4></td>
						<td><textarea name="comment" rows="10" cols="40">Enter text here</textarea></td>
					</tr>

				</tbody>
			</table>
			<input type="submit" submit value="Done" name="submit"
				style="background-color: yellowgreen; color: white; padding: 5px; font-size: 18px; border: none; padding: 8px;">

		</form>
	</div>
	<%
		ForumDAO forum = new ForumDAO();
		String user = request.getParameter("user");
		String comment = request.getParameter("comment");
	
		Application toSubmit = new Application(user, comment);
		forum.create(toSubmit);
		

		List<Application> applications = forum.selectAll();
	%>
	<table>
	<tbody>
		<%
			for (Application app : applications) {
		%>
		<div>
		<tr>
		<td><user><%=app.getUser() %></user></td>
		<td> <%=app.getInput() %></td>
		</tr>
		</div>
		<%
			}
		%>
	</tbody>
	</table>
	<footer></footer>
</body>

</html>

