package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.service.MusicService;
import com.sist.web.vo.MusicVO;

@Controller
public class MusicController {
	@Autowired
	private MusicService mservice;
	
	@GetMapping("/")
	public String music_list(@RequestParam(name="page",required=false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(curpage-1)*rowsize+1;
		int end=curpage*rowsize;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MusicVO> list=mservice.musicListData(start, end);
		int totalpage=mservice.musicTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("endPage", endPage);
		
		return "main";
	}
}
