package com.company.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/doc")
public class DocumentController {

	@GetMapping("/api")
	public String test(Model model) {
		return "/api";
	}
}
