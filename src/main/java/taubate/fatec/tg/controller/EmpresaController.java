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

import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	private String add_edit_template = "/admin/empresa/add-edit-empresa";
	private String list_template = "/admin/empresa/list-empresa";
	private String list_redirect = "redirect:/admin/empresa/list-empresa";

	@GetMapping("/list")
	public ModelAndView listarEmpresas() {

		List<Empresa> listEmpresas = (List<Empresa>) empresaService.listAllEmpresas();
		System.out.println(listEmpresas);
		ModelAndView modelAndView = new ModelAndView("/admin/empresa/list-empresa");
		modelAndView.addObject("listEmpresas", listEmpresas);

		return modelAndView;
	}
	
	@GetMapping("/add")
	public ModelAndView inserirEmpresa() {

		Empresa empresa = new Empresa();

		System.out.println(empresa);

		ModelAndView modelAndView = new ModelAndView("/admin/empresa/add-edit-empresa");
		modelAndView.addObject("empresa", empresa);

		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editarEmpresa(@PathVariable("id") Integer id) {

		Empresa empresa = empresaService.getEmpresaById(id);
		
		System.out.println(empresa);
		
		ModelAndView modelAndView = new ModelAndView("/admin/empresa/add-edit-empresa");
		modelAndView.addObject("empresa", empresa);

		return modelAndView;
	}
	
	@PostMapping("/save")
	public String gravarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa, BindingResult result,
			Model model) {

		// Obter a data atual
		LocalDate dataAtual = LocalDate.now();

		// Formatar a data no formato "AAAA-MM-DD"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		empresa.setDataAlteracao(dataAtual.format(formatter));
		empresa.setDataCadastro(dataAtual.format(formatter));// Alterar
		empresa.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
		empresa.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

		model.addAttribute("empresa", empresa);
		System.out.println("Inicio da atualizacao no /save");

		if (empresa.getCodigo() == null) {
			System.out.println("Atualizando munícipe");
			System.out.println(empresa);
			empresaService.save(empresa);
		} else {
			System.out.println("Atualizando munícipe");
			System.out.println(empresa);
			empresaService.update(empresa, empresa.getCodigo());
		}
		return "redirect:/empresa/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluirEmpresa(@PathVariable("id") Integer id) {

		empresaService.deleteEmpresaById(id);

		return "redirect:/empresa/list";

	}
}
