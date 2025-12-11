package com.sist.web.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
import lombok.RequiredArgsConstructor;
/*
 *  @RestController : 다른 언어 연결 => 전송
 *  @RequiredArgsConstructor(롬복꺼) => 생성자에서 객체주소 설정 
 *  @Autowired : 스프링에서 자동으로 객체 주소 주입
 *  @RequestParam : 데이터값 1개 받는 경우
 *  @ModelAttribute : vo단위로 값을 받는 경우
 *  @RequestBody : JSON이 전송될때 객체 변환
 *  @ResponseBody : 전송 => JSON/문자열 전송 => @RestController로 변경
 *  @Aspect : 공통으로 적용된 기능 => 데이터 관리 : 공통 모듈
 *  ---------------
 *  메모리 할당
 *  @Repository : DAO구분 => 데이터 베이스 관련된 클래스
 *  @Component : 일반 객체 => 스프링에서 객체를 생성해서 관리
 *  @Service : BI => DAO여러개 AND 메소드 여러개를 통합해서 사용
 *  @Controller : 데이터+화면 제어 
 *  @RestController : 데이터를 전송할 목적
 *  @ControllerAdvice : Controller => catch사용
 *  @RestControllerAdvice => 선택적 메모리 할당(기능별 분리 = 가독성)
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FoodRestController {
	private final FoodService fservice;
	
	@GetMapping("/food/detail_vue/")
	public ResponseEntity<FoodVO> food_detail(@RequestParam("fno") int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			vo=fservice.foodDetailData(fno);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
