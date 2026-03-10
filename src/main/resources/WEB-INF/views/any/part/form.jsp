<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.part.form.label.name" path="name"/>
<acme:form-textbox code="any.part.form.label.kind" path="kind"/>
<acme:form-textarea code="any.part.form.label.description" path="description"/>
<acme:form-money code="any.part.form.label.cost" path="cost"/>

</acme:form>