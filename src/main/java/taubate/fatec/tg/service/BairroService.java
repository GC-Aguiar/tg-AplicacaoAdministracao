package taubate.fatec.tg.service;

import java.util.List;

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
import taubate.fatec.tg.model.Bairro;
import taubate.fatec.tg.repository.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	AutenticacaoService autenticacaoService;
	
	public List<Bairro> listAllBairros(){
    	//FluxToList fluxToList;     	
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/bairros")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();
        
		Flux<Bairro> fluxBairros = webClient.get()                
                .retrieve()
                .bodyToFlux(Bairro.class);
		
		List<Bairro> listaBairros = FluxToList.converterFluxParaListBairros(fluxBairros);
        
        return listaBairros;
	}
	
	 public Bairro getBairroById(Integer id){
	    	
	        WebClient webClient = WebClient.builder()
	                .baseUrl("http://localhost:8080/bairros/" + id)
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	                .build();

	        Bairro monoBairro = webClient.get()                
	                .retrieve()
	                .bodyToMono(Bairro.class)
	                .block();
	        
	        return monoBairro;
	    }
	    
		public void save(Bairro bairro) {

			System.out.println("Inserindo munícipe no service");
			System.out.println(bairro);
	  				
	        WebClient webClient = WebClient.builder()
	                  .baseUrl("http://localhost:8080/bairros/")
	                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                  .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	                  .build();      
	           
	        Mono<ClientResponse> responseMono = webClient.post()                
	  				.body(BodyInserters.fromValue(bairro))
	  				.exchange();        
	    
			ClientResponse response = responseMono.block();
			int statusCode = response.statusCode().value();
			System.out.println("Código de retorno HTTP: " + statusCode);

	      }    
	      
	      public void update(Bairro bairro, Integer id) {
	      	
	  		System.out.println("Atualizando munícipe no service");
	  		System.out.println(bairro);
	  				
	          WebClient webClient = WebClient.builder()
	                  .baseUrl("http://localhost:8080/bairros/" + id)
	                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                  .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
	                  .build();      
	           
	          Mono<ClientResponse> responseMono = webClient.put()                
	  				.body(BodyInserters.fromValue(bairro))
	  				.exchange();        
	    
			ClientResponse response = responseMono.block();
			int statusCode = response.statusCode().value();
			System.out.println("Código de retorno HTTP: " + statusCode);
	      }
	      
	      public void deleteBairroById(Integer id) {
	      	
	          WebClient webClient = WebClient.builder()
	                  .baseUrl("http://localhost:8080/bairros/" + id)
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
