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

	//not sure where to put this so it only runs once
	/**
	 * Initializes local copy of comments and sets idCount
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
	 * Adds comment to comment list and puts it into database
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
	 * Takes string and executes sql command. Can be used to delete
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

	//just made variable static = much better saves work.
	/**
	 * retrieves the last id in the database and stores it in the id
	 */
	//	public int retrieveLastId(){
	//		int lastId = -1;
	//		
	//		String selectLastComment = "select (Id) from userEntries order by time DESC LIMIT 1";
	//		ResultSet results = sqlCall(selectLastComment);
	//		try {
	//			if(results.next()){
	//				lastId = results.getInt("Id");
	//			}
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//		return lastId;
	//		
	//	}

	//Can I make this only execute once instead of every time? Only add in new comment to list on POST()?

	public List<Comment> selectAll(){
		List<Comment> comments = new ArrayList<Comment>();
		String selectComments = "select * from userEntries order by time DESC";
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


	//	public static void main(String[] args) {
	//		ForumDAO dao = new ForumDAO();
	//		Connection connection = dao.getConnection();
	//
	//		
	//		//System.out.println(connection);
	//		dao.closeConnection(connection);
	//
	//	}

}
