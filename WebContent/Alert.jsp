<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/CommonHead_BootstrapFlat.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="include/Header_BootstrapFlat.jsp" />

			<fieldset>
				<legend style="font-size: x-large;">${alert.type}</legend>
				<table>
					<tr>
						<td><img src="images/ALERT.png" /></td>
						<td><h1>${alert.title}</h1></td>
					</tr>
				</table>
				<%-- 				<h3>${alert.subtitle }</h3>
 --%>
				<div>${alert.content}</div>
				<ul style="font-family: monospace; font-size: 1.2em;">
					<c:forEach var="list" items="${alert.list}">
						<li>${list}</li>
					</c:forEach>
				</ul>

				<ul>
					<c:forEach var="map" items="${alert.map}">
						<li>${map.key}:${map.value}</li>
					</c:forEach>
				</ul>

				<hr style="margin-top: 3em;" />
				<div style="text-align: center;">
					<c:forEach var="uri" items="${alert.uris}">
						<a href="${uri.value}" type="button">${uri.key}</a>
					</c:forEach>
				</div>
			</fieldset>
		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>