package taubate.fatec.tg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    private String add_edit_template="/admin/empresa/add-edit-empresa";
    private String list_template="/admin/empresa/list-empresa";
    private String list_redirect="redirect:/admin/empresa/list-empresa";
    
    
    @GetMapping("/list")
    public ModelAndView listarEmpresas() {

        List<Empresa> listEmpresas = (List<Empresa>) empresaService.listAllEmpresas();
        System.out.println(listEmpresas);
        ModelAndView modelAndView = new ModelAndView("/admin/empresa/list-empresa");
        modelAndView.addObject("listEmpresas", listEmpresas);
      
        return modelAndView;
    }
}
