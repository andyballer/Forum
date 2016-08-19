package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class ForumDAO {

	public List<Comment> allComments = new ArrayList<Comment>();

	/**
	 * Sets connection to database
	 * @return
	 */
	public Connection getConnection(){
		String connectionURL = "jdbc:mysql://localhost:3306/Forum?autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "root";
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes local copy of comments and sets idCount each time the server is launched
	 * through the servlet Get() method.
	 * @return
	 */
	public void onStartUp(){
		allComments = selectAll();
		if(!allComments.isEmpty()){
			Comment lastComment = allComments.get(0);
			Comment.idCount = lastComment.getId();
		}
		else{
			//if no comments then reset idCount because database was deleted or is empty
			Comment.idCount = 0;
		}
	}

	/**
	 * Adds comment passed in to the comment list and puts it into database.
	 * Keeps both a local copy in Java as well as in the database so that we
	 * do not have to query all the comments from the database on every post.
	 * @param comment
	 */
	public void createEntry(Comment comment){		

		Connection connection = getConnection();	

		String sql = null;
		//check if the comment is a reply or an initial
		if(comment.getParentId() != 0){
			//place comment right behind parent in list
			allComments.add(comment.getParentIndex(), comment);
			sql = "insert into userEntries (Id, Input, Time, ParentId) values (?,?,?,?)";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, comment.getId());
				statement.setString(2, comment.getInput());
				statement.setTimestamp(3, comment.getTime());
				statement.setInt(4, comment.getParentId());

				statement.execute();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else{
			//add comment into front of list
			allComments.add(0,comment);
			sql = "insert into userEntries (Id, Input, User, Time, City) values (?,?,?,?,?)";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, comment.getId());
				statement.setString(2, comment.getInput());
				statement.setString(3, comment.getUser());
				statement.setTimestamp(4, comment.getTime());
				statement.setString(5, comment.getCity());

				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		closeConnection(connection);

	}

	/**
	 * Takes string and executes sql command without returning anything. Can be used to delete.
	 * @param query
	 */
	public void executeQuery(String query){
		Connection connection = getConnection();	
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}

	/**
	 * Takes in a SQL query as a string and executes. Returns the ResultSet of the query.
	 * @param selectString
	 * @return
	 */
	public ResultSet sqlCall(String selectString){
		Connection connection = getConnection();

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(selectString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet results = null;
		try {
			results = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	public List<Comment> selectAll(){
		List<Comment> comments = new ArrayList<Comment>();
		String selectComments = "select * from userEntries order by Time DESC";
		ResultSet results = sqlCall(selectComments);

		try {
			while(results.next()){
				String user = results.getString("User");
				String input = results.getString("Input");
				String city = results.getString("City");
				Timestamp time = results.getTimestamp("Time");
				int id = results.getInt("Id");

				Comment comment = new Comment(user, input, city, time, id);
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

}
