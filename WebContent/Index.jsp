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
		<!-- Loads my JS page -->
    <script src="<c:url value="/resources/commentLayout.js" />"></script>

<!-- Mobile-friendly viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Client-Forum</title>
</head>

<body>

	<header>
	<h2>LoyaltyOne Forum</h2>
	</header>
	
	<!-- Takes input from a user and posts it to the forum -->
	<div>
		<form name="myForm" action="myServletPath" method="post" onsubmit="return alertNull()">
			<table>
				<tbody>

					<tr>
						<td><h4>Username:</h4></td>
						<td><input type="text" name="user" size="53" /></td>

						<td class="city"><h4>City:</h4></td>
						<td><input type="text" name="city" size ="40"/></td>
					
					</tr>
					<tr>
						<td><h4>Your Comments:</h4></td>
						<td><textarea onkeyup="countChars()" id="textarea" name="input" rows="10" cols="40"></textarea></td>
					</tr>

				</tbody>
			</table>
			<div class="buttonMargin">
				<input class="submit" type="submit" submit value="Done" name="submit"/>
				<span id="charCount" class="charCount"></span>
			</div>
		</form>
		<br>
	</div>
	
<div class="commentSection">Comments:</div>

<!-- Loads in comments from database to be displayed below form -->
	<c:forEach items="${comments}" var="comment" varStatus="index"> 
	<div class="commentBox" id="${comment.user}">
		<span class="time">${comment.time}</span>
		<span class="user">${comment.user}</span>
		<br>
		<span> ${comment.input}</span>
		<br>
		<!-- pass index as varStatus from jsp to javascript to keep track of where responses go -->
		<input type="submit" submit value = "reply" onClick="reply(${index.index});">
	</div>
	</c:forEach>


<footer>
</footer>


</body>


</html>
