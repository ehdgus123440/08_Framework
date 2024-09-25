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

	/**
	 * 회원가입
	 * @param inputMember
	 * @return reuslt
	 */
	int signUp(Member inputMember);

	/**
	 * 이메일 중복 검사
	 * @param email
	 * @return count
	 */
	int emailCheck(String email);

	/**
	 * 닉네임 중복 검사
	 * @param nickname
	 * @return count
	 */
	int nickCheck(String nickname);
  
}
