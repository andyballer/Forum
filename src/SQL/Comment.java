package SQL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;


public class Comment {
	
	private String user;
	private String input;
	private Timestamp time;
	
	public Comment(){
		super();
	}
	
	public Comment(String user, String input){
		this.input = input;
		this.user = user;
		setTime();

	}
	
	public Comment(String user, String input, Timestamp time){
		this.input = input;
		this.user = user;
		this.time = time;

	}
	
	public Comment(String input){
		this.input = input;
		setTime();
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public Timestamp getTime(){
		return time;
	}

	private Timestamp getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		return currentTimestamp;
	}
	
	private void setTime(){
		time = getCurrentTime();
	}

}
