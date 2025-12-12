package com.sist.web.controller;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.vo.*;
// SSR / SCR
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class FoodController {
   private final FoodService fService;
   
   @GetMapping("/food/detail")
   public String food_detail(@RequestParam("fno") int fno,Model model)
   {
	   FoodVO vo=fService.foodDetailData(fno);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_html", "food/detail");
	   return "main/main";
   }
   // Get + Post = RequestMapping => 사용금지 권장 (경고)
   @RequestMapping("/food/find")
   public String food_find(
		   @RequestParam(name="column",required = false) String column,
		   @RequestParam(name="ss",required = false) String ss,
		   @RequestParam(name="page",required = false) String page,
		   Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   // db연동 
	   if(column==null)
		   column="all";
	   
	   Map map=new HashMap();
	   map.put("column", column);
	   map.put("ss", ss);
	   map.put("start", (curpage*12)-12);
	   // OFFSET => 0  num BETWEEN => 1
	   List<FoodVO> list=fService.foodFindData(map);
	   int totalpage=fService.foodFindTotalPage(map);
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		  
	   if(endPage>totalpage)
		 endPage=totalpage;
		  
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("column", column);
	   if(column!=null)
	   {
	      model.addAttribute("ss", ss);
	   }
	   model.addAttribute("main_html", "food/find");
	   return "main/main";
   }
}