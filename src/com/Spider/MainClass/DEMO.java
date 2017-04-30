package com.spider.MainClass;

import com.spider.MenUrl.NewUrl;
import com.spider.MenUrl.OldUrl;

public class DEMO {

	public static void main(String[] args) {
		

		String url = "http://www.sina.com.cn/";
		int layer = 3;
		final Main Spider = new Main(url, layer);
		//Spider.setThreadCount(1);
		long starTime = System.currentTimeMillis();
		Spider.RunSpider();
		while(true)
		{
			if(Main.newUrl.getNewUrl().isEmpty() && Thread.activeCount() == 1 || Main.count == Spider.getThreadCount())
			{
				long endTime = System.currentTimeMillis();
				System.out.println("一共花费" + (endTime-starTime)/1000 + "秒");
				System.exit(1);
			}
		}
	}
}
