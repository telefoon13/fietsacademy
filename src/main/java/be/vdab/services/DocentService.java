package be.vdab.services;

import be.vdab.entities.DocentenEntity;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.DocentRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

public class DocentService extends AbstractService {
	private final DocentRepository docentRepository = new DocentRepository();

	public Optional<DocentenEntity> read(long id){
		return docentRepository.read(id);
	}

	public void create(DocentenEntity docent){
		beginTransaction();
		try {
			docentRepository.create(docent);
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

	public void delete(long id){
		beginTransaction();
		try {
			docentRepository.delete(id);
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

	public void opslag(long id, BigDecimal percent){
		beginTransaction();
		try {
			docentRepository.read(id).ifPresent(docent -> docent.opslag(percent));
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}
}
