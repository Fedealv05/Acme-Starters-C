
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
public class Sponsor extends AbstractRole {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	//@ValidText
	@Column
	private String				address;

	@Mandatory
	//@ValidHeader
	@Column
	private String				im;

	@Mandatory
	@Valid
	@Column
	private Boolean				gold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
