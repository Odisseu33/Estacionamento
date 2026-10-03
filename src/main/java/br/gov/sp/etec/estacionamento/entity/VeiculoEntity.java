package br.gov.sp.etec.estacionamento.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_veiculo")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Placa;
    private String Modelo;
    private String Cor;
    private String Observacao;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Boolean estacionado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return Placa; }
    public void setPlaca(String placa) { this.Placa = placa; }

    public String getModelo() { return Modelo; }
    public void setModelo(String modelo) { this.Modelo = modelo; }

    public String getCor() { return Cor; }
    public void setCor(String cor) { this.Cor = cor; }

    public String getObservacao() { return Observacao; }
    public void setObservacao(String observacao) { this.Observacao = observacao; }

    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalDateTime getHoraSaida() { return horaSaida; }
    public void setHoraSaida(LocalDateTime horaSaida) { this.horaSaida = horaSaida; }

    public Boolean getEstacionado() { return estacionado; }
    public void setEstacionado(Boolean estacionado) { this.estacionado = estacionado; }
}
