package com.wufulin.image;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.coobird.thumbnailator.Thumbnails;

public class ImageUtils {

	private final static Logger logger=LoggerFactory.getLogger(ImageUtils.class);
	
	public static void GenerateThumbnail(File from,File to,float scale){
		try {
			Thumbnails.of(from)
				.scale(scale)
				.toFile(to);
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}
	
//	public static void main(String[] args){
//		File from=new File("d:/Lighthouse.jpg");
//		ImageUtils.GenerateThumbnail(from, "d:/Lighthouse_thumbnail.jpg", 0.25f);
//	}
}
