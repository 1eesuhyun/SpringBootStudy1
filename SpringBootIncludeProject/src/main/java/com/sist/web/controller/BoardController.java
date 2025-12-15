package com.sist.web.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("board/detail")
	public String board_detail(@RequestParam(name="no") int no,Model model)
	{
		BoardVO vo=bservice.boardDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/detail");
		return "main/main";
	}
	@GetMapping("board/insert")
	public String board_insert(Model model)
	{
		model.addAttribute("main_html", "board/insert");
		return "main/main";
	}
	// requestbody 는 json을 객체로 변환할 때 vue나 react랑 연결할 때
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute("vo") BoardVO vo)
	{
		bservice.boardInsert(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/update")
	public String boaerd_update(@RequestParam(name="no")int no,Model model)
	{
		BoardVO vo=bservice.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/update");
		return "main/main";
	}
	@PostMapping("/board/update_ok")
	@ResponseBody
	// => @RestController 
	public String board_update_ok(@ModelAttribute("vo") BoardVO vo)
	{
		String res="";
		// 데이터베이스 연결
		boolean bCheck=bservice.boardUpdate(vo);
		// 이동 = 1. 비밀번호가 틀린 경우 / 2. 비밀번호가 맞는 경우
		if(bCheck=true)
		{
			res="<script>"
					+ "location.href=\"/board/detail?no="+vo.getNo()+"\""
				+ "</script>";
		}
		else
		{
			res="<script>"
					+ "alert(\"password fail\");"
					+ "history.back();"
					+ "</script>";
		}
		return res;
	}
	@GetMapping("/board/delete")
	public String board_delete(@RequestParam("no") int no,Model model)
	{
		model.addAttribute("no", no);
		model.addAttribute("main_html", "board/delete");
		return "main/main";
	}
	@PostMapping("/board/delete_ok")
	@ResponseBody
	// => @RestController 
	public String board_delete_ok(@RequestParam("no")int no,@RequestParam("pwd")String pwd)
	{
		String res="";
		// 데이터베이스 연결
		boolean bCheck=bservice.boardDelete(no, pwd);
		// 이동 = 1. 비밀번호가 틀린 경우 / 2. 비밀번호가 맞는 경우
		if(bCheck=true)
		{
			res="<script>"
					+ "location.href=\"/board/list\""
				+ "</script>";
		}
		else
		{
			res="<script>"
					+ "alert(\"password fail\");"
					+ "history.back();"
					+ "</script>";
		}
		return res;
	}
}
