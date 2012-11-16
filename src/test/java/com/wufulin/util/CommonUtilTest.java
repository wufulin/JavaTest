package com.wufulin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void testFriendlyURL(){
		String url="ONLY $98: Brazilian Keratin Hair Treatment @ Beauty Spot, Orchard. Valid for All Hair Lengths, All Hair Types! For Men and Women. 100% Formulated Free!";
		System.out.println(CommonUtil.toFriendlySeoTitle(url));
	}
	
	@Test
	public void testFindUrlByRegex(){
		String input="http://www.juztoday.com/deal.php?id=680";
		String regex="^http://www.juztoday.com/deal.php\\?id=(\\d)+$";
		System.out.println(CommonUtil.findUrlByRegex(input, regex));
		Assert.assertNotNull(CommonUtil.findUrlByRegex(input, regex));
		
		input="http://www.deal.com.sg/deals/singapore/Nacho-Cheese-in-Salsa-at-Archipelago-Brewery%E2%80%93Includes-2-Hand-Crafted-Brews-in-4-Different-Flavours";
		regex="^http://www.deal.com.sg/deals/singapore/.*";
		System.out.println(CommonUtil.findUrlByRegex(input, regex));
		Assert.assertNotNull(CommonUtil.findUrlByRegex(input, regex));
		
	}
	
	@Test
	public void testStringToDateLong(){
		String str="2012-11-05";
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(str);
			String uptime = String.valueOf(date.getTime()).substring(0, 10);
			System.out.println(uptime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCalendar() throws Exception{
		Calendar cal=Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, +7);
		System.out.println(cal.getTime());
	}
	
	@Test
	public void testPrintStack() throws Exception{
		try {
			Double.parseDouble("abc");
		} catch (Exception e) {
			System.out.println(CommonUtil.printStackTrace(e.getStackTrace()));
		}
	}

	@Test
	public void testSimpleDateFormat() throws Exception{
		String text="2012-11-06T00:00:00+08:00";
		int start=text.lastIndexOf(':');
		text=text.substring(0, start)+text.substring(start+1,text.length());
		System.out.println(text);
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		Date date=fmt.parse(text);
		String time=String.valueOf(date.getTime()).substring(0,10);
		System.out.println(time);
	}
	
	@Test
	public void testTimeStamp() throws Exception{
		long timeStamp=1351728000000L;
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=fmt.format(new Date(timeStamp));
		System.out.println("add time: --> "+date);
		
		Date now=new Date();
		String nowTimestamp=String.valueOf(now.getTime()).substring(0,10);
		System.out.println("now time stamp: --> "+nowTimestamp);
		System.out.println("now time: --> "+fmt.format(now));
	}

	@Test
	public void testFiltHtmlTagByRegex() throws Exception{
		String html="<html>abc</html>";
		String result=CommonUtil.filtHtmlTagByRegex(html);
		System.out.println(result);
		Assert.assertEquals("abc", result);
	}
}
