package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;
import java.util.*;
/*
 *                   thymeleaf            vue3
 *      렌더링         서버 렌더링             클라이언트 렌더링
 *      검색엔진        매우 뛰어나다          => 서버렌더링 사용(Nuxt)
 *      데이터바인딩       단방향               양방향 => AngularJS/React
 *      속도           서버처리 중심           브라우저 중심
 *                     BackEnd             FrontEnd
 *      사용처           관리페이지              |모바일 => 동적
 *                     |공공기업 사이트          | 페이징/검색/예약/결제
 *                                           | 채팅/AI
 *                                           | GPT
 *       => 기본 : ThymeLeaf / JSP
 *       => 동적 : React / Vue
 *       
 *     1. DOM(ML => 저장이 될 때 트리형태로 저장)
 *                            ------ DOM(Document Object Model)
 *        Vue3 / React
 *         |       |
 *         ---------
 *           가상 돔 + 반응형 => 동작
 *        = 가상 돔*********(100% 면접)
 *          브라우저 화면 직접 실행하는 실제 돔이 아니다
 *          가상 메모리에 저장 => 변경될때마다 실제 돔에 전송
 *               |              |
 *               ---------------- diff(비교)
 *         속도를 빠르게 / 깜박는 효과가 없이
 *       = 컴파일 = 렌더링을 실시간 수행
 *         => 데이터만 변경
 *         
 *      2. MVVM (Model - View - ViewModel)
 *         MVC (Model - View - Controller)
 *         
 *           => 요청 = Controller = Model = Controller = View
 *                        |          |         |          |
 *                     요청 받기     요청 처리  결과값 전송    화면 출력
 *           => 요청 = Model <------> ViewModel <--------> View
 *                     |                |                  |
 *                 데이터 관리         데이터 변경            화면 출력
 *                                  데이터 처리            mount영역
 *                                      |               => <template>
 *                   data()         created()
 *                                  mounted()
 *                                  updated()
 *                                  unmounted()
 *                                  methods:{}
 *              Model : 데이터의 상태 관리(state)
 *                      Pinia/VueX => 저장(stroe)
 *              View : HTML을 제어
 *              ViewModel : 양방향 바인딩, 반응성 관리, 이벤트 처리
 *              
 *              *** Model이 변경되면 Viewmodel ==> HTML반영
 *                  | 설정된 변수가 변경 시
 *        3. 생명주기 함수
 *            created(): Vue 생성 시 자동 호출
 *             mounted() : 브라우저에 HTML이 번역 후 출력이 된 경우
 *             updated() : 데이터값이 변경된 후
 *             unmounted() : 화면이 변경
 *             methods:{} =< Vue시스템에 의해 자동 호출
 *             
 *             methods: 사용자 정의 함수 => 자동이 안된다
 *             *** React / Vue / Next / Node / Nuxt => 기본 코딩은 JavaScript
 *             *** 자바/오라클
 *             *** JavaScript
 *             --------------
 *         4. 디렉티브
 *            v-model : 입력시에 data()에 있는 변수에 값을 설정
 *              | 양방향
 *              | react에 없다 => onchange
 *            v-bind => 속성에 값을 설정 => :
 *              <img v-bind:src="vo.poster">
 *              <img :src="vo.poster">
 *            v-on => 이번트 처리
 *              <button v-on:click="함수">
 *                          mouseover
 *                    => v-on => @
 *                                           
 */
@Controller
public class FoodController {
	@Autowired
	private FoodService fservice;
	
	@GetMapping("/food/find")
	public String food_find()
	{
		return "food/find";
	}
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno") int fno,Model model)
	{
		model.addAttribute("fno", fno);
		return "food/detail";
	}
}
