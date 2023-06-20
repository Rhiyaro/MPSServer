package com.mps.MPSServer.MPSUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DatetimeManager {

    public static TimeZone tz;
    public static DateFormat df;

    public static void initialize() {
        DatetimeManager.tz = TimeZone.getTimeZone("GMT-3");
        DatetimeManager.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String get_datetime_iso() {
        return DatetimeManager.df.format(new Date());
    }
}
