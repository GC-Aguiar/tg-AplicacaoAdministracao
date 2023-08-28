package taubate.fatec.tg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import extra.FluxToList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.Usuario;
import taubate.fatec.tg.model.UsuarioView;
import taubate.fatec.tg.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	AutenticacaoService autenticacaoService;

	public List<UsuarioView> listAllUsuarios() {

		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/usuarios")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<Usuario> fluxUsuarios = webClient.get().retrieve().bodyToFlux(Usuario.class);

		WebClient webClientSistemaExterno = WebClient.builder().baseUrl("http://localhost:8080/sistemas")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<SistemaExterno> fluxSistemasExternos = webClientSistemaExterno.get().retrieve().bodyToFlux(SistemaExterno.class);


		// Converte flux to lista
		List<Usuario> listaUsuarios = FluxToList
				.converterFluxParaListUsuarios(fluxUsuarios);
		
				
		List<SistemaExterno> listaSistemasExternos = FluxToList
				.converterFluxParaListSistemasExternos(fluxSistemasExternos);

		List<UsuarioView> listaUsuariosView = new ArrayList<>();

		Map<Integer, String> sistemaExternosMap = new HashMap<>();

		for (SistemaExterno sistemaExterno : listaSistemasExternos) {
			sistemaExternosMap.put(sistemaExterno.getCodigo(), sistemaExterno.getDescricao());
		}
		for (Usuario usuario : listaUsuarios) {
			Integer codigoSistemaExterno = usuario.getCodigoSistema();
			String descricaoSistemaExterno = sistemaExternosMap.get(codigoSistemaExterno);
			String descricaoPerfil = "Admin";
			if (descricaoSistemaExterno != null) {
				listaUsuariosView
						.add(new UsuarioView(usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
								usuario.getSenha(), usuario.getStatus(),usuario.getCodigoPerfil(), descricaoPerfil,
								usuario.getCodigoSistema(), descricaoSistemaExterno));
			}
		}
//		for (UsuarioView sistemaComDescricao : listaSistemasExternosView) {
//			System.out.println(sistemaComDescricao);
//		}

		return listaUsuariosView;
	}
	
	public Usuario getUsuarioById(Integer id){
		
	    WebClient webClient = WebClient.builder()
	            .baseUrl("http://localhost:8080/usuarios/" + id)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	            .build();

	    Usuario usuario = webClient.get()                
	            .retrieve()
	            .bodyToMono(Usuario.class)
	            .block();
	    
//		WebClient webClientSistemaExterno = WebClient.builder().baseUrl("http://localhost:8080/sistemaExternos")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();
//
//		Mono<SistemaExterno> monoSistemaExterno = webClientSistemaExterno.get()
//				.retrieve()
//				.bodyToMono(SistemaExterno.class);
//		
//		Mono<UsuarioView> monoUsuarioView = monoUsuario.zipWith(monoSistemaExterno)
//		        .map(tuple -> {
//		            Usuario usuario = tuple.getT1();
//		            SistemaExterno sistemaExterno = tuple.getT2();
//
//		            // Criar um TerceiroObjeto combinando os campos de usuario e sistemaExterno
//		            UsuarioView usuarioView = new UsuarioView();
//		            usuarioView.setCodigo(usuario.getCodigo());
//		            usuarioView.setDescricao(usuario.getDescricao());
//		            usuarioView.setEmpCodigo(usuario.getEmpCodigo());
//		            usuarioView.setEmpDescricao(sistemaExterno.getDescricao());
//		            usuarioView.setStatus(usuario.getStatus());
//		            usuarioView.setEmail(usuario.getEmail());
//		            usuarioView.setPreposto(usuario.getPreposto());
//
//		            return usuarioView;
//		        });	
		
		return usuario;
	}

	public void save(Usuario usuario) {

		System.out.println("Inserindo munícipe no service");
		System.out.println(usuario);
					
	    WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/usuarios/")
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	    Mono<ClientResponse> responseMono = webClient.post()                
					.body(BodyInserters.fromValue(usuario))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);

	  }    
	  
	  public void updateUsuarioById(Usuario usuario, Integer id) {
	  	
			System.out.println("Atualizando munícipe no service");
			System.out.println(usuario);
					
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/usuarios/" + id)
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	      Mono<ClientResponse> responseMono = webClient.put()                
					.body(BodyInserters.fromValue(usuario))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);
	  }
	  
	  public void deleteUsuarioById(Integer id) {
	  	
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/usuarios/" + id)
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();

	      Mono<ClientResponse> responseMono = webClient.delete()
					.exchange();        

			ClientResponse response = responseMono.block();
			int statusCode = response.statusCode().value();
			System.out.println("Código de retorno HTTP: " + statusCode);
			
			
	  }



}
