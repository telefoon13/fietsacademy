package be.vdab.services;

import be.vdab.entities.CampussenEntity;
import be.vdab.repositories.CampusRepository;

import java.util.List;
import java.util.Optional;

public class CampusService extends AbstractService {

	private final CampusRepository campusRepository = new CampusRepository();

	public List<CampussenEntity> findByGemeente(String gemeente){
		return campusRepository.findByGemeente(gemeente);
	}

	public List<CampussenEntity> findAll(){
		return campusRepository.findAll();
	}

	public Optional<CampussenEntity> read(long id){
		return campusRepository.read(id);
	}
}
