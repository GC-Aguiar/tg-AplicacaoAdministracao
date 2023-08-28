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

import taubate.fatec.tg.model.Cidade;
import taubate.fatec.tg.model.CidadeView;
import taubate.fatec.tg.model.Uf;
import taubate.fatec.tg.service.CidadeService;
import taubate.fatec.tg.service.UfService;

@Controller
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private UfService ufService;

	private String add_edit_template = "/admin/cidade/add-edit-cidade";
	private String list_template = "/admin/cidade/list-cidade";
	private String list_redirect = "redirect:/admin/cidade/list-cidade";

	@GetMapping("/list")
	public ModelAndView listarCidades() {

		List<CidadeView> listCidades = (List<CidadeView>) cidadeService.listAllCidades();
		//System.out.println(listCidades);
		ModelAndView modelAndView = new ModelAndView("/admin/cidade/list-cidade");
		modelAndView.addObject("listCidades", listCidades);

		return modelAndView;
	}
	
	 @GetMapping("/add")
		public ModelAndView inserirCidade() {

			CidadeView cidadeView = new CidadeView();
			List<Uf> ufs = ufService.listAllUfs();

			System.out.println(cidadeView);

			ModelAndView modelAndView = new ModelAndView("/admin/cidade/add-edit-cidade");
			modelAndView.addObject("cidade", cidadeView);
			modelAndView.addObject("ufs", ufs);

			return modelAndView;
		}
		
		@GetMapping("/edit/{id}")
		public ModelAndView editarCidade(@PathVariable("id") Integer id) {

			Cidade cidadeView = cidadeService.getCidadeById(id);
			Uf ufView = ufService.getUfById(cidadeView.getCodigo());
			List<Uf> ufs = ufService.listAllUfs();
			
//			System.out.println(cidadeView);
//			System.out.println("+-**-+-**--+");
//			System.out.println(ufView);
			
			
			ModelAndView modelAndView = new ModelAndView("/admin/cidade/add-edit-cidade");
			modelAndView.addObject("cidade", cidadeView);
			modelAndView.addObject("uf", ufView);
			modelAndView.addObject("ufs", ufs);
			

			return modelAndView;
		}
		
		@PostMapping("/save")
		public String gravarCidade(@ModelAttribute CidadeView cidadeView,
					Model model) {

			// Obter a data atual
			LocalDate dataAtual = LocalDate.now();
			Cidade cidade = new Cidade();
			
			System.out.println("sistema recebido");
			System.out.println(cidadeView);


			cidade.setCodigo(cidadeView.getCodigo());
			cidade.setDescricao(cidadeView.getDescricao());
			cidade.setUfCodigo(cidadeView.getUfCodigo());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			cidade.setDataAlteracao(dataAtual.format(formatter));
			cidade.setDataCadastro(dataAtual.format(formatter));// Alterar
			cidade.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
			cidade.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

			model.addAttribute("cidade", cidade);
			System.out.println("Inicio da atualizacao no /save");

			if (!cidadeService.verificaCidade(cidade.getCodigo())) {
				System.out.println("Criando Cidade");
				System.out.println(cidade);
				cidadeService.save(cidade);
			} else {
				System.out.println("Atualizando cidade");
				System.out.println(cidade);
				cidadeService.updateCidadeById(cidade, cidade.getCodigo());
			}
			return "redirect:/cidade/list";
		}
		
		@GetMapping("/delete/{id}")
		public String excluirCidade(@PathVariable("id") Integer id) {

			cidadeService.deleteCidadeById(id);

			return "redirect:/cidade/list";

		}

}
