//include jquery library

function alertNull(formName, input){
	var comment = document.forms[formName][input].value;
	if(comment == null || comment == ""){
		alert("Please write a comment to submit");
		return false;
	}
}

function countChars(){
var area = document.getElementById("textarea");
var message = document.getElementById("charCount");
var maxLength = 200;
var checkLength = function() {
    message.innerHTML = "Characters remaining: "+(maxLength-area.value.length)+"/200";
	}
setInterval(checkLength, 200);
}

function postAllComments(){
	//somehow load all comments in here from servlet
	alert("postedcomments");
	for(var comment in comments){
		var div = document.createElement("div");
		div.className = "commentBox";	
		//div.onclick= function() {reply()}; need to implement for replies add inner div. probably not here?
		
		div.innerHTML = postComment(comment);
		document.getElementsByTagName('comments')[0].appendChild(div);
	}
	
	
}

function postComment(comment){
	var commentUser = '<span class="user">' + comment.user + '</span>';
	var commentTime = '<span class="time">' + comment.time + '</span>';
	var commentInput = '<span class="input">' + comment.input + '</span>';
	
	return commentUser + commentTime + commentInput;
}

function reply(index){
	
	var replyTo = document.getElementById("comment"+index.toString());
	var innerDiv = document.createElement("div");
	//onsubmit="return alertNull(\'replyForm\', \'reply\')"
	//replyTo.innerHTML += '<form name="replyForm" action="myServletPath" method="post">';
	replyTo.innerHTML += '<textarea name="reply" rows="5" cols="60"></textarea>';
	replyTo.innerHTML += '<input type="submit" submit value = "Send" name="response"/>';
	//replyTo.innerHTML += '</form>';
	replyTo.appendChild(innerDiv);
		
	var button = document.getElementById("button"+index.toString());
	button.style.visibility = "hidden";

}
