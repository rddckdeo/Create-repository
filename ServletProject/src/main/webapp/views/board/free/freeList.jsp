<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="../../common/head.jsp" %>
</head>
<body>
  <%@ include file="../../common/header.jsp" %>
  <%@ include file="../../common/nav.jsp" %>
  
    <section>
        <div class="table-container">
          <table class="table table-hover" id="content">
          <thead>
            <tr>
              <th>글번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>조회수</th>
            </tr>
          </thead>
          <tbody class="table-group-divider" id="table-content">
            <c:choose>
            	<c:when test="${empty list} }">
            		<tr>
            			<td colspan ="5" class = "text-center">등록된 글이 없습니다.</td>
            		</tr>
            	</c:when>
            	<c:otherwise>
            		<c:forEach var="item" items ="${list}">
            			 <tr>
            			 	<td>${item.idx}</td>
            			 	<td>${item.title}</td>
            			 	<td>${item.writer}</td>
            			 	<td>${item.inDate}</td>
            			 	<td>${item.views}</td>
            			 </tr>
            		</c:forEach>
            	</c:otherwise>
            
            </c:choose>
          </tbody>
        </table>
            <button class="add-button" onclick="window.location.href = 'enrollForm.html'">등록</button>
        </div>
  </section>

    <%@ include file="../../common/footer.jsp" %>
</body>
</html>