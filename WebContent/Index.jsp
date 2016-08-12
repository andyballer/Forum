<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,SQL.ForumDAO,SQL.Application"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<meta charset="utf-8">
<meta name="LoyaltyOne Forum"
	content="Comment box for clients to submit and respond">
<meta name="keywords" content="HTML,XML,Java">
<meta name="Andy Ball" content="Project">

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

.commentbox {
	margin:300 auto;
    width: 500px;
    border: 3px solid blue;
    padding: 10px;
}
.commentbox .date { float:right }
.commentbox .name { float:left }

</style>

<script>
function alertNull(){
	var comment = document.forms["myForm"]["input"].value;
	if(comment == null || comment == ""){
		alert("Please write a comment to submit");
		return false;
	}
}
</script>

<title>Client-Forum</title>
</head>

<body>

	<header>
	<h2>Forum</h2>
	</header>
	<div>

		<form name="myForm" action="Index.jsp" method=POST onsubmit="return alertNull()">
			<table>
				<tbody>

					<tr>
						<td><h4>Username:</h4></td>
						<td><input type="text" name="user" value="" size="53" /></td>
					</tr>
					<tr>
						<td><h4>Your Comments:</h4></td>
						<td><textarea name="input" rows="10" cols="40"></textarea></td>
					</tr>

				</tbody>
			</table>
			<input type="submit" submit value="Done" name="submit"
				style="background-color: yellowgreen; color: white; padding: 5px; font-size: 18px; border: none; padding: 8px;"/>

		</form>
		<br>
	</div>

	<%
		ForumDAO forum = new ForumDAO();
		String user = request.getParameter("user");
		String input = request.getParameter("input");
	
		Application toSubmit = new Application(user, input);
		forum.create(toSubmit);
		List<Application> applications = forum.selectAll();
	
		for (Application app : applications) {
	%>
	
	
	<div class="commentbox">
		
		<span class="name"><user><%=app.getUser() %></user></span>
		<span class="date"><%=app.getTime() %></span>
		<br>
		<span> <%=app.getInput() %> </span>
	
	</div>

		<%
			}
		%>

		


<footer>
</footer>


</body>


</html>
