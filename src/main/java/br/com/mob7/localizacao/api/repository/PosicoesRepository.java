package br.com.mob7.localizacao.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mob7.localizacao.api.model.Posicoes;
import br.com.mob7.localizacao.api.model.Veiculo;

public interface PosicoesRepository extends JpaRepository<Posicoes, Long> {
	
	public List<Posicoes> findByVeiculoOrderByIdAsc(Veiculo veiculo);
	
	public List<Posicoes> findByVeiculoAndDataPosicaoBetweenOrderByIdAsc(Veiculo veiculo, LocalDateTime primeiraData, LocalDateTime segundaData);

}
