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

}
