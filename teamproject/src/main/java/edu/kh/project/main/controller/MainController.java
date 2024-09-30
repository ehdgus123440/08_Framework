package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {

		@RequestMapping("test1")
		public String summernote() {
			return "common/test1";
		}
	
}
