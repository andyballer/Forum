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
	//should only have one forum DAO
	public ForumDAO forum = new ForumDAO();

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
	//Gets information from the database when the server is launched. Makes no change to database
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forum.onStartUp();
		request.setAttribute("comments", forum.allComments);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//posts information to the webpage
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentType = request.getParameter("commentType");
		Comment toSubmit = null;
		
		if(commentType.equals("initial")){

			String user = request.getParameter("user");
			String input = request.getParameter("input");
			String city = request.getParameter("city");
			toSubmit = new Comment(user, input, city);

		}
		if(commentType.equals("reply")){
			String reply = request.getParameter("reply");
			String parentIdString = request.getParameter("parentId");
			int parentId = Integer.parseInt(parentIdString);
			System.out.println(reply + " " + parentId);
			toSubmit = new Comment(reply, parentId);
		}

		if(toSubmit != null){
			forum.createEntry(toSubmit);
		}

		String reply = request.getParameter("response");
		System.out.println(reply);


		request.setAttribute("comments", forum.allComments);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
		dispatcher.forward(request, response);



	}
}
