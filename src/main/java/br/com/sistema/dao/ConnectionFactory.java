package br.com.sistema.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaPU");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
