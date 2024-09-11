package edu.kh.project.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.member.dto.Member;
import edu.kh.project.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	/* 비밀번호 암호화 
	 * - 하는 이유 : 평문 상태로 비밀번호 저장하면 안됨
	 * 
	 * - 옛날 방식 : 데이터 -> 암호화, 암호화된 데이터 -> 복호화 -> 원본 데이터
	 * 
	 * - 현재 : 데이터를 암호화만 가능(SHA-256) -> 복호화 X
	 * 		-> 마구 잡이로 대입하서 만들어진 암호화 데이터 테이블에 뚫림
	 * 
	 * - 요즘 트렌드 : BCrypt 암호화
	 * 
	 * - 입력된 문자열(비밀번호)에 salt를 추가한 후 암호화
	 *   -> 암호화 할 때 마다 결과가 다름
	 *   -> DB에 입력받은 비밀번호를 암호화해서 넘겨줘도 
	 *   		비교 불가능
	 *   -> BCrypt가 함께 제공하는 평문, 암호화 데이터 비교 메서드인
	 *   		matches()를 이용하면 됨(같으면 true, 틀리면 false)
	 *   
	 *   		-> matches() 메서드는 자바에서 동작
	 *   			 DB에 저장된 암호화된 비밀번호를 조회해서 가져와야함
	 */
	@Override
	public Member login(String memberEmail, String memberPw) {

//		log.debug("memberPw : {}", memberPw);
//		log.debug("암호화된 memberPw : {}", encoder.encode(memberPw));
		
		Member loginMember = mapper.login(memberEmail);
		if(loginMember == null) return null;
		
//		log.debug("비밀번호 일치? : {}", encoder.matches(memberPw, loginMember.getMemberPw()));
		if ( !encoder.matches(memberPw, loginMember.getMemberPw()) ) {
			return null;
		}
		
		return loginMember;
	}
	
}
