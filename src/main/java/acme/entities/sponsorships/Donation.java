
package acme.entities.sponsorships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.validation.Mandatory;
import acme.constraints.ValidHeader;
import acme.constraints.ValidMoneyWithCurrency;
import acme.constraints.ValidText;
import acme.datatypes.DonationKind;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Donation extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidHeader
	@Column
	private String				name;

	@Mandatory
	@ValidText
	@Column
	private String				notes;

	@Mandatory
	@ValidMoneyWithCurrency(min = 0., max = 1000000.)
	@Column
	private Money				money;

	@Mandatory
	@Valid
	@Column
	private DonationKind		kind;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Sponsorship			sponsorship;

}
