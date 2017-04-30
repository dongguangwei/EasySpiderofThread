package com.Spider.Deal;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.metal.MetalFileChooserUI;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.Spider.Download.httpdownload;
import com.spider.MainClass.Main;
import com.spider.MenUrl.NewUrl;
import com.spider.MenUrl.OldUrl;

/**
 * 对网页进行处理
 * 提取出下一层网页的url
 * @author ubuntu
 *
 */
public class dealWeb {

	private static int countX = 0;
	//对页面进行提取url
	//第一层页面
	public static void HandleWeb(HttpEntity entity,int layer,int key)
	{
		Pattern patternUrl = Pattern.compile("href=\"(http://.*?)\"");
		Matcher matcherUrl = null;
		try
		{
			String content = EntityUtils.toString(entity,"utf-8");
			matcherUrl = patternUrl.matcher(content);
			//layer--;
			while(matcherUrl.find())
			{
				//如果跟首页不一样并且没有爬取过则加入到newUrl中
				if(Main.oldUrl.isOrNot(matcherUrl.group(1)) && layer > key)
				{
					Main.newUrl.add(matcherUrl.group(1),key+1);
					countX++;
					if(Main.count > 0)
					{
						synchronized(Main.signal)
   					 	{
   						 	Main.count--;  
                            Main.signal.notify();
   					 	}
					}
				}
				else
				{
					countX++;
				}
			}
			System.out.println("NewUrl:" + Main.newUrl.getNewUrl().size() + " of " + key);
			System.out.println("OldUrl:" + Main.oldUrl.getSet().size() + " of " + key);
			System.out.println(countX);
			System.out.println("-------------------------------------------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
