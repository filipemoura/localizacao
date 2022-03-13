package br.com.mob7.localizacao.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mob7.localizacao.api.controller.dto.BasePoisDefPosicoesDTO;
import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoDatas;
import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoPlaca;
import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoSemFiltro;

@RestController
@RequestMapping("/posicoes")
public class PosicoesController {
	
	@Autowired
	public GeradorQuantidadeTempoSemFiltro geradorQuantidadeTempoSemFiltro;
	
	@Autowired
	public GeradorQuantidadeTempoPlaca geradorQuantidadeTempoPlaca;
	
	@Autowired
	public GeradorQuantidadeTempoDatas geradorQuantidadeTempoDatas;
	
	@GetMapping("/listaPorTodos")
	public List<BasePoisDefPosicoesDTO> listaPorTodos() {
		return geradorQuantidadeTempoSemFiltro.calcular();
	}
	
	@GetMapping("/listaPorPlaca")
	public List<BasePoisDefPosicoesDTO> listaPorPlaca(@RequestParam String placa) {
		return geradorQuantidadeTempoPlaca.calcular(placa);
	}
	
	@GetMapping("/listaPorData")
	public List<BasePoisDefPosicoesDTO> listaPorData(@RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {
		return geradorQuantidadeTempoDatas.calcular(dataInicial, dataFinal);
	}

}
