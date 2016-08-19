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

/*
 * Handles what happens when the reply button is clicked on a comment
 * Sends a textarea to submit comment to the html, and then posts the comment
 * to the servlet upon submit, which results in the comment being posted to the 
 * website below its parent
 */
function reply(commentId, commentIndex){
	
	var replyTo = document.getElementById("comment"+commentId.toString());
	var innerDiv = document.createElement("div");
	
	innerDiv.innerHTML += '<textarea name="reply" rows="5" cols="60"></textarea>';
	innerDiv.innerHTML += '<input type="submit" submit value = "Send" name="response"/>';
	innerDiv.innerHTML += '<input type="hidden" name="parentId" value='+commentId.toString()+'>';
	innerDiv.innerHTML += '<input type="hidden" name="parentIndex" value='+commentIndex.toString()+'>';
	
	replyTo.appendChild(innerDiv);
	
	var button = document.getElementById("button"+commentId.toString());
	button.style.visibility = "hidden";

}
