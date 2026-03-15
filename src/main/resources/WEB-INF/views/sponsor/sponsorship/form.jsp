<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.sponsorship.form.label.ticker" path="ticker"/>
<acme:form-textbox code="any.sponsorship.form.label.name" path="name"/>
<acme:form-textarea code="any.sponsorship.form.label.description" path="description"/>
<acme:form-textarea code="any.sponsorship.form.label.moreInfo" path="moreInfo"/>
<acme:form-moment code="any.sponsorship.form.label.startMoment" path="startMoment"/>
<acme:form-moment code="any.sponsorship.form.label.endMoment" path="endMoment"/>

<acme:button code="any.sponsorship.form.label.donations" action="/sponsor/donation/list?sponsorshipId=${id }"/>




</acme:form>