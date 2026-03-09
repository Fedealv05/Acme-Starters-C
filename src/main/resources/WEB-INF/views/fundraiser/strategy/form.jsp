<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="fundraiser.strategy.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="fundraiser.strategy.form.label.name" path="name"/>
	<acme:form-textarea code="fundraiser.strategy.form.label.description" path="description"/>
	<acme:form-moment code="fundraiser.strategy.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="fundraiser.strategy.form.label.endMoment" path="endMoment"/>
	
	<acme:form-textbox code="fundraiser.strategy.form.label.monthsActive" path="monthsActive"/>
	<acme:form-textbox code="fundraiser.strategy.form.label.expectedPercentage" path="expectedPercentage"/>
	
	<acme:form-textarea code="fundraiser.strategy.form.label.moreInfo" path="moreInfo"/>

	<acme:button code="fundraiser.strategy.form.label.tactics" action="/fundraiser/tactic/list?strategyId=${id}" />
	
</acme:form>