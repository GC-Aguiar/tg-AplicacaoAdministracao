package taubate.fatec.tg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.SistemaExternoView;
import taubate.fatec.tg.service.EmpresaService;
import taubate.fatec.tg.service.SistemaExternoService;

@Controller
@RequestMapping("/sistemaExterno")
public class SistemaExternoController {
	
    @Autowired
    private SistemaExternoService sistemaExternoService;
    
    @Autowired
    private EmpresaService empresaService;

    private String add_edit_template="/admin/sistemaExterno/add-edit-sistema-externo";
    private String list_template="/admin/sistemaExterno/list-sistema-externo";
    private String list_redirect="redirect:/admin/sistemaExterno/list-sistema-externo";
    
    
    @GetMapping("/list")
    public ModelAndView listarSistemasExternos() {

        List<SistemaExternoView> listSistemasExternos = (List<SistemaExternoView>) sistemaExternoService.listAllSistemasExternos();
        //System.out.println(listSistemasExternos);
        ModelAndView modelAndView = new ModelAndView("/admin/sistemaExterno/list-sistema-externo");
        modelAndView.addObject("listSistemasExternos", listSistemasExternos);
      
        return modelAndView;
    }
    
    @GetMapping("/add")
	public ModelAndView inserirSistemaExterno() {

		SistemaExterno sistemaExterno = new SistemaExterno();
		List<Empresa> empresas = empresaService.listAllEmpresas();

		System.out.println(sistemaExterno);

		ModelAndView modelAndView = new ModelAndView("/admin/sistemaExterno/add-edit-sistema-externo");
		modelAndView.addObject("sistemaExterno", sistemaExterno);
		modelAndView.addObject("empresas", empresas);

		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editarSistemaExterno(@PathVariable("id") Integer id) {

		SistemaExterno sistemaExternoView = sistemaExternoService.getSistemaExternoById(id);
		Empresa empresaView = empresaService.getEmpresaById(sistemaExternoView.getCodigo());
		List<Empresa> empresas = empresaService.listAllEmpresas();
		
//		System.out.println(sistemaExternoView);
//		System.out.println("+-**-+-**--+");
//		System.out.println(empresaView);
		
		
		ModelAndView modelAndView = new ModelAndView("/admin/sistemaExterno/add-edit-sistema-externo");
		modelAndView.addObject("sistemaExterno", sistemaExternoView);
		modelAndView.addObject("empresa", empresaView);
		modelAndView.addObject("empresas", empresas);
		

		return modelAndView;
	}
	
	@PostMapping("/save")
	public String gravarSistemaExterno(@ModelAttribute SistemaExternoView sistemaExternoView,
				Model model) {

		// Obter a data atual
		LocalDate dataAtual = LocalDate.now();
		SistemaExterno sistemaExterno = new SistemaExterno();
		
		System.out.println("sistema recebido");
		System.out.println(sistemaExternoView);


		sistemaExterno.setCodigo(sistemaExternoView.getCodigo());
		sistemaExterno.setDescricao(sistemaExternoView.getDescricao());
		sistemaExterno.setEmpCodigo(sistemaExternoView.getEmpCodigo());
		sistemaExterno.setStatus(sistemaExternoView.getStatus());
		sistemaExterno.setEmail(sistemaExternoView.getEmail());
		sistemaExterno.setPreposto(sistemaExternoView.getPreposto());
		sistemaExterno.setContagemAcessos(sistemaExternoView.getContagemAcessos());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		sistemaExterno.setDataAlteracao(dataAtual.format(formatter));
		sistemaExterno.setDataCadastro(dataAtual.format(formatter));// Alterar
		sistemaExterno.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
		sistemaExterno.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

		model.addAttribute("sistemaExterno", sistemaExterno);
		System.out.println("Inicio da atualizacao no /save");

		if (sistemaExterno.getCodigo() == null) {
			System.out.println("Criando sistema");
			System.out.println(sistemaExterno);
			sistemaExternoService.save(sistemaExterno);
		} else {
			System.out.println("Atualizando sistema");
			System.out.println(sistemaExterno);
			sistemaExternoService.updateSistemaExternoById(sistemaExterno, sistemaExterno.getCodigo());
		}
		return "redirect:/sistemaExterno/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluirSistemaExterno(@PathVariable("id") Integer id) {

		sistemaExternoService.deleteSistemaExternoById(id);

		return "redirect:/sistemaExterno/list";

	}

}
