<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<HEAD>
<meta charset="UTF-8">
<TITLE>${application.exam.title}准考證</TITLE>
<STYLE TYPE="text/css">
<!--
@page {
	margin-left: 0.95cm;
	margin-right: 0.63cm;
	margin-top: 1.27cm;
	margin-bottom: 0.95cm
}

body {
	font-size: 1.5em;
	font-family: 'Arial Unicode MS, sans-serif';
}

P {
	margin-bottom: 0.21cm;
	direction: ltr;
	color: #000000;
	widows: 0;
	orphans: 0
}

P.western {
	font-family: "Times New Roman", serif;
	font-size: 12pt;
	so-language: en-US
}

P.cjk {
	font-size: 1.5em;
	font-family: 'Arial Unicode MS, sans-serif';
	font-size: 12pt;
	so-language: zh-TW
}

P.ctl {
	font-family: "Times New Roman", serif;
	font-size: 10pt;
	so-language: ar-SA
}

.value {
	font-weight: bolder;
	font-size: 20px;
}

pre {
	white-space: pre-wrap; /* css-3 */
	white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-wrap: break-word; /* Internet Explorer 5.5+ */
}
-->
</STYLE>
</HEAD>
<BODY LANG="zh-TW" TEXT="#000000" DIR="LTR">
	<P CLASS="cjk" STYLE="margin-bottom: 0cm">
	<FORM NAME="表單">
		<CENTER>
			<TABLE DIR="LTR" WIDTH=668 BORDER=1 BORDERCOLOR="#000000"
				CELLPADDING=2 CELLSPACING=0>
				<COL WIDTH=120>
				<COL WIDTH=88>
				<COL WIDTH=141>
				<COL WIDTH=300>
				<TR>
					<TD COLSPAN=4 WIDTH=662 HEIGHT=104>
						<P CLASS="cjk" ALIGN=CENTER STYLE="margin-bottom: 0cm">
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=5
								STYLE="font-size: 20pt">${applicationScope.appConfig.schoolname}</FONT></FONT>
						</P>
						<P CLASS="cjk" ALIGN=CENTER
							style="font-size: 1.5em; font-family: 'Arial Unicode MS, sans-serif'">
							${application.exam.title} 准考證</P>
					</TD>
				</TR>
				<TR>
					<TD ROWSPAN=4 COLSPAN=2 WIDTH=212 HEIGHT=79>
						<P CLASS="cjk" ALIGN=CENTER
							STYLE="margin-left: 0.2cm; margin-right: 0.2cm">
							<img alt="" src="./ShowPicture?id=${application.pictureid }"
								width="150px">
						</P>
					</TD>
					<TD WIDTH=141>
						<P CLASS="cjk" ALIGN=CENTER>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">姓 名</FONT></FONT>
						</P>
					</TD>
					<TD WIDTH=300>
						<DIV ALIGN=CENTER>
							<P class="value">${application.name}</P>
						</DIV>
					</TD>
				</TR>
				<TR>
					<TD WIDTH=141>
						<P CLASS="cjk" ALIGN=CENTER>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">甄選科別</FONT></FONT>
						</P>
					</TD>
					<TD WIDTH=300>
						<P CLASS="cjk">
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name
									}</FONT></FONT>
						</P>
					</TD>
				</TR>
				<TR>
					<TD WIDTH=141>
						<P CLASS="cjk" ALIGN=CENTER>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">准考證號碼</FONT></FONT>
						</P>
					</TD>
					<TD WIDTH=300>
						<DIV ALIGN=CENTER>
							<P CLASS="cjk">

								<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
									STYLE="font-size: 16pt"> <span class="value">${application.seatid}</span>

								</FONT></FONT>
							</P>
						</DIV>
					</TD>
				</TR>
				<TR>
					<TD WIDTH=141>
						<P CLASS="cjk" ALIGN=CENTER>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">考試地點</FONT></FONT>
						</P>
					</TD>
					<TD WIDTH=300>
						<P CLASS="cjk" ALIGN=JUSTIFY>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4
								STYLE="font-size: 16pt">${application.exam.examroom}</FONT></FONT>
						</P>
					</TD>
				</TR>
				<TR>
					<TD ROWSPAN=2 WIDTH=120 HEIGHT=100>
						<P CLASS="cjk" ALIGN=CENTER>
							<FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>應試說明：</FONT></FONT>
						</P>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN=4 WIDTH=446><div style="font-size: 1.3em;"><pre>${application.exam.formnote }</pre></div>
					</TD>
				</TR>
			</TABLE>
		</CENTER>
	</FORM>
	<FONT FACE="Times New Roman, serif"><SPAN LANG="en-US"><BR></SPAN></FONT>
	<BR>
	<P CLASS="cjk" STYLE="margin-bottom: 0cm">
		<BR>
	</P>
	<P CLASS="cjk" STYLE="margin-bottom: 0cm">
		<BR>
	</P>
	<P CLASS="cjk" STYLE="margin-bottom: 0cm">
		<BR>
	</P>
	<P CLASS="cjk" STYLE="margin-bottom: 0cm">
		<BR>
	</P>

</BODY>
</HTML>