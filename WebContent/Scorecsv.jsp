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
			<br></br>
			<h1>${application.exam.title }</h1>
			<h1>考生姓名：${application.name}</h1>
			<h4>甄選科目: ${application.subject }</h4>

			<c:if test="${fn:trim(scorecsv.step1)!=''}">
				<div class="well">
					<h2>初試成績查詢</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<c:forEach var="col"
									items="${fn:split(scorecsv.step1Array[0], ',')}">
									<th>${col }</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach var="data"
									items="${fn:split(scorecsv.step1Array[1], ',')}">
									<td>${data }</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
			<div>
				<hr>
			</div>
			<c:if test="${fn:trim(scorecsv.step2)!=''}">
				<div class="well">
					<h2>複試成績查詢</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<c:forEach var="col"
									items="${fn:split(scorecsv.step2Array[0], ',')}">
									<th>${col }</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach var="data"
									items="${fn:split(scorecsv.step2Array[1], ',')}">
									<td>${data }</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
