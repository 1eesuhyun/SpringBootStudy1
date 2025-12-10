package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
@RestController
@RequiredArgsConstructor
public class FoodRestController {
	private final FoodService fservice;
	
	@GetMapping("/food/find_vue/")
	public Map food_find_vue(@RequestParam("page") int page,@RequestParam(name="address",required = false) String address)
	{
		if(address==null)
			address="마포";
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		map.put("start", start);
		map.put("end", end);
		map.put("address", address);
		
		List<FoodVO> list=fservice.foodListData(map);
		int totalpage=fservice.foodTotalPage();
		
		final int block=10;
		int startPage=((page-1)/block*block)+1;
		int endPage=((page-1)/block*block)+block;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map; // 자동으로 JSON 변경 => jackson
	}
	
}
