<%--
- menu.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:menu-bar>
	<acme:menu-left>

		<acme:menu-option code="master.menu.administrator" access="hasRealm('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.list-user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-db-initial" action="/administrator/system/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-db-sample" action="/administrator/system/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-system-down" action="/administrator/system/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRealm('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRealm('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.invention" action="/any/invention/list"/>
			<acme:menu-suboption code="master.menu.strategy" action="/any/strategy/list"/>
			<acme:menu-suboption code="master.menu.sponsorship.list" action="/any/sponsorship/list"/>
			<acme:menu-suboption code="master.menu.campaign.list" action="/any/campaign/list"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.fundraiser" access="hasRealm('Fundraiser')">
			<acme:menu-suboption code="master.menu.strategy" action="/fundraiser/strategy/list"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.authsponsorship" access="hasRealm('Sponsor')">
			<acme:menu-suboption code="master.menu.authsponsorship.list" action="/sponsor/sponsorship/list"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.inventor" access="hasRealm('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.invention.list" action="/inventor/invention/list"/>
		</acme:menu-option>
			<acme:menu-option code="master.menu.spokesperson" access="hasRealm('Spokesperson')">
			<acme:menu-suboption code="master.menu.spokesperson.campaign.list" action="/spokesperson/campaign/list"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>		
		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-profile" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRealm('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider-profile" action="/authenticated/provider/update" access="hasRealm('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRealm('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer-profile" action="/authenticated/consumer/update" access="hasRealm('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-spokesperson" action="/authenticated/spokesperson/create" access="!hasRealm('Spokesperson')"/>
			<acme:menu-suboption code="master.menu.user-account.spokesperson-profile" action="/authenticated/spokesperson/update" access="hasRealm('Spokesperson')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRealm('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor-profile" action="/authenticated/inventor/update" access="hasRealm('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-fundraiser" action="/authenticated/fundraiser/create" access="!hasRealm('Fundraiser')"/>
			<acme:menu-suboption code="master.menu.user-account.fundraiser-profile" action="/authenticated/fundraiser/update" access="hasRealm('Fundraiser')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRealm('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor-profile" action="/authenticated/sponsor/update" access="hasRealm('Sponsor')"/>
		</acme:menu-option>
	</acme:menu-right>
</acme:menu-bar>

