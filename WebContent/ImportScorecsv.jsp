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
			<div class="clearfix"></div>
			<c:if test="${fn:length(scorecsv)==0}">目前本次甄選尚未有任何成績表。</c:if>
			<h2>成績表格式：</h2>
			<div>第一列請務必放置所有自訂的欄位名稱。</div>
			<div>第一欄請務必為【准考證號碼】，第二欄之後可以自行定義欄位名稱與數量</div>
			<div>若成績已經存在，則會進行更新。也可以分科匯入成績表。比如國文科有教育專業與本科專業分數，音樂科可以為教育專業與演奏分數。</div>
			<div>
				<br>
			</div>
			<div class="col-md-4">
				範例一: 可於欄位名稱處清楚表列每科的計分比例
				<pre>准考證號碼,教育專業(50%), 本科專業(50%), 初試總分, 結果
A001, 80, 90, 85, 通過
A002, 60, 80, 70, 不通過
				</pre>
			</div>
			<div class="col-md-4">
				範例二: 也可以不公開分數，只公開通過與否。
				<pre>准考證號碼,結果
A001, 通過
A002, 不通過
				</pre>
			</div>
			<div class="col-md-4">
				範例三: 僅公開總分，以及最低錄取門檻。
				<pre>准考證號碼, 初試總分, 結果(最低錄取門檻 81.5)
A001, 86.9, 錄取
A002, 78.4, 備取
				</pre>
			</div>
		</div>

		<div class="row">
			<form id="form1" method="post" action="ImportScorecsv">
				<textarea name="scorecsv_step1" id="scorecsv" class="form-control"
					rows="10"
					placeholder="准考證號碼,教育專業(50%), 本科專業(50%), 初試總分, 結果
A001, 80, 90, 85, 通過
A002, 60, 80, 70, 不通過">
</textarea>
				<input type="hidden" name="examid" value="${param.examid}" /> <input
					type="submit" role="button" value="匯入初試成績"
					class="btn btn-success btn-lg btn-block"></input>
			</form>
		</div>
		<div>
			<hr>
		</div>
		<div class="row">
			<form id="form1" method="post" action="ImportScorecsv">
				<textarea name="scorecsv_step2" id="scorecsv" class="form-control"
					rows="10"
					placeholder="准考證號碼, 複試總分, 結果(最低錄取門檻 81.5)
A001, 86.9, 正取
A002, 78.4, 備取">
</textarea>
				<input type="hidden" name="examid" value="${param.examid}" /> <input
					type="submit" role="button" value="匯入複試成績"
					class="btn btn-success btn-lg btn-block"></input>
			</form>
		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>
