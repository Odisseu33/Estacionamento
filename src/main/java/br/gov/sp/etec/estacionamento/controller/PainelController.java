package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PainelController {

    @Autowired
    private VeiculoService service;

    @Value("${estacionamento.vagas:50}")
    private int totalVagas;

    @GetMapping("/painel")
    public String painel(Model model) {
        List<VeiculoEntity> estacionados = service.listaVeiculosEstacionados();
        List<VeiculoEntity> todos = service.listaVeiculos();
        LocalDate hoje = LocalDate.now();

        long entradasHoje = todos.stream()
                .filter(v -> v.getHoraEntrada() != null
                        && v.getHoraEntrada().toLocalDate().equals(hoje))
                .count();

        // Cada ponto representa uma faixa de duas horas, das 08h às 20h.
        // O gráfico mostra quantos veículos estavam estacionados em cada faixa,
        // usando os registros reais de entrada e saída do banco.
        List<String> labels = List.of("08h", "10h", "12h", "14h", "16h", "18h", "20h");
        List<Integer> valores = new ArrayList<>();

        for (int i = 0; i < labels.size(); i++) {
            int hour = 8 + i * 2;
            LocalDate data = hoje;
            java.time.LocalDateTime inicio = data.atTime(hour, 0);
            java.time.LocalDateTime fim = i == labels.size() - 1
                    ? data.plusDays(1).atStartOfDay()
                    : data.atTime(hour + 2, 0);

            int quantidade = 0;
            for (VeiculoEntity v : todos) {
                if (v.getHoraEntrada() == null) continue;

                boolean entrouAntesDoFim = v.getHoraEntrada().isBefore(fim);
                boolean saiuDepoisDoInicio = v.getHoraSaida() == null
                        || v.getHoraSaida().isAfter(inicio);

                if (v.getHoraEntrada().isBefore(fim) && entrouAntesDoFim && saiuDepoisDoInicio) {
                    quantidade++;
                }
            }
            valores.add(Math.min(totalVagas, quantidade));
        }

        model.addAttribute("veiculos", estacionados);
        model.addAttribute("ocupadas", estacionados.size());
        model.addAttribute("disponiveis", Math.max(0, totalVagas - estacionados.size()));
        model.addAttribute("totalVagas", totalVagas);
        model.addAttribute("hoje", entradasHoje);
        model.addAttribute("graficoLabels", labels);
        model.addAttribute("graficoValores", valores);

        return "painel";
    }
}
