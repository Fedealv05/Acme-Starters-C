<%--
- form.jsp
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
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="authenticated.fundraiser.form.label.bank" path="bank"/>
	<acme:form-textarea code="authenticated.fundraiser.form.label.statement" path="statement"/>
	<acme:form-checkbox code="authenticated.fundraiser.form.label.agent" path="agent"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="authenticated.fundraiser.form.button.create" action="/authenticated/fundraiser/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.fundraiser.form.button.update" action="/authenticated/fundraiser/update"/>
	</jstl:if>
</acme:form>