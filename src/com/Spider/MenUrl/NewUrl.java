package com.spider.MenUrl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 用于存放未爬取的Url
 * @author ubuntu
 *
 */

public class NewUrl {

	private Queue<UrlKey> newUrl = null;

	public NewUrl()
	{
		newUrl = new LinkedList<UrlKey>() ;
	}
	
	//添加url
	public synchronized void add(String url,int layer)
	{
		UrlKey urlkey = new UrlKey(url,layer);
		newUrl.offer(urlkey);
	}
	
	//删除Url
	public synchronized UrlKey remove()
	{
		return newUrl.poll();
	}

	
	
	
	public Queue<UrlKey> getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(Queue<UrlKey> newUrl) {
		this.newUrl = newUrl;
	}
	
}
