<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<div id="header">
	<span id="servletPath" page="${sessionScope.servletPath}"></span>
	<!-- 	<h1 style="text-align: center;">
		 		<a href="./" id="headerh1"><img src="images/title.png"></img></a>

	</h1>
 -->
	<div
		style="margin: 0.5em; text-align: center; font-size: 3em; color: #fafafa; letter-spacing: 0; text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135">
		<a href="./" id="headerh1">${applicationScope.appConfig.title }</a>
	</div>
	<div style="font-size: 18px;">
		<%-- 		<c:if test="${sessionScope.session_pid!=null}">
			<a href="Personal.html">進入 ${sessionScope.currentUser.pid} 個人頁</a> | <a
				href="Logout">登出</a>
		</c:if>
 --%>
		<ul id="menu">
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
<div id="menu">
	<ul>

	</ul>
</div>


