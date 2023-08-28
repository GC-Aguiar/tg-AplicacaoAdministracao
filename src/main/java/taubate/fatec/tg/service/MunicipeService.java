package taubate.fatec.tg.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;


import extra.FluxToList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.repository.MunicipeRepository;

@Service
public class MunicipeService {
	
    @Autowired
    private MunicipeRepository municipeRepository;
        /* Realiza a autenticação no endpoint /login e retorna o token */
	@Autowired
	AutenticacaoService autenticacaoService;   

    public List<Municipe> listAllMunicipes() {  
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();

        Flux<Municipe> fluxMunicipes = webClient.get()                
                .retrieve()
                .bodyToFlux(Municipe.class);        
        List<Municipe> listaMunicipes = FluxToList.converterFluxParaList(fluxMunicipes);
        
        return listaMunicipes;
    }
    
    public Municipe getMunicipeById(Integer id){
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes/" + id)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();

        Municipe monoMunicipe = webClient.get()                
                .retrieve()
                .bodyToMono(Municipe.class)
                .block();
        
        return monoMunicipe;
    }
    
    public void save(Municipe municipe) {
    	
		System.out.println("Inserindo munícipe no service");
		System.out.println(municipe);
				
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();      
         
        Mono<ClientResponse> responseMono = webClient.post()                
				.body(BodyInserters.fromValue(municipe))
				.exchange();        
  
        		ClientResponse response = responseMono.block();
        		int statusCode = response.statusCode().value();
        		System.out.println("Código de retorno HTTP: " + statusCode);

    }    
    
    public void update(Municipe municipe, Integer id) {
    	
		System.out.println("Atualizando munícipe no service");
		System.out.println(municipe);
				
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes/" + id)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();      
         
        Mono<ClientResponse> responseMono = webClient.put()                
				.body(BodyInserters.fromValue(municipe))
				.exchange();        
  
        		ClientResponse response = responseMono.block();
        		int statusCode = response.statusCode().value();
        		System.out.println("Código de retorno HTTP: " + statusCode);
    }
    
    public void delete(Integer id) {
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes/" + id)
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
