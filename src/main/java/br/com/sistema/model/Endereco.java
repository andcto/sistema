package br.com.sistema.model;

import br.com.sistema.utility.Message;
import br.com.sistema.utility.NegocioException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable, Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column
    private Long numero;

    @Column(nullable = false, length = 8)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    public Endereco(Long id, String estado, String cidade, String logradouro, Long numero, String cep) {
        super();
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public Endereco(Pessoa pessoa) {
        super();
        this.pessoa = pessoa;
    }

    public Endereco() {
        super();
    }

    public String getEnderecoFormatado() {
        return String.format("%s, %d - %s - %s, %s",
                logradouro,
                numero,
                cidade,
                estado,
                cep
        );
    }

    public void validate() throws NegocioException {
        if(Objects.isNull(this.logradouro) || this.logradouro.isEmpty()){
            throw new NegocioException("Logradouro não pode ser vazio");
        }
        if(Objects.isNull(this.cidade) || this.cidade.isEmpty()){
            throw new NegocioException("Cidade não pode ser vazio");
        }
        if(Objects.isNull(this.estado) || this.estado.isEmpty()){
            throw new NegocioException("Estado não pode ser vazio");
        }
        if(Objects.isNull(this.cep) || this.cep.isEmpty()){
            throw new NegocioException("CEP não pode ser vazio");
        }
    }

    public Long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return Objects.equals(id, other.id);
    }
}