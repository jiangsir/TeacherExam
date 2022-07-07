<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<br />
<hr></hr>
<div class="footer">
	<div style="text-align: left">
		<p>有任何問題請洽詢本校 ${applicationScope.appConfig.renshiphone}</p>
	</div>
	<div style="text-align: right">
		TeacherExam ${applicationScope.version } Built${applicationScope.built }
		| ${applicationScope.memoryInfo } |
		<c:if test="${ms!=null}">
			<span>${now.time-ms}ms</span>
		</c:if>
		| <a href="./Admin">管理</a>
		<c:if test="${sessionScope.currentUser.getIsAdmin()}"> | ${pageContext.request.remoteAddr}</c:if>
	</div>
</div>
