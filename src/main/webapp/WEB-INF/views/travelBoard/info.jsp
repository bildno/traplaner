<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
     <div id="wrap" class="form-container">
          <h1>제목: ${b.travelId}</h1>
          <h2># 작성일자: ${b.writeDate}</h2>
          <h2># 작성자: ${b.memberNickName}</h2>
          <label for="content"><h1>내용</h1></label>
          <div id="content">${b.content}</div>
          <div class="buttons">
            <button
              class="list-btn"
              type="button"
              onclick="location.href='/board/list?pageNo=${s.pageNo}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}'"
            >
              목록
            </button>
          </div>
</body>
</html>