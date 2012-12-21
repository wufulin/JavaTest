package com.wufulin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CommonUtil {

	public static byte[] long2ByteArray(long l) {
		byte[] array = new byte[8];
		int i, shift;
		for (i = 0, shift = 56; i < 8; i++, shift -= 8) {
			array[i] = (byte) (0xFF & (l >> shift));
		}
		return array;
	}

	public static byte[] int2ByteArray(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (3 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

	public static void putIntInByteArray(int value, byte[] buf, int offset) {
		for (int i = 0; i < 4; i++) {
			int valueOffset = (3 - i) * 8;
			buf[offset + i] = (byte) ((value >>> valueOffset) & 0xFF);
		}
	}

	public static int byteArray2Int(byte[] b) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (b[i] & 0x000000FF) << shift;
		}
		return value;
	}

	public static long byteArray2Long(byte[] b) {
		int value = 0;
		for (int i = 0; i < 8; i++) {
			int shift = (8 - 1 - i) * 8;
			value += (b[i] & 0x000000FF) << shift;
		}
		return value;
	}

	public static boolean hasBinaryContent(String contentType) {
		if (contentType != null) {
			String typeStr = contentType.toLowerCase();
			if (typeStr.contains("image") || typeStr.contains("audio")
					|| typeStr.contains("video")
					|| typeStr.contains("application")) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasPlainTextContent(String contentType) {
		if (contentType != null) {
			String typeStr = contentType.toLowerCase();
			if (typeStr.contains("text/plain")) {
				return true;
			}
		}
		return false;
	}
	
	public static String toFriendlySeoTitle(String url){
		return Normalizer.normalize(url.toLowerCase(), Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		        .replaceAll("[^\\p{Alnum}]+", "-")
		        .replaceAll("[^a-zA-Z0-9]+$", "")
		        .replaceAll("^[^a-zA-Z0-9]+", "");
	}

	public static Map<String, Object> generateImgChopBody(int gid) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("goods_id", gid);
		body.put("target", Arrays.asList("smallPic", "bigPic"));
		body.put("width", Arrays.asList(100, 470));
		body.put("height", Arrays.asList(100, 0));
		body.put("msg_type", "share_goods_img_chop");
		return body;
	}

	public static String findUrlByRegex(String input, String regex) {
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(input);
		if(m.matches()){
			return input;
		}
		return null;
	}

	public static String printStackTrace(StackTraceElement[] traces){
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement trace : traces) {
			if (trace != null)
				sb.append("\n").append(trace.toString());
		}

		return sb.toString();
	}

	public static String filtHtmlTagByRegex(String htmlText){
		String regexHtml="<[^>]+>";
		Pattern p=Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE);
		Matcher m=p.matcher(htmlText);
		return m.replaceAll("");
	}
	
	public static String getNowTime(String format){
		SimpleDateFormat dateformat=new SimpleDateFormat(format);
		return dateformat.format(new Date());
	}

	public static BufferedImage getBufferedImage(String imagePath, int retryTimes, long sleep) throws Exception{
		if (imagePath == null || imagePath.trim().length() == 0)
			throw new Exception("image url can not be empty");
		
		int count = 0;
		while (true){
			try {
				if (imagePath.startsWith("http://") || imagePath.startsWith("https://")){
					URL url = new URL(imagePath);
					return ImageIO.read(url);
				}else {
					return ImageIO.read(new File(imagePath));
				}
			} catch (Exception e) {
				if (count >= retryTimes){
					throw e;
				}
				Thread.sleep(sleep);
			} 
			count++;
		}
	}

}
