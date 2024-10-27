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
                }

                #find {
                    flex-direction: row;
                }

                .form-control {
                    height: 23px;
                    width: 200px;
                }

                #select-type,
                #search-type {
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

                /* 페이지 스타일 */
                /* pagination style */
                .bottom-section {
                    display: flex;
                    width: fit-content;
                    margin: 0 auto;
                }

                .bottom-section nav {
                    flex: 1;
                    justify-content: center;
                }


                .pagination-custom a {
                    color: #444 !important;
                }

                .pagination-custom li.active a,
                .pagination-custom li:hover a {
                    background: #333 !important;
                    color: #fff !important;
                }
            </style>

        </head>

        <body>
            <h1>게시판목록</h1>



            <!-- 검색창 영역 -->
            <div class="top-section">
                <fieldset id="field">
                    <div class="search">
                        <!-- 검색창 영역 -->
                        <form action="/travelboard/list" method="get" name="search" id="find">
                            <select class="form-select" name="search.option" id="search-type">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                                <option value="tc">제목+내용</option>
                            </select>
                            <input placeholder="제목, 작성자, 내용" type="search" class="form-control" name="keyword"
                                value="${s.keyword}" autocomplete="off">
                            <button type="submit" id="searching">검색</button>
                        </form>
                        <!-- 최신순, 과거순, 좋아요순 -->
                        <form action="/travelboard/list" method="get" name="arrange" id="option">
                            <select class="form-select" name="arrange.type" id="select-type">
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
                        <a href="localhost:8181/travelboard/info/${tb.id}">
                            <img src="/assets/img/disneyland_hongkong.jpg" alt="여행이미지" class="image"> <br>
                            ${tb.shortTitle} <br>
                            ${tb.writer} <br>
                            ${tb.writeDate}
                        </a>
                    </c:forEach>
                </div>
            </main>

            <!-- 게시글 목록 하단 영역 -->
            <div class="bottom-section">
                <!-- 페이지 버튼 영역 -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg pagination-custom">
                        <c:if test="${maker.prev}">
                            <li class="page-item">
                                <a class="page-link"
                                    href="/travelboard/list?pageNo=${maker.begin-1}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">&lt;&lt;</a>
                            </li>
                        </c:if>

                        <!-- step은 기본값이 1, 생략 가능 -->
                        <c:forEach var="i" begin="${maker.begin}" end="${maker.end}">
                            <li data-page-num="${i}" class="page-item">
                                <a class="page-link"
                                    href="/travelboard/list?pageNo=${i}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">${i}</a>
                            </li>
                        </c:forEach>


                        <c:if test="${maker.next}">
                            <li class="page-item">
                                <a class="page-link"
                                    href="/travelboard/list?pageNo=${maker.end + 1}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">&gt;&gt;</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>

        </body>

        </html>