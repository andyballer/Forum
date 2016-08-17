package jUnitTests;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import SQL.Comment;
import SQL.ForumDAO;

public class TestDatabase {

	String input = "This is a comment!";
	String user = "username";
	String city = "Toronto";
	Comment comment = new Comment(user, input, city);
	ForumDAO forumDAO = new ForumDAO();

	/**
	 * Tests entry created by submitting a comment into the database, retrieving it,
	 * and verifying that the attributes are still the same
	 */
	@Test
	public void testCreateEntry(){
		System.out.println("inside testCreateEntry()");
		forumDAO.createEntry(comment) ;
		String query = "select * from userEntries order by time DESC LIMIT 1";
		ResultSet results = forumDAO.sqlCall(query);

		Comment databaseComment = null;
		try {
			while(results.next()){
				String user;

				user = results.getString("User");

				String input = results.getString("Input");
				String city = results.getString("City");
				databaseComment = new Comment(user, input, city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(comment.getInput(), databaseComment.getInput());
		assertEquals(comment.getUser(), databaseComment.getUser());
		assertEquals(comment.getCity(), databaseComment.getCity());
		
		String deleteComment = "DELETE from userEntries order by time DESC LIMIT 1";
		forumDAO.executeQuery(deleteComment);

	}

}
