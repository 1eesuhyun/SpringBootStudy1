package com.sist.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BoardAOP {
	@Around("execution(* com.sist.web.controller.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("사용자 요청:"+jp.getSignature().getName()+"=>처리 시작");
		long start=System.currentTimeMillis();
		Object obj=jp.proceed(); // 실제 호출되는 메소드
		long end=System.currentTimeMillis();
		System.out.println("처리 종료:"+(end-start)+"MS ====");
		return obj;
	}
}
