package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;
@Controller // 화면 지정  => @RestController
//                          ---- 다른 언어에 데이터 전송 
//                          ---- 웹 / 앱 ==> JSON
//                          Spring kotlin 가능자
public class RecipeController {
    // 필요한 객체 생성 => @Autowired => 멤버변수에만 적용 
	//                            => 매개변수에만 적용 
	/*
	 *    class A
	 *    {
	 *        @Autowired 
	 *        private B b;
	 *        ----------------
	 *        *** 권장 
	 *        @Autowired 
	 *        public A(B b){}
	 *    }
	 */
	 private RecipeService rService;
	 // OOP가 깨진다 
	 
	 @Autowired
	 public RecipeController(RecipeService rService)
	 {
		 this.rService=rService;
	 }
	 
	 @GetMapping("/recipe/list")
	 public String recipe_list(@RequestParam(name="page",required = false) String page,Model model)
	 {
		 // required = false : null 허용 
		 if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   Map map=new HashMap();
		   map.put("start", start);
		   map.put("end", end);
		   List<RecipeVO> list=rService.recipeListData(map);
		   int totalpage=rService.recipeTotalPage();
		   
		   final int BLOCK=10;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   /*
		    *   curpage 1~10 ==> startPage 1
		    *   curpage 11~20 ==> startPage 11
		    *   
		    *   curpage 1~10 ==> endPage 10
		    *   curpage 11~20 ==> ennPagg 20
		    */
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   if(endPage>totalpage)
		       endPage=totalpage;
		   
		   // thymeLeaf로 값 전송 
		   model.addAttribute("list", list);
		   model.addAttribute("curpage", curpage);
		   model.addAttribute("totalpage", totalpage);
		   model.addAttribute("startPage", startPage);
		   model.addAttribute("endPage", endPage);
		 return "recipe/list";
	 }
	 
	 
}