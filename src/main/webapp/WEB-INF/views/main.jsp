<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ page trimDirectiveWhitespaces="true" %>  --%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"> -->
<!-- 부트스트랩 js링크 -->
<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
crossorigin="anonymous"></script> -->

<!-- 구글웹폰트 링크 -->
<style>
    @import
    url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap')
    ;

    @import
    url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

    @import
    url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;500&display=swap')
    ;
</style>

<script>
    function playstore() {
        window.open("https://play.google.com/")
    }
    function applestore() {
        window.open("https://www.apple.com/kr/app-store/")
    }
    // 베너 클릭 시 로그인 팝업 뜨도록
    function popup() {
        /* window.open("member/login_pop","login하기","width=400, height=380, top=10, left=10") */
        let popurl = "/member/login_pop";
        let popPosition = "width=400, height=380, top=10, left=10";
        console.log(popurl);
        console.log(popPosition);
        window.open(popurl, "팝업 열기", popPosition);
    }
</script>

<h1 style="margin: 150px 0 0 0;" id="space">어떤 공간을 찾고 계세요?</h1>



<!-- 부트스트랩 사용 -->
<div id="carouselExampleFade" class="carousel slide carousel-fade"
     data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/img/공간.jpg" class="d-block w-100" alt="..."
                 style="height: 650px;">
        </div>
        <div class="carousel-item">
            <img src="/img/스튜디오.jpg" class="d-block w-100" alt="..."
                 style="height: 650px;">
        </div>
        <div class="carousel-item">
            <div class="carousel-caption" style="margin-bottom: 120px;">
                <!-- carousel-caption이라는 예약어 따로 추가시킴 -->
                <p id="intro">Space Ground는 누구나 즐길 수 있는</p>
                <h1 id="info">복합 커뮤니티 & 공간소개 플랫폼입니다</h1>
                <div class="playstore">
                    <img src="img/google.png" alt="android" id="google"
                         onclick="playstore();"> <img src="img/apple.png" alt="apple"
                                                      id="apple" onclick="applestore();">
                </div>
            </div>
            <img src="/img/우주.jpg" class="d-block w-100" alt="..."
                 style="height: 650px;">
        </div>
    </div>
    <button class="carousel-control-prev" type="button"
            data-target="#carouselExampleFade" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
            class="sr-only">Previous</span>
    </button>
    <button class="carousel-control-next" type="button"
            data-target="#carouselExampleFade" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span
            class="sr-only">Next</span>
    </button>
</div>

<!-- 중앙 콘텐츠 4개 -->
<div class="container">

    <div class="main">


        <div class="section1"
             onclick="location.href='community?contents_number=1'"
             style="cursor: pointer;">
            <div class="con1" name="contents_number" value="1">
                <img class="con-img1" src="img/yoga.jpg" alt="contents1">
                <h2 id="main_h2">Active Class</h2>
            </div>
        </div>

        <div class="section2"
             onclick="location.href='community?contents_number=2'"
             style="cursor: pointer;">
            <div class="con2">
                <img class="con-img1" src="img/cafe.jpg" alt="contents2">
                <h2 id="main_h2">Shooting Class</h2>
            </div>
        </div>

        <div class="section3"
             onclick="location.href='community?contents_number=3'"
             style="cursor: pointer;">
            <div class="con3">
                <img class="con-img1" src="img/party.jpg" alt="contents3">
                <h2 id="main_h2">Party Class</h2>
            </div>
        </div>

        <div class="section4"
             onclick="location.href='community?contents_number=4'"
             style="cursor: pointer;">
            <div class="con4">
                <img class="con-img1" src="img/갤러리.jpg" alt="contents4">
                <h2 id="main_h2">Gallery Class</h2>
            </div>
        </div>
    </div>



</div>
</div>

<div id="banner">
    <video src="/img/light.mp4" muted autoplay loop>
    </video>
    <h1 class="banner_ad">지금 바로</h1>
    <h2 class="banner_ad2">4개의 클래스와 공간에서 즐겨보세요</h2>
    <%
        String member_id = (String) session.getAttribute("member_id");
        if(member_id != null) {
    %>

    <img alt="더보기" src="/img/더보기.png" id="more"
         onclick="location.href='searchAction?search='" style="cursor: pointer;">

    <%
    } else {

    %>
    <img alt="더보기" src="/img/더보기.png" id="more"
         style="cursor: pointer;"
         onclick="popup()">
    <%
        }

    %>



</div>

<div class="hits">
    <div class="container">
        <div class="aside">
            <div id="history">
                <div class="hitxt">
                    <h3 id="main_h3">BEST 게시글</h3>
                    <hr>

                    <div class="since1">
                        <h1>
                            <img src="/img/star.png" alt="star" style="width: 25px;">Active
                            community
                        </h1>

                        <div class="sintxt">

                            <table id="main_table" border=1 style="border-color: lightgray;">
                                <c:forEach var="best1" items="${community_seqs1}">
                                    <ul class="best-table">
                                        <tr style="list-style: none; cursor: pointer"
                                            onMouseOver="this.style.backgroundColor='#eeeeee'"
                                            onMouseOut="this.style.backgroundColor='#FFFFFF'"
                                            onclick="location.href='community_info?community_number=${best1.community_number}&contents_number=${best1.community_contents_number }'">
                                            <td>
                                                <li>${best1.community_title}</li>
                                            </td>
                                            <td>${best1.community_hit}</td>
                                        </tr>
                                    </ul>
                                </c:forEach>
                            </table>

                        </div>

                    </div>
                    <hr>

                    <div class="since1">
                        <h1>
                            <img src="/img/star.png" alt="star" style="width: 25px;">Shooting
                            community
                        </h1>

                        <div class="sintxt">
                            <table id="main_table" border=1 style="border-color: lightgray;">
                                <c:forEach var="best2" items="${community_seqs2}">
                                    <ul>
                                        <tr style="list-style: none; cursor: pointer"
                                            onMouseOver="this.style.backgroundColor='#eeeeee'"
                                            onMouseOut="this.style.backgroundColor='#FFFFFF'"
                                            onclick="location.href='community_info?community_number=${best2.community_number}&contents_number=${best2.community_contents_number }'">
                                            <td>
                                                <li>${best2.community_title}</li>
                                            </td>
                                            <td>${best2.community_hit}</td>
                                        </tr>
                                    </ul>
                                </c:forEach>
                            </table>
                        </div>

                    </div>
                    <hr>

                    <div class="since1">
                        <h1>
                            <img src="/img/star.png" alt="star" style="width: 25px;">Party
                            community
                        </h1>

                        <div class="sintxt">
                            <table id="main_table" border=1 style="border-color: lightgray;">
                                <c:forEach var="best3" items="${community_seqs3}">
                                    <ul>
                                        <tr style="list-style: none; cursor: pointer"
                                            onMouseOver="this.style.backgroundColor='#eeeeee'"
                                            onMouseOut="this.style.backgroundColor='#FFFFFF'"
                                            onclick="location.href='community_info?community_number=${best3.community_number}&contents_number=${best3.community_contents_number }'">
                                            <td>
                                                <li>${best3.community_title}</li>
                                            </td>
                                            <td>${best3.community_hit}</td>
                                        </tr>
                                    </ul>
                                </c:forEach>
                            </table>
                        </div>

                    </div>
                    <hr>

                    <div class="since1">
                        <h1>
                            <img src="/img/star.png" alt="star" style="width: 25px;">Gallery
                            community
                        </h1>
                        <div class="sintxt">
                            <table id="main_table" border=1 style="border-color: lightgray;">
                                <c:forEach var="best4" items="${community_seqs4}">
                                    <ul>
                                        <tr style="list-style: none; cursor: pointer"
                                            onMouseOver="this.style.backgroundColor='#eeeeee'"
                                            onMouseOut="this.style.backgroundColor='#FFFFFF'"
                                            onclick="location.href='community_info?community_number=${best4.community_number}&contents_number=${best4.community_contents_number }'">
                                            <td>
                                                <li>${best4.community_title}</li>
                                            </td>
                                            <td>${best4.community_hit}</td>
                                        </tr>
                                    </ul>
                                </c:forEach>
                            </table>
                        </div>
                        <hr>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<!-- 푸터 -->
<!--   <div class="footer" style="margin-top: 100px;">
<div>
<div class=" footer " style="height: 200px; padding-top: 30px;">
<div class="footer1" style="font-size: 13px;">
<a id="footera" href="" style="color: white;">이용약관 </a>
<a id="footera" href="" style="color: white;">개인정보처리방침</a>
<a id="footera" href="" style="color: white;">운영정책</a>
<a id="footera" href="" style="color: white;">콘텐츠산업진흥법에 의한 표시</a>
<a id="footera" href="" style="color: white;">고객문의</a>
</div>
<hr>
<div class="footer2">
<div id="infoft">
<p style="margin-bottom: 0; margin-top: 15px; font-size: 14px;">회사 : Space Ground, 주소 : 서울특별시 강남구 삼성로 414, 대표 : 이OO
</p>
<p style="margin-bottom: 0; font-size: 14px;"> 고객지원 : 010-0000-0000, FAX : 1599-8282(평일 오전10시 ~ 오후5시 - 점심1시 ~ 2시 ),
EMAIL : office@spacegound.net, 사업자등록번호 : 000-00-00000, 통신판매업 : 제2022-서울강남-00000호</p>
<p style="margin-bottom: 0; font-size: 12px;">Copyright SPACE Corp. All Rights Reserved</p>
</div>
<div id="underlogo" onclick="location.href='main.html'" >
Space Ground
</div>

</div>
</div>
</div>
</div>
-->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>

<!-- <script>
const $topBtn = document.querySelector(".movetopbtn");

//버튼 클릭 시 맨 위로 이동
$topBtn.onclick = () => {
window.scrollTo({ top: 0, behavior: "smooth" });
}
</script> -->

