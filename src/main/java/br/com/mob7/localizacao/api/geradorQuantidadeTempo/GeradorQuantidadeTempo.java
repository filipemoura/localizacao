package br.com.mob7.localizacao.api.geradorQuantidadeTempo;

import java.time.LocalDateTime;
import java.util.List;

import br.com.mob7.localizacao.api.controller.dto.BasePoisDefPosicoesDTO;
import br.com.mob7.localizacao.api.model.BasePoisDef;
import br.com.mob7.localizacao.api.model.Posicoes;
import br.com.mob7.localizacao.api.model.Veiculo;
import br.com.mob7.localizacao.api.utils.DateUtils;

public abstract class GeradorQuantidadeTempo {
	
	protected Long somaQuantidadeTempoPoi;
	protected LocalDateTime ultimaPosicao;
	
	public abstract List<BasePoisDefPosicoesDTO> calcular(Object... objects);
	
	public BasePoisDefPosicoesDTO inserirPoiPorTempoCalculado(BasePoisDef poi, Veiculo veiculo, List<Posicoes> listaPosicoesFiltrada) {	
		return somaQuantidadeTempoPoi > 0 ? 
			new BasePoisDefPosicoesDTO(poi.getNome(),
					veiculo.getPlaca(), DateUtils.intervaloEmHorasMinutosSegundos(somaQuantidadeTempoPoi),
					listaPosicoesFiltrada) : 
						new BasePoisDefPosicoesDTO();
	}
	
	public Long calcularQuantidadeTempoPorPoi(List<Posicoes> listaPosicoesFiltrada, BasePoisDef poi,
			List<Posicoes> listaPosicoes, LocalDateTime ultimaPosicao) {
		for (Posicoes posicao : listaPosicoes) {
			Double distanciaMetrosPoi = DateUtils.formulaHaversine(poi.getLatitude(), poi.getLongitude(),
					posicao.getLatitude(), posicao.getLongitude());

			if (distanciaMetrosPoi <= poi.getRaio()) {
				if (ultimaPosicao != null) {
					somaQuantidadeTempoPoi += DateUtils.intervaloEmMinutos(ultimaPosicao, posicao.getDataPosicao());
					ultimaPosicao = posicao.getDataPosicao();
				} else {
					ultimaPosicao = posicao.getDataPosicao();
				}
				listaPosicoesFiltrada.add(posicao);
			} else {
				ultimaPosicao = null;
			}
		}
		return somaQuantidadeTempoPoi;
	}
}
