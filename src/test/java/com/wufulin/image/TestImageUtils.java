package com.wufulin.image;

import java.io.File;

import junit.framework.Assert;
import org.junit.Test;

public class TestImageUtils {

	@Test
	public void testGenerateThumbnail(){
		
		File from=new File("Lighthouse.jpg");
		Assert.assertNotNull(from);
		
		String fileName=from.getName().replace(".jpg", "_thumbnail.jpg");
		Assert.assertEquals("Lighthouse_thumbnail.jpg", fileName);
		
		File to=new File(fileName);
		if(to.exists()){
			ImageUtils.GenerateThumbnail(from, to, 0.25f);
		}
		Assert.assertEquals("Lighthouse_thumbnail.jpg", to.getName());
	}
}
