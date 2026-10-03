package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastrar";
    }

    @GetMapping("/cadastro")
    public String cadastroAlias() {
        return "cadastrar";
    }

    @PostMapping("/efetuar-cadastro")
    public String efetuarCadastro(Usuario usuario, Model model) {
        if (service.buscaUsuarioPorEmail(usuario.getEmail()) != null) {
            model.addAttribute("erro", "Já existe um usuário cadastrado com este e-mail.");
            return "cadastrar";
        }

        log.info("Cadastrando usuário: {}", usuario);
        service.cadastrarUsuario(usuario);
        return "cadastro-sucess";
    }

    @PostMapping("/efetuar_cadastro")
    public String efetuarCadastroAlias(Usuario usuario, Model model) {
        return efetuarCadastro(usuario, model);
    }

    @PostMapping("/autenticar")
    public String autenticar(String email, String senha) {
        Usuario usuario = service.buscaUsuarioPorEmail(email);

        if (usuario != null && senha != null && senha.equals(usuario.getSenha())) {
            return "redirect:/painel";
        }

        return "erro";
    }
}
