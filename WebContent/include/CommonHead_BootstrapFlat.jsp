<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>

<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />

<link rel="stylesheet" title="Normal" type="text/css" media="screen"
	href="./screen.css" />

<script
	src="${pageContext.request.contextPath}/jscripts/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jscripts/jquery-ui-1.11.4/jquery-ui.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jscripts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jscripts/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/jscripts/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jscripts/bootstrap3-dialog/bootstrap-dialog.min.css">
<script
	src="${pageContext.request.contextPath}/jscripts/bootstrap3-dialog/bootstrap-dialog.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jscripts/bootstrap-flat-3.3.4-dist/bootstrap-flat.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jscripts/bootstrap-flat-3.3.4-dist/bootstrap-flat-extras.min.css">
<link
	href="${pageContext.request.contextPath}/jscripts/bootstrap-flat-3.3.4-dist/docs.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/jscripts/bootstrap-flat-3.3.4-dist/docs-flat.css"
	rel="stylesheet">

<!-- 處理 timepicker 樣式 -->
<link rel="stylesheet" type="text/css" media="screen"
	href="jscripts/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.min.css" />
<link href="styles/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script src="jscripts/jquery-ui-timepicker-addon.js"></script>



<script type="text/javascript">
	jQuery(document).ready(function() {
		$("input:first").focus();
	});

	jQuery(document).ajaxError(function(event, jqXHR, settings, thrownError) {
		console.log("CommonHead_BootstrapFlat.jsp: .ajaxError 進行全域捕捉 error:");
		console.log("CommonHead_BootstrapFlat.jsp: event=" + event);
		console.log("CommonHead_BootstrapFlat.jsp: jqxhr.responseText=" + jqXHR.responseText);
		//console.log("settings.url=" + settings.url);
		//console.log("thrownError=" + thrownError);
	});
</script>
<meta charset="UTF-8">


<title>${prefix}${applicationScope.appConfig.title}</title>
