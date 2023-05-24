package taubate.fatec.tg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import extra.FluxToList;
import reactor.core.publisher.Flux;
import taubate.fatec.tg.model.Empresa;
import taubate.fatec.tg.model.SistemaExterno;
import taubate.fatec.tg.model.SistemaExternoView;
import taubate.fatec.tg.repository.SistemaExternoRepository;

@Service
public class SistemaExternoService {

	@Autowired
	private SistemaExternoRepository sistemaExternoRepository;
	@Autowired
	AutenticacaoService autenticacaoService;

	public List<SistemaExternoView> listAllSistemasExternos() {

		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/sistemas")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<SistemaExterno> fluxSistemasExternos = webClient.get().retrieve().bodyToFlux(SistemaExterno.class);

		WebClient webClientEmpresa = WebClient.builder().baseUrl("http://localhost:8080/empresas")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, autenticacaoService.autenticacaoApi()).build();

		Flux<Empresa> fluxEmpresas = webClientEmpresa.get().retrieve().bodyToFlux(Empresa.class);

		/*
		 * Tentativa de mesclar Flux<SistemaExternoView> sistemasExternosView =
		 * fluxSistemasExternos .join(fluxEmpresas, sistema ->
		 * Flux.just(sistema.getEmpCodigo()), // Obtém a chave do sistema (código da
		 * empresa) empresa -> Flux.just(empresa.getCodigo()), // Obtém a chave da
		 * empresa (sistema, empresa) -> new SistemaExternoView(sistema.getCodigo(),
		 * sistema.getDescricao(), sistema.getEmpCodigo(), empresa.getDescricao(),
		 * sistema.getStatus(), sistema.getEmail(), sistema.getPreposto())); // Combina
		 * o sistema com a empresa
		 * 
		 * 
		 * Flux<SistemaExternoView> sistemasComDescricaoFlux = fluxSistemasExternos
		 * .flatMap(sistema -> { Mono<Empresa> empresaMono = fluxEmpresas
		 * .filter(empresa -> empresa.getCodigo().equals(sistema.getEmpCodigo()))
		 * .next();
		 * 
		 * return empresaMono.map(empresa -> new SistemaExternoView(sistema.getCodigo(),
		 * sistema.getDescricao(), sistema.getEmpCodigo(), empresa.getDescricao(),
		 * sistema.getStatus(), sistema.getEmail(), sistema.getPreposto())); });
		 * 
		 * System.out.println("++++++++++++++++++");
		 * fluxSistemasExternos.subscribe(System.out::println);
		 */

		// Converte flux to lista
		List<SistemaExterno> listaSistemasExternos = FluxToList
				.converterFluxParaListSistemasExternos(fluxSistemasExternos);
		List<Empresa> listaEmpresas = FluxToList.converterFluxParaListEmpresas(fluxEmpresas);

		List<SistemaExternoView> listaSistemasExternosView = new ArrayList<>();

		Map<Integer, String> empresasMap = new HashMap<>();

		for (Empresa empresa : listaEmpresas) {
			empresasMap.put(empresa.getCodigo(), empresa.getDescricao());
		}
		for (SistemaExterno sistema : listaSistemasExternos) {
			Integer codigoEmpresa = sistema.getEmpCodigo();
			String descricaoEmpresa = empresasMap.get(codigoEmpresa);
			if (descricaoEmpresa != null) {
				listaSistemasExternosView
						.add(new SistemaExternoView(sistema.getCodigo(), sistema.getDescricao(), sistema.getEmpCodigo(),
								descricaoEmpresa, sistema.getStatus(), sistema.getEmail(), sistema.getPreposto()));
			}
		}
		for (SistemaExternoView sistemaComDescricao : listaSistemasExternosView) {
			System.out.println(sistemaComDescricao);
		}

		return listaSistemasExternosView;
	}

	/*
	 * Incluir os outros métodos (gravarEmpresa, buscarEmpresaPorId, alterarEmpresa)
	 */

}
