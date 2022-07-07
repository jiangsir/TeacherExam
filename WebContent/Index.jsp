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
			<br>
			<c:if test="${fn:length(activeExams)==0}">
				<h1>目前沒有任何甄試！</h1>
			</c:if>
			<c:forEach var="activeExam" items="${activeExams}">
				<table class="table table-hover">
					<tr>
						<td>
							<h1>${activeExam.title}</h1>
							<div>報名開始日期：${activeExam.applybegin }</div>
							<div>報名截止日期：${activeExam.applyend }</div>
							<div class="col-md-6">
								<ul id="files">
									<c:forEach var="upfile" items="${activeExam.upfiles }">
										<li><a href="Download?upfileid=${upfile.id}"
											class="btn btn-default btn-lg btn-block" type="button">${upfile.descript}</a></li>
									</c:forEach>
								</ul>
							</div> 
							<div class="clearfix"></div>
							<c:if test="${activeExam.isApplyRunning()}">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>科別</th>
											<th>已報名人數</th>
											<c:if test="${activeExam.After_Startline()}">
												<th>已繳費人數</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="subject" items="${activeExam.subjects}">
											<tr>
												<td>
													${subject.zhengshidaili}${subject.nianduan}${subject.name}</td>
												<td>${activeExam.getCountBySubjectid(subject.id)}</td>
												<c:if test="${activeExam.After_Startline() }">
													<td>${activeExam.getPaidCountBySubjectid(subject.id)}</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<br />
								<a href="Apply.html?examid=${activeExam.id}" type="button"
									class="btn btn-primary btn-lg">我確定要報名！</a>
								<a href="Personal.html?examid=${activeExam.id}" type="button"
									class="btn btn-primary btn-lg">已經完成報名，登入個人頁面</a>
							</c:if> <c:if test="${activeExam.Before_Applybegin()}">
								<div class="btn btn-default btn-lg">目前報名尚未開始!</div>
							</c:if> <c:if test="${activeExam.After_Applyend()}">
								<a href="Personal.html?examid=${activeExam.id}" type="button"
									class="btn btn-primary btn-lg">已經完成報名，登入個人頁面</a>
							</c:if>
						</td>
					</tr>
				</table>
			</c:forEach>
			<br /> <br /> <br />
		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>
