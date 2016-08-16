package SQL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;


public class Comment {
	
	private int id;
	private String user;
	private String input;
	private String city;
	private Timestamp time;
	private String readableTime;
	private String latitude;
	private String longitutde;
	private int parentId; //always only has one parent
	
	private static int idCount=1;
	
	public Comment(){
		super();
	}
	
	public Comment(String user, String input){
		this.input = input;
		this.user = user;
		setTime();
		setId();

	}
	
	public Comment(String user, String input, String city){
		this.input = input;
		this.user = user;
		this.city = city;
		setId();
		setTime();
	}
	
	//creates comments for comment list pulling all info from database
	public Comment(String user, String input, String city, Timestamp time, int id){
		this.input = input;
		this.user = user;
		this.city = city;
		this.time = time;
		this.id = id;
		//this should probably be on front end so comment objects dont need to carry it around
		setReadableTime();
	}
		
	public Comment(String input){
		this.input = input;
		setId();
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
	
	//need this method
	private Timestamp getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTime = new java.sql.Timestamp(calendar.getTime().getTime());
		return currentTime;
	}
		
	public void setReadableTime(){
		ParseTime timeHandler = new ParseTime(); 
		String timeString = getTime().toString();
		String readableTime = timeHandler.makeDateReadable(timeString);
		this.readableTime = readableTime;
	}
	
	public String getReadableTime(){
		return this.readableTime;
	}
	
	
	private void setTime(){
		if(this.time == null){
			this.time = getCurrentTime();
		}
	}
	
	public Timestamp getTime(){
		return this.time;
	}


	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public int getId(){
		return this.id;
	}
	
	//sets id only if the id is null
	protected void setId(){
		if(this.id == 0){
			this.id = idCount;
			idCount++;
		}
	}

}
