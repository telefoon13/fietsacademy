package be.vdab.valueobjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Adres implements Serializable {

	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisNr;
	private String postcode;
	private String gemeente;

	public Adres(String straat, String huisNr, String postcode, String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}

	public Adres() {
	}

	@Basic
	@Column(name = "straat")
	public String getStraat() {
		return straat;
	}

	@Basic
	@Column(name = "huisnr")
	public String getHuisNr() {
		return huisNr;
	}

	@Basic
	@Column(name = "postcode")
	public String getPostcode() {
		return postcode;
	}

	@Basic
	@Column(name = "gemeente")
	public String getGemeente() {
		return gemeente;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
}
