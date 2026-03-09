<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.donation.form.label.name" path="name"/>
<acme:form-textbox code="any.donation.form.label.notes" path="notes"/>
<acme:form-money code="any.donation.form.label.money" path="money"/>
<acme:form-textbox code="any.donation.form.label.kind" path="kind"/>

</acme:form>