package com.sist.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;
/*
 *  => WEB동작을 위한 기본 틀이 만들어져 있다
 *                 ------ 요청 처리 / 요청 처리 결과 출력
 *                        ------    ----------
 *                   |Model(비즈니스 로직) |View(프리젠테이션 로직) JSP,HTML,JavaScript
 *                    vo,dao,service,controller
 * 
 *  요청 ==== DispatcherServlet
 *                  |
 *            HandlerMapping
 *                  | =====> 해당 Controller찾기
 *              메소드 호출 (Model에 값을 담는다)
 *                  |
 *           DispatcherServlet
 *                  | JSP,HTML찾기
 *             ViewResolver => 경로명 / 확장자
 *                  |
 *             찾은 JSP, HTML => Model을 request로 변환 전송
 */

@Controller
public class BoardController {
	private BoardService bservice;
	
	@Autowired
	public BoardController(BoardService bservice)
	{
		this.bservice=bservice;
	}
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name="page",required=false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize-curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=bservice.boardListData(map);
		int count=bservice.boardRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((rowSize*curpage)-rowSize);
		//HTML로 전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("count", count);
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("msg", "관리자에 의해 삭제된 게시물입니다");
		
		return "board/list";
	}
	@GetMapping("/board/insert")
	public String board_insert()
	{
		return "board/insert";
	}
	/*
	 *  @ModelAttribute : 단일값 여러개를 묶어서 사용
	 *  @RequestParam : 단일값
	 *  @RequestBody : JSON => 객체 변경 => @RestController에서 사용
	 */
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute("vo") BoardVO vo)
	{
		bservice.boardInsert(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no") int no,Model model)
	{
		BoardVO vo=bservice.boardDetailData(no);
		model.addAttribute("no", no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no") int no,Model model)
	{
		BoardVO vo=bservice.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	@GetMapping("/board/reply")
	public String board_reply(@RequestParam("no") int no,Model model)
	{
		model.addAttribute("no", no);
		return "board/reply";
	}
	@PostMapping("/board/reply_ok")
	public String board_reply_ok(@RequestParam("pno") int pno,@ModelAttribute("vo") BoardVO vo)
	{
		bservice.boardReplyInsert(pno, vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/delete")
	public String board_delete_ok(@RequestParam("no") int no,Model model)
	{
		model.addAttribute("no", no);
		return "board/delete";
	}
}
