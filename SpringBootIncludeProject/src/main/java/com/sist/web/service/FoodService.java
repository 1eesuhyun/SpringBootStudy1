package com.sist.web.service;

import java.util.List;


import com.sist.web.vo.*;

public interface FoodService {
	public List<FoodVO> foodListData(int start);
	public int foodTotalPage();
	public FoodVO foodDetailData(int fno);
}
