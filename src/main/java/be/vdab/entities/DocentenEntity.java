package be.vdab.entities;

import be.vdab.enums.Geslacht;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "docenten", schema = "fietsacademy")
@NamedEntityGraph(name = DocentenEntity.MET_CAMPUS, attributeNodes = @NamedAttributeNode("campus"))
//@NamedEntityGraph(name = "Docent.metCampusEnVerantwoordelijkheden", attributeNodes = {@NamedAttributeNode("campus"), @NamedAttributeNode("verantwoordelijkheden")})
public class DocentenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;
	private Geslacht geslacht;
	private Set<String> bijnamen;
	private CampussenEntity campus;
	private Set<VerantwoordelijkhedenEntity> verantwoordelijkheden = new LinkedHashSet<>();
	private Timestamp versie;

	public static final String MET_CAMPUS = "Docent.metCampus";

	public DocentenEntity(String voornaam, String familienaam, BigDecimal wedde,Geslacht geslacht, long rijksRegisterNr) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		setRijksRegisterNr(rijksRegisterNr);
		bijnamen = new HashSet<>();
	}

	protected DocentenEntity() {}// default constructor is vereiste voor JPA


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
		if ( ! isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}

	@Basic
	@Column(name = "familienaam")
	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		if ( ! isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}

	@Basic
	@Column(name = "wedde")
	public BigDecimal getWedde() {
		return wedde;
	}

	public void setWedde(BigDecimal wedde) {
		if ( ! isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}

	@Basic
	@Column(name = "rijksRegisterNr")
	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}

	public void setRijksRegisterNr(long rijksRegisterNr) {
		if ( ! isRijksRegisterNrValid(rijksRegisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksRegisterNr;
	}

	@Basic
	@Column(name = "geslacht")
	@Enumerated(EnumType.STRING)
	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	@ElementCollection
	@CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentid"))
	@Column(name = "bijnaam")
	public Set<String> getBijnamen() {
		return bijnamen;
	}

	public void setBijnamen(Set<String> bijnamen) {
		this.bijnamen = bijnamen;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "campusid")
	public CampussenEntity getCampus() {
		return campus;
	}

	public void setCampus(CampussenEntity campus) {
		this.campus = campus;
	}

	@ManyToMany(mappedBy = "docenten")
	public Set<VerantwoordelijkhedenEntity> getVerantwoordelijkheden() {
		return verantwoordelijkheden;
	}

	public void setVerantwoordelijkheden(Set<VerantwoordelijkhedenEntity> verantwoordelijkheden) {
		this.verantwoordelijkheden = verantwoordelijkheden;
	}

	public void add(VerantwoordelijkhedenEntity verantwoordelijkheid){
		verantwoordelijkheden.add(verantwoordelijkheid);
		if (!verantwoordelijkheid.getDocenten().contains(this)){
			verantwoordelijkheid.add(this);
		}
	}

	public void remove(VerantwoordelijkhedenEntity verantwoordelijkheid){
		verantwoordelijkheden.remove(verantwoordelijkheid);
		if (verantwoordelijkheid.getDocenten().contains(this)){
			verantwoordelijkheid.remove(this);
		}
	}

	public static boolean isVoornaamValid(String voornaam) {
		return voornaam != null && ! voornaam.isEmpty();
	}

	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && ! familienaam.isEmpty();
	}

	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
	}

	public static boolean isRijksRegisterNrValid(long rijksRegisterNr) {
		long getal = rijksRegisterNr / 100;
		if (rijksRegisterNr / 1_000_000_000 < 50) {
			getal += 2_000_000_000;
		}
		return rijksRegisterNr % 100 == 97 - getal % 97;
	}

	public void opslag(BigDecimal percentage){
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}

	public void addBijnaam(String bijnaam){
		bijnamen.add(bijnaam);
	}

	public void removeBijnaam(String bijnaam){
		bijnamen.remove(bijnaam);
	}

	@Version
	public Timestamp getVersie() {
		return versie;
	}

	public void setVersie(Timestamp versie) {
		this.versie = versie;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DocentenEntity that = (DocentenEntity) o;

		if (id != that.id) return false;
		if (rijksRegisterNr != that.rijksRegisterNr) return false;

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
