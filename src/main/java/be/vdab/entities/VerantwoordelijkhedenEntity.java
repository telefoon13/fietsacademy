package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "verantwoordelijkheden", schema = "fietsacademy")
public class VerantwoordelijkhedenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private Set<DocentenEntity> docenten;

	public VerantwoordelijkhedenEntity(long id, String naam, Set<DocentenEntity> docenten) {
		this.id = id;
		this.naam = naam;
		this.docenten = new LinkedHashSet<>();
	}

	public VerantwoordelijkhedenEntity() {}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VerantwoordelijkhedenEntity that = (VerantwoordelijkhedenEntity) o;

		if (id != that.id) return false;
		return naam != null ? naam.equalsIgnoreCase(that.naam) : that.naam == null;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}

	@ManyToMany
	@JoinTable(name = "docentenverantwoordelijkheden", joinColumns = @JoinColumn(name = "verantwoordelijkheidId"), inverseJoinColumns = @JoinColumn(name = "docentId"))
	public Set<DocentenEntity> getDocenten() {
		return docenten;
	}

	public void setDocenten(Set<DocentenEntity> docenten) {
		this.docenten = docenten;
	}

	public void add(DocentenEntity docent){
		docenten.add(docent);
		if (!docent.getVerantwoordelijkheden().contains(this)){
			docent.add(this);
		}
	}

	public void remove(DocentenEntity docent){
		docenten.remove(docent);
		if (docent.getVerantwoordelijkheden().contains(this)){
			docent.remove(this);
		}
	}
}
