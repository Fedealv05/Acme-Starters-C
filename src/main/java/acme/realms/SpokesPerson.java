
package acme.realms;

import javax.persistence.Column;

import acme.client.components.basis.AbstractRole;
import acme.client.components.validation.Mandatory;

public class SpokesPerson extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	//@ValidText
	@Column
	private String				cv;

	@Mandatory
	//@ValidText
	@Column
	private String				achievements;

	@Mandatory
	//@Valid
	@Column
	private Boolean				licensed;

}
