package ma.munisys.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date addDays(Date date,int nbDay){
		Date dt = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, nbDay);
		dt = calendar.getTime();
		return dt;
	}
	
	public static Date addHours(Date date,int nbHour){
		Date dt = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.HOUR, nbHour);
		dt = calendar.getTime();
		return dt;
	}
	
	public static Date addDaysAndHours(Date date,int nbDay,int nbHour){
		Date dt = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, nbDay);
		calendar.add(Calendar.HOUR, nbHour);
		dt = calendar.getTime();
		return dt;
	}
}
