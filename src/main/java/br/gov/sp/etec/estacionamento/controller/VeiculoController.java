package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Value("${estacionamento.vagas:50}")
    private int totalVagas;


    @GetMapping
    public String listarVeiculos(Model model) {
        model.addAttribute("veiculos", service.listaVeiculosEstacionados());
        return "veiculos";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Veiculo veiculo) {
        if (service.listaVeiculosEstacionados().size() >= totalVagas) {
            return "redirect:/veiculos/registrar-entrada?lotado=true";
        }

        service.cadastrarVeiculo(veiculo);
        // O redirect força o painel a consultar o banco novamente.
        // Assim os cards, a tabela e o gráfico já refletem a nova entrada.
        return "redirect:/painel";
    }

    @GetMapping("/registrar-entrada")
    public String registrarEntrada(Model model) {
        model.addAttribute("vagasDisponiveis",
                Math.max(0, totalVagas - service.listaVeiculosEstacionados().size()));
        return "registrar-entrada";
    }

    @GetMapping("/saida")
    public String registrarSaida(Model model) {
        List<VeiculoEntity> veiculos = service.listaVeiculosEstacionados();
        model.addAttribute("veiculos", veiculos);
        return "registrar-saida";
    }

    @GetMapping("/saida/{id}")
    public String getVeiculo(@PathVariable Long id, Model model) {
        List<VeiculoEntity> veiculos = service.listaVeiculosEstacionados();
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("veiculo", service.buscaVeiculoPorId(id));
        return "registrar-saida";
    }

    @PostMapping("/saida/{id}")
    public String efetuarSaida(@PathVariable Long id) {
        service.registrarSaida(id);
        return "redirect:/painel";
    }
}
