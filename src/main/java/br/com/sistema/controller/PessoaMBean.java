package br.com.sistema.controller;

import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.service.EnderecoService;
import br.com.sistema.service.PessoaService;
import br.com.sistema.utility.Message;
import br.com.sistema.utility.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Named("pessoaMBean")
@ViewScoped
public class PessoaMBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaService pessoaService;

    @Inject
    private EnderecoService enderecoService;

    private Pessoa pessoaSelecionada;

    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    private List<Pessoa> pessoas = new ArrayList<>();

    @PostConstruct
    public void carregar() {
        try {
            pessoas = pessoaService.getAll();
        } catch (NegocioException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void adicionar() {
        try {
            pessoa.validate();
            pessoaService.save(pessoa);
        } catch (NegocioException e) {
            Message.erro(e.getMessage());
            return;
        }
        pessoa = new Pessoa();
        carregar();

        Message.info("Pessoa cadastrada com sucesso");
    }

    public void excluir() {
        try {
            String nome = pessoa.getNome();

            List<Endereco> enderecos = enderecoService.getAllByPessoa(pessoa);

            if(enderecos.isEmpty()) {
                pessoaService.remove(pessoa);
            } else {
                Message.erro(nome + " não foi removido(a) pois está associado a um endereço.");
                return;
            }

            carregar();

            Message.info(nome + " foi removido(a).");

        } catch (NegocioException e) {
            Message.erro(e.getMessage());
        }
    }

    public String irParaGerenciarEnderecos() throws NegocioException {
        return "enderecos?faces-redirect=true&includeViewParams=true&pessoaId=" + pessoaSelecionada.getId();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }
}
