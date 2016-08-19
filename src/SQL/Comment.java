package SQL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * A comment object with all the possible input parameters from the user. Also contains
 * extra information such as a unique id, the time the comment was created, the id of the parent 
 * if that comment was a reply.
 * @author Andy Ball
 *
 */
public class Comment {
	
	//use use http://openweathermap.org/api to implement the weather/lat/long feature
	
	private int id;
	private String user;
	private String input;
	private String city;
	private Timestamp time;
	private String readableTime;
	private String latitude;
	private String longitutde;
	private int temperature;
	private int parentId; //always only has one parent
	private int parentIndex; //to know where to place comment reply
	
	//id is set with the static variable idCount, which keeps track of how many comments have ever been posted
	public static int idCount = 0;
	
	public Comment(){
		super();
	}
		
	/*
	 * constructor when put into database. Sets extra info not given
	 */
	public Comment(String user, String input, String city){
		this.input = input;
		setUser(user);
		setCity(city);
		setId();
		setTime();
		setReadableTime();
	}
	
	
	/*
	 * constructor for comments coming out of database
	 */
	public Comment(String user, String input, String city, Timestamp time, int id){
		this.input = input;
		this.user = user;
		this.city = city;
		this.time = time;
		this.id = id;
		setReadableTime();
	}

	/*
	 * Constructor for replies, which always must have a parentId and parent index where they
	 * are stored on the forum, so that the replies can be placed appropriately
	 */
	public Comment(String reply, int parentId, int parentIndex) {
		this.input = reply;
		this.parentId = parentId;
		this.parentIndex=parentIndex;
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
	
	private Timestamp getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTime = new java.sql.Timestamp(calendar.getTime().getTime());
		return currentTime;
	}
	
	/*
	 * Converts the Timestamp into an actual date and time with the months of the year etc
	 */
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
	
	public int getParentId(){
		return this.parentId;
	}
	
	public int getParentIndex(){
		return this.parentIndex;
	}
	
}
