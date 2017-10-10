package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "managers", schema = "fietsacademy")
public class ManagersEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String voornaam;
	private String familienaam;
	private CampussenEntity campus;

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
	@Column(name = "voornaam")
	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	@Basic
	@Column(name = "familienaam")
	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "manager")
	public CampussenEntity getCampus() {
		return campus;
	}

	public void setCampus(CampussenEntity campus) {
		this.campus = campus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ManagersEntity that = (ManagersEntity) o;

		if (id != that.id) return false;
		if (voornaam != null ? !voornaam.equals(that.voornaam) : that.voornaam != null) return false;
		if (familienaam != null ? !familienaam.equals(that.familienaam) : that.familienaam != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (voornaam != null ? voornaam.hashCode() : 0);
		result = 31 * result + (familienaam != null ? familienaam.hashCode() : 0);
		return result;
	}
}
