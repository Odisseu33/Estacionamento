package br.gov.sp.etec.estacionamento.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Usuario {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data_nascimento;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDate getData_nascimento() { return data_nascimento; }
    public void setData_nascimento(LocalDate data_nascimento) { this.data_nascimento = data_nascimento; }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='***'" +
                ", data_nascimento=" + data_nascimento +
                '}';
    }
}
