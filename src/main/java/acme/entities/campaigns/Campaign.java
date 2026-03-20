
package acme.entities.campaigns;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import acme.constraints.ValidTicker;
import acme.constraints.campaigns.ValidCampaign;
import acme.realms.Spokesperson;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidCampaign
public class Campaign extends AbstractEntity {

	@Autowired
	@Transient
	private MilestoneRepository	repository;

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@Column(unique = true)
	@ValidTicker
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
	@Temporal(TemporalType.TIMESTAMP)
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	private Date				startMoment;

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	private Date				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Spokesperson		spokesperson;


	@Mandatory
	@Valid
	@Transient
	public Double getMonthsActive() {
		double result = 0.0;
		if (this.startMoment != null && this.endMoment != null) {
			long diff = this.endMoment.getTime() - this.startMoment.getTime();
			result = diff / (1000.0 * 60 * 60 * 24 * 30.44);
			result = Math.round(result * 10.0) / 10.0;
		}
		return result;
	}

	@Mandatory
	@ValidNumber(min = 0)
	@Transient
	public Double getEffort() {
		Double res = this.repository.findTotalEffortByCampaignId(this.getId());
		if (res == null)
			res = 0.0;
		return res;
	}

}
