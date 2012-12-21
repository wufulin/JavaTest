package com.wufulin.htmlUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.vidageek.crawler.Page;
import net.vidageek.crawler.PageVisitor;
import net.vidageek.crawler.Status;
import net.vidageek.crawler.Url;

public class myPageVisitor implements PageVisitor {

	public void onError(Url arg0, Status arg1) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Page page){
		String from = null;
		try {
			URL url = new URL(page.getUrl());
			from = url.getHost();
			File file = new File("d:/2.html");
			OutputStream out = new FileOutputStream(file);
			out.write(page.getContent().getBytes());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean followUrl(Url arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
