package br.com.mob7.localizacao.api.controller.dto;

import java.util.List;

import br.com.mob7.localizacao.api.model.Posicoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasePoisDefPosicoesDTO {

	private String nomePoi;
	private String placaVeiculo;
	private String quantidadeTempoPoi;
	private List<Posicoes> listaPosicoes;
	
}
