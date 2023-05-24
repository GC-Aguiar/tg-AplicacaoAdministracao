package taubate.fatec.tg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import taubate.fatec.tg.service.AutenticacaoService;

@Controller
@RequestMapping("/authteste")
public class ApiAutenticacaoController {
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@GetMapping("/")
	public void testeAutenticacao() {
		System.out.println("Acesso ao /authteste");
		autenticacaoService.autenticacaoApi();
	}
	
	

}
