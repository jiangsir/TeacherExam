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
<script src="InsertExam.js?${applicationScope.built }"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="include/Header_BootstrapFlat.jsp" />
			<br>
			<h1>編輯本次教師甄試(${exam.title })資訊。</h1>
			<p>
				標有 <span class="style1">*</span> 為必填欄位。<br />
			</p>

			<form action="" method="post" id="form" enctype="multipart/form-data">
				<table class="table table-hover">
					<tr>
						<th style="width: 15%;" scope="col">&nbsp;</th>
						<th style="width: 85%; text-align: left;" scope="col">&nbsp;</th>
					</tr>
					<tr>
						<th scope="row">甄試標題</th>
						<td><input type="text" name="title" value="${exam.title }"
							size="80"></input><span class="style1">*</span></td>
					</tr>
					<tr>
						<th scope="row"></th>
						<td style="vertical-align: middle;"><c:forEach var="subject"
								items="${exam.subjects}" varStatus="varstatus">
								<div id="SubjectSeat"
									style="display: inline; vertical-align: middle;">
									<input type="hidden" name="subjectid" id="subjectid"
										value="${subject.id}"></input> <select name="zhengshidaili"
										id="zhengshidaili" zhengshidaili="${subject.zhengshidaili }">
										<c:forEach var="zhengshidaili"
											items="${subject.ZHENGSHIDAILI}">
											<option value="${zhengshidaili}">${zhengshidaili}</option>
										</c:forEach>
									</select> <select name="nianduan" id="nianduan"
										nianduan="${subject.nianduan }">
										<c:forEach var="nianduan" items="${subject.NIANDUAN}">
											<option value="${nianduan}">${nianduan}</option>
										</c:forEach>
									</select> 科別： <input type="text" name="subjectname" id="subjectname"
										value="${subject.name}"></input> 准考證編號：<input type="text"
										name="seatpattern" id="seatpattern"
										value="${subject.seatpattern }"
										style="display: inline; vertical-align: middle;"></input>
									<ul id="icons" class="ui-widget ui-helper-clearfix"
										style="display: inline-block; vertical-align: middle;">
										<li class="ui-state-default ui-corner-all"
											title=".ui-icon-closethick"><span
											class="ui-icon ui-icon-closethick" id="removeSubjectSeat"></span>
										</li>

									</ul>
									<!-- 
									<span id="removeSubjectSeat" class="btn btn-default" title="刪除"><span
										class="ui-icon ui-icon-closethick"></span></span> <br />
									 -->
									<br>
								</div>
							</c:forEach>
							<div id="addSubjectSeat_dialog" title="增加一個科別！"
								style="display: none; padding: 3px;">
								<fieldset style="padding: 5px;">
									<label>甄試科別</label> <br /> <input type="text"
										name="subject_dialog" id="subject_dialog" value=""
										style="width: 90%;"></input> <br /> <label>准考證編號</label> <br />
									<input type="text" name="seatpattern_dialog" value=""
										id="seatpattern_dialog" style="width: 90%;"></input> <br />
								</fieldset>
							</div> <br />
							<div id="addSubjectSeat" examid="${exam.id }"
								class="btn btn-primary">＋新增科別</div></td>
					</tr>
					<tr>
						<th scope="row">報名費。</th>
						<td><input type="text" name="money" value="${exam.money }"
							size="30"></input>元。＊注意：報名費與「虛擬帳號」的產生相關，開始報名後就不可修改！</td>
					</tr>
					<tr>
						<th scope="row">開始報名日期</th>
						<td><input name="applybegin" size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.applybegin}" />"></input></td>
					</tr>
					<tr>
						<th scope="row">報名截止日期</th>
						<td><input name="applyend" size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.applyend}" />"></input>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th scope="row">繳費開始/截止日期</th>
						<td>繳費開始:<input name="startline" id="startline" size="30"
							value="${exam.startline }"></input>(含當天)<br /> 繳費截止:<input
							name="deadline" id="deadline" size="30" value="${exam.deadline }"></input>(含當天)<br>＊注意：繳費截止日期與「虛擬帳號」的產生相關，開始報名後就不可修改！
						</td>
					</tr>
					<%-- 					<tr>
						<th scope="row">初試開始時間</th>
						<td><input name="firsttest" id="firsttest" size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.firsttest }" />"></input></td>
					</tr>
					<tr>
						<th scope="row">複試開始時間</th>
						<td><input name="secondtest" id="secondtest" size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.secondtest }" />"></input></td>
					</tr>
 --%>
					<tr>
						<th scope="row">公佈初試成績日期</th>
						<td><input name="scoreboardbegin" id="scoreboardbegin"
							size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.scoreboardbegin }" />"></input></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th scope="row">考試地點：</th>
						<td><input type="text" name="examroom"
							value="${exam.examroom}" size="100"></input></td>
					</tr>
					<tr>
						<th scope="row">『報名表』內「附註」欄的說明：</th>
						<td><textarea name="note" style="width: 90%; height: 5em;">${exam.note}</textarea></td>
					</tr>
					<tr>
						<th scope="row">『准考證』內「應試說明」欄的說明：</th>
						<td><textarea name="formnote"
								style="width: 90%; height: 5em;">${exam.formnote }</textarea></td>
					</tr>
					<tr>
						<th scope="row">是否允許任何時候修改報名表？</th>
						<td
							isapplicationalwayseditable="${exam.isApplicationAlwaysEditable}"><input
							name="isapplicationalwayseditable" type="radio"
							id="isapplicationalwayseditable" value="false">不指定(報名截止後就不能修改了)。</input><br />
							<input name="isapplicationalwayseditable" type="radio"
							id="isapplicationalwayseditable" value="true">無條件允許修改報名表。</input></td>
					</tr>
					<tr>
						<th scope="row">簡章、日程表等相關表件</th>
						<td><br />
							<ul id="upfiles">
								<c:forEach var="upfile" items="${exam.upfiles }">
									<li id="examupfile">檔案說明：${upfile.descript },
										檔案名稱：${upfile.filename } <span id="deleteUpfile"
										upfileid="${upfile.id}" class="btn btn-default btn-xs"
										title="刪除已上傳的檔案！"><span
											class="ui-icon ui-icon-closethick"></span></span> <!-- 
										<button id="deleteUpfile" upfileid="${upfile.id}"
											style="font-size: 0.5em;">刪除檔案</button>
									-->
										<div id="deleteUpfile_dialog" title="刪除已上傳的檔案！"
											style="display: none; padding: 3px;">
											<fieldset style="padding: 5px;">
												<label>這個動作將會刪除檔案
													${upfile.filename}(${upfile.descript})，確定嗎？</label><br /> <br />
											</fieldset>
										</div> <br />
									</li>
								</c:forEach>
							</ul>
							<div id="newUpfile"
								style="display: inline; vertical-align: middle;">
								檔案說明：<input name="descripts" type="text" id="descript"
									value="${upfile.descript }" />選擇檔案：<input name="upfiles"
									type="file" style="display: inline; vertical-align: middle;" />
								<!-- 
								<button id="removeUpfile"
									style="display: inline; vertical-align: middle; font-size: 0.5em;">放棄上傳這個檔案</button>
									 -->
								<span id="removeUpfile" class="btn btn-default" title="放棄上傳這個檔案"><span
									class="ui-icon ui-icon-closethick"></span></span> <br />
							</div> <span id="addUpfile" class="btn btn-primary">+增加一個檔案</span></td>
					</tr>
					<tr>
						<th>參數調整</th>
						<td>
						</td>
					</tr>
					<tr></tr>
					<tr>
						<th scope="row">公佈複試成績日期</th>
						<td><input name="step2scoreboardbegin"
							id="step2scoreboardbegin" size="30"
							value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
            value="${exam.step2scoreboardbegin }" />"></input></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th scope="row">考生各種文件上傳表單。</th>
						<td><input name="uploadform" id="uploadform" size="100"
							value="${exam.uploadform}"></input> <br>表單欄位留白，代表不需要考生上傳資料。</td>
						<td></td>
					</tr>
				</table>

				<input type="hidden" name="examid" value="${exam.id}" />
				<button type="submit" class="btn btn-success btn-lg btn-block">送出</button>
			</form>

			<jsp:include page="include/Footer.jsp" />
		</div>
	</div>
</body>
</html>
