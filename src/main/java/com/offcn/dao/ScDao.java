package com.offcn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.offcn.po.Sc;

public interface ScDao {

	public void saves(List<Sc> list);
	
	public List<Sc> getAllByDate(@Param("begin_date")String begin_date,@Param("end_date")String end_date);
}
