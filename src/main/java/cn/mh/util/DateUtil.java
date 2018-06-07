package cn.mh.util;

import com.alibaba.fastjson.JSON;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author LiLei
 * @Date 2016年7月6日
 * 
 */
public class DateUtil {

	public static String[] PATTERN = new String[] { "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd",
			"yyyy/MM/dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };

	public static Date convertToDate(String source, String pattern) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String convertToString(Date source, String pattern) {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			str = dateFormat.format(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static Date getDayBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getDayEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getWeekBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getWeekEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getMonthBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getMonthEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getTenMinuteBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		if (minute.length() > 1) {
			minute = minute.substring(0, 1) + "0";
		} else {
			minute = "0";
		}
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getTenMinuteEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		if (minute.length() > 1) {
			minute = minute.substring(0, 1) + "9";
		} else {
			minute = "9";
		}
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getHourBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getHourEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getQuarterBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month <= 3) {
			calendar.set(Calendar.MONTH, 0);
		} else if (month > 3 && month <= 6) {
			calendar.set(Calendar.MONTH, 3);
		} else if (month > 6 && month <= 9) {
			calendar.set(Calendar.MONTH, 6);
		} else {
			calendar.set(Calendar.MONTH, 9);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getQuarterEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month <= 3) {
			calendar.set(Calendar.MONTH, 2);
		} else if (month > 3 && month <= 6) {
			calendar.set(Calendar.MONTH, 5);
		} else if (month > 6 && month <= 9) {
			calendar.set(Calendar.MONTH, 8);
		} else {
			calendar.set(Calendar.MONTH, 11);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getYearBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getYearEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 判断超时
	 * 
	 * @param histime
	 *            历史时间
	 * @param timeout
	 *            超时时差
	 * @return 超时返回treu,否则返回false
	 */
	public static Boolean isTimeout(Date histime, Integer timeout) {
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		Date curtime = calendar.getTime();
		calendar.setTime(histime);
		calendar.add(Calendar.MILLISECOND, timeout);
		if (calendar.getTime().after(curtime)) {
			flag = true;
		}
		return flag;
	}

	public static DateFormat getDateFormat() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat;
	}

	public static DateFormat getDateFormat(String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat;
	}

	public static int getDayByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getMonthBeginTime(date));
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	public static int getMinutesOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return hour * 60 + minute;
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	//根据输入年月获取当前月的天数
	public static int daysofmonth(String yearmonth) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); //如果写成年月日的形式的话，要写小d，如："yyyy/MM/dd"
		try {
			rightNow.setTime(simpleDate.parse(yearmonth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	private static Calendar getCalendarFormYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		return cal;
	}

	/**
	 * 根据年和周获取周的开始日期
	 * 
	 * @param year
	 * @param weekNo
	 * @return
	 */
	public static String getStartDayOfWeekNo(int year, int weekNo) {
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 根据年和周获取周的结束日期
	 * 
	 * @param year
	 * @param weekNo
	 * @return
	 */
	public static String getEndDayOfWeekNo(int year, int weekNo) {
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取几个月后的结束时间
	 * @param startDate
	 * @param count 不传默认返回一个月
	 * @return Date
	 */
	public static Date getEndDate(Date startDate,Integer count) {
		count=count==null?1:count;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, count);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = calendar.getTime();
		return endDate;
	}
	/**
	 * 获取当前时间的几天前的开始时间
	 * @param count 传正负值，正数为几天后的最晚时间，负数为几天前的开始时间
	 * @return Date
	 */
	public static Date getPreDateByCount(Date curDate,Integer count) {
		count=count==null?0:count;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.DAY_OF_MONTH, count);
		if(count>0){
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}else{
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		Date endDate = calendar.getTime();
		return endDate;
	}
	/**
	 * 获取最近的几个月时间
	 * @param curTime
	 * @param count
	 * @return List<Date>
	 */
	public static List<Date> getLastMonthDateFromYear(Date curTime,Integer count) {
		List<Date> list = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(curTime);
		calendar.add(Calendar.MONTH, -count+1);
		while(calendar.before(calendarEnd)) {
			list.add(calendar.getTime());
			calendar.add(Calendar.MONTH, 1);
		}
		list.add(curTime);
		return list;
	}
	/**
	 * 获取最近的几天时间
	 * @param curTime
	 * @param count
	 * @return List<Date>
	 */
	public static List<Date> getLastDayDateFromYear(Date curTime,Integer count) {
		List<Date> list = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(curTime);
		calendar.add(Calendar.DAY_OF_MONTH, -count+1);
		while(calendar.before(calendarEnd)) {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		list.add(curTime);
		return list;
	}
	/**
	 * 获取某个月内的每一天
	 * @param time 某个月内的任意一个时间点
	 * @return List<Date>
	 */
	public static List<Date> getDayListFromOneMonth(Date time) {
		List<Date> list = new ArrayList<Date>();
		Date start = getMonthBeginTime(time);
		Date end = getMonthEndTime(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		while(calendar.getTime().getTime()<end.getTime()) {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}

	public static List<Date> getDayListFromOneWeek(Date date){
		List<Date> list = new ArrayList<Date>();
		Date start=getWeekBeginTime(date);
		Date end=getWeekEndTime(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		while(calendar.getTime().getTime()<end.getTime()) {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}
	public static void main(String[] args) {
		//System.out.println(DateUtil.getDaysOfMonth(new Date()));
		/*int a = daysofmonth("2015-12");
		System.out.println(a);
		String weeks = getStartDayOfWeekNo(2015, 51);
		String weeke = getEndDayOfWeekNo(2015, 51);
		System.out.println(weeks + "====" + weeke);*/
		
		//System.out.println(DateUtil.convertToString(getPreDateByCount(Calendar.getInstance().getTime(),3), "yyyy-MM-dd HH:mm:ss"));
//System.out.println(JSON.toJSONString(getLastDayDateFromYear(Calendar.getInstance().getTime(),5)));
		//getDayListFromOneMonth(Calendar.getInstance().getTime());
		/*System.out.println(EncryptUtils.encrypt("123", String.valueOf(DateUtil.convertToDate("2017-11-01 14:19:32", "yyyy-MM-dd HH:mm:ss").getTime())));
		System.out.println(EncryptUtils.decrypt("2tHDrvAL2e+oFLQvEKRZEMyosM9nUZr3",  String.valueOf(DateUtil.convertToDate("2017-09-12 15:05:53", "yyyy-MM-dd HH:mm:ss").getTime())));*/
		//dXomKxZyPyXW6OFHLeTS+UVrNHS2yUUZ
		for(Date date:getDayListFromOneWeek(new Date())){
			System.out.println(convertToString(date,"yyyy-MM-dd HH:mm:ss"));
		}
	}

}
