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
        <%
            String userName = "방문자";
            // 요청 객체에게 쿠키를 달라고 하면 모든 쿠키가 담긴 배열리 리턴.
            // 클라이언트에게 쿠키를 검사
            Cookie[] cookies = request.getCookies();
            for(Cookie c : cookies) {
                if (c.getName().equals("login")) {
                    userName = c.getValue();
                }
            }
        %>
        <h1><%= userName %> 님 안녕하세요!</h1>
                <header>
                    <a href="http://localhost:8181">로고</a>
                    <a href="http://localhost:8181/travelboard">게시판</a>
                    <a href="members/myPage">마이페이지</a>
                    <a href="members/sign-in">로그인</a>
                    <a href="http://localhost:8181/home">로그아웃</a>
                </header>
                <br>
                <section>
                    <h2>best3</h2>
                </section>
                <br>
                <section>
                    <h3>MY TRAPLAN</h3> <br>
                    <a href="http://localhost:8181/travelplan">여행계획작성</a>
                </section>

            <button>커밋 테스트용 버튼</button>
</body>
</html>