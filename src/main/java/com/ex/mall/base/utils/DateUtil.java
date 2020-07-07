package com.ex.mall.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Package: com.ex.mall.base.utils
* @ClassName: DateUtil
* @Description: 时间转换工具类
* @Author: mbm
* @date: 2020/7/7 23:20
* @Version: 1.0
*/

public class DateUtil {

    public static final String  DEFAULT_PATTERN = "yyyy-MM-dd";
    public static String TIMES_PATTERN = "HH:mm:ss";
    public static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String DIR_PATTERN = "yyyy/MM/dd/";

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseString
     * @Param:  [date, format]
     * @return: java.lang.String
     * @Description:
     *               时间 -> 字符
     * @date: 2020/7/7 23:44
     * @Version: 1.0
     */
    public static String parseString(Date date){
        return new SimpleDateFormat().format(date);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseString
     * @Param:  [date, format]
     * @return: java.lang.String
     * @Description:
     *              时间 -> 字符
     *              可指定格式
     * @date: 2020/7/7 23:46
     * @Version: 1.0
     */
    public static String parseString(Date date,String format){
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: currentTime
     * @Param:  []
     * @return: java.lang.String
     * @Description:
     *              当前时间 -> 时间戳格式
     * @date: 2020/7/7 23:53
     * @Version: 1.0
     */
    public static String currentTimeStamp(){
        return parseString(new Date(),TIMESTAMP_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: currentTimeDefault
     * @Param:  []
     * @return: java.lang.String
     * @Description:
     *              当前时间 -> 年月日格式
     * @date: 2020/7/7 23:55
     * @Version: 1.0
     */
    public static String currentTimeDefault(){
        return parseString(new Date(),DEFAULT_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: currentTimeHours
     * @Param:  []
     * @return: java.lang.String
     * @Description:
     *              当前时间 -> 时分秒格式
     * @date: 2020/7/7 23:56
     * @Version: 1.0
     */
    public static String currentTimeHours(){
        return parseString(new Date(),TIMES_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: currentTimeDir
     * @Param:  []
     * @return: java.lang.String
     * @Description:
     *              当前时间 -> 字符文件夹格式
     * @date: 2020/7/7 23:57
     * @Version: 1.0
     */
    public static String currentTimeDir(){
        return parseString(new Date(),DIR_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: formatTimeToString
     * @Param:  [date]
     * @return: java.lang.String
     * @Description: 时间 -> 字符年月日格式
     * @date: 2020/7/8 0:12
     * @Version: 1.0
     */
    public static String formatTimeToDefaultString(Date date){
        return parseString(date,DEFAULT_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: formatTimeToStamptring
     * @Param:  [date]
     * @return: java.lang.String
     * @Description:
     *              时间 -> 字符时间戳格式
     * @date: 2020/7/8 0:13
     * @Version: 1.0
     */
    public static String formatTimeToStamptring(Date date){
        return parseString(date,TIMESTAMP_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: formatTimeToHoursString
     * @Param:  [date]
     * @return: java.lang.String
     * @Description:
     *              时间 -> 字符时分秒格式
     * @date: 2020/7/8 0:14
     * @Version: 1.0
     */
    public static String formatTimeToHoursString(Date date){
        return parseString(date,TIMES_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: formatTimeDirString
     * @Param:  [date]
     * @return: java.lang.String
     * @Description:
     *              时间 -> 字符文件夹格式
     * @date: 2020/7/8 0:15
     * @Version: 1.0
     */
    public static String formatTimeDirString(Date date){
        return parseString(date,DIR_PATTERN);
    }


    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDate
     * @Param:  [dateStr, pattern]
     * @return: java.util.Date
     * @Description:
     *              日期 -> 时间
     * @date: 2020/7/8 0:00
     * @Version: 1.0
     */
    public static Date parseDate(String dateStr,String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateStr);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDefaultDate
     * @Param:  [dateStr]
     * @return: java.util.Date
     * @Description:
     *              字符 -> 年月日格式
     * @date: 2020/7/8 0:04
     * @Version: 1.0
     */
    public static Date parseDefaultDate(String dateStr){
        return parseDate(dateStr,DEFAULT_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseTimeStamp
     * @Param:  [dateStr]
     * @return: java.util.Date
     * @Description:
     *              字符 -> 时间戳格式
     * @date: 2020/7/8 0:05
     * @Version: 1.0
     */
    public static Date parseTimeStampDate(String dateStr){
        return parseDate(dateStr,TIMESTAMP_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseHoursDate
     * @Param:  [dateStr]
     * @return: java.util.Date
     * @Description:
     *              字符 -> 时分秒格式
     * @date: 2020/7/8 0:06
     * @Version: 1.0
     */
    public static Date parseHoursDate(String dateStr){
        return parseDate(dateStr,TIMES_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDirDate
     * @Param:  [dateStr]
     * @return: java.util.Date
     * @Description:
     *              字符 -> 文件夹格式
     * @date: 2020/7/8 0:07
     * @Version: 1.0
     */
    public static Date parseDirDate(String dateStr){
        return parseDate(dateStr,DIR_PATTERN);
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDateToDefaultDate
     * @Param:  [date]
     * @return: java.util.Date
     * @Description: 时间 -> 时间年月日格式
     * @date: 2020/7/8 0:26
     * @Version: 1.0
     */
    public static Date parseDateToDefaultDate(Date date){
        return parseDefaultDate(parseString(date));
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDateToStampDate
     * @Param:  [date]
     * @return: java.util.Date
     * @Description:
     *              时间 -> 时间时间戳格式
     * @date: 2020/7/8 0:26
     * @Version: 1.0
     */
    public static Date parseDateToStampDate(Date date){
        return parseTimeStampDate(parseString(date));
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDateToHoursDate
     * @Param:  [date]
     * @return: java.util.Date
     * @Description:
     *              时间 -> 时间时分秒格式
     * @date: 2020/7/8 0:27
     * @Version: 1.0
     */
    public static Date parseDateToHoursDate(Date date){
        return parseHoursDate(parseString(date));
    }

    /**
     * @Author: mbm
     * @ClassName: DateUtil
     * @MethodName: parseDateToDirDate
     * @Param:  [date]
     * @return: java.util.Date
     * @Description:
     *              时间 -> 时间文件夹格式
     * @date: 2020/7/8 0:27
     * @Version: 1.0
     */
    public static Date parseDateToDirDate(Date date){
        return parseDirDate(parseString(date));
    }




}
