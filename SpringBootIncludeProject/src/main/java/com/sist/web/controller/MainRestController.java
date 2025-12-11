package com.sist.web.controller;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // vuejs 랑 포트가 다르기때문에 추가해야댐
public class MainRestController {
	private final FoodService fservice;
	
	@GetMapping("/main_vue/")
	public ResponseEntity<Map> main_vue(@RequestParam(name="page",required=false) int page)
	{
		Map map=new HashMap();
		try
		{
		int start=(page-1)*12;
		List<FoodVO> list=fservice.foodListData(start);
		int totalpage=fservice.foodTotalPage();
		
		final int block=10;
		int startPage=((page-1)/block*block)+1;
		int endPage=((page-1)/block*block)+block;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("list", list);
		map.put("start", start);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
