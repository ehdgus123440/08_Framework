package edu.kh.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.demo.dto.UserDto;
import edu.kh.demo.mapper.TestMapper;

// @Service 역할(비즈니스 로직)임을 명시
// - Bean 등록 (Spring이 관리하는 객체 == IOC)
@Service
public class UserServiceImpl implements UserService{
	
	/* @Autowired 
	 * - 등록된 Bean 중에서
	 * 	 자료형이 같은 Bean을 얻어와 필드에 대입
	 *   == DI(의존성 주입)
	 */	 
	@Autowired
	private TestMapper mapper;

	@Override
	public String selectUserName(int userNo) {
		
		return mapper.selectUserName(userNo);
	}

	@Override
	public List<UserDto> selectAll() {
		
		// mapper : 의존성 주입(DI)받은 TestMapper를
		//      	  상속받아 구현된 클래스로 만들어진 Bean
		return mapper.selectAll();
	}

	/* userNo가 일치하는 유저 조회 */
	@Override
	public UserDto selectUser(int userNo) {
		
		return mapper.selectUser(userNo);
	}

	/* 사용자 정보 수정 */
	// -> DML 수행 하면 트랜잭션 제어 처리
	// @Transactional : 해당 메서드 수행 중 RuntimeException 발생 시 롤백 수행
	// 									예외가 발생하지 않으면 메서드 종료 후 커밋 수행
	
	@Transactional
	@Override
	public int update(UserDto user) {
		return mapper.updateUser(user);
	}
	@Transactional
	@Override
	public int deleteUser(int userNo) {
		return mapper.deleteUser(userNo);
	}

	@Transactional
	@Override
	public int insertUser(UserDto user) {
		return mapper.insertUser(user);
	}
	
}
