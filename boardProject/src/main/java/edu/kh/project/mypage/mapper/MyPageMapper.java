package edu.kh.project.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

@Mapper
public interface MyPageMapper {

	int updateInfo(Member inputMember);
	
	int checkNickname(String input);


}
