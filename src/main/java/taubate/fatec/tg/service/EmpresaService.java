package taubate.fatec.tg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import extra.FluxToList;
import reactor.core.publisher.Flux;
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	AutenticacaoService autenticacaoService;
	
	public List<Empresa> listAllEmpresas(){
    	//FluxToList fluxToList;     	
    	
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
	
	/* 
 	Incluir os outros métodos (gravarEmpresa, buscarEmpresaPorId, alterarEmpresa)	 
	 */
}
