package be.vdab.repositories;

import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

abstract class AbstractRepository {

	public static EntityManager getEntityManager(){
		return JPAFilter.getEntityManager();
	}
}
