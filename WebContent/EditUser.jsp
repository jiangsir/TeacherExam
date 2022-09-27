<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/CommonHead.jsp" />
<!-- <link href="styles/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script src="jscripts/jquery-ui-timepicker-addon.js"></script>
 -->
<script src="EditExam.js?${applicationScope.built }"></script>
<style>
#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

input, textarea {
	height: 1.8em;
	margin: 3px;
}
</style>
</head>

<body>
	<div id="main">
		<jsp:include page="include/Header.jsp" />
		<div id="panel" style="text-align: center;">
			<br> </br>
			<h1>帳號管理。</h1>
			<p>
				標有 <span class="style1">*</span> 為必填欄位。<br />
			</p>

			<form action="" method="post" id="form">
				<table width="50%"
					style="text-align: left; margin: auto; margin-top: 1em;">
					<tr>
						<th scope="row">身分證字號<br />(管理者帳號可不受規範)
						</th>
						<td><input type="text" name="pid" value="${user.pid}"
							size="40%"></input><span class="style1">*</span></td>
					</tr>
					<tr>
						<th scope="row">密碼</th>
						<td><input type="password" name="passwd1"
							value="${user.passwd}" size="40%"></input><span class="style1">*</span></td>
					</tr>
					<tr>
						<th scope="row">密碼(再次輸入)</th>
						<td><input type="password" name="passwd2"
							value="${user.passwd}" size="40%"></input><span class="style1">*</span></td>
					</tr>
				</table>
				<p style="text-align: center;">
					<input type="hidden" name="userid" value="${user.id}" /> <input
						type="submit" value="送出"></input>
				</p>
			</form>
			<br /> <br> </br>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
