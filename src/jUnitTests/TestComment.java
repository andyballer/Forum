package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import SQL.Comment;

public class TestComment {

	String input = "This is a comment!";
	String user = "username";
	String city = "Toronto";
	
	Comment comment = new Comment(user, input, city);
	
	@Test
	public void testCommentInput() {
		System.out.println("Inside testCommentInput()");
		assertEquals(input, comment.getInput());
	}
	
	@Test
	public void testCommentUser() {
		System.out.println("Inside testCommentUser()");
		assertEquals(user, comment.getUser());
	}
	
	@Test
	public void testCommentCity() {
		System.out.println("Inside testCommentCity()");
		assertEquals(city, comment.getCity());
	}

}
