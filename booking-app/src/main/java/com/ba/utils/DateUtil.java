package com.ba.utils;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;  

public class DateUtil {
	
	public static final DateTimeFormatter HH_MM_AM_PM_FORMAT = DateTimeFormatter.ofPattern("hh:mm a");  

	
	public static Date getDateFromStringinup(String incomingDate) { 
		Date date=null;
		try { 
			if(incomingDate!=null && !incomingDate.trim().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				date=dateFormat.parse(incomingDate.trim());
				System.out.println(date);	 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date; 
	}
	public static Date getDateFromString(String incomingDate) { 
		Date date=null;
		try { 
			if(incomingDate!=null && !incomingDate.trim().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");  
				date=dateFormat.parse(incomingDate.trim());
				System.out.println(date);	 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date; 
	}
	
	public static String getDatetoString(String incomingDate) { 
		String date=null;
		try { 
			if(incomingDate!=null && !incomingDate.trim().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm a");  
                date = dateFormat.format(getDateFromString(incomingDate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date; 
	}
	
	 
	/*public static Date getDateFromString(String incomingDate) { 
		Date date=null;
		try { 
			if(incomingDate!=null && !incomingDate.trim().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
				date=dateFormat.parse(incomingDate.trim());
				System.out.println(date);	 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
		  
	}
	public static String getDatetoString(String incomingDate) { 
		String date=null;
		try { 
			if(incomingDate!=null && !incomingDate.trim().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm");  
				Date dDate=dateFormat.parse(incomingDate); 
                date = dateFormat.format(dDate);
				System.out.println("datetostring: "+date);	 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date; 
	}*/
	
	public static String localDateTimeToTimeString(LocalDateTime date)
	{
        return date.format(HH_MM_AM_PM_FORMAT);
	}
}

 