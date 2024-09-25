package edu.kh.project.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

@Mapper
public interface MemberMapper {

	/**
	 * memberEmail이 일치하는 회원 정보 조회
	 * @param string
	 * @return loginMember 또는 null
	 */
	Member login(String string);

	/**
	 * 회원가입
	 * @param inputMember
	 * @return result
	 */
	int signUp(Member inputMember);
	/**
	 * 이메일 중복 검사
	 * @param email
	 * @return
	 */
	int emailCheck(String email);

	/**
	 * 닉네임 중복 검사
	 * @param nickname
	 * @return
	 */
	int nickCheck(String nickname);

}
