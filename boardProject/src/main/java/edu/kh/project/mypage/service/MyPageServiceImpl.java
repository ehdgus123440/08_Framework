package edu.kh.project.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.dto.Member;
import edu.kh.project.mypage.mapper.MyPageMapper;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageMapper mapper;

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
}
