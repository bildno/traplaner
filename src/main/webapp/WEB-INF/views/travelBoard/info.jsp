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
            <h1>게시글상세조회</h1>
            <div class="post">
                <h1>${tOne.id}번 게시물 내용</h1>
                <br>
                <h2>작성일자: ${tOne.writeDate}</h2>
                <h2>작성자: ${tOne.writer}</h2>
                <br>
                <h3>제목: ${tOne.title}</h3>
                <h3>좋아요: ${tOne.likeCount}</h3>
                <div id="content">
                    <h3>내용: ${tOne.content}</h3>
                </div>
            </div>
        </body>

        </html>