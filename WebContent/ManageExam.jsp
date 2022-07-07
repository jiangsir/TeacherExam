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

<script type="text/javascript">
	jQuery(document).ready(function () {
		console.log('window.location.hash=' + window.location.hash);
		if (window.location.hash != null) {
			jQuery('.nav-tabs a[aria-controls="' + window.location.hash.substring(1) + '"]').tab('show');
		}
	});
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="include/Header_BootstrapFlat.jsp" />
			<h2>${exam.title }</h2>
			<br />
			<h3>共 ${fn:length(exam.applications)} 人報名！</h3>
			<c:set var="exam" value="${exam}" scope="request" />
			<jsp:include page="include/DIV_ExamStatistic.jsp" />
			<hr />
			<div>
				<span id="makeSeatid" data-examid="${exam.id }" class="btn btn-primary">製作准考證號碼。</span>
				<div id="makeSeatid_dialog" title="確定製作准考證號碼？" style="display: none;">
					請注意: <br /> 1. 繳款截止之後，才能開始製作准考證。<br>2.已繳款考生才會配給准考証號碼。<br /> <br />確定要製作准考證字號？
				</div>
				<span id="setExamformprintable" data-examid="${exam.id}" class="btn btn-primary">開放考生列印准考證。</span>
				<div id="setExamformprintable_dialog" title="請先確定已經將准考證字號製作完畢！" class="btn btn-default"
					style="display: none;">
					請先確定已經將准考證字號製作完畢！<br />再開放考生列印准考證！
				</div>
				<hr />
				<a href="./ExportCSV.api?target=PaidXLS&examid=${exam.id}" type="button"
					class="btn btn-primary">匯出所有已繳費者 XLS 檔</a> <a
					href="./ExportCSV.api?target=PaidCSV&examid=${exam.id}" type="button"
					class="btn btn-primary">匯出所有已繳費者 CSV 檔</a>
				<div id="exportPaid_dialog" title="匯出已繳費的考生名冊！" style="display: none;" class="btn btn-default">
					匯出已繳費的考生名冊！</div>
			</div>
			<br />
			<ul class="nav nav-tabs" role="tablist">
				<li><a href="?examid=${exam.id}#tab_0" aria-controls="tab_0" role="presentation"><span
							class="glyphicon glyphicon-tag"></span>所有科目報名人</a></li>
				<li><a href="?examid=${exam.id}&ispaid=1#tab_1" aria-controls="tab_1" role="presentation"><span
							class="glyphicon glyphicon-tag"></span>列出所有已繳費者</a></li>
				<c:forEach var="subject" items="${subjects}" varStatus="status">
					<li><a href="?examid=${exam.id}&subjectid=${subject.id}#tab_${status.count+1}" role="presentation"
							aria-controls="tab_${status.count+1}"> <span class="glyphicon glyphicon-tag"></span>
							${subject.zhengshidaili}${subject.nianduan}${subject.name}
						</a></li>
				</c:forEach>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0" role="tabpanel" aria-labelledby="home-tab">...</div>
				<c:forEach var="subject" items="${subjects}" varStatus="status">
					<div class="tab-pane" id="tab_${status.count}" role="tabpanel"></div>
				</c:forEach>
			</div>
			<br />
			<h2>${subject.zhengshidaili}${subject.nianduan}${subject.name}</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th data-sort="int" width="3%">序號</th>
						<th data-sort="string" width="10%">基本資料</th>

						<!-- 
						<th>是否繳費？</th>
						 -->
						<th>成績查詢 | <a href="./ImportScorecsv?examid=${exam.id}"
							type="button" class="btn btn-success">匯入成績表</a></th>
						<th width="5%">管理</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="application" items="${applications}" varStatus="varstatus">
						<tr>
							<th>${varstatus.count}</th>
							<th scope="row" style="text-align: center;">
								<!-- 
							<img
								src="./ShowPicture?id=${application.pictureid}"
								style="width: 100px;" /> <br />
								 -->
								<c:set var="subject" value="${application.subject}"></c:set>
								<div>${subject.zhengshidaili}${subject.nianduan}${subject.name
									}</div>
								<a
									href="EditApplication.html?applicationid=${application.id}">${application.name}</a><br />
								<div>${application.pid}</div>
								<div>${application.bankaccount}</div>
								<c:if test="${application.seatid==''}">
									准考證號尚未製作！
								</c:if>
								<c:if test="${application.seatid!=''}">
									准考証號：${application.seatid}</c:if> <br />
								<div>${application.timestamp}</div> <a
									href="Personal.html?examid=${application.examid}&pid=${application.pid}"
									class="btn btn-default">考生個人頁</a>
							</th>

							<td><a
									href="Scorecsv.html?examid=${application.examid}&seatid=${application.seatid}">初試:</a>
								<c:set var="scorecsv" value="${application.getScorecsv()}"></c:set>
								<c:if test="${empty scorecsv.getStep1()}">尚未有初試成績。<br>
								</c:if>
								<c:if test="${!empty scorecsv.getStep1()}">
									<table class="table table-hover">
										<thead>
											<tr>
												<c:forEach var="col" items="${fn:split(scorecsv.step1Array[0], ',')}">
													<th>${col }</th>
												</c:forEach>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:forEach var="data" items="${fn:split(scorecsv.step1Array[1], ',')}">
													<td>${data }</td>
												</c:forEach>
											</tr>
										</tbody>
									</table>
								</c:if>
								<hr> <a
									href="Scorecsv.html?examid=${application.examid}&seatid=${application.seatid}">複試:</a>
								<c:if test="${empty scorecsv.getStep2()}">尚未有複試成績。<br>
								</c:if>
								<c:if test="${!empty scorecsv.getStep2()}">
									<table class="table table-hover">
										<thead>
											<tr>
												<c:forEach var="col" items="${fn:split(scorecsv.step2Array[0], ',')}">
													<th>${col }</th>
												</c:forEach>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:forEach var="data" items="${fn:split(scorecsv.step2Array[1], ',')}">
													<td>${data }</td>
												</c:forEach>
											</tr>
										</tbody>
									</table>
								</c:if>
							</td>
							<td>
								<c:if test="${!application.ispaid}">
									<div class="input-group-btn">
										<button class="btn btn-default">未繳費 !</button>
										<button class="btn btn-default" type="button"
											title="手動設定為已繳費？(${application.bankaccount })" id="setPaid"
											data-bankaccount="${application.bankaccount}">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										</button>
									</div>
									<div id="ispaid-dialog-${application.bankaccount}" title="確定要手動設定為已繳費？"
										style="display: none;">
										姓名：<span id="name">${application.name }</span><br /> 身份證字號：<span
											id="pid">${application.pid }</span><br /> 虛擬帳號：<span
											id="bankaccount">${application.bankaccount }</span><br />
									</div>
								</c:if>
								<c:if test="${application.ispaid}">
									<div class="input-group-btn">
										<button class="btn btn-info" id="bankdata"
											title="${application.bankaccount}繳費明細">已繳費！(${application.bankdatasSum}元)</button>
										<c:set var="bankdatas" value="${application.bankdatas}" scope="request"></c:set>
										<jsp:include page="include/DIALOG_Bankdatas.jsp" />
										<button class="btn btn-default" type="button"
											title="手動取消繳費紀錄 (${application.bankaccount })" id="unPaid"
											data-bankaccount="${application.bankaccount}">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										</button>
										<div id="unpaid-dialog-${application.bankaccount}" title="確定要手動取消繳費資訊？"
											style="display: none;">
											姓名：<span id="name">${application.name }</span><br /> 身份證字號：<span
												id="pid">${application.pid }</span><br /> 虛擬帳號：<span
												id="bankaccount">${application.bankaccount }</span>
										</div>
									</div>
									<c:if test="${fn:length(application.bankdatas)==0}">查無繳費明細</c:if>
								</c:if><br />
								<button id="deleteApplication" applicationid="${application.id}"
									class="btn btn-danger">刪除</button>
								<div id="deleteApplication-dialog" title="確定要刪除這個報名人？" style="display: none;">
									姓名：<span id="name">${application.name }</span><br /> 身份證字號：<span
										id="pid">${application.pid }</span><br /> 虛擬帳號：<span
										id="bankaccount">${application.bankaccount }</span><br />
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<div>對帳資料自 ${begin } 到 ${end }</div>

			<c:if test="${fn:length(allbankdatas) == 0 }">
				<div>目前沒有接收到銀行方面任何資訊。</div>
			</c:if>

			<ul>
				<c:forEach var="bankdata" items="${allbankdatas}" varStatus="count">
					<li>${count.count}, ${bankdata.filename},
						虛擬帳號:${bankdata.bot.RCPTId}, 接收日期: ${bankdata.receivetime}</li>
				</c:forEach>
			</ul>

			<p>&nbsp;</p>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
