package be.vdab.services;

import be.vdab.entities.CursusEntity;
import be.vdab.repositories.CursusRepository;

import java.util.List;

public class CursusService extends AbstractService {

	private final CursusRepository cursusRepository = new CursusRepository();

	public List<CursusEntity> findByNaamContains(String woord) {
		return cursusRepository.findByNaamContains(woord);
	}

}
