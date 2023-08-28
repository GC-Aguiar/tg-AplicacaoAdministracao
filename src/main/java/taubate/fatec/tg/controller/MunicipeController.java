package taubate.fatec.tg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.service.MunicipeService;

@Controller
@RequestMapping("/municipe")
public class MunicipeController {

	@Autowired
	private MunicipeService municipeService;

	private String add_edit_template = "/admin/municipe/add-edit-municipe";
	private String list_template = "/admin/municipe/list-municipe";
	private String list_redirect = "redirect:/admin/municipe/list-municipe";

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
		
		ModelAndView modelAndView = new ModelAndView("/admin/municipe/add-edit-municipe");
		modelAndView.addObject("municipe", municipe);

		return modelAndView;
	}

	@PostMapping("/save")
	public String gravarMunicipe(@Valid @ModelAttribute("municipe") Municipe municipe, BindingResult result,
			Model model) {

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

		if (municipe.getCodigo() == null) {
			System.out.println("Atualizando munícipe");
			System.out.println(municipe);
			municipeService.save(municipe);
		} else {
			System.out.println("Atualizando munícipe");
			System.out.println(municipe);
			municipeService.update(municipe, municipe.getCodigo());
		}
		return "redirect:/municipe/list";

	}

	@GetMapping("/delete/{id}")
	public String excluirMunicipe(@PathVariable("id") Integer id) {

		municipeService.delete(id);

		return "redirect:/municipe/list";

	}

	@GetMapping("/list")
	public ModelAndView listarMunicipes() {

		List<Municipe> listMunicipes = (List<Municipe>) municipeService.listAllMunicipes();
		// System.out.println(listMunicipes);
		ModelAndView modelAndView = new ModelAndView("/admin/municipe/list-municipe");
		modelAndView.addObject("listMunicipes", listMunicipes);

		return modelAndView;
	}
}
