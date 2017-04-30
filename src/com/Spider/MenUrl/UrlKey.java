package com.spider.MenUrl;

public class UrlKey {

	private String url; //存储url
	private int layer; //存储当前页面的层数
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public UrlKey()
	{
		
	}
	
	public UrlKey(String url,int layer)
	{
		this.url = url;
		this.layer = layer;
	}
}
