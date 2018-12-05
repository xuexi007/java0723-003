package com.offcn.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.offcn.po.Sc;

@Service
public class CatTask {

	@Autowired
	ScService service;
	
	@Scheduled(cron="0/10 * 9,12,14,15,19 * * ?")
	public void catData(){
		Date nowdate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date=df.format(nowdate);
		String url="http://www.xinfadi.com.cn/marketanalysis/0/list/1.shtml?prodname=大白菜&begintime="+date+"&endtime="+date;
		
		  CloseableHttpClient client = HttpClients.createDefault();
		  
		 HttpGet get = new HttpGet(url);
		 
		 CloseableHttpResponse response;
		try {
			response = client.execute(get);
			 int code=response.getStatusLine().getStatusCode();
			 
			 if(code==200){
				 
				 HttpEntity entity = response.getEntity();
				 String html=EntityUtils.toString(entity, "utf-8");
				 System.out.println("抓取蔬菜数据");
				 Document doc = Jsoup.parse(html);
				 Elements trs = doc.select(".hq_table").first().select("tr");
				 trs.remove(0);
				 List<Sc> list=new ArrayList<Sc>();
				 for (Element tr : trs) {
					String str=tr.text();
					//System.out.println(str);
					String[] ss = str.split(" ");
					if(ss!=null&&ss.length>0){
					Sc sc = new Sc();
					sc.setName(ss[0]);
					sc.setLowprice(Float.parseFloat(ss[1]));
					sc.setAvgprice(Float.parseFloat(ss[2]));
					sc.setHprice(Float.parseFloat(ss[3]));
					sc.setGuige(ss[4]);
					sc.setUnit(ss[5]);
					sc.setCreatedate(df.parse(ss[6]));
					
					list.add(sc);
					}
				}
				 if(list.size()>0){
				 service.saves(list);
				 }
			 }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			 
		 try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		
	}
}
