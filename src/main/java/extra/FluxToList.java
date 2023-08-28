package extra;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taubate.fatec.tg.model.Bairro;
import taubate.fatec.tg.model.Cidade;
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.Uf;
import taubate.fatec.tg.model.Usuario;

public class FluxToList {
    
	public static List<Municipe> converterFluxParaList(Flux<Municipe> fluxo) {
        Mono<List<Municipe>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<Empresa> converterFluxParaListEmpresas(Flux<Empresa> fluxo) {
        Mono<List<Empresa>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<SistemaExterno> converterFluxParaListSistemasExternos(Flux<SistemaExterno> fluxo) {
        Mono<List<SistemaExterno>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<Cidade> converterFluxParaListCidades(Flux<Cidade> fluxo) {
        Mono<List<Cidade>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<Uf> converterFluxParaListUfs(Flux<Uf> fluxo) {
        Mono<List<Uf>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<Bairro> converterFluxParaListBairros(Flux<Bairro> fluxo) {
        Mono<List<Bairro>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	public static List<Usuario> converterFluxParaListUsuarios(Flux<Usuario> fluxo) {
        Mono<List<Usuario>> listaMono = fluxo.collectList();
        return listaMono.block();
    }
	
	
	
}
