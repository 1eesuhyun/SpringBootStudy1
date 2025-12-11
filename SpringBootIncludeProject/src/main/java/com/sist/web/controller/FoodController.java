package com.sist.web.controller;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sist.web.service.*;
import com.sist.web.vo.*;
// SSR / CSR
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fservice;
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno")int fno,Model model)
	{
		FoodVO vo=fservice.foodDetailData(fno);
		model.addAttribute("vo", vo);
		return "food/detail";
	}
}
