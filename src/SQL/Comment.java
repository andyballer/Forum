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
	
	public static int idCount = 0;
	
	//redo the comments so that the constructors just call get and set methods
	//dont want to have to pass in all these things. if null then anonymous etc
	
	public Comment(){
		super();
	}
		
	//constructor when put into database
	public Comment(String user, String input, String city){
		this.input = input;
		setUser(user);
		setCity(city);
		setId();
		setTime();
		setReadableTime();
	}
	
	
	//constructor for comments coming out of database
	public Comment(String user, String input, String city, Timestamp time, int id){
		this.input = input;
		this.user = user;
		this.city = city;
		this.time = time;
		this.id = id;
		setReadableTime();
	}

	public Comment(String reply, int parentId) {
		this.input = reply;
		this.parentId = parentId;
		setId();
		setTime();
		setReadableTime();
		
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
		String noUser = "Anonymous";
		if(user == ""){
			this.user = noUser;
		}
		else this.user = user;
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
		if(city.equals("")){
			this.city = "Unknown";
		}
		else this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public int getId(){
		return this.id;
	}
	
	//sets id only if the id is null
	protected void setId(){
		idCount++;
		this.id = idCount;
	}
	
}
