package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicController {
	// 화면 이동
	@GetMapping("/")
	public String music_init()
	{
		return "main";
	}
}
