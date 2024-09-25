package edu.kh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import java.net.URL; // URL을 다루기 위한 클래스 임포트
import java.awt.image.BufferedImage; // 이미지를 다루기 위한 클래스 임포트
import javax.imageio.ImageIO; // 이미지 입출력을 위한 클래스 임포트

@Slf4j
@Controller
public class testcontroller {
	
	@RequestMapping("test")
	public String test1(Model model) {
		
		
		return "test1";
	}
	
}
