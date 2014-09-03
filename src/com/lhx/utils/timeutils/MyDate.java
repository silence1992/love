package com.lhx.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;


public final class MyDate {
	private static String type = "yyyy-MM-dd";
	private static String type_cn = "yyyy年MM月dd日HH时mm分";
	private static String type_second = "yyyy-MM-dd HH:mm:ss";
	private static String type_minute = "yyyy-MM-dd HH:mm";
	public static void setType(String type) {
		MyDate.type = type;
	}
	public static String format(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(d);
	}
	public static String formatCn(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(type_cn);
		return sdf.format(d);
	}
	
	public static String formatToMinute(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(type_minute);
		return sdf.format(d);
	}
	public static String formatToSecond(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(type_second);
		return sdf.format(d);
	}
	public static String getType_cn() {
		return type_cn;
	}

	public static void setType_cn(String type_cn) {
		MyDate.type_cn = type_cn;
	}

	public static String getType_second() {
		return type_second;
	}

	public static void setType_second(String type_second) {
		MyDate.type_second = type_second;
	}
	
	public static String fromEnToCn(String dateStrToMinute)throws ParseException{
		Date d = (DateFormat.getDateInstance().parse(dateStrToMinute));
		return MyDate.formatCn(d);
	}
	public static void main(String[] args) throws ParseException {
		Date d = (DateFormat.getDateInstance().parse("2012-21-5 12:25"));
		System.out.println(MyDate.formatCn(d));
	}
}
