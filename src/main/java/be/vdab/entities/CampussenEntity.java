package be.vdab.entities;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Campussentelefoonnrs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "campussen", schema = "fietsacademy")
public class CampussenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String naam;
	private Adres adres;
	private Set<Campussentelefoonnrs> campussentelefoonnrs;

	public CampussenEntity(int id, String naam, Adres adres, Set<Campussentelefoonnrs> campussentelefoonnrs) {
		this.id = id;
		this.naam = naam;
		this.adres = adres;
		campussentelefoonnrs = new LinkedHashSet<>();
	}

	public CampussenEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "naam")
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Embedded
	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	@ElementCollection
	@CollectionTable(name = "campussenTelefoonnrs", joinColumns = @JoinColumn(name = "campusid"))
	@OrderBy("fax")
	public Set<Campussentelefoonnrs> getCampussentelefoonnrs() {
		return Collections.unmodifiableSet(campussentelefoonnrs);
	}

	public void setCampussentelefoonnrs(Set<Campussentelefoonnrs> campussentelefoonnrs) {
		this.campussentelefoonnrs = campussentelefoonnrs;
	}

	public void addCampussentelefoonnr(Campussentelefoonnrs nummer){
		campussentelefoonnrs.add(nummer);
	}

	public void removeCampussentelefoonnr(Campussentelefoonnrs nummer){
		campussentelefoonnrs.remove(nummer);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CampussenEntity that = (CampussenEntity) o;

		if (id != that.id) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}
}
