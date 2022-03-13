package br.com.mob7.localizacao.api.geradorQuantidadeTempo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mob7.localizacao.api.controller.dto.BasePoisDefPosicoesDTO;
import br.com.mob7.localizacao.api.model.BasePoisDef;
import br.com.mob7.localizacao.api.model.Veiculo;
import br.com.mob7.localizacao.api.repository.BasePoisDefRepository;
import br.com.mob7.localizacao.api.repository.PosicoesRepository;
import br.com.mob7.localizacao.api.repository.VeiculoRepository;

@Service
public class GeradorQuantidadeTempoSemFiltro extends GeradorQuantidadeTempo {

	@Autowired
	public PosicoesRepository posicoesRepository;
	
	@Autowired
	public BasePoisDefRepository basePoisDefRepository;

	@Autowired
	public VeiculoRepository veiculoRepository;
	
	@Override
	public List<BasePoisDefPosicoesDTO> calcular(Object... semFiltro) {
		List<BasePoisDef> listaPois = basePoisDefRepository.findAll();
		List<Veiculo> listaVeiculos = veiculoRepository.findAll();
		
		for (BasePoisDef poi : listaPois) {
			for (Veiculo veiculo : listaVeiculos) {
				listaPosicoes = posicoesRepository.findByVeiculo(veiculo);
				ultimaPosicao = null;
				somaQuantidadeTempoPoi = 0L;

				calcularQuantidadeTempoPorPoi(poi);
				listaBasePoisDefPosicoes.add(inserirPoiPorTempoCalculado(poi, veiculo));					
				listaPosicoesFiltrada = new ArrayList<>();
			}
		}
				
		return listaBasePoisDefPosicoes.stream().filter(f -> f.getNomePoi() != null).collect(Collectors.toList());
	}

}
