package be.vdab.repositories;

import be.vdab.entities.CampussenEntity;
import be.vdab.entities.CursusEntity;

import java.util.List;
import java.util.Optional;

public class CampusRepository extends AbstractRepository {

	public List<CampussenEntity> findByGemeente(String gemeente){
		return getEntityManager().createNamedQuery("CampussenEntity.findByGemeente", CampussenEntity.class)
				.setParameter("gemeente", gemeente)
				.getResultList();
	}

	public List<CampussenEntity> findAll(){
		return getEntityManager().createNamedQuery("CampussenEntity.findAll", CampussenEntity.class).getResultList();
	}

	public Optional<CampussenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(CampussenEntity.class, id));
	}
}
