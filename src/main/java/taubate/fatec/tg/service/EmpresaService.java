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
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	AutenticacaoService autenticacaoService;
	
	public List<Empresa> listAllEmpresas(){ 	
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/empresas")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();
        
		Flux<Empresa> fluxEmpresas = webClient.get()                
                .retrieve()
                .bodyToFlux(Empresa.class);		
		List<Empresa> listaEmpresas = FluxToList.converterFluxParaListEmpresas(fluxEmpresas);
        
        return listaEmpresas;
	}
	
    public Empresa getEmpresaById(Integer id){
    	
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/empresas/" + id)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                .build();

        Empresa monoEmpresa = webClient.get()                
                .retrieve()
                .bodyToMono(Empresa.class)
                .block();
        
        return monoEmpresa;
    }
    
	public void save(Empresa empresa) {

		System.out.println("Inserindo munícipe no service");
		System.out.println(empresa);
  				
        WebClient webClient = WebClient.builder()
                  .baseUrl("http://localhost:8080/empresas/")
                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                  .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                  .build();      
           
        Mono<ClientResponse> responseMono = webClient.post()                
  				.body(BodyInserters.fromValue(empresa))
  				.exchange();        
    
		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);

      }    
      
      public void update(Empresa empresa, Integer id) {
      	
  		System.out.println("Atualizando munícipe no service");
  		System.out.println(empresa);
  				
          WebClient webClient = WebClient.builder()
                  .baseUrl("http://localhost:8080/empresas/" + id)
                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                  .defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi())
                  .build();      
           
          Mono<ClientResponse> responseMono = webClient.put()                
  				.body(BodyInserters.fromValue(empresa))
  				.exchange();        
    
		ClientResponse response = responseMono.block();
		int statusCode = response.statusCode().value();
		System.out.println("Código de retorno HTTP: " + statusCode);
      }
      
      public void deleteEmpresaById(Integer id) {
      	
          WebClient webClient = WebClient.builder()
                  .baseUrl("http://localhost:8080/empresas/" + id)
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
