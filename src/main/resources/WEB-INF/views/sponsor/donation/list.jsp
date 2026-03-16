<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.donation.list.label.name" path="name" />
<acme:list-column code="any.donation.list.label.notes" path="notes" />
<acme:list-column code="any.donation.list.label.money" path="money" />
<acme:list-column code="any.donation.list.label.kind" path="kind" />

</acme:list>

<jstl:if test="${draftMode == true}">
<acme:button code="sponsor.donation.list.button.create" action="/sponsor/donation/create?sponsorshipId=${sponsorshipId}"/>
</jstl:if>