package edu.kh.project.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.dto.Member;
import edu.kh.project.mypage.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@SessionAttributes("loginMember")
@Slf4j
@Controller
@RequestMapping("myPage")
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	@GetMapping("info")
	public String info(
			Model model,
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		if(loginMember.getMemberAddress() != null) {
			 String[] arr = loginMember.getMemberAddress().split(",");
			 model.addAttribute("postcode", arr[0]);
			 model.addAttribute("address", arr[1]);
			 model.addAttribute("detailAddress", arr[2]);
		}
		
		return "myPage/myPage-info";
	}
	
	/**
	 * 내 정보 수정
	 * @param inputMember : 수정할 닉네임, 전화번호, 주소
	 * @param loginMember : 현재 로그인된 회원 정보, session에 저장된 Member 객체의 주소가 반환됨
	 * 											수정 시 session에 저장된 Member객체 수정 가능
	 * @return ra : 리다이렉트 시 request scope로 전달
	 */
	@PostMapping("info")
	public String updateInfo(
			@ModelAttribute Member inputMember,
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra
			) {
		
		int memberNo = loginMember.getMemberNo();
		
		inputMember.setMemberNo(memberNo);
		
		int result = service.updateInfo(inputMember);
		
		String message = null;
		
		if(result > 0) {
			message = "수정 성공";
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
		}
		else message = "수정 실패";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info";
	}
	
	
	/**
	 * (비동기) 닉네임 중복 검사
	 * @param input
	 * @return 0 중복X / 1 중복O
	 */
	@GetMapping("checkNickname")
	@ResponseBody
	public int checkNickname(@RequestParam("input") String input) {
		
		return service.checkNickname(input);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
