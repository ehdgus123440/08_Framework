package edu.kh.todolist.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.todoDTO;

public interface TodoListService {

	/**
	 * 할 일 목록 조회 + 완료된 할 일 개수
	 * @return map
	 */
	Map<String, Object> selectTodoList();

	/**
	 * 해당 넘버 todo 조회
	 * @param todoNo
	 * @return
	 */
	todoDTO selectTodo(int todoNo);

	/**
	 * 해당 넘버 디테일 조회
	 * @param todoNo
	 * @return
	 */
	List<String> detail(int todoNo);

}
