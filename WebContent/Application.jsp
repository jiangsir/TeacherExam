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
<script type="text/javascript"
	src="Application.js?${applicationScope.built }"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="include/Header_BootstrapFlat.jsp" />
			<h1>${application.exam.title}-填寫基本報名資料</h1>
			<div>
				標有 <span class="style1">*</span> 為必填欄位。
			</div>

			<form action="" method="post" enctype="multipart/form-data" id="form"
				class="form">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-md-2"></th>
							<th class="col-md-7"></th>
							<th class="col-md-3">備註</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>身份證字號</th>
							<td>
								<div class="input-group">
									<input name="pid" type="text" id="pid" class="form-control"
										placeholder="身份證字號，作為後續登入帳號" value="${application.pid }">
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td>請小心填寫，將作為後續登入的帳號。</td>
						</tr>
						<tr>
							<th>密碼：</th>
							<td>
								<div class="form-inline">
									<div class="form-group">
										<input class="form-control" name="passwd" type="password"
											id="passwd" value="${user.passwd }" placeholder="密碼"
											size="20" maxlength="40">
									</div>
									<div class="form-group">
										<input class="form-control" name="passwd1" type="password"
											id="passwd1" placeholder="再次確認密碼" size="20" maxlength="40">
									</div>
								</div>
							</td>
							<td>修改報名資料時使用。請務必牢記。</td>
						</tr>
						<tr>
							<th>報名科別</th>
							<td><select class="form-control input-lg" name="subjectid"
								id="subjectid" subject="${application.subject.id}">
									<option value="0">請選擇...</option>
									<c:forEach var="subject" items="${application.exam.subjects}">
										<option value="${subject.id}">${subject.zhengshidaili}${subject.nianduan}${subject.name}</option>
									</c:forEach>
							</select></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>
								<div class="input-group">
									<input type="text" class="form-control" placeholder="您的姓名"
										name="name" value="${application.name}">
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<th>性別</th>
							<td gender="${application.gender}"><input name="gender"
								type="radio" id="gender" value="男" /> 男性 <span class="style1">*</span><br />
								<input name="gender" type="radio" id="gender" value="女" /> 女性</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>出生年月日</th>
							<td>
								<div class="input-group">
									<input type="text" class="form-control" placeholder="您的生日"
										name="birthday" value="${application.birthday}"
										id="birthday_datepicker">
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td>請以如下範例格式填寫：${application.birthday} (分別為 西元年-月-日)</td>
						</tr>
						<tr>
							<th>電子郵件</th>
							<td>
								<div class="input-group">
									<input class="form-control" name="email" type="text" id="email"
										value="${application.email }" placeholder="電子郵件">
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td>請正確填寫。</td>
						</tr>
						<tr>
							<th>現職服務單位</th>
							<td position="${application.position}"><input name="school"
								type="text" id="school" class="form-control"
								placeholder="現職服務單位" value="${application.school }"
								maxlength="30"></td>
							<td>無則留空白。</td>
						</tr>
						<tr>
							<th>現任職務</th>
							<td><input name="position" type="text" id="position"
								class="form-control" placeholder="現任職務"
								value="${application.position}" maxlength="30"></td>
							<td>請填寫主任、組長、導師、專任 or 代理 or 實習，若無則留空白</td>
						</tr>
						<tr>
							<th>聯絡電話（辦公室）</th>
							<td><input name="teloffice" type="text" id="teloffice"
								class="form-control" placeholder="聯絡電話（辦公室）"
								value="${application.teloffice }" maxlength="30" /></td>
							<td>無則免填</td>
						</tr>

						<tr>
							<th>行動電話</th>
							<td>
								<div class="input-group">
									<input name="cellphone" type="text" id="cellphone"
										class="form-control" placeholder="行動電話"
										value="${application.cellphone }" maxlength="30" />
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td>無行動電話者填可聯絡得上的電話。</td>
						</tr>
						<tr>
							<th>通訊處地址</th>
							<td>
								<div class="input-group">
									<input name="address" type="text" id="address"
										class="form-control" placeholder="通訊處地址"
										value="${application.address }" maxlength="50" />
									<div class="input-group-addon" title="必填項目">*</div>
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>教師證書</th>
							<td>
								<div class="input-group">
									<label for="exampleInputEmail1">教師科別：</label> <input
										class="form-control" name="license" type="text" id="license"
										value="${application.license}" maxlength="30"
										placeholder="教師科別">
								</div>
								<div class="input-group">
									<label for="exampleInputEmail1">發證年月 ：</label> <input
										class="form-control" name="licensedate" type="text"
										id="licensedate" value="${application.licensedate}"
										maxlength="20" placeholder="發證年月">
								</div>
								<div class="input-group">
									<label for="exampleInputEmail1">證書字號：</label> <input
										class="form-control" name="licenseid" type="text"
										id="licenseid" value="${application.licenseid}" maxlength="30"
										placeholder="證書字號">
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>學歷</th>
							<td>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">大學(系)：學校：</label> <input class="form-control"
											name="bachelorschool" type="text" id="bachelorschool"
											value="${application.bachelorschool}" size="20"
											maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">科系：</label> <input class="form-control"
											name="bachelormajor" type="text" id="bachelormajor"
											value="${application.bachelormajor}" size="20" maxlength="40">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="bachelorbegin" type="text" id="bachelorbegin"
											value="${application.bachelorbegin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="bachelorend" type="text" id="bachelorend"
											value="${application.bachelorend}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">碩士(所)：學校：</label> <input class="form-control"
											name="masterschool" type="text" id="masterschool"
											value="${application.masterschool}" size="20" maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">科系：</label> <input class="form-control"
											name="mastermajor" type="text" id="mastermajor"
											value="${application.mastermajor}" size="20" maxlength="40">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="masterbegin" type="text" id="masterbegin"
											value="${application.masterbegin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="masterend" type="text" id="masterend"
											value="${application.masterend}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">博士(所)：學校：</label> <input class="form-control"
											name="phdschool" type="text" id="phdschool"
											value="${application.phdschool}" size="20" maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">科系：</label> <input class="form-control"
											name="phdmajor" type="text" id="phdmajor"
											value="${application.phdmajor}" size="20" maxlength="40">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="phdbegin" type="text" id="phdbegin"
											value="${application.phdbegin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="phdend" type="text" id="phdend"
											value="${application.phdend}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>
							</td>
							<td>學校及系所名稱請完整填寫！</td>
						</tr>
						<tr>
							<th>經歷</th>
							<td>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">服務單位1：</label> <input class="form-control"
											name="school1" type="text" id="school1"
											value="${application.school1}" size="40" maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">職務1：</label> <input class="form-control"
											name="position1" type="text" id="position1"
											value="${application.position1}" size="10" maxlength="10">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="school1begin" type="text" id="school1begin"
											value="${application.school1begin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="school1end" type="text" id="school1end"
											value="${application.school1end}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">服務單位2：</label> <input class="form-control"
											name="school2" type="text" id="school2"
											value="${application.school2}" size="40" maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">職務2：</label> <input class="form-control"
											name="position2" type="text" id="position2"
											value="${application.position2}" size="10" maxlength="10">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="school2begin" type="text" id="school2begin"
											value="${application.school2begin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="school2end" type="text" id="school2end"
											value="${application.school2end}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">服務單位3：</label> <input class="form-control"
											name="school3" type="text" id="school3"
											value="${application.school3}" size="40" maxlength="40">
									</div>
									<div class="form-group">
										<label for="end">職務3：</label> <input class="form-control"
											name="position3" type="text" id="position3"
											value="${application.position3}" size="10" maxlength="10">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">自民國</label> <input class="form-control"
											name="school3begin" type="text" id="school3begin"
											value="${application.school3begin}" size="5" maxlength="5">
									</div>
									<div class="form-group">
										<label for="end">年 至民國</label> <input class="form-control"
											name="school3end" type="text" id="school3end"
											value="${application.school3end}" size="5" maxlength="5">
										<label for="end">年</label>
									</div>
								</div>

							</td>
							<td>無者免填。</td>
						</tr>
						<tr>
							<th scope="row">特殊教育相關證明</th>
							<td>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">證明1:</label> <input class="form-control"
											name="special1" type="text" id="special1"
											value="${application.special1}" size="50" maxlength="80">
									</div>
									<div class="form-group">
										<label for="end">期間：</label> <input class="form-control"
											name="special1date" type="text" id="special1date"
											value="${application.special1date}" size="10" maxlength="10">
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label for="end">證明2:</label> <input class="form-control"
											name="special2" type="text" id="special2"
											value="${application.special2}" size="50" maxlength="80">
									</div>
									<div class="form-group">
										<label for="end">期間：</label> <input class="form-control"
											name="special2date" type="text" id="special2date"
											value="${application.special2date}" size="10" maxlength="10">
									</div>
								</div>
							</td>
							<td>請填寫所參加研習、修畢學分、擔任特殊教育等相關工作</td>
						</tr>
						<tr>
							<th scope="row">相關專長、檢定、證照</th>
							<td><input class="form-control" name="license1" type="text"
								id="license1" value="${application.license1}"
								placeholder="相關專長、檢定、證照 1"> <input class="form-control"
								name="license2" type="text" id="license2"
								value="${application.license2}" placeholder="相關專長、檢定、證照 2">

								<input class="form-control" name="license3" type="text"
								id="license3" value="${application.license3}"
								placeholder="相關專長、檢定、證照 3"></td>
							<td>無者免填。</td>
						</tr>
						<tr>
							<th scope="row">各類競賽獎勵紀錄</th>
							<td>
								<table>
									<tr>
										<th>事由</th>
										<th>獎勵及名次</th>
									</tr>
									<c:forEach var="honor" items="${application.honors }">
										<tr>
											<td><input class="form-control" name="reason"
												type="text" id="reason" value="${honor.reason}" size="50"
												maxlength="50" /></td>
											<td><input class="form-control" name="award" type="text"
												id="award" value="${honor.award}" size="50" maxlength="50" /></td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<td>請正確填寫。</td>
						</tr>
						<tr>
							<th scope="row">各種補充說明：</th>
							<td><textarea class="form-control" rows="5" name="other">${application.other}</textarea>
							</td>
							<td>若上開欄位不足，仍需補充，可在此說明「經歷」、「特殊教育相關證明」、「相關專長、檢定、證照」、「各類競賽獎勵紀錄」等資料。</td>
						</tr>
						<tr>
							<th>相片</th>
							<td><c:if test="${application.id != 0}">
									<img src="ShowPicture?id=${application.pictureid}" alt=""
										width="200" />
								</c:if> <input name="picture" type="file" id="picture" /> <br />
								請使用寬高比接近 2:3 (直立) 之 jpg 或 png 圖片檔。檔案請勿超過 5MB。</td>
							<td>相片要求：2年內、清晰、正面、脫帽、勿濃妝、勿使用生活照。</td>
						</tr>
						<tr>
							<th>附註：</th>
							<td><textarea class="form-control" rows="5" name="note">${application.note}</textarea>
							<td>${application.exam.note }</td>
						</tr>
					</tbody>
				</table>

				<div>
					<input type="hidden" name="examid" value="${application.examid}" />
					<input type="hidden" name="pictureid"
						value="${application.pictureid}" /> <input type="hidden"
						name="applicationid" value="${application.id}" />
					<div id="check_dialog" title="有部分資料不正確！" style="display: none;"></div>
					<button type="submit" class="btn btn-success btn-lg btn-block">送出</button>
				</div>

			</form>

		</div>
		<jsp:include page="include/Footer.jsp" />
	</div>
</body>
</html>
