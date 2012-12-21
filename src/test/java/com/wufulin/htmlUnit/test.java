package com.wufulin.htmlUnit;

import net.vidageek.crawler.PageCrawler;
import net.vidageek.crawler.config.CrawlerConfiguration;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrawlerConfiguration cfg = new CrawlerConfiguration("http://www.atcrazy.com/deals/dealToday.aspx");
		PageCrawler crawler = new PageCrawler(cfg);

		crawler.crawl(new myPageVisitor());

	}

}
