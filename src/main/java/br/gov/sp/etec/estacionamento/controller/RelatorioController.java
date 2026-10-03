package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RelatorioController {

    @Autowired
    private VeiculoService service;

    @GetMapping("/relatorios")
    public String relatorios(Model model) {
        List<VeiculoEntity> veiculos = service.listaVeiculos();
        model.addAttribute("veiculos", veiculos);
        return "relatorios";
    }
}
