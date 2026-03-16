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
	<acme:form-textbox code="authenticated.inventor.form.label.bio" path="bio"/>
	<acme:form-textbox code="authenticated.inventor.form.label.keyWords" path="keyWords"/>
	<acme:form-checkbox code="authenticated.inventor.form.label.licensed" path="licensed"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="authenticated.inventor.form.button.create" action="/authenticated/inventor/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.inventor.form.button.update" action="/authenticated/inventor/update"/>
	</jstl:if>
</acme:form>
