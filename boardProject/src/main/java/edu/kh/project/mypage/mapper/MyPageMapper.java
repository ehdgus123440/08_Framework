package edu.kh.project.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.kh.project.member.dto.Member;

@Mapper
public interface MyPageMapper {

	int updateInfo(Member inputMember);
	
	int checkNickname(String input);

	int changePw(@Param("memberNo") int memberNo, @Param("encPw") String encPw);

	int secession(int memberNo);

}
