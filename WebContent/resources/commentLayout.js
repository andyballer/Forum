function alertNull(){
	var comment = document.forms["myForm"]["input"].value;
	if(comment == null || comment == ""){
		alert("Please write a comment to submit");
		return false;
	}
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

function reply(div){
	var innerDiv = document.createElement('div');
	//use div.appendChild(innerDiv
	return;
}
