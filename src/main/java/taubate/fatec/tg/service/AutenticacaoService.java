package taubate.fatec.tg.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

@Service
public class AutenticacaoService {

	String urlAPI = "http://localhost:8080/login"; // URL de autenticação da API
    String login = "admin";
    String password = "admin";
    String token; 
    
    public String autenticacaoApi() {
    	System.out.println("**********************");
    	System.out.println("Inicio da autenticação");
    	System.out.println("**********************");
    	
        WebClient webClient = WebClient.builder()
                .baseUrl(urlAPI)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String response = webClient.post()
                .body(BodyInserters.fromValue("{\"login\":\"" + login + "\",\"password\":\"" + password + "\"}"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        System.out.println("------------------");
        System.out.println(response);
        System.out.println("------------------");
        
        return response;
    }
    




	
}
