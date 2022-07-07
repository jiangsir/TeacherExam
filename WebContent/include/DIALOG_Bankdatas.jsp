<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="bankdata-dialog" title="繳費明細！" style="display: none;">
    <c:forEach var="bankdata" items="${bankdatas}" varStatus="varstatus">
        <div>
            虛擬帳戶：${bankdata.bot.RCPTId}<br /> 交易日期：${bankdata.bot.trnDt}<br />
            繳款時間：${bankdata.bot.trnTime}<br />
            繳費金額：${bankdata.bot.curAmt}<br /> 代收類別：${bankdata.bot.code}<br />
            備註資料：${bankdata.bot.userData}<br />
            代收單位：${bankdata.bot.CName}<br /> 繳款人姓名：${bankdata.bot.PName}<br />
            繳費方式：${bankdata.bot.trnType}<br />
            繳款日期：${bankdata.bot.SITDate}<br /> 代收行：${bankdata.bot.CLLBR}<br />
        </div>
    </c:forEach>
</div>