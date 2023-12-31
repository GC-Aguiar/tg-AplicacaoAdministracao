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
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.SistemaExternoView;
import taubate.fatec.tg.repository.SistemaExternoRepository;

@Service
public class SistemaExternoService {

	@Autowired
	private SistemaExternoRepository sistemaExternoRepository;
	@Autowired
	AutenticacaoService autenticacaoService;

	public List<SistemaExternoView> listAllSistemasExternos() {

		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/sistemas")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<SistemaExterno> fluxSistemasExternos = webClient.get().retrieve().bodyToFlux(SistemaExterno.class);

		WebClient webClientEmpresa = WebClient.builder().baseUrl("http://localhost:8080/empresas")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<Empresa> fluxEmpresas = webClientEmpresa.get().retrieve().bodyToFlux(Empresa.class);


		// Converte flux to lista
		List<SistemaExterno> listaSistemasExternos = FluxToList
				.converterFluxParaListSistemasExternos(fluxSistemasExternos);
		
		List<Empresa> listaEmpresas = FluxToList.converterFluxParaListEmpresas(fluxEmpresas);

		List<SistemaExternoView> listaSistemasExternosView = new ArrayList<>();

		Map<Integer, String> empresasMap = new HashMap<>();

		for (Empresa empresa : listaEmpresas) {
			empresasMap.put(empresa.getCodigo(), empresa.getDescricao());
		}
		for (SistemaExterno sistema : listaSistemasExternos) {
			Integer codigoEmpresa = sistema.getEmpCodigo();
			String descricaoEmpresa = empresasMap.get(codigoEmpresa);
			if (descricaoEmpresa != null) {
				listaSistemasExternosView
						.add(new SistemaExternoView(sistema.getCodigo(), sistema.getDescricao(), sistema.getEmpCodigo(),
								descricaoEmpresa, sistema.getStatus(), sistema.getEmail(), sistema.getPreposto(), sistema.getContagemAcessos()));
			}
		}
//		for (SistemaExternoView sistemaComDescricao : listaSistemasExternosView) {
//			System.out.println(sistemaComDescricao);
//		}

		return listaSistemasExternosView;
	}
	
	public SistemaExterno getSistemaExternoById(Integer id){
		
	    WebClient webClient = WebClient.builder()
	            .baseUrl("http://localhost:8080/sistemas/" + id)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	            .build();

	    SistemaExterno sistemaExterno = webClient.get()                
	            .retrieve()
	            .bodyToMono(SistemaExterno.class)
	            .block();
	    
//		WebClient webClientEmpresa = WebClient.builder().baseUrl("http://localhost:8080/empresas")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();
//
//		Mono<Empresa> monoEmpresa = webClientEmpresa.get()
//				.retrieve()
//				.bodyToMono(Empresa.class);
//		
//		Mono<SistemaExternoView> monoSistemaExternoView = monoSistemaExterno.zipWith(monoEmpresa)
//		        .map(tuple -> {
//		            SistemaExterno sistemaExterno = tuple.getT1();
//		            Empresa empresa = tuple.getT2();
//
//		            // Criar um TerceiroObjeto combinando os campos de sistemaExterno e empresa
//		            SistemaExternoView sistemaExternoView = new SistemaExternoView();
//		            sistemaExternoView.setCodigo(sistemaExterno.getCodigo());
//		            sistemaExternoView.setDescricao(sistemaExterno.getDescricao());
//		            sistemaExternoView.setEmpCodigo(sistemaExterno.getEmpCodigo());
//		            sistemaExternoView.setEmpDescricao(empresa.getDescricao());
//		            sistemaExternoView.setStatus(sistemaExterno.getStatus());
//		            sistemaExternoView.setEmail(sistemaExterno.getEmail());
//		            sistemaExternoView.setPreposto(sistemaExterno.getPreposto());
//
//		            return sistemaExternoView;
//		        });	
		
		return sistemaExterno;
	}

	public void save(SistemaExterno sistemaExterno) {

		System.out.println("Inserindo munícipe no service");
		System.out.println(sistemaExterno);
					
	    WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/sistemas/")
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	    Mono<ClientResponse> responseMono = webClient.post()                
					.body(BodyInserters.fromValue(sistemaExterno))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);

	  }    
	  
	  public void updateSistemaExternoById(SistemaExterno sistemaExterno, Integer id) {
	  	
			System.out.println("Atualizando munícipe no service");
			System.out.println(sistemaExterno);
					
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/sistemas/" + id)
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	      Mono<ClientResponse> responseMono = webClient.put()                
					.body(BodyInserters.fromValue(sistemaExterno))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);
	  }
	  
	  public void deleteSistemaExternoById(Integer id) {
	  	
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/sistemas/" + id)
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
