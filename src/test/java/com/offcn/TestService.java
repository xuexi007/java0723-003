package com.offcn;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.po.Sc;
import com.offcn.po.Stu;
import com.offcn.service.ScService;
import com.offcn.service.StuService;

public class TestService {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-service.xml","spring-dao.xml");

		/*StuService service = context.getBean(StuService.class);
		
		List<Stu> list = service.getAllStu();
		for (Stu stu : list) {
			System.out.println(stu);
		}*/
		
		ScService service = context.getBean(ScService.class);
		
		 List<Sc> list = service.getAllByDate("2018-12-03", "2018-12-03");
		 for (Sc sc : list) {
			System.out.println(sc);
		}
	}

}
