package be.vdab.valueobjects;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Campussentelefoonnrs implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nummer;
	private String opmerking;
	private boolean fax;

	@Basic
	@Column(name = "nummer")
	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	@Basic
	@Column(name = "opmerking")
	public String getOpmerking() {
		return opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	@Basic
	@Column(name = "fax")
	public boolean isFax() {
		return fax;
	}

	public void setFax(boolean fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return nummer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Campussentelefoonnrs that = (Campussentelefoonnrs) o;

		return nummer != null ? nummer.equals(that.nummer) : that.nummer == null;
	}

	@Override
	public int hashCode() {
		return nummer.toUpperCase().hashCode();
	}
}
