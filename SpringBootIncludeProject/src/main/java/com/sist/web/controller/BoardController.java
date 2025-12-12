package com.sist.web.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bservice;
	/*
	 *  Spring 5.xx
	 *    => 보안 목적(HttpServletRequest => 사용하지 않는다(권장))
	 *               | 요청값, 결과값 전송
	 *                   |      |
	 *                매개변수  Model => 전송 객체
	 *  Spring 6.xx : 도메인 방식
	 *  ----------------------
	 *             | MVC단점 : DispatcherServlet이 한 개
	 *                                 | 집중이 된다
	 *                                 | 분산해서 사용 => MSA
	 *  Spring 7 => web3.0 => 블록체인
	 */
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<BoardVO> list=bservice.BoardListData((curpage*10)-10);
		int totalpage=bservice.boardTotalpage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		// 화면 출력
		model.addAttribute("main_html", "board/list");
		return "main/main";
	}
}
