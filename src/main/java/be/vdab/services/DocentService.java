package be.vdab.services;

import be.vdab.entities.DocentenEntity;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.DocentRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class DocentService {
	private final DocentRepository docentRepository = new DocentRepository();

	public Optional<DocentenEntity> read(long id){
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return docentRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
	}
}
