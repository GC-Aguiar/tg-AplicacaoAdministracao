package taubate.fatec.tg.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.repository.MunicipeRepository;

@Service
public class LgpdService {
	
    @Autowired
    private MunicipeRepository municipeRepository;
    @Autowired
    private MunicipeService municipeService;
    
    /* Realiza a autenticação no endpoint /login e retorna o token */
	@Autowired
	AutenticacaoService autenticacaoService; 
	
    public List<Municipe> listMunicipesLgpd(){
    	
        List<Municipe> listaMunicipesLgpd = municipeService.listAllMunicipes();
        
        // Remover elementos com solicitaExclusao = false
           Iterator<Municipe> iterator = listaMunicipesLgpd.iterator();
           while (iterator.hasNext()) {
               Municipe municipe = iterator.next();
               if (!municipe.isSolicitaExclusao()) {
                   iterator.remove();
               }
           }
           // Exibir os municipes restantes
           for (Municipe municipe : listaMunicipesLgpd) {
               System.out.println(municipe);
           }
           
           return listaMunicipesLgpd;
       }   
    	
    }


