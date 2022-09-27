<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${applicationScope.appConfig.schoolname }教師甄選報名表</title>
	<STYLE TYPE="text/css">
		@page {
			margin-left: 2cm;
			margin-right: 2cm;
			margin-top: 1.2cm;
			margin-bottom: 1.2cm
		}
	
		body {
			font-family: Arial Unicode MS, sans-serif;
		}
	
		TABLE {
			vertical-align: middle;
			margin-left: auto;
			margin-right: auto;
		}
	
		TD P {
			margin-bottom: 0cm
		}
	
		.title {
			margin-bottom: 0cm;
			font-family: Arial Unicode MS, sans-serif;
			font-weight: bold;
			text-align: center;
			font-size: x-large;
		}
	
		.key {
			margin-bottom: 0cm;
			font-family: Arial Unicode MS, sans-serif;
			text-align: center;
			font-size: large;
		}
	
		.value {
			margin-bottom: 0cm;
			font-family: Arial Unicode MS, sans-serif;
			font-weight: bolder;
			text-align: center;
			font-size: large;
		}
	
		pre {
			white-space: pre-wrap;
			word-wrap: break-word;
		}
	</STYLE>
</head>

<BODY LANG="zh-TW" DIR="LTR">
	<div class="title">
		${applicationScope.appConfig.schoolname}<br />${application.exam.title
		}報名表
	</div>
	<TABLE WIDTH=900 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5 CELLSPACING=0>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<COL WIDTH=75>
		<TR>
			<TD COLSPAN=12>
				<div class="key" style="text-align: left;">准考證號碼: <span class="value">${application.seatid}</span></div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=5 COLSPAN=2 style="text-align: center; vertical-align: middle;"><img
					src="./ShowPicture?id=${application.pictureid}" width="150px">
			</TD>
			<TD colspan="2">
				<div class="key">姓名</div>
			</TD>
			<TD COLSPAN=2>
				<div class="value">${application.name}</div>
			</TD>
			<TD>
				<div class="key">生日</div>
			</TD>
			<TD COLSPAN=2>
				<div class="value">${application.birthday }</div>
			</TD>
			<TD>
				<div class="key">性別</div>
			</TD>
			<TD colspan="2">
				<div class="value">${application.gender}</div>
			</TD>
		</TR>
		<TR>
			<TD colspan="2">
				<div class="key">通訊地址</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value" style="text-align: left;">${application.address}</div>
			</TD>
			<TD COLSPAN=4>
				<div class="key" style="text-align: left;">行動電話: <span class="value">${application.cellphone
						}</span></div>
				<div class="key" style="text-align: left;">辦公室: <span class="value">${application.teloffice }</span>
				</div>
			</TD>
		</TR>
		<TR>
			<TD colspan="2">
				<div class="key">甄選科別</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">
					${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name}
				</div>
			</TD>
			<TD>
				<div class="key">身份證</div>
			</TD>
			<TD COLSPAN=3>
				<div class="value">${application.pid}</div>
			</TD>
		</TR>
		<TR>
			<TD colspan="2">
				<div class="key">服務學校(機構)</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">${application.school}</div>
			</TD>
			<TD>
				<div class="key">職稱</div>
			</TD>
			<TD COLSPAN=3>
				<div class="value">${application.position}</div>
			</TD>
		</TR>
		<TR>
			<TD colspan="2">
				<div class="key">教師證書(科別)</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">${application.license}</div>
			</TD>
			<TD>
				<div class="key">證書字號</div>
			</TD>
			<TD COLSPAN=3>
				<div class="value">${application.licenseid}</div>
				<div class="value">${application.licensedate}</div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=3>
				<div class="key">學歷</div>
			</TD>
			<TD COLSPAN=7>
				<div class="key" style="text-align: left;">大學: <span
						class="value">${application.bachelorschool}${application.bachelormajor}</span></div>
			</TD>
			<TD COLSPAN=4>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.bachelorbegin}</span>年 至
					<span class="value">${application.bachelorend }</span>年
				</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=7>
				<div class="key" style="text-align: left;">碩士: <span class="value">${application.masterschool
						}${application.mastermajor }</span></div>
			</TD>
			<TD COLSPAN=4>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.masterbegin
						}</span>年 至 <span class="value">${application.masterend }</span>年</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=7>
				<div class="key" style="text-align: left;">博士: <span
						class="value">${application.phdschool}${application.phdmajor}</span></div>
			</TD>
			<TD COLSPAN=4>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.phdbegin }</span>年 至
					<span class="value">${application.phdend }</span>年
				</div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=4>
				<div class="key">經歷</div>
			</TD>
			<TD COLSPAN=4>
				<div class="key">服務學校(機構)</div>
			</TD>
			<TD COLSPAN=4>
				<div class="key">職務</div>
			</TD>
			<TD COLSPAN=3>
				<div class="key">服務起訖</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=4>
				<div class="value">${application.school1}</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">${application.position1}</div>
			</TD>
			<TD COLSPAN=3>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.school1begin}</span>年 至
					<span class="value">${application.school1end}</span>年
				</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=4>
				<div class="value">${application.school2}</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">${application.position2}</div>
			</TD>
			<TD COLSPAN=3>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.school2begin}</span>年 至
					<span class="value">${application.school2end}</span>年
				</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=4>
				<div class="value">${application.school3}</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value">${application.position3}</div>
			</TD>
			<TD COLSPAN=3>
				<div class="key" style="text-align: left;"> 自 <span class="value">${application.school3begin}</span>年 至
					<span class="value">${application.school3end}</span>年
				</div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=3>
				<div class="key">特殊教育相關證明</div>
			</TD>
			<TD COLSPAN=7>
				<div class="key">參加研習、修畢學分、擔任特殊教育相關工作</div>
			</TD>
			<TD COLSPAN=4>
				<div class="key">時間</div>
			</TD>
		</TR>

		<TR>
			<TD COLSPAN=7>
				<div class="value" style="text-align: left;">${application.special1}　</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value" style="text-align: left;">${application.special1date}　</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=7>
				<div class="value">${application.special2}　</div>
			</TD>
			<TD COLSPAN=4>
				<div class="value"> ${application.special2date}　</div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=3>
				<div class="key">相關專長、檢定、證照</div>
			</TD>
			<TD COLSPAN=11>
				<div class="value">${application.license1}　</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=11>
				<div class="value">${application.license2}　</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=11>
				<div class="value">${application.license3}　</div>
			</TD>
		</TR>
		<TR>
			<TD ROWSPAN=4>
				<div class="key">各類競賽獎勵紀錄</div>
			</TD>
			<TD COLSPAN=6>
				<div class="key">事由</div>
			</TD>
			<TD COLSPAN=5>
				<div class="key">獎勵及名次</div>
			</TD>
		</TR>
		<c:forEach var="honor" items="${application.honors}">
			<TR>
				<TD COLSPAN=6>
					<div class="value">${honor.reason}　</div>
				</TD>
				<TD COLSPAN=5>
					<div class="value">${honor.award}　</div>
				</TD>
			</TR>
		</c:forEach>
		<TR>
			<TD COLSPAN=12>
				<div class="key" style="text-align: left;">其他補充說明：</div>
				<pre class="value" style="text-align: left;">${application.other}　</pre>
			</TD>
		</TR>
		<!-- <TR>
			<TD COLSPAN=12>
				<div class="key" style="text-align: left;">附註：</div>
				<div class="value" style="text-align: left;">${application.note}　</div>
			</TD>
		</TR> -->
		<TR>
			<TD ROWSPAN=2 COLSPAN=1>
				<div class="key">應迴避關係表</div>
			</TD>
			<TD COLSPAN=6>
				<div class="key" style="text-align: left;">是否曾在本校就讀、實習或與本校教師曾有師生、同學之關係者</div>
			</TD>
			<TD COLSPAN=5>
				<div class="key" style="text-align: left;">□否 □是，姓名 _________________ <br>關係 _________________</div>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN=6>
				<div class="key" style="text-align: left;">本人或配偶(或前配偶)是否有四親等內之血親或三親等內之姻親(或曾有此關係者)在本校任職</div>
			</TD>
			<TD COLSPAN=5>
				<div class="key" style="text-align: left;">□否 □是，姓名 _________________ <br>關係 _________________</div>
			</TD>
		</TR>

		<TR>
			<TD COLSPAN=12>
				<div class="key" style="text-align: left;">本人以上所填內容屬實，如有不實，願自負法律責任。</div>
				<div class="key" style="text-align: left;">填表人簽章：</div>
				<p></p>
				<div class="key" style="text-align: right;">年　　　　月　　　　日　(簽名或蓋章)</div>
			</TD>
		</TR>
		<TR>
			<TD>
				<div class="key" style="height: 1.5em;">初審</div>
			</TD>
			<TD COLSPAN=5>
				<p>　</p>
				<p>　</p>
			</TD>
			<TD>
				<div class="key">複審</div>
			</TD>
			<TD COLSPAN=5>
				<p>　</p>
				<p>　</p>
			</TD>
		</TR>
	</TABLE>
</BODY>

</html>