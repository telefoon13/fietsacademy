package be.vdab.entities;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Campussentelefoonnrs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "campussen", schema = "fietsacademy")
public class CampussenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private Adres adres;
	private Set<Campussentelefoonnrs> campussentelefoonnrs;
	private Set<DocentenEntity> docenten;
	private ManagersEntity manager;

	public CampussenEntity(int id, String naam, Adres adres, ManagersEntity manager) {
		this.id = id;
		this.naam = naam;
		this.adres = adres;
		campussentelefoonnrs = new LinkedHashSet<>();
		docenten = new LinkedHashSet<>();
		this.manager = manager;
	}

	public CampussenEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		return campussentelefoonnrs;
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

	@OneToMany(mappedBy = "campus")
	@OrderBy("voornaam, familienaam")
	public Set<DocentenEntity> getDocenten() {
		return docenten;
	}

	public void setDocenten(Set<DocentenEntity> docent) {
		this.docenten = docent;
	}


	public void addDocenten(DocentenEntity docentA){
		docenten.add(docentA);
		if (docentA.getCampus() != this){
			docentA.setCampus(this);
		}
	}

	public void removeDocenten(DocentenEntity docentA){
		docenten.remove(docentA);
		if (docentA.getCampus() == this){
			docentA.setCampus(null);
		}
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerid")
	public ManagersEntity getManager() {
		return manager;
	}

	public void setManager(ManagersEntity manager) {
		this.manager = manager;
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
		int result = (int) id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}
}
