package br.com.sistema.service;

import br.com.sistema.dao.DAO;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.utility.NegocioException;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class EnderecoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DAO<Endereco> dao;

    public void save(Endereco e) throws NegocioException {
        dao.save(e);
    }

    public void saveAll(List<Endereco> list) throws NegocioException {
        dao.saveAll(list);
    }

    public void remove(Endereco e) throws NegocioException{
        dao.remove(Endereco.class, e.getId());
    }

    public List<Endereco> getAll() throws NegocioException{
        return dao.listAll("select e from Endereco e order by e.estado");
    }

    public List<Endereco> getAllByPessoa(Pessoa pessoa) throws NegocioException {
      return dao.listAll("select e from Endereco e " +
                            "join e.pessoa p " +
                            "where p.id = " + pessoa.getId() +
                            "order by e.estado");
    }
}
