package edu.kh.todolist.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todolist.dto.todoDTO;
import edu.kh.todolist.mapper.TodoListMapper;

@Transactional
@Service // Service 역할임을 명시 + Bean 등록
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	private TodoListMapper mapper;

	@Override
	public Map<String, Object> selectTodoList() {

		// 1) 할 일 목록 조회
		List<todoDTO> todoList = mapper.selectTodoList();

		// 2) 완료된 할 일 개수 조회
		int complateCount = mapper.selectComplateCount();

		// 3) Map 객체 생성 후 조회 결과 담기
		Map<String, Object> map = new HashMap<>();

		map.put("todoList", todoList);
		map.put("complateCount", complateCount);

		return map;
	}

	@Override
	public todoDTO selectTodo(int todoNo) {

		todoDTO dto = mapper.selectDto(todoNo);

		return dto;
	}

	@Override
	public List<String> detail(int todoNo) {

		List<String> detail = mapper.detail(todoNo);
		return detail;
	}

	@Override
	public int todoAdd(String todoTitle, String detail1) {

		int todoNo = mapper.selectNo();
		int resultM = mapper.todoAdd(todoNo, todoTitle);
		int resultD = mapper.detailAdd(todoNo, detail1);

		int result = 0;

		if ((resultM + resultD) != 2) {

		}
		return result;
	}

	@Override
	public int detailAdd(int todoNo, String detailInput) {

		int result = mapper.detailupdate(todoNo, detailInput);

		return result;
	}

	@Override
	public int delete(int todoNo, String detail) {

		int result = mapper.delete(todoNo, detail);

		return result;
	}

	@Override
	public int todoDelete(int todoNo) {

		int result = mapper.todoDelete(todoNo);

		return result;
	}

	@Override
	public int complate(int todoNo, String complate) {

		int result;
		if (complate.equals("X")) {
			result = mapper.complateX(todoNo);
		}
		else if(complate.equals("O")) {
			result = mapper.complateO(todoNo);
		}
		else {
			result = mapper.complateX(todoNo);
		}
		
		return result;
	}

}
