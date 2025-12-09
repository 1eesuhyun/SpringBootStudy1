package com.sist.web.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.sist.web.interceptor.BoardInterceptor;

/*
 * 1. DI => @Autowired : 주소값 주입
 * 2. AOP => 로그 / 공통으로 보내는 값
 * 3. MVC의 구조
 * 4. ORM : MyBatis / JAP(React)
 *                     | MySQL => csv
 *                       | 데이터형 / 내장 함수
 * 5. Transactional
 * ------------------------------------------- 필수
 * 6. Interceptor
 * 7. 공통 예외
 * -------------------------------- 선택 
 * 
 *  ThymeLeafk
 *  ---------- 화면 UI(HTML주로 사용) JSP:JSTL
 *        |                             |
 *        -------------------------------
 *                    EL ${}
 *    디렉티브
 *    ------
 *      th:each => for
 *      th:if   => if
 *      --------------
 *      th:속성="" , th:text="
 *                  <td th:text=""></td>
 *                  <td>[[${}]]</td>
 *      th:each="vo:${list}"     
 *               (vo,index):${list}
 *      <img th:attr="src=${img} alt=${값}">
 *       속성에 값이 많이 사용 => th:attr
 *       <a th:gref="@{URL(전송값,전송값)}">
 *       
 *       th:with
 *       ------- 지역 변수
 *       <div th:with="price=${p*100}">
 *        <p th:text="${price}"></p>
 *        </div>
 *        --------------------------- Layout
 *             
 */

@Configuration
public class BoardConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BoardInterceptor()).addPathPatterns("/board/**").excludePathPatterns("/board/*_ok");
	}
	
}
