package com.sist.web.commons;

import com.sist.web.controller.FoodController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsException {

    private final FoodController foodController;

    CommonsException(FoodController foodController) {
        this.foodController = foodController;
    }
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex)
	{
		System.out.println("에러 발생");
		ex.printStackTrace();
		System.out.println("에러 발생");
	}
	@ExceptionHandler(Throwable.class)
	public void throwable(Throwable ex)
	{
		System.out.println("에러 발생");
		ex.printStackTrace();
		System.out.println("에러 발생");
	}
}
