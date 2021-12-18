<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<meta charset="UTF-8">
<TITLE></TITLE>
<STYLE TYPE="text/css">
<!--
@page {
	margin-left: 2cm;
	margin-right: 2cm;
	margin-top: 1.8cm;
	margin-bottom: 0.73cm
}

P {
	margin-bottom: 0.21cm;
}

TD P {
	margin-bottom: 0cm
}

table {
	margin: auto;
	text-align: center;
}
-->
</STYLE>
</HEAD>
<BODY DIR="LTR">

    <!-- ********************************************************************************************* -->
    <P LANG="zh-CN" ALIGN=CENTER
        STYLE="margin-bottom: 0cm; line-height: 100%">
        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=5><SPAN
                LANG="en-US">${applicationScope.appConfig.schoolname} </SPAN></FONT></FONT><FONT
            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=5>教師甄選繳款單</FONT></FONT>
    </P>
    <P LANG="zh-CN" ALIGN=CENTER
        STYLE="margin-bottom: 0cm; line-height: 0.5cm">
        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>第一聯：繳款人收執聯</FONT></FONT>
    </P>
    <CENTER>
        <TABLE WIDTH=857 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5
            CELLSPACING=0>
            <COL WIDTH=101>
            <COL WIDTH=154>
            <COL WIDTH=244>
            <COL WIDTH=316>
            <TR>
                <TD COLSPAN=3 WIDTH=519 HEIGHT=67>
                    <P ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="zh-CN">繳款項目：</SPAN></FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.exam.title}</SPAN></FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=316>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>報考科別：</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name}</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=60>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>繳款期限</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.exam.deadline }</SPAN></FONT></FONT>
                    </P>
                </TD>
                <TD ROWSPAN=3 COLSPAN=2 WIDTH=570>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>虛擬帳號</FONT></FONT><br />
                        <img
                            src="./barcode?type=Code39&data=${application.bankaccount}&height=33&width=2">
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">*${application.bankaccount}*</SPAN></FONT></FONT>
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER STYLE="margin-bottom: 0cm">
                        <BR> <img
                            src="./barcode?type=Code39&data=${application.exam.money}&height=33&width=2">
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>新台幣
                        </FONT></FONT><FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US"> ${application.exam.money}</SPAN></FONT></FONT><FONT FACE="Arial Unicode MS, sans-serif"><FONT
                            SIZE=4>元整</FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=60>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>收款銀行</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${applicationScope.appConfig.bankname}</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=60>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>姓名</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.name }</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD COLSPAN=4 WIDTH=845 ALIGN=LEFT>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>繳款方式一、臨櫃繳款：臺銀臨櫃繳款者須自付手續費10元。</FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>可攜帶本繳款單至「</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${applicationScope.appConfig.bankname}</SPAN></FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>」臨櫃繳款。</FONT></FONT>
                    </P>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>繳款方式二、金融卡</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">(ATM)</SPAN></FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>轉帳繳款：</FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>步驟如下：</FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>插入金融卡
                                → 輸入密碼 → 請選擇『轉帳』交易 → 輸入銀行代號 → 輸入繳款之 </FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">14</SPAN></FONT></FONT><FONT FACE="Arial Unicode MS, sans-serif"><FONT
                            SIZE=4>位『虛擬帳號』→ 輸入繳款金額 → 核對輸入資料正確無誤後，請按『確認』完成轉帳繳款 → 取回明細表。</FONT></FONT>
                    </P>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>繳款方式三、跨行匯款：</FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>收款銀行：</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${applicationScope.appConfig.bankname}</SPAN></FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>收款人帳號：</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.bankaccount}</SPAN></FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>收款人戶名：</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${applicationScope.appConfig.bankhuming}</SPAN></FONT></FONT>
                    </P>
                    <P LANG="zh-CN" STYLE="margin-left: 1.25cm">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>匯款人請填寫『考生姓名』，並請註明報考科別。</FONT></FONT>
                    </P>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
    <CENTER>
        <TABLE WIDTH=833 BORDER=0 CELLPADDING=5 CELLSPACING=0>
            <COL WIDTH=129>
            <COL WIDTH=129>
            <COL WIDTH=129>
            <COL WIDTH=129>
            <COL WIDTH=129>
            <COL WIDTH=129>
            <TR>
                <TD WIDTH=129 HEIGHT=53>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>主辦出納</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=129>
                    <P ALIGN=LEFT>
                        ${applicationScope.appConfig.chuna }<BR>
                    </P>
                </TD>
                <TD WIDTH=129>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>主辦主計</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=129>
                    <P ALIGN=LEFT>
                        ${applicationScope.appConfig.zhuji}<BR>
                    </P>
                </TD>
                <TD WIDTH=129>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>校長</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=129>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        ${applicationScope.appConfig.principal}<BR>
                    </P>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
    <P LANG="zh-CN" STYLE="margin-bottom: 0cm">
        <BR>
    </P>
    <hr style="width: 100%; border-style: dashed; border-width: 2px;">
    <P LANG="zh-CN" ALIGN=CENTER
        STYLE="margin-bottom: 0cm; line-height: 100%">
        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=5><SPAN
                LANG="en-US">${applicationScope.appConfig.schoolname} </SPAN></FONT></FONT><FONT
            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=5>教師甄選繳款單</FONT></FONT>
    </P>
    <P LANG="zh-CN" ALIGN=CENTER
        STYLE="margin-bottom: 0cm; line-height: 0.5cm">
        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>第二聯：代收單位留存聯</FONT></FONT>
    </P>
    <CENTER>
        <TABLE WIDTH=857 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5
            CELLSPACING=0>
            <COL WIDTH=101>
            <COL WIDTH=154>
            <COL WIDTH=244>
            <COL WIDTH=316>
            <TR>
                <TD COLSPAN=3 WIDTH=519 HEIGHT=68>
                    <P ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="zh-CN">繳款項目：</SPAN></FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.exam.title}</SPAN></FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=316>
                    <P LANG="zh-CN" ALIGN=LEFT>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>報考科別：</FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">
                                    ${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name}</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=62>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>繳款期限</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.exam.deadline }</SPAN></FONT></FONT>
                    </P>
                </TD>
                <TD ROWSPAN=3 COLSPAN=2 WIDTH=570>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>虛擬帳號</FONT></FONT><BR>
                        <img
                            src="./barcode?type=Code39&data=${application.bankaccount}&height=33&width=2">
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER STYLE="margin-bottom: 0cm">
                        *${application.bankaccount}*<BR>
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER STYLE="margin-bottom: 0cm">
                        <BR> <img src="./barcode?type=Code39&data=${application.exam.money}&height=33&width=2">
                    </P>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>新台幣
                        </FONT></FONT><FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.exam.money} </SPAN></FONT></FONT><FONT
                            FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>元整</FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=62>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>收款銀行</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${applicationScope.appConfig.bankname}</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
            <TR>
                <TD WIDTH=101 HEIGHT=62>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>姓名</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=154>
                    <P LANG="zh-CN" ALIGN=CENTER>
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4><SPAN
                                LANG="en-US">${application.name }</SPAN></FONT></FONT>
                    </P>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
    <CENTER>
        <TABLE WIDTH=853 BORDER=0 CELLPADDING=0 CELLSPACING=0>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <COL WIDTH=85>
            <TR>
                <TD WIDTH=85 HEIGHT=63>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>主管</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P>
                        <BR>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>會計</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P>
                        <BR>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>記帳</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P>
                        <BR>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>驗印</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P>
                        <BR>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P LANG="zh-CN">
                        <FONT FACE="Arial Unicode MS, sans-serif"><FONT SIZE=4>經辦</FONT></FONT>
                    </P>
                </TD>
                <TD WIDTH=85>
                    <P>
                        <BR>
                    </P>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
    <P LANG="zh-CN" STYLE="margin-bottom: 0cm">
        <BR>
    </P>


</BODY>
</HTML>