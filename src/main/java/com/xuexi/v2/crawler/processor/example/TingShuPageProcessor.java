package com.xuexi.v2.crawler.processor.example;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xuexi.v2.crawler.Page;
import com.xuexi.v2.crawler.Site;
import com.xuexi.v2.crawler.Spider;
import com.xuexi.v2.crawler.extension.JsonFilePipeline;
import com.xuexi.v2.crawler.processor.PageProcessor;

public class TingShuPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com$").match()) {//第一级
			page.addTargetRequests(page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").all());	
		}
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").match()) {
			//第三deep 	//分页
			page.addTargetRequests(page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[1-9]\\d*_[1-9]\\d*+\\.html$").all());
		}
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").match()
				|| page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[1-9]\\d*_[1-9]\\d*+\\.html$").match()) {//第二级
			
			System.out.println(page.getUrl().toString());
			
			int i = page.getUrl().toString().lastIndexOf("/");
			int j =  page.getUrl().toString().lastIndexOf(".");
			String s = "";
			if(i>=0&&j>=0) {
				s = page.getUrl().toString().substring(i+1, j);
			} 
			page.putField("kind", page.getHtml().xpath("//div[@class='list-r-t']/text()").toString()+"_"+(StringUtils.isEmpty(s)?"1":s));
		    page.putField("imageurl", page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").regex("<(img|IMG)(.*?)(/>|></img>|>)",0).regex("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").all());
		    page.putField("mp3url", page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/[0-9]*+\\.html$").all());
			page.putField("bookname", page.getHtml().xpath("//div[@class='hotbox']").xpath("//dl").xpath("//dt").xpath("//a/html()").all());
			page.putField("description", page.getHtml().xpath("//div[@class='hotbox']").xpath("//dl").xpath("//dd").replace("(<dd class=\"xxts\">)([\\s\\S]*</dd>)","").replace("(<a[\\s\\S]*</a>)","").replace("(<dd>|</dd>)","").all());
			if (page.getResultItems().get("kind") == null) {
				// skip this page
				page.setSkip(true);
			}
			
		//	page.addTargetRequests(page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/+[0-9]+\\.html$").all());	
		}
		
		/*if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/mp3/+[0-9]+\\.html$").match()) {
			
			page.putField("videourl", page.getHtml().xpath("//div[@class='play-list']").xpath("//ul").xpath("//li").links().all());
		}
		*/
	}
 
	
	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new TingShuPageProcessor()).addUrl("http://www.woaitingshu.com")
				.addPipeline(new JsonFilePipeline("G:\\webmagic\\")) .run();
	}
}


