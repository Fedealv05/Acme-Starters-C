
package acme.realms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;

import acme.client.components.basis.AbstractRole;
import acme.client.components.validation.Mandatory;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fundraiser extends AbstractRole {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	//@ValidHeader // Basado en el estereotipo del UML { Mandatory, ValidHeader, Column }
	@Column
	private String				bank;

	@Mandatory
	//@ValidText // Basado en el estereotipo del UML { Mandatory, ValidText, Column }
	@Column
	private String				statement;

	@Mandatory
	@Valid
	@Column
	private Boolean				agent;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
