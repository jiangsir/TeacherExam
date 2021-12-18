<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<div id="header">
	<div class="headerh1">
		<a href="./" id="headerh1">${applicationScope.appConfig.title}</a>
	</div>

	<div class="collapse navbar-collapse pull-right"
		id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" id="nav">
			<c:choose>
				<c:when
					test="${sessionScope.currentUser != null && !sessionScope.currentUser.isNullUser}">
					<!-- 登出 項 -->
					<li><a href="./Logout">登出 </a></li>
					<li><a href="Personal.html">進入
							${sessionScope.currentUser.pid} 個人頁</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>
</div>