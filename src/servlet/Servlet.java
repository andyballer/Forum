package servlet;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SQL.Comment;
import SQL.ForumDAO;


/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private boolean didStartUp = false;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//Gets information from the database
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(didStartUp == false){
			ForumDAO forum = new ForumDAO();
			forum.onStartUp();
			didStartUp = true;
		}
//		
//		List<Comment> comments = forum.selectAll();
		
		
		//request.setAttribute("comments", comments); //.getSession?
		request.setAttribute("comments", ForumDAO.allComments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//posts information to the webpage
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("user");
		String input = request.getParameter("input");
		String city = request.getParameter("city");
		
		//submit to database
		Comment toSubmit = new Comment(user, input, city);
		
		if(toSubmit != null){
			ForumDAO helpSubmit = new ForumDAO();
			helpSubmit.createEntry(toSubmit);
		}

		//reloads page and uploads new comment from server
		//typically doPost will forward to new page, but we're keeping same page so need doGet again
		doGet(request, response);



	}
}
