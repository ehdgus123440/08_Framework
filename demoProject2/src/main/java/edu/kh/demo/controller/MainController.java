package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 컨트롤러 역할(요청,응답 제어) 명시 + Bean 등록
public class MainController {
	
	// "/" 요청시 매핑하여 처리하는 메서드
	// 장점 : Java를 거쳐서 메인페이지가 보여짐
	// 		-> 추가 세팅 값, DB 조회 값을 위임된 html에서 출력 가능
	@RequestMapping("/")
	public String mainPage() {
		
		// 사용하는 템플릿 엔진 : Thymeleaf
		// Thymeleaf를 사용하는 프로젝트에서 forward시
		// 제공하는 접두사 : classpath:/templates/
		// 제공하는 접미사 : .html
		
		// classpath:/templates/common/main.html 파일로 forward
		
		return "common/main";
	}
	
	
}
