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

import taubate.fatec.tg.model.SistemaExternoView;
import taubate.fatec.tg.model.Usuario;
import taubate.fatec.tg.model.UsuarioView;
import taubate.fatec.tg.service.SistemaExternoService;
import taubate.fatec.tg.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private SistemaExternoService sistemaExternoService;

	
	private String add_edit_template = "/admin/usuario/add-edit-usuario";
	private String list_template = "/admin/usuario/list-usuario";
	private String list_redirect = "redirect:/admin/usuario/list-usuario";

	@GetMapping("/list")
	public ModelAndView listarUsuarios() {

		List<UsuarioView> listUsuarios = (List<UsuarioView>) usuarioService.listAllUsuarios();
		//List<SistemaExternoView> listSistemas = (List<SistemaExternoView>) sistemaExternoService.listAllSistemasExternos();
		System.out.println(listUsuarios);
		ModelAndView modelAndView = new ModelAndView("/admin/usuario/list-usuario");
		modelAndView.addObject("listUsuarios", listUsuarios);
		//modelAndView.addObject("listSistemas", listSistemas);

		return modelAndView;
	}
	
	 @GetMapping("/add")
		public ModelAndView inserirUsuario() {

			UsuarioView usuario = new UsuarioView();
			List<SistemaExternoView> sistemas = sistemaExternoService.listAllSistemasExternos();
	
			ModelAndView modelAndView = new ModelAndView("/admin/usuario/add-edit-usuario");
			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("sistemas", sistemas);

			return modelAndView;
		}
		
		@GetMapping("/edit/{id}")
		public ModelAndView editarUsuario(@PathVariable("id") Integer id) {

			Usuario usuario = usuarioService.getUsuarioById(id);
			UsuarioView usuarioView = new UsuarioView(usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
					"", usuario.getStatus(), usuario.getCodigoPerfil(),"Admin", usuario.getCodigoSistema(),
					sistemaExternoService.getSistemaExternoById(usuario.getCodigoSistema()).getDescricao());
			List<SistemaExternoView> sistemas = sistemaExternoService.listAllSistemasExternos();
			
			System.out.println(usuarioView);
			
			ModelAndView modelAndView = new ModelAndView("/admin/usuario/add-edit-usuario");
			modelAndView.addObject("usuario", usuarioView);
			modelAndView.addObject("sistemas", sistemas);

			return modelAndView;
		}
		
		@PostMapping("/save")
		public String gravarUsuario(@ModelAttribute Usuario usuarioView,
					Model model) {

			// Obter a data atual
			LocalDate dataAtual = LocalDate.now();
			Usuario usuario = new Usuario(usuarioView.getCodigo(), usuarioView.getNome(), usuarioView.getLogin(),
					usuarioView.getSenha(),	usuarioView.getStatus(), usuarioView.getCodigoPerfil(), 
					usuarioView.getCodigoSistema());
			
			System.out.println("sistema recebido");
			System.out.println(usuario);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			usuario.setDataAlteracao(dataAtual.format(formatter));
			usuario.setDataCadastro(dataAtual.format(formatter));// Alterar
			usuario.setUsuarioAlteracao(1); // alterar para pegar o usuário logado
			usuario.setUsuarioCadastro(1); // alterar para pegar o usuário de cadastro

			model.addAttribute("usuario", usuario);
			System.out.println("Inicio da atualizacao no /save");

			if (usuario.getCodigo() == null) {
				System.out.println("Criando usuario");
				System.out.println(usuario);
				usuarioService.save(usuario);
			} else {
				System.out.println("Atualizando usuario");
				System.out.println(usuario);
				usuarioService.updateUsuarioById(usuario, usuario.getCodigo());
			}
			return "redirect:/usuario/list";
		}
		
		@GetMapping("/delete/{id}")
		public String excluirUsuario(@PathVariable("id") Integer id) {

			usuarioService.deleteUsuarioById(id);

			return "redirect:/usuario/list";

		}

}
