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
import taubate.fatec.tg.model.Cidade;
import taubate.fatec.tg.model.CidadeView;
import taubate.fatec.tg.model.Cidade;
import taubate.fatec.tg.model.Uf;
import taubate.fatec.tg.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	AutenticacaoService autenticacaoService;

	public List<CidadeView> listAllCidades() {
		// FluxToList fluxToList;

		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/cidades")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<Cidade> fluxCidades = webClient.get().retrieve().bodyToFlux(Cidade.class);

		WebClient webClientUf = WebClient.builder().baseUrl("http://localhost:8080/ufs")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<Uf> fluxUfs = webClientUf.get().retrieve().bodyToFlux(Uf.class);

		// Converte flux to List
		List<Cidade> listaCidades = FluxToList.converterFluxParaListCidades(fluxCidades);
		List<Uf> listaUfs = FluxToList.converterFluxParaListUfs(fluxUfs);

		List<CidadeView> listaCidadesView = new ArrayList<>();

		Map<Integer, String> ufsMap = new HashMap<>();

		for (Uf uf : listaUfs) {
			ufsMap.put(uf.getCodigo(), uf.getDescricao());
		}
		for (Cidade cidade : listaCidades) {
			Integer codigoUf = cidade.getUfCodigo();
			String descricaoUf = ufsMap.get(codigoUf);
			if (descricaoUf != null) {
				listaCidadesView.add(
						new CidadeView(cidade.getCodigo(), cidade.getDescricao(), cidade.getUfCodigo(), descricaoUf));
			}
		}
//		for (CidadeView cidadeComDescricao : listaCidadesView) {
//			System.out.println(cidadeComDescricao);
//		}
		return listaCidadesView;
	}

public Cidade getCidadeById(Integer id){
		
	    WebClient webClient = WebClient.builder()
	            .baseUrl("http://localhost:8080/cidades/" + id)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	            .build();

	    Cidade cidade = webClient.get()                
	            .retrieve()
	            .bodyToMono(Cidade.class)
	            .block();
	    
//		WebClient webClientEmpresa = WebClient.builder().baseUrl("http://localhost:8080/empresas")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();
//
//		Mono<Empresa> monoEmpresa = webClientEmpresa.get()
//				.retrieve()
//				.bodyToMono(Empresa.class);
//		
//		Mono<CidadeView> monoCidadeView = monoCidade.zipWith(monoEmpresa)
//		        .map(tuple -> {
//		            Cidade cidade = tuple.getT1();
//		            Empresa empresa = tuple.getT2();
//
//		            // Criar um TerceiroObjeto combinando os campos de cidade e empresa
//		            CidadeView cidadeView = new CidadeView();
//		            cidadeView.setCodigo(cidade.getCodigo());
//		            cidadeView.setDescricao(cidade.getDescricao());
//		            cidadeView.setEmpCodigo(cidade.getEmpCodigo());
//		            cidadeView.setEmpDescricao(empresa.getDescricao());
//		            cidadeView.setStatus(cidade.getStatus());
//		            cidadeView.setEmail(cidade.getEmail());
//		            cidadeView.setPreposto(cidade.getPreposto());
//
//		            return cidadeView;
//		        });	
		
		return cidade;
	}

	public void save(Cidade cidade) {

		System.out.println("Inserindo munícipe no service");
		System.out.println(cidade);
					
	    WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/cidades/")
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	    Mono<ClientResponse> responseMono = webClient.post()                
					.body(BodyInserters.fromValue(cidade))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);

	  }    
	  
	  public void updateCidadeById(Cidade cidade, Integer id) {
	  	
			System.out.println("Atualizando cidade no service");
			System.out.println(cidade);
			
					
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/cidades/" + id)
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();      
	       
	      Mono<ClientResponse> responseMono = webClient.put()                
					.body(BodyInserters.fromValue(cidade))
					.exchange();        

		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);
	  }
	  
	  public void deleteCidadeById(Integer id) {
	  	
	      WebClient webClient = WebClient.builder()
	              .baseUrl("http://localhost:8080/cidades/" + id)
	              .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	              .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	              .build();

	      Mono<ClientResponse> responseMono = webClient.delete()
					.exchange();        

			ClientResponse response = responseMono.block();
			int statusCode = response.statusCode().value();
			System.out.println("Código de retorno HTTP: " + statusCode);

		}
	  
	  public boolean verificaCidade(Integer id){
		  
		  return cidadeRepository.existsById(id);
		  
	  }

	}
