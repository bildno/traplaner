<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
  <title>Title</title>
  <style>
    .container {
      height: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
    .mypage_section {
      display: flex;
      justify-content: center;
    }
    .mypage_section1 {
      display: inline-block;
      margin-right: 50px;
      text-align: center;
      border: 1px solid rgb(238, 238, 238);
      border-radius: 5px;
      margin-bottom: 50px;
      height: 700px;
    }
    .img_box {
      height: 50%;
      width: 50%;
      background-color: blue;
    }
    .manage_box {
      width: 500px;
      display: flex;
      flex-direction: column;
    }
    .cal {
      width: 700px;
    }
    #mypage_h1 {
      margin-bottom: 100px;
    }
    .profile_img {
      width: 200px;
      height: 200px;
    }
    .mypage_section2{
      width: 800px;
    }
    .con22 input{
      /*border-radius: 5px;*/
      width: 80%;
      margin: 5px 0 5px 20px;
      cursor: pointer;
      /*border: 1px solid rgb(238, 238, 238);*/
      border: none;
      text-align: center;
      align-content: center;
    }
    td {
      text-align: center;
    }
    .searchOption{
      width: 100px;
      border: 2px solid lightgray;
      border-radius: 5px;
    }
    .manage_box a{
      margin-bottom: 20px;
      text-decoration: none;
      color: black;
    }
  </style>
</head>
<body>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script src="fullcalendar/lib/locales-all.js"></script>





<%@ include file="../header.jsp"%>
<div class="container">
  <h1 id="mypage_h1">내 게시물</h1>
  <div class="mypage_section">
   <div class="mypage_section1">
            <c:if test="${login.profile == null}">
                <img src="/assets/img/anonymous.jpg" alt="프사" style="width: 30px" class="rounded-pill"/>
            </c:if>
            <c:if test="${login != null && login.profile != null}">
                <img src="/display${login.profile}" alt="프사"
                     style="width: 250px; border-radius: 50%; margin-bottom: 50px; margin-top: 30px" class="rounded-pill"/>
            </c:if>
      <div class="manage_box">
        <a href="">계정관리</a><a  href="/my-page/mytravelboard/${login.nickName}">내 게시물</a
      ><a href="/my-page/mytravel/${login.id}">나의 여행</a>
        <a   href="/my-page/${login.id}">여행일정</a><a style="font-weight: bold" href="/my-page/favorite/${login.id}">좋아요한 게시물</a>
      </div>
    </div>
    <div class="mypage_section2">
      <div class="con22">
        <select name="saerchOption" class="searchOption">
          <option value="pastOrder">과거순</option>
          <option value="up-to-date-order">최신순</option>
        </select>
        <table>
          <tr>
            <th style="width: 5%;"><span>번호</span></th>
            <th style="width: 70%;">게시글 제목</th>
            <th style="width: 10%;"><span>작성자</span></th>
            <th style="width: 15%;"><span>작성일</span></th>
          </tr>




          <c:forEach var="list" items="${list}">
            <tr>
              <td><span>${list.id}</span></td>
              <td><input type="text" name="con_text" id="con_text"
                         value="${list.title}"
                         readonly></td>
                         <td><span>${list.memberNickName}</span></td>
              <td><span>${list.formatDate}</span></td>



            </tr>
          </c:forEach>


        </table>
      </div>
    </div>
  </div>
</div>


</body>
</html>
