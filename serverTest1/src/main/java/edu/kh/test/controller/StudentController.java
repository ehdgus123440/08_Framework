package edu.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.test.dto.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("student")
@Controller
public class StudentController {

	@PostMapping("select")
	public String selectStudent(Model model, @ModelAttribute Student student ) {
		
		model.addAttribute("stdName", student.getStdName());
		model.addAttribute("stdAge", student.getStdAge());
		model.addAttribute("stdAddress", student.getStdAddress());
		
		System.out.println(student.getStdName());
		System.out.println(student.getStdAge());
		System.out.println(student.getStdAddress());
		
		return "student/select";
	}
	
}