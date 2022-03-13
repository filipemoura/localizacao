package br.com.mob7.localizacao.api.geradorQuantidadeTempo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mob7.localizacao.api.controller.dto.BasePoisDefPosicoesDTO;
import br.com.mob7.localizacao.api.model.BasePoisDef;
import br.com.mob7.localizacao.api.model.Posicoes;
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
	public List<BasePoisDefPosicoesDTO> calcular(Object... objects) {
		List<BasePoisDef> listaPois = basePoisDefRepository.findAll();
		List<Veiculo> listaVeiculos = veiculoRepository.findAll();
		List<Posicoes> listaPosicoesFiltrada = new ArrayList<>();
		List<BasePoisDefPosicoesDTO> listaBasePoisDefPosicoes = new ArrayList<>();
		
		for (BasePoisDef poi : listaPois) {
			for (Veiculo veiculo : listaVeiculos) {
				List<Posicoes> listaPosicoes = posicoesRepository.findByVeiculo(veiculo);
				LocalDateTime ultimaPosicao = null;
				Long somaQuantidadeTempoPoi = 0L;

//				somaQuantidadeTempoPoi = calcularQuantidadeTempoPorPoi(listaPosicoesFiltrada, poi, listaPosicoes, ultimaPosicao, somaQuantidadeTempoPoi);
//				listaBasePoisDefPosicoes.add(inserirPoiPorTempoCalculado(poi, veiculo, somaQuantidadeTempoPoi, listaPosicoesFiltrada));					
				
				listaPosicoesFiltrada = new ArrayList<>();
			}
		}
				
		return listaBasePoisDefPosicoes.stream().filter(f -> f.getNomePoi() != null).collect(Collectors.toList());
	}

}
