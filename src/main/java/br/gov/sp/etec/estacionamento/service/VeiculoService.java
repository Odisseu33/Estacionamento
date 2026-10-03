package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    void cadastrarVeiculo(Veiculo veiculo);
    List<VeiculoEntity> listaVeiculos();
    List<VeiculoEntity> listaVeiculosEstacionados();
    VeiculoEntity buscaVeiculoPorId(Long id);
    boolean registrarSaida(Long id);
    boolean deletarVeiculo(Long id);
    VeiculoEntity atualizarVeiculo(VeiculoEntity veiculo);
}
