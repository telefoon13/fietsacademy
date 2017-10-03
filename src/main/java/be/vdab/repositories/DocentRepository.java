package be.vdab.repositories;

import be.vdab.entities.DocentenEntity;
import java.util.Optional;

public class DocentRepository extends AbstractRepository {

	public Optional<DocentenEntity> read(long id){
			return Optional.ofNullable(getEntityManager().find(DocentenEntity.class, id));
	}

	public void create(DocentenEntity docent){
		getEntityManager().persist(docent);
	}

	public void delete(long id){
		read(id).ifPresent(docent -> getEntityManager().remove(docent));
	}
}
