<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="spokesperson.campaign.form.label.ticker" path="ticker"/>
<acme:form-textbox code="spokesperson.campaign.form.label.name" path="name"/>
<acme:form-textarea code="spokesperson.campaign.form.label.description" path="description"/>
<acme:form-url code="spokesperson.campaign.form.label.moreInfo" path="moreInfo"/>
<acme:form-moment code="spokesperson.campaign.form.label.startMoment" path="startMoment"/>
<acme:form-moment code="spokesperson.campaign.form.label.endMoment" path="endMoment"/>

<acme:form-textbox code="spokesperson.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
<acme:form-double code="spokesperson.campaign.form.label.effort" path="effort" readonly="true"/>


<jstl:choose>
    <jstl:when test="${_command == 'show' && draftMode == false}">
        <acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
    </jstl:when>
    <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
        <acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
        <acme:submit code="spokesperson.campaign.form.button.update" action="/spokesperson/campaign/update"/>
        <acme:submit code="spokesperson.campaign.form.button.delete" action="/spokesperson/campaign/delete"/>
        <acme:submit code="spokesperson.campaign.form.button.publish" action="/spokesperson/campaign/publish"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
        <acme:submit code="spokesperson.campaign.form.button.create" action="/spokesperson/campaign/create"/>
    </jstl:when>
</jstl:choose>
</acme:form>