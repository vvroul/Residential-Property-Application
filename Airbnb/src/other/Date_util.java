package other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_util {
	
	public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static String inc_date(String date1, int day) {
		
		String new_date = null;
		
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = formatter.parse(date1);
			date = Date_util.addDays(date, day);
			new_date = formatter.format(date);
		}
		catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return new_date;
    }
	
	public static int days_diff_by_dates(String date1 , String date2) {
		
		int days = -1;
		
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date temp1 = myFormat.parse(date1);
		    Date temp2 = myFormat.parse(date2);
		    long diff = temp2.getTime() - temp1.getTime();
		    days = (int)(diff / (1000*60*60*24));
		    days = days + 1;
		} 
		catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return days;
		
	}
	
	public static Date String_to_date(String string_date) {
		
		Date date = null;
		
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = myFormat.parse(string_date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
		
	}
	
}