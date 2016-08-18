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

<body class="backgroundColor">
	<header>
		<a href="https://www.loyalty.com/" >
		<img class ="image" src="resources/images/loyaltyLogo.png" width="1000" height="500" alt="LoyaltyOne" >
		</a>
		<div class="headerText">
			<p><h2> <span class="company"> Company </span> <span class="forum" >Forum </span></h2> </p>
		</div>
	</header>
	
	<!-- Takes input from a user and posts it to the forum -->	
	<div class="form">
		<form name="myForm" action="myServletPath" method="post" onsubmit="return alertNull('myForm', 'input')">
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
						<td><textarea onkeyup="countChars()" id="textarea" name="input" rows="10" cols="55"></textarea></td>
					</tr>

				</tbody>
			</table>
			<div class="buttonMargin">
				<input class="submit" type="submit" submit value="Submit" name="submit"/>
				<span id="charCount" class="charCount">Characters remaining: 200</span>
			</div>
		</form>
		<br>
	</div>
	
	
	<div class="commentTitle">Comments:</div>
	
	<div class = "commentSection">
	
	<!--  cannot dynamically add form in javascript, so this form handles replies -->
	<form name="replyForm" action="myServletPath" method="post" onsubmit="return alertNull('replyForm', 'reply')">

		<br>
		
<!-- Loads in comments from allComments list to be displayed below form -->
		<c:forEach items="${comments}" var="comment"> 
	
			<div class="commentBox" id="comment${comment.id}">
				<span class="user">${comment.user}</span>
				<span class="time"> Posted on ${comment.readableTime}</span>
				<br>
				<div class ="city"> From: ${comment.city}</div>
				<div class="input"> ${comment.input}</div>		
			</div>
			<div id="responses" class = "responses" ></div>
			<div class="reply">
				<button type="button" id="button${comment.id}" onClick="reply(${comment.id});">Reply</button>
			</div>
		</c:forEach>
	</form>
	</div>


<footer>
</footer>


</body>


</html>
