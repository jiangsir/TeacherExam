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
	<script src="jscripts/stupidtable.min.js"></script>
	<script type="text/javascript" src="ManageExam.js?${applicationScope.built }"></script>
	<style type="text/css">
		@media print {
			* {
				-webkit-print-color-adjust: exact !important;
			}

			.noBreak {
				break-inside: avoid;
			}

			.noPrint {
				display: none;
			}
		}

		.box {
			display: inline-block;
			text-align: center;
			width: 10em;
			margin: 1em;
			border-width: 3 px;
		}

		.after-box {
			clear: left;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>${exam.title}-${subject.zhengshidaili}${subject.nianduan}${subject.name}</h2>
			<div class="noPrint">
				<h3>所有科別共 ${fn:length(exam.applications)} 人已報名！</h3>
				<hr>
				<a href="Applications.html?examid=${exam.id}" type="button" class="btn btn-primary"> <span
						class="glyphicon glyphicon-tag"></span>
					所有科別
				</a>
				<c:forEach var="subject" items="${subjects}">
					<a href="Applications.html?examid=${exam.id}&subjectid=${subject.id}" type="button"
						class="btn btn-primary"> <span class="glyphicon glyphicon-tag"></span>
						${subject.zhengshidaili}${subject.nianduan}${subject.name}
					</a>
				</c:forEach>
			</div>
			<h2>${subject.zhengshidaili}${subject.nianduan}${subject.name}
				(共 ${fn:length(applications)} 人已繳費！)</h2>
			<table border="1">
				<c:set var="rowsize" value="4"></c:set>
				<c:forEach begin="1" end="${fn:length(applications)/rowsize+1}" varStatus="row">
					<tr>
						<c:forEach var="application" items="${applications}" begin="${(row.count-1)*rowsize}"
							end="${row.count*rowsize-1}" varStatus="varstatus">
							<td class="box">${(row.count-1)*rowsize+varstatus.count}<img
									src="./ShowPicture?id=${application.pictureid}" width="100px" />
								<br />${application.pid}<br />${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name
								}<br />
								${application.name}<br />
								<c:if test="${application.seatid==''}">
									准考證號尚未製作！
								</c:if>
								<c:if test="${application.seatid!=''}">
									准考証號：${application.seatid}</c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
