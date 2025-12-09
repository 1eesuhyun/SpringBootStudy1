package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;
@Controller
public class FoodController {
	private FoodService fservice;
	@Autowired
	public FoodController(FoodService fservice)
	{
		this.fservice=fservice;
	}
	@GetMapping("/food/list")
	public String food_list(@RequestParam(name="page",required=false) String page,Model model)
	{
		return"";
	}
}
