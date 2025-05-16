package br.com.sistema.controller;

import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.service.EnderecoService;
import br.com.sistema.service.PessoaService;
import br.com.sistema.utility.Message;
import br.com.sistema.utility.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("enderecoMBean")
@ViewScoped
public class EnderecoMBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EnderecoService enderecoService;

    @Inject
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private List<Endereco> enderecos;
    private Endereco endereco = new Endereco();

    @PostConstruct
    public void init() throws NegocioException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long pessoaId = Long.parseLong(params.get("pessoaId"));
        pessoa = pessoaService.findById(pessoaId);
        endereco.setPessoa(pessoa);
        carregar();
    }

    public void carregar() {
        try {
            enderecos = enderecoService.getAllByPessoa(pessoa);
        } catch (NegocioException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void adicionar() {
        try {
            endereco.validate();
            enderecoService.save(endereco);
        } catch (NegocioException e) {
            Message.erro(e.getMessage());
            return;
        }

        endereco = new Endereco(pessoa);
        carregar();

        Message.info("Endereço cadastrado com sucesso");
    }

    public void excluir(Endereco endereco) throws NegocioException {
        try {
            enderecoService.remove(endereco);
            carregar();

            Message.info("Endereço removido(a).");

        } catch (NegocioException e) {
            Message.erro(e.getMessage());
        }
    }

    public String voltarCadastro() {
        return "cadastro?faces-redirect=true";
    }

    public List<SelectItem> getEstados() {
        List<SelectItem> estados = new ArrayList<>();

        estados.add(new SelectItem("AC", "Acre"));
        estados.add(new SelectItem("AL", "Alagoas"));
        estados.add(new SelectItem("AP", "Amapá"));
        estados.add(new SelectItem("AM", "Amazonas"));
        estados.add(new SelectItem("BA", "Bahia"));
        estados.add(new SelectItem("CE", "Ceará"));
        estados.add(new SelectItem("DF", "Distrito Federal"));
        estados.add(new SelectItem("ES", "Espírito Santo"));
        estados.add(new SelectItem("GO", "Goiás"));
        estados.add(new SelectItem("MA", "Maranhão"));
        estados.add(new SelectItem("MT", "Mato Grosso"));
        estados.add(new SelectItem("MS", "Mato Grosso do Sul"));
        estados.add(new SelectItem("MG", "Minas Gerais"));
        estados.add(new SelectItem("PA", "Pará"));
        estados.add(new SelectItem("PB", "Paraíba"));
        estados.add(new SelectItem("PR", "Paraná"));
        estados.add(new SelectItem("PE", "Pernambuco"));
        estados.add(new SelectItem("PI", "Piauí"));
        estados.add(new SelectItem("RJ", "Rio de Janeiro"));
        estados.add(new SelectItem("RN", "Rio Grande do Norte"));
        estados.add(new SelectItem("RS", "Rio Grande do Sul"));
        estados.add(new SelectItem("RO", "Rondônia"));
        estados.add(new SelectItem("RR", "Roraima"));
        estados.add(new SelectItem("SC", "Santa Catarina"));
        estados.add(new SelectItem("SP", "São Paulo"));
        estados.add(new SelectItem("SE", "Sergipe"));
        estados.add(new SelectItem("TO", "Tocantins"));

        return estados;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
