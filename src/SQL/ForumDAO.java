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

	public Connection getConnection(){
		String connectionURL = "jdbc:mysql://localhost:3306/Forum?autoReconnect=true&useSSL=false";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "root");
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
	
	
	public void create(Application application){
		Connection connection = getConnection();
		
		String sql = "insert into comments (Input, User, Time) values (?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, application.getInput());
			statement.setString(2, application.getUser());
			statement.setTimestamp(3, application.getTime());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		closeConnection(connection);
		
	}
	
	
	public List<Application> selectAll(){
		List<Application> applications = new ArrayList<Application>();
		Connection connection = getConnection();
		String sql = "select * from comments";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
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
		try {
			while(results.next()){
				String user = results.getString("User");
				String input = results.getString("Input");
				Timestamp time = results.getTimestamp("Time");
				Application application = new Application(user, input, time);
				applications.add(application);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;
	}
	

	public static void main(String[] args) {
		ForumDAO dao = new ForumDAO();
		Connection connection = dao.getConnection();

		
		System.out.println(connection);
		dao.closeConnection(connection);

	}

}
