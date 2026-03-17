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
	<acme:form-textbox code="authenticated.sponsor.form.label.address" path="address"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.im" path="im"/>
	<acme:form-checkbox code="authenticated.sponsor.form.label.gold" path="gold"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
	</jstl:if>
</acme:form>
