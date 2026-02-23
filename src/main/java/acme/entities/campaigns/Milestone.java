
package acme.entities.campaigns;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.constraints.ValidHeader;
import acme.datatypes.MilestoneKind;

@Entity
public class Milestone extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidHeader
	@Column
	private String				title;

	@Mandatory
	//@ValidText
	@Column
	private String				achievements;

	@Mandatory
	//@ValidNumber(positive)
	@Column
	private Double				effort;

	@Mandatory
	//@Valid
	@Column
	private MilestoneKind		kind;

	@Mandatory
	@ManyToOne(optional = false)
	private Campaign			campaign;

}
