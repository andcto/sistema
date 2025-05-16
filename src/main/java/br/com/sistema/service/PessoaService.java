package br.com.sistema.service;

import br.com.sistema.dao.DAO;
import br.com.sistema.model.Pessoa;
import br.com.sistema.utility.NegocioException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Dependent
public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private DAO<Pessoa> dao;

    public Pessoa findById(Long id) throws NegocioException {
        return dao.findByID(Pessoa.class, id);
    }

    public void save(Pessoa p) throws NegocioException{
        dao.save(p);
    }

    public void remove(Pessoa p) throws NegocioException{
        dao.remove(Pessoa.class, p.getId());
    }

    public List<Pessoa> getAll() throws NegocioException{
        return dao.listAll("select p from Pessoa p order by p.nome");
    }

}
