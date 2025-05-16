package br.com.sistema.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import br.com.sistema.model.Base;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final EntityManager manager = ConnectionFactory.getEntityManager();

	public T findByID(Class<T> clazz, long id) {
		return manager.find(clazz, id);
	}

	public void save(T t) {
		try {
			manager.getTransaction().begin();

			if (t.getId() == null)
				manager.persist(t);
			else
				manager.merge(t);

			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remove(Class<T> clazz, long id) {
		T t = findByID(clazz, id);
		
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public List<T> listAll(String jpql){
		Query query = manager.createQuery(jpql);
		return query.getResultList();
	}

	public void saveAll(List<T> list) {
		try {
			manager.getTransaction().begin();

			for (T t : list) {
				if (t.getId() == null) {
					manager.persist(t);
				} else {
					manager.merge(t);
				}
			}

			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}



