package edu.kh.demo.service;

import java.util.List;

import edu.kh.demo.dto.UserDto;

public interface UserService {
	
	/**
	 * 사용자 이름 조회
	 * @param userNo
	 * @return userName
	 */
	String selectUserName(int userNo);

	/**
	 * 유저 전체 조회
	 * @return userList
	 */
	List<UserDto> selectAll();

	/**
	 * 유저 조회
	 * @param userNo
	 * @return
	 */
	UserDto selectUser(int userNo);

	/**
	 * 사용자 정보 수정
	 * @param user
	 * @return result
	 */
	int update(UserDto user);

	/**
	 * 사용자 정보 삭제
	 * @param userNo
	 * @return
	 */
	int deleteUser(int userNo);

	/**
	 * 사용자 추가
	 * @param userName
	 * @param userId
	 * @param userPw
	 * @return
	 */
	int insertUser(UserDto user);

}
