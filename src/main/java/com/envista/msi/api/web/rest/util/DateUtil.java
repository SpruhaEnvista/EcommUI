package com.envista.msi.api.web.rest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sujit kumar on 06/04/2017.
 */

/**
 * This class contains utility methods to perform operation on date type.
 */
public class DateUtil {
    /**
     * To subtract days from the passed date.
     * @param date
     * @param days
     * @return
     */
    public static Date subtractDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    /**
     * Used to format date.
     * @param date
     * @param dateFormat
     * @return
     */
    public static String format(Date date, String dateFormat){
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * To format a date string into specified format.
     * @param dateString
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static String format(String dateString, String dateStringFormat, String dateFormat) throws ParseException {
        return format(parse(dateString, dateStringFormat), dateFormat);
    }

    /**
     * To parse a date string into specified date format.
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateString,String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse (dateString);
    }
}
