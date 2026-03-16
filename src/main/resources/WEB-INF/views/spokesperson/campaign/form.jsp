<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="spokesperson.campaign.form.label.ticker" path="ticker"/>
<acme:form-textbox code="spokesperson.campaign.form.label.name" path="name"/>
<acme:form-textarea code="spokesperson.campaign.form.label.description" path="description"/>
<acme:form-textarea code="spokesperson.campaign.form.label.moreInfo" path="moreInfo"/>
<acme:form-moment code="spokesperson.campaign.form.label.startMoment" path="startMoment"/>
<acme:form-moment code="spokesperson.campaign.form.label.endMoment" path="endMoment"/>

<acme:form-moment code="spokesperson.campaign.form.label.monthsActive" path="monthsActive"/>
<acme:form-moment code="spokesperson.campaign.form.label.effort" path="effort"/>


<acme:button code="spokesperson.campaign.form.label.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>

</acme:form>