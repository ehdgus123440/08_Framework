package edu.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

	// 의존성 주입 (DI)
	@Autowired
	private UserService service;

	/**
	 * 사용자 번호를 입력 받아 일치하는 사용자의 이름 조회
	 * 
	 * @param userNo : 제출된 파라미터 중 key값이 "userNo"인 값
	 * @param model  : Spring에서 사용하는 데이터 전달용 객체(request)
	 * @return
	 */
	@GetMapping("test1")
	public String selectUserName(@RequestParam("userNo") int userNo, Model model) {
		
		// 사용자 이름 조회 서비스 호출 후 결과 반환 받기
		String userName = service.selectUserName(userNo);
		
		// 조회 결과를 model에 추가
		model.addAttribute("userName", userName);
		
		// classpath:/templates/user/searchName.html 요청 위임
		return "user/searchName";
		
	}
}
