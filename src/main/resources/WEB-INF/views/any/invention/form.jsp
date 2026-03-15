<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.invention.form.label.ticker" path="ticker"/>
<acme:form-textbox code="any.invention.form.label.name" path="name"/>
<acme:form-textarea code="any.invention.form.label.description" path="description"/>
<acme:form-textarea code="any.invention.form.label.moreInfo" path="moreInfo"/>
<acme:form-moment code="any.invention.form.label.startMoment" path="startMoment"/>
<acme:form-moment code="any.invention.form.label.endMoment" path="endMoment"/>
<acme:form-money code="any.invention.form.label.cost" path="cost"/>
<acme:form-textbox code="any.invention.form.label.monthsActive" path="monthsActive"/>

<acme:button code="any.invention.form.label.parts" action="/any/part/list?inventionId=${id}"  />

<acme:button code="any.invention.form.label.inventor" action="/any/inventor/show?id=${inventorId}"  />

</acme:form>