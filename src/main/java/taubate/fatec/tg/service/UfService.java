package taubate.fatec.tg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import extra.FluxToList;
import reactor.core.publisher.Flux;
import taubate.fatec.tg.model.Uf;
import taubate.fatec.tg.repository.UfRepository;

@Service
public class UfService {
	
	@Autowired
	private UfRepository ufRepository;
	@Autowired
	AutenticacaoService autenticacaoService;
	
	
	public List<Uf> listAllUfs(){ 	
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/ufs")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();
        
		Flux<Uf> fluxUfs = webClient.get()                
                .retrieve()
                .bodyToFlux(Uf.class);		
		List<Uf> listaUfs = FluxToList.converterFluxParaListUfs(fluxUfs);
        
        return listaUfs;
	}
	
    public Uf getUfById(Integer id){
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/ufs/" + id)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();

        Uf monoUf = webClient.get()                
                .retrieve()
                .bodyToMono(Uf.class)
                .block();
        
        return monoUf;
    }

}
