package br.com.mob7.localizacao.api.controller;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoDatas;
import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoPlaca;
import br.com.mob7.localizacao.api.geradorQuantidadeTempo.GeradorQuantidadeTempoSemFiltro;

@Validated
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
	public ResponseEntity<?> listaPorTodos() {
		return ResponseEntity.ok(geradorQuantidadeTempoSemFiltro.calcular());			
	}
	
	@GetMapping("/listaPorPlaca")
	public ResponseEntity<?> listaPorPlaca(@RequestParam @Size(min = 1, max = 8) String placa) {
		return ResponseEntity.ok(geradorQuantidadeTempoPlaca.calcular(placa));			
	}
	
	@GetMapping("/listaPorData")
	public ResponseEntity<?> listaPorData(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial, 
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal) {
		return ResponseEntity.ok(geradorQuantidadeTempoDatas.calcular(dataInicial, dataFinal));			
	}

}
