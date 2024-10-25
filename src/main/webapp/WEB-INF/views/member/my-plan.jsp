<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      width: 70%;
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

<%@ include file="../header.jsp"%>


<div class="container">
  <h1 id="mypage_h1">나의 여행</h1>
  <div class="mypage_section">
    <div class="mypage_section1">
      <img
              class="profile_img"
              src="data:image/png;base64,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"
              alt=""
      />
      <div class="manage_box">
        <a href="">계정관리</a><a href="/my-page/mytravelboard/${login.nickName}">내 게시물</a
      ><a  style="font-weight: bold" href="/my-page/mytravel/${login.id}">나의 여행</a>
        <a href="/my-page/${login.id}">여행일정</a><a href="/mypage/favorite/${login.id}">좋아요한 게시물</a>
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
            <th style="width: 50%;"><span>게시글 제목</span></th>
            <th style="width: 25%;"><span>여행일</span></th>
            <th style="width: 10%;"><span>공유여부</span></th>
          </tr>
          <c:forEach var="travels" items="${list}">
            <tr>
              <td><span>${travels.id}</span></td>
              <td><input type="text" name="con_text"
                         value="${travels.title}"
                         onclick="location.href='community_info?community_number=&contents_number='"
                         readonly></td>
              <td>
              <span>${travels.startDate} ~ ${travels.endDate}</span>
              <td>
              <input type="hidden" value="${travels.share}" id="shareValue">
              <c:choose>
                <c:when test="${travels.share == 'true'}">
                  <input type="checkbox" id="shareIs" checked onchange="shareChange(${travels.id})">
                </c:when>
                <c:otherwise>
                  <input type="checkbox" id="shareIs">
                </c:otherwise>
              </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</div>

<script>
  function shareChange(id){

  }
</script>
</body>
</html>

