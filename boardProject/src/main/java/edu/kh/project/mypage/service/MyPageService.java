package edu.kh.project.mypage.service;

import edu.kh.project.member.dto.Member;

public interface MyPageService {

	/**
	 * 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/**
	 * (비동기) 닉네임 중복 검사
	 * @param input
	 * @return
	 */
	int checkNickname(String input);

	/**
	 * 
	 * @param currentPw
	 * @param newPw
	 * @param loginMember
	 * @return
	 */
	int changePw(String currentPw, String newPw, Member loginMember);

	/** 회원 탈퇴
	 * @param memberPw
	 * @param loginMember
	 * @return result
	 */
	int secession(String memberPw, Member loginMember);


}
