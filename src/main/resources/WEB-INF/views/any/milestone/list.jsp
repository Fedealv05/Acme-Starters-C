<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.milestone.list.label.title" path="title" width="10%" />
<acme:list-column code="any.milestone.list.label.effort" path="effort" width="20%" />
<acme:list-column code="any.milestone.list.label.kind" path="kind" width="20%" />

</acme:list>