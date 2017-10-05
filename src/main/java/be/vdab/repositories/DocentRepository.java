package be.vdab.repositories;

import be.vdab.entities.DocentenEntity;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnID;

import java.math.BigDecimal;
import java.util.List;
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

	public List<DocentenEntity> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen){
		return getEntityManager()
				.createNamedQuery("DocentenEntity.findByWeddeBetween", DocentenEntity.class)
				.setParameter("van", van)
				.setParameter("tot",tot)
				.setFirstResult(vanafRij)
				.setMaxResults(aantalRijen)
				.getResultList();
	}

	public List<VoornaamEnID> findVoornaam(){
		return getEntityManager().createQuery("SELECT new be.vdab.valueobjects.VoornaamEnID(d.id,d.voornaam) FROM DocentenEntity d", VoornaamEnID.class).getResultList();
	}

	public BigDecimal findMaxWedde(){
		return getEntityManager().createQuery("SELECT MAX(d.wedde) FROM DocentenEntity d",BigDecimal.class).getSingleResult();
	}

	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde(){
		return getEntityManager().createQuery("SELECT new be.vdab.valueobjects.AantalDocentenPerWedde(d.wedde,COUNT(d)) FROM DocentenEntity d GROUP BY d.wedde",AantalDocentenPerWedde.class).getResultList();
	}

	public void algemeneOpslag(BigDecimal factor){
		getEntityManager().createNamedQuery("DocentenEntity.algemeneOpslag").setParameter("factor", factor).executeUpdate();
	}
}
