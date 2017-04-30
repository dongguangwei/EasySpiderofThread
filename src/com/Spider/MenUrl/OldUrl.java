package com.spider.MenUrl;

import java.util.HashSet;
import java.util.Set;

import com.sun.swing.internal.plaf.synth.resources.synth_zh_CN;

/**
 *存放已经爬取过的URl
 * @author ubuntu
 *
 */
public class OldUrl {

	private Set<String>  set = null;
	
	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public OldUrl()
	{
		this.set = new HashSet<String>();
	}
	
	//将爬取过的url添加到Url管理器
	public synchronized void addUrl(String oldurl)
	{
		this.set.add(oldurl);
	}
	
	public boolean isOrNot(String oldurl)
	{
		boolean flag = false;
		try
		{
			if(!this.set.contains(oldurl))
			{
				flag = true;
			}
		}
		catch(Exception e)
		{
			flag = false;
			System.out.println("判断是否已经存在爬取过的URL初选出现错误");
			e.printStackTrace();
		}
		return flag;
	}
}
