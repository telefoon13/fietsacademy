package be.vdab.services;

import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

abstract class AbstractService {

	private EntityManager getEntityManager(){
		return JPAFilter.getEntityManager();
	}

	void beginTransaction(){
		getEntityManager().getTransaction().begin();
	}

	void commit(){
		getEntityManager().getTransaction().commit();
	}

	void rollback(){
		getEntityManager().getTransaction().rollback();
	}
}
