<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="spokesperson.campaign.list.label.ticker" path="ticker" width="10%" />
<acme:list-column code="spokesperson.campaign.list.label.name" path="name" width="50%" />
<acme:list-column code="spokesperson.campaign.list.label.startMoment" path="startMoment" width="20%" />
<acme:list-column code="spokesperson.campaign.list.label.endMoment" path="endMoment" width="20%" />
</acme:list>

<acme:button code="spokesperson.campaign.list.button.create" action="/spokesperson/campaign/create"/>
