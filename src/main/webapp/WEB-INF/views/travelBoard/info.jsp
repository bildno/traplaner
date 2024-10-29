<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>

            <script src="https://kit.fontawesome.com/7dcd39bc6e.js" crossorigin="anonymous"></script>

            <style>
                button {
                    background: none;
                    border: none;
                    width: auto;
                    height: auto;
                }

                * {
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }

                body {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    margin: 0;
                }

                .container {
                    width: 90%;
                    max-width: 980px;
                    padding: 40px 80px;
                    background-color: #ffffff;
                    border: 1px solid #ddd;
                    border-radius: 1%;
                }

                h1 {
                    font-size: 24px;
                    font-weight: bold;
                    margin-bottom: 12px;
                }

                .author-date {
                    font-size: 14px;
                    color: #606060;
                    margin-bottom: 20px;
                }

                .heart {
                    float: right;
                    color: #606060;
                }

                .section {
                    width: 100%;
                    height: 300px;
                    background-color: #d3d3d3;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 20px;
                    color: #333;
                    margin-bottom: 16px;
                    border-radius: 4px;
                }

                #content {
                    background-color: white;
                    justify-content: flex-start;
                    align-items: flex-start;
                }

                .day-title {
                    font-size: 18px;
                    font-weight: bold;
                    margin-bottom: 12px;
                }

                .day-date {
                    font-size: 14px;
                    color: #888;
                    margin-left: 8px;
                }
            </style>
        </head>

        <body>

            <div class="container">
                <!-- <h1>게시글상세조회</h1>
            <h1>${tOne.id}번 게시물 내용</h1> -->
                <h1>${tOne.title}</h1>
                <p class="author-date">${tOne.writer} ${tOne.writeDate} <span class="heart"><button
                            onclick="toggleLike(${tOne.id})"><i class="fa-regular fa-heart"
                                style="color: #ff00bb;"></i></button>
                        ${tOne.likeCount}</span></p>

                <div class="section photo">사진</div>
                <div class="section text" id="content">${tOne.content}</div>

                <h2 class="day-title">1일차 <span class="day-date">2024년 8월 29일</span></h2>

                <div class="section route">경로</div>
                <div class="section schedule">여정</div>
            </div>

            <script>
                function toggleLike(travelBoardId) {
                    fetch(`/travelboard/${travelBoardId}/toggle-like`, {
                        method: "POST"
                    })
                        .then(response => response.text())  // 응답을 텍스트로 받아
                        .then(data => {
                            document.getElementById("like-count").innerText = data; // 정수를 그대로 표시
                        })
                        .catch(error => console.error("Error:", error));
                }
            </script>
        </body>

        </html>