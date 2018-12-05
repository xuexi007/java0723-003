package com.offcn.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.po.Sc;
import com.offcn.service.ScService;

public class CatSc {

	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");

		ScService service = context.getBean(ScService.class);
		
		for(int i=1;i<36;i++){
		String url="http://www.xinfadi.com.cn/marketanalysis/0/list/"+i+".shtml?prodname=大白菜&begintime=2017-01-01&endtime=2018-12-03";
	
	  CloseableHttpClient client = HttpClients.createDefault();
	  
	 HttpGet get = new HttpGet(url);
	 
	 CloseableHttpResponse response = client.execute(get);
	 int code=response.getStatusLine().getStatusCode();
	 
	 if(code==200){
		 
		 HttpEntity entity = response.getEntity();
		 String html=EntityUtils.toString(entity, "utf-8");
		 
		 Document doc = Jsoup.parse(html);
		 Elements trs = doc.select(".hq_table").first().select("tr");
		 trs.remove(0);
		 List<Sc> list=new ArrayList<Sc>();
		 for (Element tr : trs) {
			String str=tr.text();
			//System.out.println(str);
			String[] ss = str.split(" ");
			Sc sc = new Sc();
			sc.setName(ss[0]);
			sc.setLowprice(Float.parseFloat(ss[1]));
			sc.setAvgprice(Float.parseFloat(ss[2]));
			sc.setHprice(Float.parseFloat(ss[3]));
			sc.setGuige(ss[4]);
			sc.setUnit(ss[5]);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sc.setCreatedate(df.parse(ss[6]));
			
			list.add(sc);
		}
		 
		 service.saves(list);
	 }
	 
	 client.close();
		}
	}

}
