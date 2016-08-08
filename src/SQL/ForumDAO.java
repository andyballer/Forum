package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ForumDAO {

	public Connection getConnection(){
		String connectionURL = "jdbc:mysql://localhost:3306/Forum";
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
		
		String sql = "insert into entry (Input, User) values (?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, application.getInput());
			statement.setString(2, application.getUser());
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
		String sql = "select * from entry";
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
				System.out.println(user);
				String input = results.getString("Input");
				Application application = new Application(input, user);
				applications.add(application);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;
	}

	public static void main(String[] args) {
		System.out.println("hello");
		ForumDAO dao = new ForumDAO();
		Connection connection = dao.getConnection();
		
		dao.selectAll();
		Application application = new Application("more input", "Andy");
		dao.create(application);
		
		System.out.println(connection);
		dao.closeConnection(connection);

	}

}
