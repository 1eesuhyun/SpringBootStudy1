package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
@RestController
public class MusicRestController {
	@Autowired
	private MusicService mservice;
	
	@GetMapping("/list_vue")
	public Map music_list_vue(@RequestParam(name="page",required = false) int page)
	{
		int rowsize=12;
		int start=(rowsize*page)-(rowsize-1);
		int end=rowsize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MusicVO> list=mservice.musicListData(map);
		int totalpage=mservice.musicTotalPage();
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		map=new HashMap();
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
}
