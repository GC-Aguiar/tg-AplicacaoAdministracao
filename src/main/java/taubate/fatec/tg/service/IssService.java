package taubate.fatec.tg.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import taubate.fatec.tg.model.Iss;

@Service
public class IssService {
	
	public void buscarIss() {
		
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();
		
		Iss iss = restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544", Iss.class);
		
		System.out.println(iss.toString());
	}

}
