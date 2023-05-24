package taubate.fatec.tg.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import extra.FluxToList;
import reactor.core.publisher.Flux;
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
    	
    	FluxToList fluxToList;     	
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/municipes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();

        Flux<Municipe> fluxMunicipes = webClient.get()                
                .retrieve()
                .bodyToFlux(Municipe.class);
        
        List<Municipe> listaMunicipes = FluxToList.converterFluxParaList(fluxMunicipes);
        
        //listaMunicipes.subscribe(municipe -> System.out.println("Municipe: " + municipe));
        
        return listaMunicipes;
    }
    

    
    /*

     * 
     * 
    public Mono<Employee> findById(Integer id)
    	{
    		return webClient.get()
    			.uri("/employees/" + id)
    			.retrieve()
    			//.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
    	        //        clientResponse -> Mono.empty())
    			.bodyToMono(Employee.class);
    	}
    */
    
    //verificar funcionamento !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*
    public void addMunicipe(Municipe municipe) {
    	WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<Municipe> novoMunicipe = webClient.post()
        		.uri("/municipes")
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        		.body(Mono.just(municipe), Municipe.class)
        		.retrieve()
        		.bodyToMono(Municipe.class);

    }
    */
}
