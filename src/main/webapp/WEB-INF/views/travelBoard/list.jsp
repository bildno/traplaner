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
        <div id="wrap">
            <div class="main-title-wrapper">
                <h1 class="main-title">게시판</h1>
            </div>
            <div class="top-section">
                <!-- 검색창 영역 -->
                <div class="search">
                    <form action="/travelboard" method="get">
                        <select class="form-select" name="type" id="search-type">
                            <option value="travelId">제목</option>
                            <option value="content">내용</option>
                            <option value="memberNickName">작성자</option>
                            <option value="place">여행지</option>
                            <option value="tc">제목+내용</option>
                        </select>
                        <input type="text" class="form-control" name="keyword" value="${s.keyword}">
                        <button class="btn btn-primary" type="submit">
                            <i class="fas fa-search"></i>
                        </button>
                    </form>
                </div>
            </div>



            <!-- 메인 게시판 영역 -->
            <h4>글목록</h4>
            <div class="card-container">
                <c:forEach var="b" items="${bList}">
                    <div class="card-wrapper">
                        <section class="card" data-bno="${b.id}">
                            <div class="card-title-wrapper">
                                <h2 class="card-title">
                                    ${b.travelId}
                                </h2>
                                <div class="time-view-wrapper">
                                    <div class="time">
                                        <i class="far fa-clock"></i>
                                            ${b.writeDate} </div>
                                </div>
                            </div>
                            <div class="card-content">
                              ${b.shortContent}
                            </div>
                        </section>
                        <div class="card-btn-group">
                            <button class="del-btn" data-href="${b.id}">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 게시글 목록 하단 영역 -->
            <div class="bottom-section">
                <!-- 페이지 버튼 영역 -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg pagination-custom">
                            <c:if test="${maker.prev}">
                                <li class="page-item">
                                    <a class="page-link" href="/board/list?pageNo=${maker.begin-1}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">&lt;&lt;</a>
                                </li>
                            </c:if>

                            <!-- step은 기본값이 1, 생략 가능 -->
                            <c:forEach var="i" begin="${maker.begin}" end="${maker.end}">
                                <li data-page-num="${i}" class="page-item">
                                    <a class="page-link" href="/board/list?pageNo=${i}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">${i}</a>
                                </li>
                            </c:forEach>


                            <c:if test="${maker.next}">
                                <li class="page-item">
                                    <a class="page-link" href="/board/list?pageNo=${maker.end + 1}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}">&gt;&gt;</a>
                                </li>
                            </c:if>
                    </ul>
                </nav>
            </div>
        </div>
        </div>
</body>
</html>