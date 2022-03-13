package br.com.mob7.localizacao.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mob7.localizacao.api.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	public List<Veiculo> findByPlaca(String placa);

}
