package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "docenten", schema = "fietsacademy")
public class DocentenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;

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

	@Basic
	@Column(name = "wedde")
	public BigDecimal getWedde() {
		return wedde;
	}

	public void setWedde(BigDecimal wedde) {
		this.wedde = wedde;
	}

	@Basic
	@Column(name = "rijksRegisterNr")
	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}

	public void setRijksRegisterNr(long rijksRegisterNr) {
		this.rijksRegisterNr = rijksRegisterNr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DocentenEntity that = (DocentenEntity) o;

		if (id != that.id) return false;
		if (rijksRegisterNr != that.rijksRegisterNr) return false;
		if (voornaam != null ? !voornaam.equals(that.voornaam) : that.voornaam != null) return false;
		if (familienaam != null ? !familienaam.equals(that.familienaam) : that.familienaam != null) return false;
		if (wedde != null ? !wedde.equals(that.wedde) : that.wedde != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (voornaam != null ? voornaam.hashCode() : 0);
		result = 31 * result + (familienaam != null ? familienaam.hashCode() : 0);
		result = 31 * result + (wedde != null ? wedde.hashCode() : 0);
		result = 31 * result + (int) (rijksRegisterNr ^ (rijksRegisterNr >>> 32));
		return result;
	}
}
