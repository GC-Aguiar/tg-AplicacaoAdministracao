package taubate.fatec.tg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.service.LgpdService;
import taubate.fatec.tg.service.MunicipeService;

@Controller
@RequestMapping("/lgpd")
public class LgpdController {
	
	@Autowired
	private LgpdService lgpdService;
	@Autowired
	private MunicipeService municipeService;
	
    @GetMapping("/list")
    public ModelAndView listarSolicitacoesLgpd() {

        List<Municipe> listMunicipesLgpd = (List<Municipe>) lgpdService.listMunicipesLgpd();
        System.out.println(listMunicipesLgpd);
        ModelAndView modelAndView = new ModelAndView("/admin/lgpd/list-lgpd");
        modelAndView.addObject("listMunicipesLgpd", listMunicipesLgpd);
      
        return modelAndView;
    }
    
	@GetMapping("/add")
	public ModelAndView inserirMunicipe() {

		Municipe municipe = new Municipe();

		System.out.println(municipe);

		ModelAndView modelAndView = new ModelAndView("/admin/municipe/add-edit-municipe");
		modelAndView.addObject("municipe", municipe);

		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editarMunicipe(@PathVariable("id") Integer id) {

		Municipe municipe = municipeService.getMunicipeById(id);
		
		System.out.println(municipe);
		
		ModelAndView modelAndView = new ModelAndView("/admin/lgpd/add-edit-lgpd");
		modelAndView.addObject("municipe", municipe);

		return modelAndView;
	}
	
	@PostMapping("/save")
	public String gravarMunicipe(@Valid @ModelAttribute("municipe") Municipe municipeView, BindingResult result,
			Model model) {
		
		Municipe municipe = new Municipe();
		municipe = municipeService.getMunicipeById(municipeView.getCodigo());
		
		if (municipeView.isSolicitaExclusao()) {
		    municipe.setSolicitaExclusao(true);
		} else {
		    municipe.setSolicitaExclusao(false);
		}

		// Obter a data atual
		LocalDate dataAtual = LocalDate.now();

		// Formatar a data no formato "AAAA-MM-DD"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		municipe.setDataAlteracao(dataAtual.format(formatter));
		municipe.setDataCadastro(dataAtual.format(formatter));// Alterar
		municipe.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
		municipe.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

		model.addAttribute("municipe", municipe);
		System.out.println("Inicio da atualizacao no /save");
		System.out.println(municipe);
		
		municipeService.update(municipe, municipe.getCodigo());
	
		return "redirect:/lgpd/list";

	}
}
