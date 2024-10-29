    <%--
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Header Navigation</title>
      <link rel="stylesheet" href="styles.css">
      <style>
        body {
          margin: 0;
          padding: 0;
          font-family: Arial, sans-serif;
        }
        .navbar {
          display: flex;
          justify-content: space-between;
          align-items: center;
          background-color: #fff;

          padding: 10px 20px;
        }
        .logo {
          display: flex;
          align-items: center;
        }
        .logo img {
          height: 40px;
          margin-right: 10px;
        }
        .logo span {
          font-size: 1.5em;
          font-weight: bold;
          color: #333;
        }
        .nav-links {
          list-style: none;
          display: flex;
          gap: 7px;
          margin: 0;
          margin-left: auto;
        }
        .nav-links li {
          display: inline;
        }
        .nav-links a {
          text-decoration: none;
          color: #333;
          font-weight: bold;
          font-size: 0.5em;
        }
        .nav-links a:hover {
          color: #007BFF;
        }
      </style>
    </head>




    <body>
    <header>
      <nav class="navbar">
        <!-- 로고 이미지 -->
        <div class="logo">
          <a href="index.jsp">
            <img src="./image/logo.png" alt="Logo"/>
          </a>
        </div>

        <!-- 메뉴 항목 -->
        <ul class="nav-links">
          <li><a href="travelboard.jsp">게시판</a></li>

          <c:if>
            <c:if test="${not empty sessionScope.user}">
              <li><a href="mypage.jsp">마이페이지</a></li>
              <li><a href="home.jsp">로그아웃</a></li>
            </c:if>
            <c:if test="${empty sessionScope.user}">
              <li><a href="sign-in.jsp">로그인</a></li>
            </c:if>
          </c:if>
        </ul>
      </nav>
    </header>












    </body>
    </html>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Header Navigation</title>
      <link rel="stylesheet" href="styles.css">
      <style>
        body {
          margin: 0;
          padding: 0;
          font-family: Arial, sans-serif;
        }
        .navbar {
          display: flex;
          justify-content: space-between;
          align-items: center;
          background-color: #fff;

          padding: 10px 20px;
        }
        .logo {
          display: flex;
          align-items: center;
        }
        .logo img {
          height: 40px;
          margin-right: 10px;
        }
        .logo span {
          font-size: 1.5em;
          font-weight: bold;
          color: #333;
        }
        .nav-links {
          list-style: none;
          display: flex;
          gap: 7px;
          margin: 0;
          margin-left: auto;
        }
        .nav-links li {
          display: inline;
        }
        .nav-links a {
          text-decoration: none;
          color: #333;
          font-weight: bold;
          font-size: 0.5em;
        }
        .nav-links a:hover {
          color: #007BFF;
        }
      </style>
    </head>
    <body>
    <header>
      <nav class="navbar">
        <!-- 로고 이미지 -->
        <div class="logo">
          <a href="index.jsp">
            <img src="./image/logo.png" alt="Logo"/>
          </a>
        </div>

        <!-- 메뉴 항목 -->
        <ul class="nav-links">
          <li><a href="travelboard.jsp">게시판</a></li>

          <c:if>
            <c:if test="${not empty sessionScope.user}">
              <li><a href="mypage.jsp">마이페이지</a></li>
              <li><a href="home.jsp">로그아웃</a></li>
            </c:if>
            <c:if test="${empty sessionScope.user}">
              <li><a href="sign-in.jsp">로그인</a></li>
            </c:if>
          </c:if>
        </ul>
      </nav>
    </header>
    </body>
    </html>--%>
