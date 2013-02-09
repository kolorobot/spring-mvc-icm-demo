<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="hasRole('ROLE_USER')">
<p>
	<a href='<s:url value="/incident/create" />' class="btn btn-primary btn-large"> New incident </a>
</p>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">

</security:authorize>
