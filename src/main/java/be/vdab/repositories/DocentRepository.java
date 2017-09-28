package be.vdab.repositories;

import be.vdab.entities.DocentenEntity;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;
import java.util.Optional;

public class DocentRepository {

	public Optional<DocentenEntity> read(long id){
		EntityManager entityManager = JPAFilter.getEntityManager();
		try{
			return Optional.ofNullable(entityManager.find(DocentenEntity.class, id));
		} finally {
			entityManager.close();
		}
	}
}
