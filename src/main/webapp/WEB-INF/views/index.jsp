<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TRAPLAN(main)</title>
  </head>
  <style>
  /*
    .container{
        width: 640px;
        border: 4px solid white;
        display: flex;
        flex-flow: row nowrap;
    }

    .profile-box {
        width: 100px;
        height: 100px;
        background-color: rgb(191, 197, 209);
        border-radius: 10px;
        font-size: 30px;
        color: white;
    }

    .intro-text {
        width: 100px;
        height: 100px;
        background-color: rgb(191, 197, 209);
        border-radius: 10px;
        font-size: 10px;
        color: white;
    }
    
    .item {
        width: 100px;
        height: 100px;
        background-color: white;
        border-radius: 10px;
        font-size: 10px;
        color: white;
        align-content: center;
    }
*/
    </style>
  <body>
        
    <div>
    <!-- 프로필 사진-->
    <div class="profile-box">
        <c:if test="${login.profile == null}">
            <img src="/assets/img/anonymout.jpg" alt="프사" />
        </c:if>

        <c:if test="${login != null && login.profile != null}">
            <c:choose>
                <c:when test="${login.loginMethod == 'COMMON'}">
                    <img src="/display/${login.profile}" alt="프사">
                </c:when>
                <c:otherwise>
                    <img src="${login.profile}" alt="프사">
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
    <div>
    <h2 class="intro-text">
        Welcome!!! ${sessionScope.login == null ? '': login.name}
    </h2>
</div>
</div>


    <div class="container">
                <div class="item"><a href="/">로고</a></div>
                <div class="item"><a href="/travelboard">게시판(공유여행목록)</a></div>
                <div class="item"><a href="/my-page">마이페이지</a></div>
                <div class="item"><a href="/members/sign-in">로그인</a></div>
                <div class="item"><a href="/members/sign-out">로그아웃</a></div>
    </div>

    <div class="container">
        <div class="item"><a href="/travelplan">여행계획작성</a></div>
</div>

  </body>
</html>
