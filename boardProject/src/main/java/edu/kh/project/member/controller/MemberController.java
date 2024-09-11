package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.dto.Member;
import edu.kh.project.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes({ "loginMember" })
@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService service;

	/*
	 * Cookie란?
	 * 
	 * - 클라이언트 측(브라우저)에서 관리하는 데이터(파일 형식)
	 * 
	 * - Cookie에는 만료기간, 데이터(key=value), 사용하는 사이트(주소) 가 기록되어 있음
	 * 
	 * - 클라이언트가 쿠키에 기록된 사이트로 요청으로 보낼 때 요청에 쿠키가 담겨져서 서버로 넘어감
	 * 
	 * - Cookie의 생성, 수정, 삭제는 Server가 관리 저장은 Client가 함
	 * 
	 * - Cookie는 HttpServletResponse를 이용해서 생성, 클라이언트에게 전달(응답) 할 수 있다
	 */

	/**
	 * 로그인
	 * 
	 * @param memberEmail : 제출된 이메일
	 * @param memberPw    : 제출된 비밀번호
	 * @param saveEmail   : 이메일 저장 여부
	 * @param ra          : 리다이렉트 시 request scope로 값 전달하는 객체
	 * @return
	 */

	@PostMapping("login")
	public String login(@RequestParam("memberEmail") String memberEmail, @RequestParam("memberPw") String memberPw,
			@RequestParam(name = "saveEmail", required = false) String saveEmail, RedirectAttributes ra, Model model,
			HttpServletResponse resp) {

//	log.debug("memberEmail :{}", memberEmail);
//	log.debug("memberPw :{}", memberPw);

		// 로그인 서비스 호출
		Member loginMember = service.login(memberEmail, memberPw);

		if (loginMember == null) { // 로그인 실패
			ra.addFlashAttribute("message", "이메일 또는 비밀번호가 일치하지 않습니다");
		} else {
			// loginMember를 session scope에 추가
			// 방법 1) HttpSession 이용
			// 방법 2) @SessionAttributes + Model 이용 방법

			/* Model을 이용해서 Session scope에 값 추가하는 방법 */
			// 1. model에 값 추가
			model.addAttribute("loginMember", loginMember);

			// 2. 클래스 선언부 위에 @SessionAttributes({"key}) 추가
			// -> key 값은 model에 추가된 key값 loginMember 작성
			// SessionAttributes : Model 추가된 값 중 session scope에 올리고 싶은 값의
			// key를 작성하는 어노테이션

			/* 이메일 저장 코드(cookie) */
			Cookie cookie = new Cookie("saveEmail", memberEmail);

			cookie.setPath("/"); // localhost 또는 현재 ip이하 모든 주소

			if (saveEmail == null) {
				cookie.setMaxAge(0); // 만들자마자 삭제, 기존 쿠키 존재시 덮어씌우고 삭제
			} else {
				cookie.setMaxAge(60 * 60 * 24 * 30); // 초 단위
			}

			resp.addCookie(cookie);
		}

		return "redirect:/";
	}

	/*
	 * SessionStatus - @SessionAttributes를 이용해 등록된 객체(값)의 상태를 관리하는 객체
	 * 
	 * - SessionStatus.setComplate(); -> 세션 상태 완료 == 제거(만료)
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {

		status.setComplete(); // 세션 만료

		return "redirect:/";
	}

}
