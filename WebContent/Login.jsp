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
			<div class="col-md-6 col-md-offset-3">
				<h3>確認您的身份</h3>
				<p>請提供您先前報名時填寫的部分資料。</p>
				<p style="font-size: large; font-weight: bold; color: red;">
					${sessionScope.LoginMessage}</p>
				<form action=".${applicationScope.defaultLogin}" method="post"
					id="form">
					<table class="table table-hover">
						<tr>
							<td>您的身份證字號：</td>
							<td><input name="pid" type="password" size="30" /></td>
						</tr>
						<tr>
							<td>您報名時所提供的密碼：</td>
							<td><input name="passwd" type="password" id="passwd"
								size="30" /></td>
						</tr>
					</table>

					<input type="submit" role="button" value="送出"
						class="btn btn-success btn-lg btn-block"></input>
				</form>
			</div>
		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>
