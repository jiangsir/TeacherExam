<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-hover">
    <thead>
        <tr>
            <th>科別</th>
            <th>已報名人數</th>
            <c:if test="${exam.After_Startline()}">
                <th>已繳費人數</th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subject" items="${exam.subjects}">
            <tr>
                <td>
                    ${subject.zhengshidaili}${subject.nianduan}${subject.name}</td>
                <td>${exam.getCountBySubjectid(subject.id)}</td>
                <c:if test="${exam.After_Startline() }">
                    <td>${exam.getPaidCountBySubjectid(subject.id)}</td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>
