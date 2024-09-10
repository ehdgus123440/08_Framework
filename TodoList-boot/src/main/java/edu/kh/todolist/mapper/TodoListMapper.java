package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	int selectNo();

	int todoAdd(@Param("todoNo") int todoNo, @Param("todoTitle") String todoTitle);

	int detailAdd(@Param("todoNo") int todoNo, @Param("detail1") String detail1); 

	int detailupdate(@Param("todoNo") int todoNo, @Param("detailInput") String detailInput);

	int delete(@Param("todoNo") int todoNo, @Param("detail") String detail);

	int todoDelete(@Param("todoNo") int todoNo);

	int complateX(@Param("todoNo")int todoNo);

	int complateO(@Param("todoNo")int todoNo);


}
