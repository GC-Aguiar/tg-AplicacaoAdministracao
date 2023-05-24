package extra;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.model.SistemaExterno;

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
	
	
	
}
