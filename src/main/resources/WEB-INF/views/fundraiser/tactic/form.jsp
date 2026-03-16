<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="fundraiser.tactic.form.label.name" path="name"/>
	<acme:form-textarea code="fundraiser.tactic.form.label.notes" path="notes"/>
	<acme:form-textbox code="fundraiser.tactic.form.label.expectedPercentage" path="expectedPercentage"/>
	
<acme:form-select code="fundraiser.tactic.form.label.kind" path="kind" choices="${tacticKinds}" />

	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="fundraiser.tactic.form.button.update" action="/fundraiser/tactic/update"/>
			<acme:submit code="fundraiser.tactic.form.button.delete" action="/fundraiser/tactic/delete"/>
		</jstl:when>
		
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="fundraiser.tactic.form.button.create" action="/fundraiser/tactic/create?strategyId=${strategyId}"/>
		</jstl:when>
	</jstl:choose>

</acme:form>