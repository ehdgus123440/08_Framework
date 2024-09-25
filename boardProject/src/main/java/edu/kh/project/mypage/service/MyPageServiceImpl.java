package edu.kh.project.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.dto.Member;
import edu.kh.project.mypage.mapper.MyPageMapper;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageMapper mapper;
	
	@Autowired // 의존성 주입(DI)
	private BCryptPasswordEncoder encoder;
	

	@Override
	public int updateInfo(Member inputMember) {
		
		if(inputMember.getMemberAddress().equals(",,")){
			inputMember.setMemberAddress(null);
		}
		return mapper.updateInfo(inputMember);
	}

	@Override
	public int checkNickname(String input) {
		
		return mapper.checkNickname(input);
	}

	@Override
	public int changePw(String currentPw, String newPw, Member loginMember) {

		// 비밀번호가 일치하지 않으면
		if( encoder.matches(currentPw, loginMember.getMemberPw()) == false ) {
			return 0;
		}
		
		String encPw = encoder.encode(newPw);
		
		return mapper.changePw(loginMember.getMemberNo(), encPw);
	}

	
	// 회원 탈퇴
	@Override
	public int secession(String memberPw, Member loginMember) {

		// 1) 비밀번호 일치 검사
		if(encoder.matches(memberPw, loginMember.getMemberPw()) == false) {
			return 0; // 다를 경우 0 반환
		}
		
		// 2) 회원 탈퇴 Mapper 호출(update)
		return mapper.secession(loginMember.getMemberNo());
	}
	
	
}
