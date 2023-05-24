package taubate.fatec.tg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.SistemaExternoView;
import taubate.fatec.tg.service.SistemaExternoService;

@Controller
@RequestMapping("/sistemaExterno")
public class SistemaExternoController {
	
    @Autowired
    private SistemaExternoService sistemaExternoService;

    private String add_edit_template="/admin/sistemaExterno/add-edit-sistema-externo";
    private String list_template="/admin/sistemaExterno/list-sistema-externo";
    private String list_redirect="redirect:/admin/sistemaExterno/list-sistema-externo";
    
    
    @GetMapping("/list")
    public ModelAndView listarSistemasExternos() {

        List<SistemaExternoView> listSistemasExternos = (List<SistemaExternoView>) sistemaExternoService.listAllSistemasExternos();
        System.out.println(listSistemasExternos);
        ModelAndView modelAndView = new ModelAndView("/admin/sistemaExterno/list-sistema-externo");
        modelAndView.addObject("listSistemasExternos", listSistemasExternos);
      
        return modelAndView;
    }

}
