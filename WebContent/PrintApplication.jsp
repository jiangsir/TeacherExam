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
<!--
@page {
	margin-left: 2cm;
	margin-right: 2cm;
	margin-top: 1.59cm;
	margin-bottom: 1.61cm
}

body {
	font-family: Arial Unicode MS, sans-serif;
}

TABLE {
	vertical-align: middle;
	margin-left: auto;
	margin-right: auto;
}

P {
	margin-bottom: 0.21cm
}

TD P {
	margin-bottom: 0cm
}

.title {
	margin-bottom: 0cm;
	font-family: Arial Unicode MS, sans-serif;
	font-weight: bold;
	text-align: center;
	font-size: xx-large;
}

.value {
	font-weight: bolder;
}
-->
</STYLE>
</head>
<BODY LANG="zh-TW" DIR="LTR">
	<P class="title">
		${applicationScope.appConfig.schoolname}<br />${application.exam.title
		}報名表
	</P>
	<br />
	<FORM NAME="表單">
		<TABLE WIDTH=856 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5
			CELLSPACING=0>
			<COL WIDTH=86>
			<COL WIDTH=78>
			<COL WIDTH=87>
			<COL WIDTH=79>
			<COL WIDTH=1>
			<COL WIDTH=38>
			<COL WIDTH=65>
			<COL WIDTH=70>
			<COL WIDTH=88>
			<COL WIDTH=76>
			<COL WIDTH=74>
			<TR>
				<TD ROWSPAN=6 COLSPAN=2 WIDTH=174 HEIGHT=31
					style="text-align: center; vertical-align: middle;"><img
					src="./ShowPicture?id=${application.pictureid}" width="150px">
				</TD>
				<TD WIDTH=87>
					<P ALIGN=CENTER STYLE="font-weight: normal;">
						<FONT FACE="Arial Unicode MS, sans-serif">姓名</FONT>
					</P>
				</TD>
				<TD COLSPAN=3 WIDTH=139>
					<DIV ALIGN=CENTER>
						<P class="value">${application.name}</P>
					</DIV>
				</TD>
				<TD WIDTH=65>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">生日</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=168>
					<DIV ALIGN=CENTER>
						<P class="value">${application.birthday }</P>
					</DIV>
				</TD>
				<TD WIDTH=76>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">性別</FONT>
					</P>
				</TD>
				<TD WIDTH=74>
					<DIV ALIGN=CENTER>
						<P class="value">${application.gender}</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD WIDTH=87>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">通訊地址</FONT>
					</P>
				</TD>
				<TD COLSPAN=5 WIDTH=295>
					<DIV ALIGN=CENTER>
						<P class="value" style="text-align: left;">${application.address}</P>
					</DIV>
				</TD>
				<TD COLSPAN=3 WIDTH=257>
					<DIV ALIGN=LEFT>
						<P STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif"><SPAN
								LANG="en-US">(</SPAN></FONT><FONT FACE="Arial Unicode MS, sans-serif"><SPAN
								LANG="zh-CN">行動</SPAN></FONT><FONT FACE="Arial Unicode MS, sans-serif"><SPAN
								LANG="en-US">) ${application.cellphone }</SPAN></FONT>
						</P>
					</DIV>
					<DIV ALIGN=LEFT>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif"><SPAN
								LANG="en-US">(</SPAN></FONT><FONT FACE="Arial Unicode MS, sans-serif">辦公室</FONT><FONT
								FACE="Arial Unicode MS, sans-serif"><SPAN LANG="en-US">)
									${application.teloffice }</SPAN></FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD WIDTH=87>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">甄選科別</FONT>
					</P>
				</TD>
				<TD COLSPAN=5 WIDTH=295>
					<DIV ALIGN=CENTER>
						<P class="value">${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name
							}</P>
					</DIV>
				</TD>
				<TD WIDTH=88>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">身份證</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=160>
					<DIV ALIGN=CENTER>
						<P class="value">${application.pid}</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD WIDTH=87>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">服務學校(機構)</FONT>
					</P>
				</TD>
				<TD COLSPAN=5 WIDTH=295>
					<DIV ALIGN=CENTER>
						<P class="value">${application.school}</P>
					</DIV>
				</TD>
				<TD WIDTH=88>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">職稱</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=160>
					<DIV ALIGN=CENTER>
						<P class="value">${application.position}</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=2 WIDTH=87>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">教師證書</FONT>
					</P>
				</TD>
				<TD COLSPAN=5 WIDTH=295>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">科別</FONT>
					</P>
				</TD>
				<TD WIDTH=88>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">年月</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=160>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">證書字號</FONT>
					</P>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=5 WIDTH=295>
					<DIV ALIGN=CENTER>
						<P class="value">${application.license}</P>
					</DIV>
				</TD>
				<TD WIDTH=88>
					<DIV ALIGN=CENTER>
						<P class="value">${application.licensedate}</P>
					</DIV>
				</TD>
				<TD COLSPAN=2 WIDTH=160>
					<DIV ALIGN=CENTER>
						<P class="value">${application.licenseid}</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=4 WIDTH=86>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">學歷</FONT>
					</P>
				</TD>
				<TD COLSPAN=6 WIDTH=400>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">畢業學校</FONT>
					</P>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">修業起訖</FONT>
					</P>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=6 WIDTH=400>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">大學</FONT><FONT
								FACE="Arial Unicode MS, sans-serif"><SPAN LANG="en-US">(</SPAN></FONT><FONT
								FACE="Arial Unicode MS, sans-serif">院</FONT><FONT
								FACE="Arial Unicode MS, sans-serif"><SPAN LANG="en-US">)</SPAN></FONT><FONT
								FACE="Arial Unicode MS, sans-serif"> <span class="value">${application.bachelorschool}${application.bachelormajor}</span>
								系
							</FONT>
						</P>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.bachelorbegin}</span> 至 <span
								class="value">${application.bachelorend }</span>
							</FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=6 WIDTH=400>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">碩士 <span
								class="value">${application.masterschool
									}${application.mastermajor }</span> 所
							</FONT>
						</P>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.masterbegin }</span> 至 <span
								class="value">${application.masterend }</span></FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=6 WIDTH=400>
					<DIV ALIGN=CENTER>
						<P STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif"><SPAN
								LANG="zh-CN">博士</SPAN> <span class="value">${application.phdschool}${application.phdmajor}</span>
								所</FONT>
						</P>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.phdbegin }</span> 至 <span
								class="value">${application.phdend }</span></FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=4 WIDTH=86>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">經歷</FONT>
					</P>
				</TD>
				<TD COLSPAN=4 WIDTH=276>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">服務學校(機構)</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=114>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">職務</FONT>
					</P>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">服務起訖</FONT>
					</P>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=4 WIDTH=276>
					<DIV ALIGN=CENTER>
						<p class="value">${application.school1}</p>
					</DIV>
				</TD>
				<TD COLSPAN=2 WIDTH=114>
					<DIV ALIGN=CENTER>
						<p class="value">${application.position1}</p>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.school1begin}</span> 至 <span
								class="value">${application.school1end}</span>
							</FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=4 WIDTH=276>
					<DIV ALIGN=CENTER>
						<p class="value">${application.school2}</p>
					</DIV>
				</TD>
				<TD COLSPAN=2 WIDTH=114>
					<DIV ALIGN=CENTER>
						<p class="value">${application.position2}</p>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.school2begin}</span> 至 <span
								class="value">${application.school2end}</span>
							</FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=4 WIDTH=276>
					<DIV ALIGN=CENTER>
						<p class="value">${application.school3}</p>
					</DIV>
				</TD>
				<TD COLSPAN=2 WIDTH=114>
					<DIV ALIGN=CENTER>
						<p class="value">${application.position3}</p>
					</DIV>
				</TD>
				<TD COLSPAN=4 WIDTH=338>
					<DIV ALIGN=CENTER>
						<P LANG="zh-CN" STYLE="font-weight: normal">
							<FONT FACE="Arial Unicode MS, sans-serif">自 <span
								class="value">${application.school3begin}</span> 至 <span
								class="value">${application.school3end}</span>
							</FONT>
						</P>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=3 WIDTH=86>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">特殊教育相關證明</FONT>
					</P>
				</TD>
				<TD COLSPAN=7 WIDTH=481>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">參加研習、修畢學分、擔任特殊教育相關工作</FONT>
					</P>
				</TD>
				<TD COLSPAN=3 WIDTH=257>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">時間</FONT>
					</P>
				</TD>
			</TR>

			<TR>
				<TD COLSPAN=7 WIDTH=481>
					<DIV ALIGN=CENTER>
						<p class="value">${application.special1}</p>
					</DIV>
				</TD>
				<TD COLSPAN=3 WIDTH=257>
					<DIV ALIGN=CENTER>
						<p class="value">${application.special1date}</p>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=7 WIDTH=481>
					<DIV ALIGN=CENTER>
						<p class="value">${application.special2}</p>
					</DIV>
				</TD>
				<TD COLSPAN=3 WIDTH=257>
					<DIV ALIGN=CENTER>
						<p class="value">${application.special2date}</p>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=3 WIDTH=86>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">相關專長、檢定、證照</FONT>
					</P>
				</TD>
				<TD COLSPAN=10 WIDTH=748>
					<DIV ALIGN=CENTER>
						<p class="value">${application.license1}</p>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=10 WIDTH=748>
					<DIV ALIGN=CENTER>
						<p class="value">${application.license2}</p>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=10 WIDTH=748>
					<DIV ALIGN=CENTER>
						<p class="value">${application.license3}</p>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD ROWSPAN=4 WIDTH=85 HEIGHT=14>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">各類競賽獎勵紀錄</FONT>
					</P>
				</TD>
				<TD COLSPAN=6 WIDTH=369>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">事由</FONT>
					</P>
				</TD>
				<TD COLSPAN=5 WIDTH=368>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">獎勵及名次</FONT>
					</P>
				</TD>
			</TR>
			<c:forEach var="honor" items="${application.honors }">
				<TR>
					<TD COLSPAN=6 WIDTH=369>
						<P ALIGN=LEFT STYLE="font-weight: normal">
							${honor.reason }<BR>
						</P>
					</TD>
					<TD COLSPAN=5 WIDTH=368>
						<P ALIGN=LEFT STYLE="font-weight: normal">
							${honor.award }<BR>
						</P>
					</TD>
				</TR>
			</c:forEach>
			<TR>
				<TD COLSPAN=11 WIDTH=844>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">其他補充說明：</FONT>
					</P>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						${application.other }<BR>
					</P>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=11 WIDTH=844>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">附註：</FONT>
					</P>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						${application.note}<BR>
					</P>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=11 WIDTH=844>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">本人以上所填內容屬實，如有不實，願自負法律責任。</FONT>
					</P>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">填表人簽章：</FONT><FONT
							FACE="Arial Unicode MS, sans-serif"><SPAN LANG="en-US">
								(</SPAN></FONT><FONT FACE="Arial Unicode MS, sans-serif">簽名或蓋章</FONT><FONT
							FACE="Arial Unicode MS, sans-serif"><SPAN LANG="en-US">)</SPAN></FONT>
					</P>
					<P ALIGN=LEFT STYLE="font-weight: normal">
						<BR>
					</P>
				</TD>
			</TR>
			<TR>
				<TD WIDTH=86>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">初審</FONT>
					</P>
				</TD>
				<TD COLSPAN=2 WIDTH=176>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<BR>
					</P>
				</TD>
				<TD WIDTH=79>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">複審</FONT>
					</P>
				</TD>
				<TD COLSPAN=3 WIDTH=125>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<BR>
					</P>
				</TD>
				<TD WIDTH=70>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">報名費</FONT>
					</P>
				</TD>
				<TD WIDTH=88>
					<P ALIGN=CENTER STYLE="font-weight: normal">
						<BR>
					</P>
				</TD>
				<TD WIDTH=76>
					<P LANG="zh-CN" ALIGN=CENTER STYLE="font-weight: normal">
						<FONT FACE="Arial Unicode MS, sans-serif">准考證</FONT>
					</P>
				</TD>
				<TD WIDTH=74>
					<DIV ALIGN=CENTER>
						<p class="value">${application.seatid}</p>
					</DIV>
				</TD>
			</TR>
		</TABLE>
	</FORM>
	<P LANG="zh-CN" ALIGN=CENTER
		STYLE="margin-bottom: 0cm; font-weight: normal">
		<BR>
	</P>
</BODY>
</html>