package taubate.fatec.tg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.service.LgpdService;

@Controller
@RequestMapping("/lgpd")
public class LgpdController {
	
	@Autowired
	private LgpdService lgpdService;
	
    @GetMapping("/list")
    public ModelAndView listarSolicitacoesLgpd() {

        List<Municipe> listMunicipesLgpd = (List<Municipe>) lgpdService.listMunicipesLgpd();
        System.out.println(listMunicipesLgpd);
        ModelAndView modelAndView = new ModelAndView("/admin/lgpd/list-lgpd");
        modelAndView.addObject("listMunicipesLgpd", listMunicipesLgpd);
      
        return modelAndView;
    }

}
