package ma.munisys.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {

	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate, int nbDateExcluded) {

		startDate = getDateWithInitTime(startDate);

		endDate = getDateWithInitTime(endDate);

		List<Integer> holidays = Arrays.asList(1, 11, 121, 211, 226, 232, 233, 310, 322);

		// holidays.add(1);holidays.add(11);holidays.add(121);holidays.add(211);holidays.add(226);holidays.add(232);

		// holidays.add(233);holidays.add(310);holidays.add(322);

		Calendar startCal;

		Calendar endCal;

		startCal = Calendar.getInstance();

		startCal.setTime(startDate);

		endCal = Calendar.getInstance();

		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {

			return 0;

		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {

			startCal.setTime(endDate);

			endCal.setTime(startDate);

		}

		do {

			startCal.add(Calendar.DAY_OF_MONTH, 1);

			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY

					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY

					&& !holidays.contains((Integer) startCal.get(Calendar.DAY_OF_YEAR))

			) {

				++workDays;

			}

		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

		// System.out.println("Working DAY DAY " + workDays);

		return workDays - nbDateExcluded;

	}

	public static Date getDateWithInitTime(Date date) {

		SimpleDateFormat isoFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String date2 = new SimpleDateFormat("yyyy/MM/dd").format(date);

		String merge = date2 + " 00:00:00";

		isoFormat1.setTimeZone(TimeZone.getTimeZone("GMT"));

		Date date3;

		try {

			date3 = isoFormat1.parse(merge);

			return date3;

		} catch (ParseException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return null;

	}

}
