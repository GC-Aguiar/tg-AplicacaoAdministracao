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

import taubate.fatec.tg.model.Bairro;
import taubate.fatec.tg.service.BairroService;

@Controller
@RequestMapping("/bairro")
public class BairroController {

	@Autowired
	private BairroService bairroService;

	private String add_edit_template = "/admin/bairro/add-edit-bairro";
	private String list_template = "/admin/bairro/list-bairro";
	private String list_redirect = "redirect:/admin/bairro/list-bairro";

	@GetMapping("/list")
	public ModelAndView listarBairros() {

		List<Bairro> listBairros = (List<Bairro>) bairroService.listAllBairros();
		System.out.println(listBairros);
		ModelAndView modelAndView = new ModelAndView("/admin/bairro/list-bairro");
		modelAndView.addObject("listBairros", listBairros);

		return modelAndView;
	}
	
	@GetMapping("/add")
	public ModelAndView inserirBairro() {

		Bairro bairro = new Bairro();

		System.out.println(bairro);

		ModelAndView modelAndView = new ModelAndView("/admin/bairro/add-edit-bairro");
		modelAndView.addObject("bairro", bairro);

		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editarBairro(@PathVariable("id") Integer id) {

		Bairro bairro = bairroService.getBairroById(id);
		
		System.out.println(bairro);
		
		ModelAndView modelAndView = new ModelAndView("/admin/bairro/add-edit-bairro");
		modelAndView.addObject("bairro", bairro);

		return modelAndView;
	}
	
	@PostMapping("/save")
	public String gravarBairro(@Valid @ModelAttribute("bairro") Bairro bairro, BindingResult result,
			Model model) {

		// Obter a data atual
		LocalDate dataAtual = LocalDate.now();

		// Formatar a data no formato "AAAA-MM-DD"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		bairro.setDataAlteracao(dataAtual.format(formatter));
		bairro.setDataCadastro(dataAtual.format(formatter));// Alterar
		bairro.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
		bairro.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

		model.addAttribute("bairro", bairro);
		System.out.println("Inicio da atualizacao no /save");

		if (bairro.getCodigo() == null) {
			System.out.println("Criando novo bairro");
			System.out.println(bairro);
			bairroService.save(bairro);
		} else {
			System.out.println("Atualizando bairro");
			System.out.println(bairro);
			bairroService.update(bairro, bairro.getCodigo());
		}
		return "redirect:/bairro/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluirBairro(@PathVariable("id") Integer id) {

		bairroService.deleteBairroById(id);

		return "redirect:/bairro/list";

	}

}
