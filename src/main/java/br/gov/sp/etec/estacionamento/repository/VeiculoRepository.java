package br.gov.sp.etec.estacionamento.repository;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    List<VeiculoEntity> findByHoraSaidaIsNull();
}
