package edu.kh.project.member.service;

import edu.kh.project.member.dto.Member;

public interface MemberService {

	/**
	 * 로그인 서비스
	 * @param memberEmail
	 * @param memberPw
	 * @return Member객체 loginMember 또는 null
	 */
	Member login(String memberEmail, String memberPw);
  
}
