package com.Spider.Download;

import java.net.SocketTimeoutException;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;

import com.Spider.Deal.dealWeb;
import com.spider.MainClass.Main;
import com.spider.MenUrl.NewUrl;
import com.spider.MenUrl.OldUrl;
import com.spider.MenUrl.UrlKey;

import javafx.animation.Interpolator;

/**
 * 对网页进行下载
 * @author ubuntu
 *
 */

public class httpdownload {

	//实现GET方法
	public static void sendGet(String url,int key,int layer)
	{
		System.out.println(url);
		try
		{
			//创建httpclient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			//设置超时
			RequestConfig requestConfig = RequestConfig.custom()    
			        .setConnectTimeout(5000).setConnectionRequestTimeout(5000)    
			        .setSocketTimeout(5000).build();
			
			//设置默认cookie
			RequestConfig localConfig = RequestConfig.copy(requestConfig)  
				    .setCookieSpec(CookieSpecs.STANDARD_STRICT)  
				    .build();
			
			//创建httpget对象
			HttpGet httpget = new HttpGet(url);
			
			httpget.setConfig(localConfig);
			
			//添加header
			httpget.setHeader("Accept-Encoding","gzip, deflate, sdch, br");
			httpget.setHeader("Accept-Language","zh-CN,zh;q=0.8");
			httpget.setHeader("Cache-Control","no-cache");
			httpget.setHeader("Connection","Upgrade");
			httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			httpget.setHeader("Upgrade","websocket");
			
			
			//执行方法得到响应
			CloseableHttpResponse response = httpclient.execute(httpget);
			
			try
			{
				//如果正确执行而且返回值正确，进行解析
				if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				{
					System.out.println(url + " is " + " TRUE " + Thread.currentThread().getName());
					HttpEntity entity = response.getEntity();
					Main.oldUrl.addUrl(url); //将已经爬取过的Url放入OldUrl中
					dealWeb.HandleWeb(entity,layer,key);
				}
				else
				{
					System.out.println(url + " is " + response.getStatusLine().getStatusCode() + " FALSE ");
				}
			}
			finally
			{
				//关闭资源
				 httpclient.close();
	             response.close();
			}
		}
		catch(ConnectTimeoutException e)
		{
			System.out.println("连接超时");
			e.printStackTrace();
		}
		catch(SocketTimeoutException e)
		{
			System.out.println("数据读取超时");
		}
		catch(Exception e)
		{
			System.out.println("Unknown ERRER");
			e.printStackTrace();
		}
	}
}
