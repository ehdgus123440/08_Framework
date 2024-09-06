package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.dto.todoDTO;

// 상속 받은 클래스 생성 후 Bean 등록
@Mapper
public interface TodoListMapper {
	
	/**
	 * todolist 전체 조회
	 * @return
	 */
	List<todoDTO> selectTodoList();

	/**
	 * todoComplate 완료 개수 
	 * @return
	 */
	int selectComplateCount();

	todoDTO selectDto(int todoNo);

	List<String> detail(int todoNo);


}
