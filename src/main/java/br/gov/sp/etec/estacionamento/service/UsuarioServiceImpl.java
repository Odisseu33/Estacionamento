package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.UsuarioEntity;
import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public String cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(usuario.getNome());
        entity.setCpf(usuario.getCpf());
        entity.setEmail(usuario.getEmail());
        entity.setData_nascimento(usuario.getData_nascimento());
        entity.setTelefone(usuario.getTelefone());
        entity.setSenha(usuario.getSenha());

        repository.save(entity);
        return "Usuário cadastrado com sucesso!";
    }

    @Override
    public List<Usuario> listarUsuario() {
        return repository.findAll().stream().map(this::toUsuario).toList();
    }

    @Override
    public String atualizarUsuario(Usuario usuario) {
        return "Atualização de usuário não implementada.";
    }

    @Override
    public String deletarUsuario(Long id) {
        if (!repository.existsById(id)) {
            return "Usuário não encontrado.";
        }
        repository.deleteById(id);
        return "Usuário removido com sucesso.";
    }

    @Override
    public Usuario buscaUsuarioPorEmail(String email) {
        UsuarioEntity entity = repository.findByEmail(email);
        return entity == null ? null : toUsuario(entity);
    }

    private Usuario toUsuario(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setNome(entity.getNome());
        usuario.setCpf(entity.getCpf());
        usuario.setEmail(entity.getEmail());
        usuario.setTelefone(entity.getTelefone());
        usuario.setSenha(entity.getSenha());
        usuario.setData_nascimento(entity.getData_nascimento());
        return usuario;
    }
}
