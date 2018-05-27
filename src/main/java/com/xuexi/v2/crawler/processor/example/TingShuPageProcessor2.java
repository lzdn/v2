package com.xuexi.v2.crawler.processor.example;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.xuexi.v2.crawler.Page;
import com.xuexi.v2.crawler.Site;
import com.xuexi.v2.crawler.Spider;
import com.xuexi.v2.crawler.extension.JsonFilePipeline;
import com.xuexi.v2.crawler.processor.PageProcessor;

public class TingShuPageProcessor2 implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com$").match()) {//第一级
			page.addTargetRequests(page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").all());	
		}
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").match()) {
			//第三deep 	//分页
			 List<String> lists =  page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[1-9]\\d*_[1-9]\\d*+\\.html$").all();
			 int max = 0;
			 int base = 0;
			 for (String string : lists) {
				 int i = string.lastIndexOf("_");
				 int j = string.lastIndexOf(".");
				 int m = string.lastIndexOf("/");
				 int temp = new Integer(string.substring(i+1, j));
				 if(temp>max)max=temp;
				 if(base==0)base = new Integer(string.substring(m+1, i));
			}
			List<String> result = new ArrayList<String>(); 
			for (int n=1;n<=max;n++) {
				if(n==1) {
					result.add("http://www.woaitingshu.com/book/"+base+".html");	
				}else {
					result.add("http://www.woaitingshu.com/book/"+base+"_"+n+".html");	
				}
			}
			page.addTargetRequests(result);
		}
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/book/[0-9]*+\\.html$").match()
				|| page.getHtml().links().regex("^http://www\\.woaitingshu\\.com/book/[1-9]\\d*_[1-9]\\d*+\\.html$").match()) {//第二级
			
			int i = page.getUrl().toString().lastIndexOf("/");
			int j =  page.getUrl().toString().lastIndexOf(".");
			String s = "";
			if(i>=0&&j>=0) {
				s = page.getUrl().toString().substring(i+1, j);
			} 
			page.putField("kind", page.getHtml().xpath("//div[@class='list-r-t']/text()").toString()+"_"+(StringUtils.isEmpty(s)?"1":s));
		    page.putField("imageurl", page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").regex("<(img|IMG)(.*?)(/>|></img>|>)",0).regex("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").all());
		    page.putField("mp3url", page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/[0-9]*+\\.html$").all());
		    //bookid
		    List<String> ids = new ArrayList<String>();
		    List<String> mp3urls = page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/[0-9]*+\\.html$").all();
		    for (String string : mp3urls) {
		    	ids.add(getId(string));
			}
		    page.putField("bookids", ids);
		    
			page.putField("bookname", page.getHtml().xpath("//div[@class='hotbox']").xpath("//dl").xpath("//dt").xpath("//a/html()").all());
			page.putField("description", page.getHtml().xpath("//div[@class='hotbox']").xpath("//dl").xpath("//dd").replace("(<dd class=\"xxts\">)([\\s\\S]*</dd>)","").replace("(<a[\\s\\S]*</a>)","").replace("(<dd>|</dd>)","").all());
			if (page.getResultItems().get("kind") == null) {
				// skip this page
				page.setSkip(true);
			}
			page.addTargetRequests(page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/[0-9]*+\\.html$").all());
		}
//		page.addTargetRequests(page.getHtml().xpath("//div[@class='hotbox']").xpath("//div[@class='tab-img']").xpath("//a").links().regex("^http://www\\.woaitingshu\\.com/mp3/+[0-9]+\\.html$").all());
		if(page.getUrl().regex("^http://www\\.woaitingshu\\.com/mp3/[0-9]*+\\.html$").match()) {
			//String sss= page.getHtml().xpath("//div[@class='detail-info']").xpath("//h2//html()").toString();
			//page.putField("kind",sss);
			page.putField("id",getId(page.getUrl().toString()));
			page.putField("videourl", page.getHtml().xpath("//div[@class='play-list']").xpath("//ul").xpath("//li").links().all());
		}
		
	}
 
	public static String getId(String iss) {
		Pattern regex = Pattern.compile("/+([0-9]{1,})", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher matcher = regex.matcher(iss);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
	
	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new TingShuPageProcessor2()).addUrl("http://www.woaitingshu.com")
				.addPipeline(new JsonFilePipeline("G:\\webmagic\\")).thread(15).run();
	}
}


