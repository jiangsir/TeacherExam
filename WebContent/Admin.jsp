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
<script type="text/javascript" src="Admin.js?${applicationScope.built }"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="include/Header_BootstrapFlat.jsp" />
			<div>
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tabs-1"
						aria-controls="tabs-1" role="tab" data-toggle="tab">管理甄選項目</a></li>
					<li role="presentation"><a href="#tabs-2"
						aria-controls="tabs-2" role="tab" data-toggle="tab">系統設定</a></li>
					<li role="presentation"><a href="#tabs-3"
						aria-controls="tabs-3" role="tab" data-toggle="tab">管理員帳號</a></li>
					<li role="presentation"><a href="#tabs-4"
						aria-controls="tabs-4" role="tab" data-toggle="tab">系統資訊</a></li>
				</ul>
				<div class="tab-content">
					<div id="tabs-1" role="tabpanel" class="tab-pane fade in active">
						<br> <a href="InsertExam.html" type="button"
							class="btn btn-primary">建立一個新的教師甄試。</a>
						<hr>
						<fieldset>
							<legend>進行中的甄選</legend>
							<table class="table table-hover">
								<c:forEach var="exam" items="${exams}">
									<c:if test="${exam.isactive}">
										<tr>
											<td width="65%"><div id="activeExam">
													<h2>${exam.title}</h2>
													<button id="disactiveExam" examid="${exam.id }"
														class="btn btn-danger">結束本次甄試</button></td>
											<td width="35%"><a
												href="./EditExam.html?examid=${exam.id}" type="button"
												class="btn btn-default">修改甄試設定</a> <a
												href="./ManageExam.html?examid=${exam.id}" type="button"
												class="btn btn-default">管理報名人員</a> <a
												href="./Applications.html?examid=${exam.id}" type="button"
												class="btn btn-default">列印監試資料</a> <!-- 
														<a
														href="./ImportScoreboard?examid=${exam.id}" type="button"
														class="btn btn-default">匯入初選成績表</a><a
														href="./ImportStep2Scoreboard?examid=${exam.id}"
														type="button" class="btn btn-default">匯入複選成績表</a>
														 -->
												<div id="disactiveExam_dialog" title="確定結束本次甄試？"
													style="display: none;">確定結束『${exam.title }』?</div>

												</div></td>
										</tr>
									</c:if>
								</c:forEach>
							</table>

						</fieldset>
						<hr>
						<h2>甄試列表</h2>
						<table class="table table-hover">
							<tbody>
								<c:forEach var="exam" items="${exams}">
									<c:if test="${!exam.isactive}">
										<tr>
											<td width="70%"><span style="font-size: 1.2em;">#${exam.id}.${exam.title }</span>
												<span style="margin: 3px;"> <c:if
														test="${exam.isactive==false}">
														<button id="enactiveExam" examid="${exam.id }"
															class="btn btn-success">設定為進行中</button>
													</c:if> <c:if test="${exam.isactive==true}">
                            進行中...
                        </c:if>
											</span></td>
											<td width="30%"><a
												href="./EditExam.html?examid=${exam.id}" type="button"
												class="btn btn-default">修改甄試設定</a> <a
												href="./ManageExam.html?examid=${exam.id}" type="button"
												class="btn btn-default">管理報名人員</a> <!-- 
												<a
												href="./ExportCSV.api?target=exam&examid=${exam.id }"
												type="button" class="btn btn-default">匯出報考者資料(報部用) </a>
												 -->

												<button id="deleteExam" class="btn btn-default"
													data-examid="${exam.id}">刪除</button>
												<div id="deleteExam_dialog" title="確定要刪除這個甄試？"
													style="display: none;">
													<h3>刪除</h3>
													<h3>【#${exam.id}. ${exam.title}】?</h3>
												</div>
												<div id="enactiveExam_dialog" title="確定啓動本次甄試？"
													style="display: none;" class="btn btn-default">將會啓動『${exam.title}』，並且停止所有其它甄試。</div>
											</td>
										</tr>
									</c:if>

								</c:forEach>
							</tbody>
						</table>

						<%-- 					<p>對帳資料</p>
					<ul>
						<c:forEach var="bankdata" items="${bankdatas}" varStatus="count">
							<li>${count.count}. ${bankdata.filename}:
								虛擬帳號:${bankdata.bot.RCPTId}: 接收日期: ${bankdata.receivetime }</li>
						</c:forEach>
					</ul>
 --%>
						<!-- 				<p>&nbsp;</p>
					<button id="setAllBankaccount">建立所有 bankaccount, 只需做一次！</button>
 -->
					</div>
					<div id="tabs-2" role="tabpanel" class="tab-pane">
						<br>
						<form action="" method="post" id="form">
							<table>
								<tr>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>系統標題：</th>
									<td><input name="title" type="text"
										value="${applicationScope.appConfig.title}" size="40" /></td>
								</tr>
								<tr>
									<th>學校名稱：</th>
									<td><input name="schoolname" type="text"
										value="${applicationScope.appConfig.schoolname}" size="40" /></td>
								</tr>
								<tr>
									<th scope="col">校長：</th>
									<td><input name="principal" type="text"
										value="${applicationScope.appConfig.principal}" size="20" /></td>
								</tr>
								<tr>
									<th scope="col">主辦主計：</th>
									<td><input name="zhuji" type="text"
										value="${applicationScope.appConfig.zhuji}" size="20" /></td>
								</tr>
								<tr>
									<th scope="col">出納：</th>
									<td><input name="chuna" type="text"
										value="${applicationScope.appConfig.chuna}" size="20" /></td>
								</tr>
								<tr>
									<th scope="col">人事室：</th>
									<td><input name="renshi" type="text"
										value="${applicationScope.appConfig.renshi}" size="20" /></td>
								</tr>
								<tr>
									<th scope="col">人事室電話(洽詢方式)：</th>
									<td><input name="renshiphone" type="text"
										value="${applicationScope.appConfig.renshiphone}" size="20" /></td>
								</tr>
								<tr>
									<th scope="col">收款戶名：</th>
									<td><input name="bankhuming" type="text"
										value="${applicationScope.appConfig.bankhuming}" size="40" /></td>
								</tr>
								<tr>
									<th scope="col">管理員 IP：</th>
									<td><input name="manager_ip" type="text"
										value="${applicationScope.appConfig.manager_ip}" size="50" />例:[0.0.0.0/0]
										全部開放 或 [127.0.0.1, 192.168.1.0/24] 開放本機與 192.168.1.* 網段</td>
								</tr>
								<tr>
									<th scope="col">可以進入網站的 IP：</th>
									<td><input name="allowed_ip" type="text"
										value="${applicationScope.appConfig.allowed_ip}" size="50" />例:[0.0.0.0/0]
										全部開放 或 [127.0.0.1, 192.168.1.0/24] 開放本機與 192.168.1.* 網段</td>
								</tr>
								<tr>
									<th scope="col">台灣銀行虛擬帳號前導編號：</th>
									<td><input name="bankprefix" type="text"
										value="${applicationScope.appConfig.bankprefix}" size="20" />(應向台灣銀行申請虛擬帳號才會獲得此編號，本系統僅支援產生台灣銀行虛擬帳號)</td>
								</tr>
								<tr>
									<th scope="col">台灣銀行及分行：</th>
									<td><input name="bankname" type="text"
										value="${applicationScope.appConfig.bankname}" size="40" />(e.q
										台灣銀行 高雄分行)</td>
								</tr>
								<tr>
									<th scope="col">台灣銀行自動對帳資料接收程式：</th>
									<td><div style="font-style: italic;">${receiveDataUrl}</div>請將此網址回報台灣銀行電子金融部。<br />本接收程式僅適用於接收台灣銀行電子對帳資料(各銀行有各自對帳資料格式)，若收款銀行並非台灣銀行，則需進行人工對帳，及手動設定考生是否已繳款。</td>
								</tr>
							</table>
							<div>
								<input type="submit" value="送出" role="button"
									class="btn btn-success btn-lg btn-block"></input>
							</div>
						</form>
					</div>
					<div id="tabs-3" role="tabpanel" class="tab-pane">
						<br>
						<div>在此僅列出所有具備「管理員(ROLE="ADMIN")」權限者。</div>
						<c:forEach var="user" items="${users}">
							<div>
								${user.id}. ${user.pid}: <a href="EditUser?userid=${user.id}"
									title="編輯帳號" class="btn btn-primary">更改密碼</a>
								<!-- 
								<img src="images/edit.svg"
									style="height: 1em;" alt="編輯帳號" /> -->
							</div>
						</c:forEach>
					</div>
					<div id="tabs-4" role="tabpanel" class="tab-pane">
						<br> pageContext.request.requestURI=
						${pageContext.request.requestURI}<br />
						pageContext.request.authType= ${pageContext.request.authType}<br />
						pageContext.request.characterEncoding=${pageContext.request.characterEncoding}<br />
						pageContext.request.contentLength=${pageContext.request.contentLength}<br />
						pageContext.request.contentType=${pageContext.request.contentType}<br />
						pageContext.request.contextPath=${pageContext.request.contextPath}<br />
						pageContext.request.localAddr=${pageContext.request.localAddr}<br />
						pageContext.request.locale=${pageContext.request.locale}<br />
						pageContext.request.localName=${pageContext.request.localName}<br />
						pageContext.request.localPort=${pageContext.request.localPort}<br />
						pageContext.request.method=${pageContext.request.method}<br />
						pageContext.request.pathInfo=${pageContext.request.pathInfo}<br />
						pageContext.request.pathTranslated=${pageContext.request.pathTranslated}<br />
						pageContext.request.protocol=${pageContext.request.protocol}<br />
						pageContext.request.queryString=${pageContext.request.queryString}<br />
						pageContext.request.remoteAddr=${pageContext.request.remoteAddr}<br />
						pageContext.request.remoteHost=${pageContext.request.remoteHost}<br />
						pageContext.request.remotePort=${pageContext.request.remotePort}<br />
						pageContext.request.remoteUser=${pageContext.request.remoteUser}<br />
						pageContext.request.requestedSessionId=${pageContext.request.requestedSessionId}<br />
						pageContext.request.requestURI=${pageContext.request.requestURI}<br />
						pageContext.request.requestURL=${pageContext.request.requestURL}<br />
						pageContext.request.scheme=${pageContext.request.scheme}<br />
						pageContext.request.serverName=${pageContext.request.serverName}<br />
						pageContext.request.serverPort=${pageContext.request.serverPort}<br />
						pageContext.request.servletPath=${pageContext.request.servletPath}<br />
						pageContext.request.userPrincipal=${pageContext.request.userPrincipal}<br />

					</div>
				</div>
			</div>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
