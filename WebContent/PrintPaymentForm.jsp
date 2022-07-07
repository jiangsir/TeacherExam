<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>${applicationScope.appConfig.schoolname }繳款單</TITLE>
    <STYLE TYPE="text/css">
        @page {
            margin-left: 2cm;
            margin-right: 2cm;
            margin-top: 1cm;
            margin-bottom: 1cm
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
            font-size: medium;
        }

        .value {
            margin-bottom: 0cm;
            font-family: Arial Unicode MS, sans-serif;
            font-weight: bolder;
            text-align: center;
            font-size: medium;
        }

        .left {
            text-align: left;
        }

    </STYLE>
</HEAD>
<BODY DIR="LTR">
    <!-- ********************************************************************************************* -->
    <div class="title">${applicationScope.appConfig.schoolname}<br />${application.exam.title
        }繳款單</div>
    <div class="title">第一聯：繳款人收執聯</div>
    <TABLE WIDTH=840 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5 CELLSPACING=0>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <TR>
            <TD COLSPAN=6>
                <div class="key">繳款項目：<span class="value">${application.exam.title}</span></div>
            </TD>
            <TD COLSPAN=6>
                <div class="key">報考科別：<span
                        class="value">${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name}</span>
                </div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">繳款期限：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${application.exam.deadline }</div>
            </TD>
            <TD ROWSPAN=3 COLSPAN=8>
                <div class="key">虛擬帳號</div>
                <br />
                <div class="value">
                    <img src="./barcode?type=Code39&data=${application.bankaccount}&height=33&width=2">
                </div>
                <div class="value">*${application.bankaccount}*</div>
                <p></p>
                <p></p>
                <div class="value">
                    <img src="./barcode?type=Code39&data=${application.exam.money}&height=33&width=2">
                </div>
                <div class="key">新台幣 <span class="value">${application.exam.money}</span>元整</div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">收款銀行：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${applicationScope.appConfig.bankname}</div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">姓名：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${application.name }</div>
            </TD>
        </TR>
        <TR>
            <TD COLSPAN=12>
                <div class="key left">繳款方式一、臨櫃繳款：臺銀臨櫃繳款者須自付手續費。</div>
                <div class="key left" style="margin-left: 2em;">可攜帶本繳款單至「<span
                        class="value">${applicationScope.appConfig.bankname}</span>」臨櫃繳款。</div>
                <div class="key left">繳款方式二、金融卡(ATM)轉帳繳款：</div>
                <div class="key left" style="margin-left: 2em;">大略步驟：</div>
                <div class="key left" style="margin-left: 2em;">插入金融卡(不同設備會略有不同)
                    → 輸入密碼 → 請選擇『轉帳』交易 → 輸入銀行代號 → 輸入繳款之 14 位『虛擬帳號』<span class="value">${application.bankaccount}</span>→
                    輸入繳款金額 →
                    核對輸入資料正確無誤後，請按『確認』完成轉帳繳款 → 取回明細表。<span class="value"></span></div>
                <div class="key left">繳款方式三、跨行匯款：</div>
                <div class="key left" style="margin-left: 2em;">收款銀行：<span
                        class="value">${applicationScope.appConfig.bankname}</span></div>
                <div class="key left" style="margin-left: 2em;">收款人帳號：<span
                        class="value">${application.bankaccount}</span></div>
                <div class="key left" style="margin-left: 2em;">收款人戶名：<span
                        class="value">${applicationScope.appConfig.bankhuming}</span></div>
                <div class="key left">繳款方式四、各種行動支付、信用卡繳費：</div>
                <div class="key left" style="margin-left: 2em;">虛擬帳戶：<span
                        class="value">${application.bankaccount}</span></div>
            </TD>
        </TR>
    </TABLE>
    <p></p>
    <TABLE WIDTH=833 BORDER=0 CELLPADDING=5 CELLSPACING=0>
        <COL WIDTH=129>
        <COL WIDTH=129>
        <COL WIDTH=129>
        <COL WIDTH=129>
        <COL WIDTH=129>
        <COL WIDTH=129>
        <TR>
            <TD>
                <div class="key">主辦出納</div>
            </TD>
            <TD>
                <div class="value left">${applicationScope.appConfig.chuna }</div>
            </TD>
            <TD>
                <div class="key">主辦主計</div>
            </TD>
            <TD>
                <div class="value left">${applicationScope.appConfig.zhuji}</div>
            </TD>
            <TD>
                <div class="key">校長</div>
            </TD>
            <TD>
                <div class="value left">${applicationScope.appConfig.principal}</div>
            </TD>
        </TR>
    </TABLE>
    <P LANG="zh-CN" STYLE="margin-bottom: 0cm">
        <BR>
    </P>
    <hr style="width: 100%; border-style: dashed; border-width: 2px;">
    <div class="title">${applicationScope.appConfig.schoolname}<br />${application.exam.title
        }繳款單</div>
    <div class="title">第二聯：代收單位留存聯</div>
    <TABLE WIDTH=840 BORDER=1 BORDERCOLOR="#000000" CELLPADDING=5 CELLSPACING=0>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <COL WIDTH=70>
        <TR>
            <TD COLSPAN=6>
                <div class="key">繳款項目：<span class="value">${application.exam.title}</span></div>
            </TD>
            <TD COLSPAN=6>
                <div class="key">報考科別：<span
                        class="value">${application.subject.zhengshidaili}${application.subject.nianduan}${application.subject.name}</span>
                </div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">繳款期限：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${application.exam.deadline }</div>
            </TD>
            <TD ROWSPAN=3 COLSPAN=8>
                <div class="key">虛擬帳號</div>
                <br />
                <div class="value">
                    <img src="./barcode?type=Code39&data=${application.bankaccount}&height=33&width=2">
                </div>
                <div class="value">*${application.bankaccount}*</div>
                <p></p>
                <p></p>
                <div class="value">
                    <img src="./barcode?type=Code39&data=${application.exam.money}&height=33&width=2">
                </div>
                <div class="key">新台幣 <span class="value">${application.exam.money}</span>元整</div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">收款銀行：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${applicationScope.appConfig.bankname}</div>
            </TD>
        </TR>
        <TR>
            <TD colspan="2">
                <div class="key">姓名：</div>
            </TD>
            <TD colspan="2">
                <div class="value">${application.name }</div>
            </TD>
        </TR>
    </TABLE>
    <p></p>
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
            <TD>
                <div class="key">主管</div>
            </TD>
            <TD>
            </TD>
            <TD>
                <div class="key">會計</div>
            </TD>
            <TD>
            </TD>
            <TD>
                <div class="key">記帳</div>
            </TD>
            <TD>
            </TD>
            <TD>
                <div class="key">驗印</div>
            </TD>
            <TD>
            </TD>
            <TD>
                <div class="key">經辦</div>
            </TD>
            <TD>
            </TD>
        </TR>
    </TABLE>
</BODY>
</HTML>