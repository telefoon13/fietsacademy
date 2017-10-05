package be.vdab.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "individuelecursussen")
public class IndividueleCursusEntity extends CursusEntity {

	private static final long serialVersionUID = 1L;
	private int duurtijd;
}
