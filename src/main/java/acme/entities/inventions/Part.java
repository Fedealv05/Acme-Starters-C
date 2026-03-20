
package acme.entities.inventions;

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
import acme.datatypes.PartKind;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Part extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidHeader
	@Column
	private String				name;

	@Mandatory
	@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoneyWithCurrency(min = 0, max = 1000000000)
	@Column
	private Money				cost;

	@Mandatory
	@Valid
	@Column
	private PartKind			kind;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Invention			invention;
}
