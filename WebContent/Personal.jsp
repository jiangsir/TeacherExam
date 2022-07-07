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
			<h1>甄選名稱: ${application.exam.title}</h1>
			<h2>${application.name}老師您好！</h2>
			<div>
				<c:if test="${application.ispaid}">
					<button class="btn btn-success btn-lg">您已繳費成功！</button>
				</c:if>
				<c:if test="${!application.ispaid}">
					<button class="btn btn-danger btn-lg">您尚未繳費或尚未收到銀行對帳資訊！</button>
				</c:if>
			</div>
			<hr>
			<div class="col-xs-5">

				<a href="EditApplication.html?applicationid=${application.id }"
					role="button" class="btn btn-default btn-lg btn-block">修改報名表資料</a>
				<c:if test="${application.exam.hasUploadform()}">
					<a href="${application.exam.uploadform }" role="button"
						class="btn btn-default btn-lg btn-block">上傳各項報名證明文件</a>
				</c:if>
				<a href="PrintApplication.html?applicationid=${application.id}"
					role="button" class="btn btn-default btn-lg btn-block">列印報名表</a> <a
					href="PrintPaymentForm.html?applicationid=${application.id}"
					role="button" class="btn btn-default btn-lg btn-block">列印繳款單</a> <a
					href="PrintExamForm.html?applicationid=${application.id}"
					role="button" class="btn btn-default btn-lg btn-block">列印准考證</a> <a
					href="Scorecsv.html?examid=${application.examid}&seatid=${application.seatid}"
					role="button" class="btn btn-default btn-lg btn-block">查詢成績</a>
				<!-- 
					<a
					href="Scoreboard.html?applicationid=${application.id}"
					role="button" class="btn btn-default btn-lg btn-block">查詢初試成績</a> <a
					href="Step2Scoreboard.html?applicationid=${application.id}"
					role="button" class="btn btn-default btn-lg btn-block">查詢複試成績</a>
					 -->
			</div>
		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>
