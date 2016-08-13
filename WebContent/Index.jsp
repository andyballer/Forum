<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="java.util.*,SQL.ForumDAO,SQL.Comment"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
	<meta name="LoyaltyOne Forum"
		content="Comment box for clients to submit and respond">
	<meta name="keywords" content="HTML,XML,Java">
	<meta name="Andy Ball" content="Project">
	 
	<link href="<c:url value="/resources/commentLayout.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/commentLayout.js" />"></script>
<!-- Mobile-friendly viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" href="css/styles.css?v=1.0">

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
	
		Comment toSubmit = new Comment(user, input);
		forum.create(toSubmit);
		List<Comment> comments = forum.selectAll();
	
		for (Comment comment : comments) {
	%>
	
	
	<div class="commentbox" onclick="return reply()">
		
		<span class="name"><%=comment.getUser() %></span>
		<span class="date"><%=comment.getTime() %></span>
		<br>
		<span> <%=comment.getInput() %> </span>
	
	</div>

		<%
			}
		%>

		


<footer>
</footer>


</body>


</html>
