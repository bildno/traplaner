<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>

            <!-- <link rel="stylesheet" href="외부 스타일 시트 파일 경로"> -->

            <style>
                /* 검색창 */
                #field {
                    border: none;
                }

                .search {
                    display: flex;
                    flex-direction: row-reverse;
                    margin-right: 50px;
                }

                #option {
                    flex-direction: row;
                    margin-right: 8px;
                    margin-top: 1px;
                }

                #find {
                    flex-direction: row;
                }

                .form-control {
                    height: 23px;
                    width: 200px;
                }

                #select-type {
                    height: 23px;
                    width: 100px;
                }

                /* 목록창 */
                a {
                    color: black;
                    text-decoration: none;
                }

                #list {
                    position: relative;
                    left: auto;
                    top: auto;
                    width: 100%;
                }

                .list-container {
                    width: 600px;
                    place-items: center;
                    display: grid;
                    grid-template-columns: repeat(3, 1fr);
                    grid-template-rows: minmax(100px, auto);
                    gap: 60px 80px;
                }

                .image {
                    width: 300px;
                    height: 240px;
                    border-radius: 1%;
                }
            </style>

        </head>

        <body>
            <h1>게시판목록</h1>



            <!-- 검색창 영역 -->
            <div class="search">
                <fieldset id="field">
                    <div class="search">
                        <!-- 검색창 영역 -->
                        <form action="/travelboard/list" method="get" name="search" id="find">
                            <!-- <select class="form-select" name="type" id="search-type">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                                <option value="tc">제목+내용</option>
                            </select> -->
                            <input placeholder="제목, 작성자, 내용" type="search" class="form-control" name="keyword"
                                value="${s.keyword}" autocomplete="off">
                            <button type="submit" id="searching">검색</button>
                        </form>
                        <!-- 최신순, 과거순, 좋아요순 -->
                        <form action="/travelboard/list" method="get" id="option">
                            <select class="form-select" name="type" id="select-type">
                                <option value="new">최신순</option>
                                <option value="old">과거순</option>
                                <option value="best">좋아요순</option>
                            </select>
                        </form>
                    </div>
                </fieldset>
            </div>

            <!-- 목록창 영역 -->
            <main id="list">
                <div class="list-container">
                    <c:forEach var="tb" items="${tbList}">
                        <a href="localhost:8181/travelboard/info/${tOne.id}">
                            <img src="/assets/img/disneyland_hongkong.jpg" alt="여행이미지" class="image"> <br>
                            ${tb.shortTitle} <br>
                            ${tb.writer} <br>
                            ${tb.writeDate}
                        </a>
                    </c:forEach>
                </div>
            </main>

        </body>

        </html>