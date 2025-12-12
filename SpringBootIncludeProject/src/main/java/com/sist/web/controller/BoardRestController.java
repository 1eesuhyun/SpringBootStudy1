package com.sist.web.controller;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.service.*;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BoardRestController {
	private final BoardService bservice;
}
