package br.com.sistema.model;

import br.com.sistema.utility.Message;
import br.com.sistema.utility.NegocioException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable, Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa() {}

    public Pessoa(Long id, String nome, Date dataNascimento, Sexo sexo, List<Endereco> enderecos) {
        super();
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.enderecos = enderecos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
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
        Pessoa other = (Pessoa) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo
                + ", enderecos=" + enderecos + "]";
    }

    public void validate() throws NegocioException {
        if(Objects.isNull(this.dataNascimento)){
            throw new NegocioException("Data de nascimento não pode ser vazio");
        }
        if(Objects.isNull(this.sexo)){
            throw new NegocioException("Sexo não pode ser vazio");
        }
        if(Objects.isNull(this.nome) || this.nome.isEmpty()){
            throw new NegocioException("Nome não pode ser vazio");
        }
    }

}
