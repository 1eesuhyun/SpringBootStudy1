package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
/*
 * 	1. 교재
 *     1장 : SpringFramework
 *          = DI, AOP(경량 컨테이너)
 *            DI : 객체 생성시에 필요한 데이터를 주입
 *                 => 이미 생성된 객체의 주소값을 얻는 경우
 *                 => 큰 의미 : 객체와 객체의 연관 관계 설정
 *                 => DI의 종류
 *                    1) setter DI : set메소드를 이용해서 변수의 초기화
 *                       <bean id="" class=""
 *                       	p:name=""
 *                       />
 *                       = application.properties
 *                       = application.yml에 등록
 *                    2) 생성자 DI = 매개변수에 값을 첨부
 *                       <bean id="" class=""
 *                       	c:name=""
 *                       />
 *                    3) method DI
 *                       = 객체 생성시에 자동 호출
 *                       = 객체 소멸시에 자동 호출
 *                       <bean id="" class=""
 *                       	init-methods="init"
 *                       	destroy-method="destroy"/>
 *                    4) 객체 주소값 주입 : 스프링에서 생성된 객체
 *                       @Autowired => 스프링에서 자동으로 객체 찾아서 주소값 대입
 *                       A a;
 *                       => @Resource(name="id명") => 1.8 이전에서만 사용
 *           = Container : 스프링에 등록된 클래스를 관리하는 영역
 *                                ---------------------
 *                                객체 생성 ~ 객체 소멸
 *                            종류
 *                             BeanFactory
 *                                 |
 *                            ApplicationContext(XML전용)
 *                                 | = AnnotationConfigApplicationContext
 *                                     | 어노테이션 기반(Java전용)
 *                                     | 보안
 *                          WebApplicationContext
 *                          <bean>
 *                          <context:component-scan basepackage=""> : 부트에서는 자동 인식
 *                                                  => default 패키지안에 설정한다
 *                                                  => com.sist.web
 *                              : 패키지 단위로 등록
 *                              : 객체 선택
 *                                @Component : 일반 클래스
 *                                             Open API, AOP ...
                                  @Repository : DAO = 데이터베이스 연동
                                  @Service : BI(통합)
                                             DAO여러개 사용
                                             부가적인 부분
                                             = 로그인(세션 저장)
                                             = 중복 체크
                                  @Controller : 화면 UI / 화면 제어 / 출력 데이터 전송
                                  @RestController : 파일 제어(X) / 데이터만 전송 가능
                                                                 -------------
                                                                 다른 언어와 연동
                                                                 => Vue/React/모바일
                                                                 => JSON
                                  @ControllerAdvice : 모든 Controller에 예외처리를 공통으로 사용
                                  
                     = AOP : 횡단지향적 프로그램
                             => OOP에서 불가능한 프로그램을 제공
                             => OOP를 보완
                             => OOP에서는 자동 호출이 불가능
                                --- 소스 중복이 있는 경우
                                    | 메소드(클래스 한곳) / 여러개의 클래스 사용시(클래스를 이용)
                                    ------------------------------------------------ => 공통 모듈
                             => AOP에는 자동 호출(Callback:시스템에서 자동 호출되는 메소드)
                                --- 공통 사용시에 지정
                                1. 어떤 메소드에서 호출 여부 => PointCut
                                2. 메소드의 어떤 영역에서 호출할지 여부 => JoinPoint
                                   JoinPoint
                                    = @Before : try 진입전
                                                public void disp()
                                                {
                                                	@Before
                                                	try
                                                	{
                                                	}
                                                }
                                    = @After : finally에서 호출 => 무조건 수행
                                    = @After-Throwsable : catch에서 수행
                                    = @After-Returning : 정상수행 => return 된 상태
                                    = 코드
                                      ---- 1
                                       코드
                                      ---- 2 => @Arround : 트랜젝션 / 보안 / 로그 파일
                                    JoinPoint / PointCut => Advice
                                                            ------ 여러개 통합 : Aspect
                                    => 클래스의 기능별 분리가 되어 있다 : Annotation 사용
                                       유지보수가 편리
                               = MVC
                               1. 클라이언트 요청 발생
                                  <a> <form> : html => JS와 호환성 떨어짐
                               2. 톰캣(내장)에 전송
                               3. 톰캣에서 DispatcherServlet로 전달
                                  미리 등록
                               4. DispatcherServlet
                                       |
                                    Controller중에 => URI를 가지고 있는 메소드 찾기
                                                     => @GeaMapping / @PostMapping
                                    --------------------- HandlerMapping
                               5. 데이터를 전송할 JSP/HTML이 지정 => return
                                  => 데이터 전송 : reuturn "폴더/파일명";
                                  => 기존의 설정된 Mapping호출 : return "redirect: URI명";
                               6. 화면 출력 => 출력에 필요한 데이터 전송 : ViewResolver
                                             => 경로명 / 확장자 전송
                                                prefix suffix
                               7. 화면 UI
                                  = JSP
                                  = ThymeLeaf
                                  = React/Vue
                                  
                               = JDBC
                                 = ORM 
                                   --- MyBatis / JPA(Hibernate) => dataSet
                                       | XML에서 주로 사용
                             --------------- 스프링의 기본
                     ThymeLeaf
                     --------
                      주요 기능
                        => Vue와 비슷 => 태그안에서 제어문 사용
                           <div v-for=""> <div th:each="vo:${list}">
                                           => 자바에서 사용하는 데이터를 그대로 사용
                        => 서버 사이드 랜더링 엔진
                           -------- HTML로 변환
                        => 화면 UI => Front단
                        => 자바와 호환성
                        => JSP보다 현대적이고 구조적
                        => HTML을 브라우저에서 실행시 적ㅇ상적으로 수행
                        => 권장 상태
              ------------------------------------------------------------------------
                                     ThymeLeaf          JSP
              ------------------------------------------------------------------------
              HTML파일 바로 실행 여부     실행 가능          실행 불가능
              ------------------------------------------------------------------------
              SpringMVC통합            매우 우수           제한적
              ------------------------------------------------------------------------
              템플릿 분법                 현대식             구식
              ------------------------------------------------------------------------
              속도                       빠름              느림
              ------------------------------------------------------------------------
              현재 트랜드               아주 많이 사용       사용 안함
                                                       *** 유지보수              
                                     Vue / React
              ------------------------------------------------------------------------
                               
         입문(학원)
         --------
          1) 자바
             = 연산자 / 제어문 / 변수
             = 객체지향 프로그램
               = 특성(캡슐화, 상속|포함, 다형성(오버라이딩))
               = 클래스와 객체
               = 인터페이스 / 추상 클래스
             = 예외처리
             = 기본 라이브러리
               java.util / java.io / java.lang / java.sql / java.net
                   |          |          |           |          |
                                                               URL
                                                            URLEncoder
                                                            
               *** 어노테이션 / JSON파싱 / XML파싱
           2) 오라클
              SQL문장
                |
             --------------------------------
             |        |          |          |
           *DQL     *DML        DDL       *TCL
            ---      ---        ---        --- COMMIT / ROLLBACK
          SELECT    INSERT     CREATE
                    UPDATE     ALTER
                    DELETE     DROP
                               TRUNCATE
                               RENAME
          => SEQUENCE / JOIN / SUBQUERY / View
        3) WEB
           HTML / CSS
           JavaScript
           ---------- Jquery / Vue / React
        4) Spring / SpringBoot
             |          |
             ------------
                 MVC(DI/AOP/ORM(Transaction))
        5) CI/CD => 우분투 명령어
                          | git action
                      ----------
                      |        |
                    Docker, Docker-Compose, MiniJube, Jenkins
 */

import com.sist.web.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.web.service.*;
@Controller
public class FoodController {
	@Autowired
	private EmpService eservice;
	
	@Autowired
	private FoodService fservice;
	
	@GetMapping("/")
	public String main_main(Model model)
	{
		List<EmpVO> list=eservice.empListData();
		model.addAttribute("list", list);
		return "main"; // .html => main.html
	}
	@GetMapping("/food/list")
	public String food_list(@RequestParam(name="page",required=false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list=fservice.foodListData(map);
		int totalpage=fservice.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("curpage", curpage);
	
		if(endPage>totalpage)
			endPage=totalpage;
		
		return "food/list";
	}
	@GetMapping("/food/detail_before")
	public String food_detail_before(@RequestParam(name="fno",required=false) int fno, HttpServletResponse response,RedirectAttributes ra)
	{
		Cookie cookie=new Cookie("food_"+fno,String.valueOf(fno));
		// cookie는 저장 값 => String
		// 1. 저장 위치
		cookie.setPath("/");
		// 2. 저장 시간
		cookie.setMaxAge(60*60*24);
		// 3. 해당 브라우저로 전송
		response.addCookie(cookie);
		// response = Cookie 전송, 다운로드
		// request = cookie 목록
		ra.addAttribute("fno", fno);
		// redirect에서 다른 파일로 값을 전송
		return "redirect:../food/detail";
		// 강제로 서버에서 이동
		/*
		 *             | detail
		 * ok insert/update/delete
		 *      |             |
		 *      ---------------
		 *           list
		 */
	}
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam(name="fno",required=false) int fno, Model model)
	{
		// => 1. hit증가
		
		// => 2. 상세보기 데이터 읽기
		FoodVO vo=fservice.foodDetailData(fno);
		model.addAttribute("vo", vo);
		List<FoodVO> list=fservice.foodTop10();
		for(FoodVO fvo:list)
		{
			String addr=fvo.getAddress();
			addr=addr.substring(0,addr.indexOf(" "));
			fvo.setAddress(addr.trim());
		}
		model.addAttribute("list", list);
		return "food/detail"; // 화면을 바로 보여주는 경우
	}
	
}
