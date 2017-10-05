package be.vdab.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "groepscursussen")
public class GroepsCursusEntity extends CursusEntity {

	private static final long serialVersionUID = 1L;
	private LocalDate van;
	private LocalDate tot;

}
