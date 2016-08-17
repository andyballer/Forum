package SQL;

public class ParseTime {
		
	String[] monthNames = {"January", "February", "March", "April", "May", "June",
	                       "July", "August", "September", "October", "November", "December"};
	
	public String makeDateReadable(String time){
		String year = time.substring(0,4);
		String month = numsToLetters(time.charAt(6)+"");
		String day = time.substring(8,10);
		
		String hour = time.substring(11,19);
		String date = month + " " + day + " " + year + " @ " + hour; 
		//System.out.println(date);
		return date;
	}
	
	private String numsToLetters(String month){
		return monthNames[Integer.parseInt(month)-1];
		
	}

}
