package be.vdab.services;

import be.vdab.entities.DocentenEntity;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnID;

import java.math.BigDecimal;
import java.util.List;
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

	public List<DocentenEntity> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen){
		return docentRepository.findByWeddeBetween(van,tot,vanafRij,aantalRijen);
	}

	public List<VoornaamEnID> findVoornaam(){
		return docentRepository.findVoornaam();
	}

	public BigDecimal findMaxWedde(){
		return docentRepository.findMaxWedde();
	}

	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde(){
		return docentRepository.findAantalDocentenPerWedde();
	}

	public void algemeneOpslag(BigDecimal percentage){
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		try{
			docentRepository.algemeneOpslag(factor);
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}
}
