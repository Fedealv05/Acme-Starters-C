
package acme.entities.campaigns;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Moment;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidUrl;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Campaign extends AbstractEntity {

	@Autowired
	@Transient
	private MilestoneRepository	repository;

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@Column(unique = true)
	//@ValidTicker 
	private String				ticker;

	@Mandatory
	//@ValidHeader
	@Column
	private String				name;

	@Mandatory
	//@ValidText
	@Column
	private String				description;

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	//@ValidMoment(future) 
	private Moment				startMoment;

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	//@ValidMoment(future)
	private Moment				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;


	//@Mandatory
	@Transient
	@Valid
	public Double monthsActive() {
		double result = 0.0;
		if (this.startMoment != null && this.endMoment != null) {
			long diff = this.endMoment.getTime() - this.startMoment.getTime();
			result = diff / (1000.0 * 60 * 60 * 24 * 30.44);
			result = Math.round(result * 10.0) / 10.0;
		}
		return result;
	}

	//@Mandatory
	@Transient
	//@ValidNumber(positive)
	public Double effort() {
		return this.repository.findTotalEffortByCampaignId(this.getId());
	}


	@Mandatory
	@Valid
	@Column
	private Boolean draftmode;

}
