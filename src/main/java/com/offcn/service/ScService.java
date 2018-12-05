package com.offcn.service;

import java.util.List;


import com.offcn.po.Sc;

public interface ScService {
public void saves(List<Sc> list);
	
	public List<Sc> getAllByDate(String begin_date,String end_date);
}
