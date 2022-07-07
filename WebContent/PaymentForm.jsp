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
<!-- <link href="styles/smoothness/jquery-ui-1.10.3.custom.css"
	rel="stylesheet" />
<script src="jscripts/jquery-1.9.1.js"></script>
<script src="jscripts/jquery-ui-1.10.3.custom.js"></script>
 -->
</head>
<body>
	<div id="main">
		<jsp:include page="include/Header.jsp" />
		<div id="panel">
			<br></br>
			<h1>下載並列印繳款單。</h1>
			<p></p>
			<p>恭喜您，已經完成線上報名程序。</p>
			<p></p>
			<h4>繳款方式一、臨櫃繳款:</h4>
			<blockquote>
				<p>
					臨櫃繳款請點擊 <a
						href="PrintPaymentForm.html?applicationid=${application.id}">
						列印繳款單</a> ，並至收款銀行櫃台繳款。(請務必使用黑白雷射印表機列印繳款單。)
				</p>
				<p>若無法下載，或下載後無法打開，請洽詢${applicationScope.appConfig.renshiphone
					}協助，或改用“繳款方式二、轉帳繳款”。</p>
			</blockquote>
			<p></p>
			<h4>繳款方式二、轉帳繳款：</h4>
			<blockquote>
				<p>收款銀行：${applicationScope.appConfig.bankname }</p>
				<p>收款人帳號： ${application.bankaccount}</p>
				<p>收款人戶名：${applicationScope.appConfig.bankhuming }</p>
				<p>繳款金額: 新台幣 ${application.exam.money} 元整。</p>
				<p>匯款人請填寫『考生姓名』，並請註明報考科別。</p>
				<p>繳款期限為
					${application.exam.deadline}。(請注意：若繳款期限前無法入帳，將視為未繳款) &nbsp;</p>
			</blockquote>
			<br></br>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
