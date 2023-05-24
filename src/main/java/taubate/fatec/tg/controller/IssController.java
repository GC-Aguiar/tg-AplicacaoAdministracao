package taubate.fatec.tg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import taubate.fatec.tg.service.IssService;

@Controller
@RequestMapping("/iss")
public class IssController {
	
	@Autowired
	IssService issService;

	@GetMapping
	public String verificarIss(){
		
		issService.buscarIss();
		
		
		
		return "admin/iss/iss.html";
	}
}
