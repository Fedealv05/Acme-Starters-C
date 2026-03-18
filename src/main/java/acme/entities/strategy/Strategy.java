
package acme.entities.strategy;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import acme.constraints.ValidTicker;
import acme.constraints.strategy.ValidStrategy;
import acme.realms.Fundraiser;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidStrategy
public class Strategy extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@ValidTicker
	@Column(unique = true)
	private String				ticker;

	@Mandatory
	@ValidHeader
	@Column
	private String				name;

	@Mandatory
	@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	@Mandatory
	@Column
	private Boolean				draftMode;

	// Relaciones -------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Fundraiser			fundraiser;

	@OneToMany(mappedBy = "strategy")
	private Collection<Tactic>	tactics;


	@Transient
	public Double getMonthsActive() {
		double result = 0.0;
		if (this.startMoment != null && this.endMoment != null) {
			long diff = this.endMoment.getTime() - this.startMoment.getTime();
			result = diff / (1000.0 * 60 * 60 * 24 * 30);
			result = Math.round(result * 10.0) / 10.0;
		}
		return result;
	}

	@Transient
	public Double getExpectedPercentage() {
		double result = 0.0;
		if (this.tactics != null)
			for (final Tactic t : this.tactics)
				result += t.getExpectedPercentage();
		return result;
	}
}
