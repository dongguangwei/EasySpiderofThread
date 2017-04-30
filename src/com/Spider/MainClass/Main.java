package com.spider.MainClass;

import com.Spider.Download.httpdownload;
import com.spider.MenUrl.NewUrl;
import com.spider.MenUrl.OldUrl;
import com.spider.MenUrl.UrlKey;

public class Main {

	private String url; //url入口
	private int layer; //爬取的深度
	public static final NewUrl newUrl = new NewUrl(); //存放未爬取过的Url
	public static final OldUrl oldUrl = new OldUrl(); //存放已经爬取过的Url
	private int threadCount; //线程最大数 默认为10
	public static int count = 0; //线程等待数
	public static final Object signal = new Object(); //线程间通信变量 
	
	
	public int getCount() {
		return count;
	}
	
	
	public int getThreadCount() {
		return threadCount;
	}



	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}



	public Main(String url,int layer)
	{
		this.url = url;
		this.layer = layer;
		newUrl.add(url,1);
		this.threadCount = 100;
	}
	
	//开始进行爬取
	public void RunSpider()
	{
		for(int i = 0;i < this.threadCount;i++)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true)
					{
						//判断队列是否为空 
						//如果为空等待
						if(!newUrl.getNewUrl().isEmpty())
						{
							UrlKey urlkey = Main.newUrl.remove(); //的考一个url-key 并且删除
							httpdownload.sendGet(urlkey.getUrl(),urlkey.getLayer() ,layer);
						}
						else
						{
							synchronized (signal)
							{
								try
								{
									count++;
									System.out.println("当前有" + count + "个线程正在等待");
									signal.wait(); //线程等待
								}
								catch(InterruptedException e)
								{
									System.out.println("发生线程调用错误");
									e.printStackTrace();
								}
							}
						}
					}
					
				}
			},"thread-"+i).start();
		}
		
		//httpdownload.sendGet(this.newUrl, this.oldUrl,this.layer);
	}
}
