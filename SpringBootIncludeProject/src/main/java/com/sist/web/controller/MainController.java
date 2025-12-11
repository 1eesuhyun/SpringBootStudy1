package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;
// 화면 변경 => 요청처리
/*
 *  1. MVC 동작 과정
 *  2. Annotation 역할
 *     => 메모리 할당(스테레오 타입)
 *  3. ORM(Mybatis)
 *  4. HttpSession / Cookie 처리
 *  5. 요청 데이터 => @RequestParam / @ModelAttribute / Vue, React(JSON) => @RequestBody(객체로 변환)
 *  6. @Controller / @RestController
 *  7. @ControllerAdvice : 예외처리 공통으로 처리
 *  8. @Aspect : AOP
 *  9. interceptor
 *  10. FileUpload
 *  ------------------------------------------
 *  11. Security / Batch / WebSocket / NodeJS
 *                                     
 *                                    ----------
 *  FullStack : Javascript / Jquery / Vue / React(redux/tanstackQuery)
 *                             |       |      
 *                           Ajax   vuex/pinia
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MainController {
	private final FoodService fservice;
	
	@GetMapping("/")
	public String main_page(@RequestParam(name="page",required=false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int start=(curpage-1)*12;
		List<FoodVO> list=fservice.foodListData(start);
		int totalpage=fservice.foodTotalPage();
		
		final int block=10;
		int startPage=((curpage-1)/block*block)+1;
		int endPage=((curpage-1)/block*block)+block;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
}
