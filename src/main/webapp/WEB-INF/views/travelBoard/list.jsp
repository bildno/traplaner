<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
        </head>

        <body>
            <h1>게시판목록</h1>

            <!-- 최신순, 과거순, 좋아요순 -->
            <div class="search">
                <form action="/travelboard/list" method="get">
                    <select class="form-select" name="type" id="search-type">
                        <option value="new">최신순</option>
                        <option value="old">과거순</option>
                        <option value="best">좋아요순</option>
                    </select>
                </form>
            </div>

            <!-- 검색창 영역 -->
            <div class="search">
                <form action="/travelboard/list" method="get">
                    <select class="form-select" name="type" id="search-type">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                        <option value="tc">제목+내용</option>
                    </select>
                    <input type="text" class="form-control" name="keyword" value="${s.keyword}">
                    <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>

            <!-- 메인 게시판 영역 -->
            <main class="list-container">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tb" items="${tbList}">
                            <section>
                                <tr>
                                    <td><img src="" alt="여행이미지"></td>
                                    <td><a href="localhost:8181/travelboard/info/${id}">${tb.shortTitle}</a></td>
                                    <td>${tb.writer}</td>
                                    <td>${tb.writeDate}</td>
                                </tr>
                            </section>
                            <br>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </body>

        </html>