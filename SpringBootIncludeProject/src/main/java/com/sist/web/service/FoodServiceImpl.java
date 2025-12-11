package com.sist.web.service;

import java.util.*;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/*
 *  @Autowired
 *  private FoodMapper mapper;
 *  
 *  private FoodMapper mapper
 *  @Autowired
 *  public FoodServiceImpl(FoodMapper mapper_
 *  {
 *  	this.mapper=mapper;
 *  }
 *  저거를 한번에 쓰는게 ==> @RequiredArgsConstructor
 */
public class FoodServiceImpl implements FoodService{
	private final FoodMapper mapper;

	@Override
	public List<FoodVO> foodListData(int start) {
		// TODO Auto-generated method stub
		return mapper.foodListData(start);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		mapper.HitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
}
