<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/CommonHead.jsp" />
</head>
<body id="main">
	<jsp:include page="include/Header.jsp" />
	<div id="panel">
		<div style="font-size: large">
			糟糕囉！網頁發生錯誤${pageContext.errorData.statusCode}<br />請通知管理員！謝謝！<a
				href="mailto:${applicationScope.SystemConfig.SYSTEM_MAIL}">${applicationScope.SystemConfig.SYSTEM_MAIL}</a>
		</div>
		<p>
			${pageContext.exception}<br /> Request that failed:
			${pageContext.errorData.requestURI}<br /> Status code:
			${pageContext.errorData.statusCode}<br /> Exception:
			${pageContext.errorData.throwable}<br /> <br /> <br />
		</p>
		<div style="font-size: large">顯示例外堆疊追蹤：</div>
		<div
			style="text-align: left; width: 70%; font-size: 12px; padding: 10px; margin: auto">
			<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
${trace}<br />
			</c:forEach>
		</div>

	</div>
	<jsp:include page="include/Footer.jsp" />
</body>
</html>
