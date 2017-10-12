package be.vdab.services;

import be.vdab.entities.CampussenEntity;
import be.vdab.entities.DocentenEntity;
import be.vdab.exceptions.DocentBestaadAlException;
import be.vdab.repositories.CampusRepository;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnID;

import java.math.BigDecimal;
import java.util.*;

public class DocentService extends AbstractService {

	private final DocentRepository docentRepository = new DocentRepository();
	private final CampusRepository campusRepository = new CampusRepository();

	public Optional<DocentenEntity> read(long id){
		return docentRepository.read(id);
	}

	public void create(DocentenEntity docent){
		if (docentRepository.findByRijksRegisterNr(docent.getRijksRegisterNr()).isPresent()){
			throw new DocentBestaadAlException();
		}
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

	public void bijnaamToevoegen(long id, String bijnaam){
		beginTransaction();
		try {
			docentRepository.read(id).ifPresent(docentenEntity -> docentenEntity.addBijnaam(bijnaam));
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

	public void bijnamenVerwijderen(long id, String[] bijnamen) {
		beginTransaction();
		try {
			docentRepository.read(id).ifPresent(docent -> Arrays.stream(bijnamen).forEach(bijnaam->docent.removeBijnaam(bijnaam)));
			commit();
		} catch (RuntimeException ex) {
			rollback();
			throw ex;
		}
	}

	public List<DocentenEntity> findBestBetaaldeVanEenCampus(long id){
		Optional<CampussenEntity> optionalCampus = campusRepository.read(id);
		if (optionalCampus.isPresent()){
			return docentRepository.findBestBetaaldeVanEenCampus(optionalCampus.get());
		}
		return Collections.emptyList();
	}
}
