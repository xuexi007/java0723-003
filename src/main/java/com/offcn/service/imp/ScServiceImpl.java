package com.offcn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.dao.ScDao;
import com.offcn.po.Sc;
import com.offcn.service.ScService;
@Service
public class ScServiceImpl implements ScService {

	@Autowired
	ScDao dao;
	
	@Override
	public void saves(List<Sc> list) {
		dao.saves(list);

	}

	@Override
	public List<Sc> getAllByDate(String begin_date, String end_date) {
		return dao.getAllByDate(begin_date, end_date);
	}

}
