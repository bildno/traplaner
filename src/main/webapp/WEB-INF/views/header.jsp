<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    /* 전체 페이지의 기본 스타일 설정 */
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
    }

    /* 헤더 스타일 - 로고와 메뉴 항목을 포함한 상단바 */
    .traplan-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      background-color: white;
      padding: 10px 20px;
      box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    }

    /* 로고 이미지 스타일 */
    .traplan-logo {
      height: 40px;
      cursor: pointer;
    }

    /* 메뉴 항목 스타일 - 헤더 내 각 메뉴를 정렬 */
    .traplan-menu {
      display: flex;
      gap: 5px;
      font-size: 10px;
      align-items: center;
    }

    /* 메뉴 링크 스타일 설정 */
    .traplan-menu a {
      text-decoration: none;
      color: black;
      transition: color 0.3s;
    }

    /* 메뉴 링크에 마우스를 올렸을 때 색상 변경 */
    .traplan-menu a:hover {
      color: #1cf10d;
    }

    /* 헤더의 상하 여백 및 양쪽 여백 수정 가능 */
    .traplan-header-padding {
      padding: 30px 200px;
    }

    /* 반응형 디자인 - 화면이 600px 이하일 때 스타일 변경 */
    @media screen and (max-width: 600px) {
      .traplan-header {
        flex-direction: column;
        align-items: flex-start;
      }
      .traplan-menu {
        margin-top: 10px;
      }
    }

    /* 프로필 이미지 스타일 설정 */
    .traplan-profile-img {
      max-height: 30px;
      border-radius: 50%;
    }
  </style>
</head>
<body>
<div class="traplan-header traplan-header-padding">
  <!-- 로고 이미지 클릭 시 메인 페이지 이동 -->
  <a href="/">
    <img src="./image/logo.png" alt="TRAPLAN" class="traplan-logo">
  </a>

  <!-- 메뉴 항목 -->
  <div class="traplan-menu">
    <!-- 여행 게시판 링크 -->
    <a class="nav-link" href="게시판 링크">게시판</a>

    <!-- 로그인 상태에 따른 메뉴 표시 - 가로로 나란히 정렬 -->
    <c:if test="${login == null}">
      <a href="sign-in.jsp" class="nav-link">로그인</a>
    </c:if>

    <c:if test="${login != null}">
      <div class="d-flex align-items-center gap-3">
        <a href="/my-page/${login.id}" class="nav-link">마이페이지</a>
        <a href="/members/sign-out" class="nav-link">로그아웃</a>
        <!-- 프로필 이미지 표시 -->
        <img src="./image/logo.png" alt="Profile" class="traplan-profile-img">
      </div>
    </c:if>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
