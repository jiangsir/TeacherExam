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
			<h1>初試成績查詢。</h1>
			<p>&nbsp;</p>
			<p>准考證號碼：${application.seatid}</p>
			<p>考生姓名：${application.name}</p>
			<p>&nbsp;</p>
			<table width="50%" border="0">
				<tr>
					<th scope="col">項目</th>
					<th scope="col">成績</th>
				</tr>

				<%-- 				<tr>
					<th scope="row">教育專業(原始)</th>
					<td>${application.score_teach}</td>
				</tr>
				<tr>
					<th scope="row">本科專業(原始)</th>
					<td>${application.score_subject}</td>
				</tr>
				<tr>
					<th scope="row">教育專業(T分數)</th>
					<td>${application.tscore_teach}</td>
				</tr>
				<tr>
					<th scope="row">本科專業(T分數)</th>
					<td>${application.tscore_subject}</td>
				</tr>
				<tr>
					<th scope="row">教育專業(T分)所佔比例成績</th>
					<td>${application.percent_teach}</td>
				</tr>
				<tr>
					<th scope="row">本科專業(T分)所佔比例成績</th>
					<td>${application.percent_subject}</td>
				</tr>
 --%>
				<tr>
					<th scope="row">初試總分</th>
					<td><span class="style1"><fmt:formatNumber
								value="${application.totalscore}" pattern="#.#" type="number" /></span></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			初試結果：<span class="style1">${application.result } </span>
			<p>
				<br /> <br /> <br /> <br />
			</p>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
