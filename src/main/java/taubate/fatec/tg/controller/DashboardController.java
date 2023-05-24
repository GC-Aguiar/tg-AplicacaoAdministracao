package taubate.fatec.tg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import taubate.fatec.tg.service.AutenticacaoService;
import taubate.fatec.tg.service.MunicipeService;

@Controller
public class DashboardController {
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	MunicipeService municipeService;

    @RequestMapping("/")
    public String index() {
    	//Teste de autenticação
    	autenticacaoService.autenticacaoApi();
    	//Teste de busca de municipes
    	//municipeService.listAllMunicipes();
    	
        return "admin/dashboard/index";
    }
    
    @RequestMapping("/dashboard/view")
    public String dashboard() {
    	    	
        return "admin/dashboard/dashboard";
    }

    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
