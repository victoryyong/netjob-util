package com.thsword.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.thsword.utils.validator.ValidatorUtil;

/**
 * 日期转换
 *
 * @author 李小勇
 */
public class DateUtil {

    /**
     * 样式--yyyy-MM-dd HH:mm:ss
     */
    public static String FORMAT_STYLE_1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 样式--yyyy-MM-dd
     */
    public static String FORMAT_STYLE_2 = "yyyy-MM-dd";
   
    public static String FORMAT_STYLE_3 = "yyyy年MM月dd日";

   
    public static String getString(Date date) {
        // 日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STYLE_1);
        return sdf.format(date);
    }

    /**
     * 将Date按一定的格式转成成String
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStringDate(Date date, String format) {
        // 日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将Date转换成String
     *
     * @param date
     * @param format
     * @return
     */
    public static String getString(Date date, String format) {
        // 日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 字符转成日期
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateString) {
    	if (ValidatorUtil.isNotNull(dateString))
        {
          SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STYLE_1);
          try
          {
            return sdf.parse(dateString);
          }
          catch (ParseException e)
          {
            e.printStackTrace();
            return null;
          }
        }
        return null;
    }

    /**
     * 字符转成日期
     *
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateString, String format)
            throws ParseException {
    	if (ValidatorUtil.isNotNull(dateString))
        {
          SimpleDateFormat sdf = new SimpleDateFormat(format);
          try
          {
            return sdf.parse(dateString);
          }
          catch (ParseException e)
          {
            e.printStackTrace();
            return null;
          }
        }
        return null;
    }

    /**
     * 获取系统当前时间戳String
     *
     * @return
     */
    public static String getCurrentTimeMillis2String() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间----Date
     *
     * @return Date
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取2个时间之间的分钟�?
     *
     * @param startday
     * @param endday
     * @return
     */
    public static int getIntervalMins(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60));
    }

    /**
     * 返回指定时间�?�?
     *
     * @param time
     * @return
     */
    public static Timestamp getZeroTime(Timestamp time) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 返回指定时间�?3�?
     *
     * @param time
     * @return
     */
    public static Timestamp getBigTime(Timestamp time) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(time);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获取相差指定日期
     *
     * @param time指定日期
     * @param days可正可负 负为指定日期前，正为指定日期�?
     * @return
     */
    public static Timestamp getTimeAdd(Timestamp time, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获取距离当前时间的指定日�?
     *
     * @param days可正可负 负为指定日期前，正为指定日期�?
     * @return
     */
    public static Date getDayChange(int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(getNowDate());
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 获取相差指定日期
     *
     * @param time指定日期
     * @param days可正可负 负为指定日期�?，正为指定日期后
     * @return
     */
    public static Timestamp getMonthAdd(Timestamp time, int months) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(time);
        cal.add(Calendar.MONTH, months);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 判断给定日期是否已经超时 判断年，月，�?
     *
     * @param time指定日期
     * @return 超时=true，未超时=false
     */
    public static Boolean judgeTimeByTimestamp(Timestamp time) {
        Timestamp nowTime = new Timestamp((new Date()).getTime());
        nowTime = getBigTime(nowTime);
        time = getBigTime(time);
        return nowTime.after(time);
    }

    /**
     * 判断给定日期是否已经超时 判断年，月，�?
     *
     * @param date
     * @return 超时=true，未超时=false
     */
    public static Boolean judgeTimeByDate(Date date) {
        Date now = getNowDate();
        return now.after(date);
    }

    /**
     * 获得上月1号的日期
     *
     * @return
     */
    public static Date getLastMonthBeginDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得上个月最后一天的日期
     *
     * @return
     */
    public static Date getLastMonthEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获得这个月最后一天的日期
     *
     * @return
     */
    public static Date getThisMonthEndDate() {
        Calendar cal = Calendar.getInstance();
        // 当月�?��天数
        int a = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, a);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    /**
     * 获得这个月天数
     *
     * @return
     */
    public static Integer getThisMonthDay() {
         return getMonthDay(new Date());
    }
    
    /**
     * 获得指定月天数
     *
     * @return
     */
    public static Integer getMonthDay(Date data) {
    	 Calendar calendar = Calendar.getInstance();  
         calendar.setTime(data);  
         return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得时间�?�?
     *
     * @param date
     * @return
     */
    public static int getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获得前后几天�?的前，正的后
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDaysBefore(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getMonday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getTuesday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getWednesday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getThursday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getFriday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getSaturday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return cal.getTime();
    }

    /**
     * 获得指定时间后的星期�?
     *
     * @param date
     * @return
     */
    public static Date getSunday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 取得年龄
     *
     * @param year出生�?
     * @return
     */
    public static int getAge(int year, int month, int day) {
        try {
            // 年龄
            int age = DateUtil.getNowYear();
            age = age - year;
            if (0 > age) {
                age = 0;
            }
            if (DateUtil.getNowMonth() > month) {
                // 当前月大于出生月，年龄加1
                age++;
            } else if (DateUtil.getNowMonth() == month) {
                // 当前日等于出生月,并且日大于生�?
                if (DateUtil.getNowDay() > day) {
                    age++;
                }
            }
            return age;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 取得当前�?
     *
     * @return
     */
    public static int getNowYear() {
        try {
            // 当前�?
            return Calendar.getInstance().get(Calendar.YEAR);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 取得当前�?
     *
     * @return
     */
    public static int getNowMonth() {
        try {
            // 当前�?
            return Calendar.getInstance().get(Calendar.MONTH) + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 取得当前�?
     *
     * @return
     */
    public static int getNowDay() {
        try {
            // 当前�?
            return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
