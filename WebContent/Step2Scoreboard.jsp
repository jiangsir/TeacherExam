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
			<h1>複試成績查詢。</h1>
			<p>&nbsp;</p>
			<p>准考證號碼：${application.seatid}</p>
			<p>考生姓名：${application.name}</p>
			<p>&nbsp;</p>
			<table width="50%" border="0">
				<tr>
					<th scope="col">項目</th>
					<th scope="col">成績</th>
				</tr>


				<tr>
					<th scope="row">複試總分</th>
					<td>${application.step2totalscore}<fmt:formatNumber value="0"
							pattern="#.#" type="number" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			複試結果：<span class="style1">${application.step2result}</span>
			<p>
				<br /> <br /> <br /> <br />
			</p>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
