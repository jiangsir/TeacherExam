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
</head>
<body>
	<div id="main">
		<jsp:include page="include/Header.jsp" />
		<div id="panel">
			<form id="form1" method="post" action="ImportScoreboard">
				<div>成績表格式：</div>
				<div>
					准考證號碼,教育專業(原始),本科專業(原始),教育專業(T分數),本科專業(T分數),教育專業(T分)所佔比例成績,本科專業(T分)所佔比例成績,初試總分,結果<br />
					<br /> 不使用的欄位可以直接留空。但逗點仍須保留。<br /> 任一行的開頭若為 # 則代表這一行資料將被忽略。<br />下載範例
					csv 檔：<a href="scores.csv">成績表 csv 檔</a>
				</div>
				<div>
					<textarea name="scorecsv" id="scorecsv"
						style="width: 100%; height: 30em;">#准考證號碼,教育專業(原始),本科專業(原始),教育專業(T分數),本科專業(T分數),教育專業(T分)所佔比例成績,本科專業(T分)所佔比例成績,初試總分,結果
#A001,,,,,,,90,通過
#A039,,,,,,,35,不通過
</textarea>
					<input type="hidden" name="examid" value="${param.examid}" /> <input
						type="submit" name="Submit" value="送出" />
				</div>
			</form>
			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
