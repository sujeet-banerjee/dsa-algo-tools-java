package test.calendar;


import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class GregorianCalendarTest {

	@Test
	void testCalendarInstanceCreation() {
		Calendar cal = Calendar.getInstance(
				TimeZone.getTimeZone(ZoneId.systemDefault()), 
				Locale.US);
		System.out.println("TimeZone: " + 
				TimeZone.getTimeZone(ZoneId.systemDefault()));
		System.out.println("Cal: "+ cal.getCalendarType());
	}
	
	@Test
	void testCurrentDateTimeFetchAcrossMultipleYears() {
		Calendar cal = Calendar.getInstance(
				TimeZone.getTimeZone(ZoneId.systemDefault()), 
				Locale.US);
		int date = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		
		String msg = String.format(
				"Date=%d; \nTime=%s; \nDoM=%d, \nMonth=%d, \nTz=%s", 
				date, cal.getTime(), dayOfMonth, month, cal.getTimeZone());
		System.out.println("Msg="+ msg);
		
		System.out.println("<-------------->\n");
		System.out.println("== 2 years ago ==");
		cal.roll(Calendar.YEAR, false);
		cal.roll(Calendar.YEAR, false);
		msg = String.format(
				"Date=%d; \nTime=%s; \nDoM=%d, \nMonth=%d, \nTz=%s", 
				date, cal.getTime(), dayOfMonth, month, cal.getTimeZone());
		System.out.println("Msg="+ msg);
	}
}
