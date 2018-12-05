package com.offcn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.po.Pie;
import com.offcn.po.Stu;
import com.offcn.service.StuService;

@Controller
public class StuController {

	@Autowired
	StuService service;
	@RequestMapping("/getall")
	@ResponseBody
	public List<Stu> getAllStu(){
		return service.getAllStu();
	}
	
	@RequestMapping("/getallpie")
	@ResponseBody
	public List<Pie> getAllStuPie(){
		List<Pie> listp=new ArrayList<Pie>();
		List<Stu> list = service.getAllStu();
		for (Stu stu : list) {
			Pie pie = new Pie();
			pie.setName(stu.getName());
			pie.setValue(stu.getScore());
			
			listp.add(pie);
		}
		
		return listp;
	}
}
