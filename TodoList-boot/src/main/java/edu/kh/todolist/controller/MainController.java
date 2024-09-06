package edu.kh.todolist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todolist.dto.todoDTO;
import edu.kh.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	
	@Autowired
	private TodoListService service;
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		Map<String, Object> map = service.selectTodoList();
		
		// map에 담긴 값 꺼내놓기
		List<todoDTO> todoList = (List<todoDTO>)map.get("todoList");
		
		int complateCount = (int)map.get("complateCount");
		
		model.addAttribute("todolist", todoList);
		model.addAttribute("count", complateCount);
		
		
		return "common/main";
	}
	
	@GetMapping("selectTodo/{todoNo}")
	public String selectTodo(
			@PathVariable("todoNo") int todoNo,
			Model model
			) {
		
		todoDTO todo = service.selectTodo(todoNo);
		List<String> detail = service.detail(todoNo);
		System.out.println(todoNo);
		
		
		model.addAttribute("todo", todo);
		model.addAttribute("detail", detail);
		
		System.out.println(todo);
		System.out.println(detail);
		
		return "common/detail";
	}
	
	
	
	
}
