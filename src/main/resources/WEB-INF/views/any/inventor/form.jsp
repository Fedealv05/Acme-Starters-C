<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.part.form.label.bio" path="bio"/>
<acme:form-textbox code="any.part.form.label.keyWords" path="keyWords"/>
<acme:form-textarea code="any.part.form.label.licensed" path="licensed"/>

</acme:form>