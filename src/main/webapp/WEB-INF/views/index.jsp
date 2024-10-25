<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="/assets/img/favicon.ico">
    <title>TRAPLAN(Main)</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <!------------------ 네비게이션바/메뉴 ----------------------->
    <nav class="navbar navbar-expand-sm bg-body-tertiary fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="/">TRAPLAN</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/travelboard">게시판</a>
            </li>
            <c:if test="${login.id != null}">
              <li class="nav-item">
                <a class="nav-link" href="/my-page/${login.id}">마이페이지</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/members/sign-out">로그아웃</a>
              </li>
            </c:if>
            <c:if test="${login.id == null}">
              <a class="nav-link" href="/members/sign-in">로그인</a>
              <a class="nav-link" href="/members/sign-up">회원가입</a>
            </c:if>
            <li class="nav-item">
              <!--a class="nav-link disabled" href="/members/sign-in" aria-disabled="true">로그인</a-->

            </li>
          </ul>
        </div>
      </div>
    </nav>



    <!------------------ 추천 여행지 carousel ----------------------->
    <div id="demo" class="carousel slide mt-5" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button data-bs-target="#demo" data-bs-slide-to="1"></button>
        <button data-bs-target="#demo" data-bs-slide-to="2"></button>
        <button data-bs-target="#demo" data-bs-slide-to="3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/assets/img/경주-800x320.jpg" alt="" class="d-block w-100" />
        </div>
        <div class="carousel-item">
          <img src="/assets/img/부산-800x320.jpg" alt="" class="d-block w-100" />
        </div>
        <div class="carousel-item">
          <img src="/assets/img/남이섬-800x320.jpg" alt="" class="d-block w-100" />
        </div>
        <div class="carousel-item">
          <img src="/assets/img/홍콩-800x320.jpg" alt="" class="d-block w-100" />
        </div>
        <button class="carousel-control-prev" data-bs-target="#demo" data-bs-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" data-bs-target="#demo" data-bs-slide="next">
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>
    </div>


    <!--------------------- My여행추가, My여행목록 ----------------------->
    <div class="container mt-4">
      <div class="row">
        <div class="col-sm-4">
          <div class="card p-1">
            <a href="/travelplan">
              <img src="/assets/img/add-250x140.jpg" alt="" class="card-img-top img-fluid" />
            </a>
            <!--
            <div class="card-img-overlay">
              <h4 class="card-title text-primary">여행추가</>
            </div>
            -->
          </div>
        </div>


        <div class="col-sm-4">
          <div class="card p-1">
            <img src="/assets/img/홍콩-250x140.jpg" alt="" class="card-img-top" />
            <div class="card-img-overlay">
              <h4 class="card-title text-white">홍콩</h4>
              <p class="card-text text-white">침사추이, 몽콕, 스탠리, 빅토리아피크, 망고쥬스</p>
            </div>
          </div>
        </div>


        <div class="col-sm-4">
          <div class="card p-1">
            <img src="/assets/img/부산-250x140.jpg" alt="" class="card-img-top" />
            <div class="card-img-overlay">
              <h4 class="card-title text-white">부산-마이뭇다아이가</h4>
              <p class="card-text text-white">해운대, 광안리, 국제시장, 자갈치시장, 서면</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

