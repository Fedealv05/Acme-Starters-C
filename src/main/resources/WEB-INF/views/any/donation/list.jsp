<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.donation.list.label.name" path="name" />
<acme:list-column code="any.donation.list.label.notes" path="notes" />
<acme:list-column code="any.donation.list.label.money" path="money" />

</acme:list>