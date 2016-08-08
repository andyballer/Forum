package SQL;
import java.sql.Date;
import java.sql.Time;


public class Application {
	
	private String input;
	private String user;
	private Date date;
	private Time time;
	
	public Application(){
		super();
	}
	
	public Application(String user, String input){
		this.input = input;
		this.user = user;
	}
	
	public Application(String input, Date date, Time time){
		this.input = input;
		this.date = date;
		this.time = time;
	}

	public Application(String input, String user, Date date, Time time) {
		super();
		this.input = input;
		this.user = user;
		this.date = date;
		this.time = time;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
}
