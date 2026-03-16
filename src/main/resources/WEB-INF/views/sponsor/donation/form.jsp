<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.donation.form.label.name" path="name"/>
<acme:form-textbox code="any.donation.form.label.notes" path="notes"/>
<acme:form-money code="any.donation.form.label.money" path="money"/>
<acme:form-select code="sponsor.donation.form.label.kind" path="kind" choices="${donationKinds}" />

<jstl:choose>
    <jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
        <acme:submit code="sponsor.donation.form.button.update" action="/sponsor/donation/update"/>
        <acme:submit code="sponsor.donation.form.button.delete" action="/sponsor/donation/delete"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
        <acme:submit code="sponsor.donation.form.button.create" action="/sponsor/donation/create?sponsorshipId=${sponsorshipId}"/>
    </jstl:when>
</jstl:choose>

</acme:form>