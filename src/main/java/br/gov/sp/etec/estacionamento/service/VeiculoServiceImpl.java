package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        repository.save(toEntity(veiculo));
    }

    @Override
    public List<VeiculoEntity> listaVeiculos() {
        return repository.findAll();
    }

    @Override
    public List<VeiculoEntity> listaVeiculosEstacionados() {
        return repository.findByHoraSaidaIsNull();
    }

    @Override
    public VeiculoEntity buscaVeiculoPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public boolean registrarSaida(Long id) {
        Optional<VeiculoEntity> encontrado = repository.findById(id);

        if (encontrado.isEmpty()) {
            return false;
        }

        VeiculoEntity entity = encontrado.get();

        if (entity.getHoraSaida() != null) {
            return false;
        }

        entity.setHoraSaida(LocalDateTime.now());
        entity.setEstacionado(false);
        repository.save(entity);
        return true;
    }

    @Override
    public boolean deletarVeiculo(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public VeiculoEntity atualizarVeiculo(VeiculoEntity veiculo) {
        return repository.save(veiculo);
    }

    private VeiculoEntity toEntity(Veiculo veiculo) {
        VeiculoEntity entity = new VeiculoEntity();
        entity.setPlaca(veiculo.getPlaca());
        entity.setModelo(veiculo.getModelo());
        entity.setCor(veiculo.getCor());
        entity.setObservacao(veiculo.getObservacao());
        entity.setHoraEntrada(LocalDateTime.now());
        entity.setEstacionado(true);
        return entity;
    }
}
