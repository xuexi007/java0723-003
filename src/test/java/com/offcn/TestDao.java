package com.offcn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.dao.ScDao;
import com.offcn.po.Sc;

public class TestDao {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");

		/*StuDao dao = context.getBean(StuDao.class);
		
		List<Stu> list = dao.getAllStu();
		for (Stu stu : list) {
			System.out.println(stu);
		}*/
		
		ScDao dao = context.getBean(ScDao.class);
		/*List<Sc> list=new ArrayList<Sc>();
		Sc sc1 = new Sc();
		sc1.setLowprice(0.25F);
		sc1.setAvgprice(0.30F);
		sc1.setHprice(0.50F);
		sc1.setName("大白菜");
		sc1.setGuige("普通");
		sc1.setUnit("斤");
		sc1.setCreatedate(new Date());
		
		Sc sc2 = new Sc();
		sc2.setLowprice(0.25F);
		sc2.setAvgprice(0.30F);
		sc2.setHprice(0.50F);
		sc2.setName("菠菜");
		sc2.setGuige("普通");
		sc2.setUnit("斤");
		sc2.setCreatedate(new Date());
		
		list.add(sc1);
		list.add(sc2);
		dao.saves(list);*/
		
		List<Sc> list = dao.getAllByDate("2018-12-03", "2018-12-03");
		for (Sc sc : list) {
			System.out.println(sc);
		}
	}

}
