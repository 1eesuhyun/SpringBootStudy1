package com.sist.web.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.service.*;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
/*
 * class A
 * {
 *   @Autowired
 * 	 private B b;
 *   
 *   @Autowired
 *   public A(B b)
 *   {
 *   }
 * }
 * 
 *  2. @RequiredArgsConstructor
 *     생성자에서 @Autowired
 *  3. @CrossOrigin(origins="*") : port가 다른 경우
 *     port 허용
 *  4. @ReqestParam => 한개의 요청값
 *     @ModelAttribute => VO단위로 값을 받는 경우
 *     @RequestBody => JSON으로 받는 경우 = 객체 변환
 */
public class BoardRestController {
	private final BoardService bservice;
	
	@GetMapping("/board/list_vue")
	public ResponseEntity<Map> board_list_vue(@RequestParam(name="page") int page)
	{
		Map map=new HashMap();
		try
		{
			List<BoardVO> list=bservice.BoardListData((page*10)-10);
			int totalpage=bservice.boardTotalpage();
			
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	@GetMapping("/board/detail_vue")
	public ResponseEntity<BoardVO> board_detail_vue(@RequestParam(name="no") int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			vo=bservice.boardDetailData(no);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	// 글 작성 vuex => pinia(개인 프로젝트)
	@PostMapping("/board/insert_vue")
	public ResponseEntity<Map> board_insert_vue(@RequestBody BoardVO vo)
	{
		Map map=new HashMap();
		try
		{
			bservice.boardInsert(vo);
			map.put("msg", "yes");
		}catch(Exception ex)
		{
			map.put("msg", "no");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/board/update_vue")
	public ResponseEntity<Map> board_update_vue(@RequestBody BoardVO vo)
	{
		Map map=new HashMap();
		try
		{
			boolean bCheck=bservice.boardUpdate(vo);
			if(bCheck==true)
			{
				map.put("msg", "yes");
			}
			else
			{
				map.put("msg", "no");
			}
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
