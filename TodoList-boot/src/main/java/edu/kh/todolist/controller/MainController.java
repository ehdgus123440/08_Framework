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
		
		return "common/detail";
	}
	
	@GetMapping("addTab")
	public String addTab() {
		return "common/addTab";
	}
	
	@GetMapping("todoAdd")
	public String todoAdd(
		@RequestParam("todoTitle") String todoTitle,
		@RequestParam("detail1") String detail1
		) {
		
		System.out.println(todoTitle);
		System.out.println(detail1);
		
		int result = service.todoAdd(todoTitle, detail1);
		
		return "redirect:/";
		
	}
	
	@GetMapping("detail/update/{todoNo}")
	public String detailUpdate(
			@PathVariable("todoNo") String No
			, Model model
			) {
		
		int todoNo = Integer.parseInt(No);
		
		todoDTO todo = service.selectTodo(todoNo);
		
		model.addAttribute("todo",todo);
		
		
		return "common/updateTab";
		
	}
	
	
	@GetMapping("detailAdd")
	public String detailAdd(
			@RequestParam("detailInput") String detailInput,
			@RequestParam("todoNo") String No
			) {
		System.out.println(detailInput);
		System.out.println(No);
		
		int todoNo = Integer.parseInt(No);
		
		int result = service.detailAdd(todoNo, detailInput);
		
		return "redirect:selectTodo/" + todoNo;
	}
	
	@GetMapping("delete/{detailDelete}/{todoNo}")
	public String delete(
			@PathVariable("detailDelete") String detail,
			@PathVariable("todoNo") String no
			) {
		int todoNo = Integer.parseInt(no);
		System.out.println(todoNo);
		System.out.println(detail);
		
		int result = service.delete(todoNo, detail);
		return "redirect:/selectTodo/"+todoNo;
	}
	
	@GetMapping("todoDelete/{no}")
	public String todoDelete(
			@PathVariable("no") String no
			) {
		
		int todoNo = Integer.parseInt(no);
		
		int result = service.todoDelete(todoNo);
		
		return "redirect:/";
	}
	
	@GetMapping("complate")
	public String complate(
			@RequestParam("todoNo") String No,
			@RequestParam("complate") String complate
			) {
		
		int todoNo = Integer.parseInt(No);
		int result = service.complate(todoNo, complate);
		
		return "redirect:/selectTodo/"+todoNo;  
	}
}
