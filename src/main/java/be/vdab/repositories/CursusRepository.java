package be.vdab.repositories;

import be.vdab.entities.CursusEntity;

import java.util.List;

public class CursusRepository extends AbstractRepository {

	public List<CursusEntity> findByNaamContains(String woord) {
		return getEntityManager().createNamedQuery("Cursus.findByNaamContains",
				CursusEntity.class)
				.setParameter("zoals", '%' + woord + '%')
				.getResultList();
	}

}
