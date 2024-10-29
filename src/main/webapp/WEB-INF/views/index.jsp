<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="/assets/img/favicon.ico" />
    <title>TRAPLAN(Main)</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <!------------------ 네비게이션바/메뉴 ----------------------->
    <nav class="navbar navbar-expand-sm bg-body-tertiary fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="/">
          <!-- img src="/assets/img/logo.png" alt="TRAPLAN" style="width: 30px" class="rounded-pill" /-->
          TRAPLAN
        </a>
        <!--- 프로필 출력::시작 --->
        <c:if test="${login.profile == null}">
          <img src="/assets/img/anonymous.jpg" alt="프사" style="width: 30px" class="rounded-pill" />
        </c:if>

        <c:if test="${login != null && login.profile != null}">
          <img src="/display${login.profile}" alt="프사" style="width: 30px" class="rounded-pill" />
        </c:if>
        <span class="navbar-text">&nbsp;&nbsp;Welcome ${sessionScope.login == null ? '' : login.nickName}</span>
        <!--- 프로필 출력::종료 --->

        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/travelboard/list">게시판</a>
            </li>
            <c:if test="${login.id != null}">
              <li class="nav-item">
                <a class="nav-link" href="/my-page/${login.id}">마이페이지</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/members/sign-out">로그아웃</a>
              </li>
            </c:if>
            <c:if test="${login.id == null}">
              <a class="nav-link" href="/members/sign-in">로그인</a>
              <a class="nav-link" href="/members/sign-up">회원가입</a>
            </c:if>
            <li class="nav-item">
              <!--a class="nav-link disabled" href="/members/sign-in" aria-disabled="true">로그인</a-->
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!------------------ 추천 여행지 carousel ----------------------->
    <div id="demo" class="carousel slide mt-5" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button data-bs-target="#demo" data-bs-slide-to="1"></button>
        <button data-bs-target="#demo" data-bs-slide-to="2"></button>
        <button data-bs-target="#demo" data-bs-slide-to="3"></button>
      </div>

      <div class="carousel-inner">
        
        <c:if test="${login.profile == null}">
          <div class="carousel-item active">
            <img src="/assets/img/경주-800x320.jpg" alt="" class="d-block w-100" />
          </div>
          <div class="carousel-item">
            <img src="/assets/img/부산-800x320.jpg" alt="" class="d-block w-100" />
          </div>
          <div class="carousel-item">
            <img src="/assets/img/남이섬-800x320.jpg" alt="" class="d-block w-100" />
          </div>
        </c:if>
  
        <c:if test="${login != null && login.profile != null}">
          <c:forEach items="${login.topThreeFavoriteTravelDtoList}" var="travel" varStatus="status">
              <div class="carousel-item<c:out value='${status.first ? " active" : ""}'/>">
                <a href="/travelboard/info/${travel.id}">
                  <img src="/display${travel.travelImg}" alt="" class="d-block w-100" />
                </a>
              </div>
          </c:forEach>
        </c:if>

        <button class="carousel-control-prev" data-bs-target="#demo" data-bs-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" data-bs-target="#demo" data-bs-slide="next">
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>
    </div>

    <!--------------------- My여행추가, My여행목록 ----------------------->
    <div class="container mt-4">
      <div class="row">
        <div class="col-sm-4">
          <div class="card p-1 mt-2">
            <a href="/travelplan">
              <img src="/assets/img/add-800x320.jpg" alt="" class="card-img-top img-fluid" />
            </a>
            <div class="pt-2">
              <h6 class="card-title">여행을 떠나요~</h6>
            </div>
            <!--
            <div class="card-img-overlay">
              <h4 class="card-title text-primary">여행추가</>
            </div>
            -->
          </div>
        </div>
 
        <c:if test="${login != null && login.profile != null}">

          <c:forEach items="${login.mainTravelDtoList}" var="travel" varStatus="status">
            <div class="col-sm-4">
              <div class="card p-1 mt-2">
                <a href="/my-page/mytravel/${travel.id}">
                  <img src="/display${travel.travelImg}" class="card-img-bottom" alt="이미지를 클릭하면 해당 여행으로 이동합니다."/>
                </a>
                <div class="pt-2">
                  <h6 class="card-title">${travel.title}</h6>
                </div>
              </div>
            </div>
          </c:forEach>
<!--
          <div class="col-sm-4">
            <div class="card p-1">
              <img src="/assets/img/홍콩-250x140.jpg" alt="" class="card-img-top" />
              <div class="card-img-overlay">
                <h4 class="card-title text-white">홍콩</h4>
                <p class="card-text text-white">침사추이, 몽콕, 스탠리, 빅토리아피크, 망고쥬스</p>
              </div>
            </div>
          </div>

          <div class="col-sm-4">
            <div class="card p-1">
              <img src="/assets/img/부산-250x140.jpg" alt="" class="card-img-top" />
              <div class="card-img-overlay">
                <h4 class="card-title text-white">부산-마이뭇다아이가</h4>
                <p class="card-text text-white">해운대, 광안리, 국제시장, 자갈치시장, 서면</p>
              </div>
            </div>
          </div>
-->
        </c:if>
      </div>
    </div>

    <!--------------------- 페이징 ---------------------------------------->
    <div class="container mt-4">
      <h2 class="mb-4">Travel List</h2>
      <div class="row">
      <div id="card-container" class="col-sm-4">
          <!-- 카드 항목들이 JavaScript로 생성됩니다 -->
      </div>
  </div>
      <nav aria-label="Page navigation example" class="mt-4">
          <ul id="pagination" class="pagination justify-content-center">
              <!-- 페이지 번호가 JavaScript로 생성됩니다 -->
          </ul>
      </nav>
    </div>

    <script>
      
     const travelList = [];
      
      <c:if test="${login != null && login.profile != null}">
        // JSTL을 사용해 JSP에서 자바스크립트 배열로 데이터 전송

        <c:forEach items="${login.mainTravelDtoList}" var="travel" varStatus="status">
            travelList.push({
                id: "${travel.id}",
                travelImg: "${fn:escapeXml(travel.travelImg)}",
                travelTitle: "${travel.title}"
            });
        </c:forEach>
      </c:if>
      
      const cardsPerPage = 3;
      let currentPage = 1;

      console.log(travelList);
      
      function displayCards() {
          const cardContainer = document.getElementById("card-container");
          cardContainer.innerHTML = "";

          const start = (currentPage - 1) * cardsPerPage;
          const end = start + cardsPerPage;
          const cards = travelList.slice(start, end);

          console.log("cards: ");
          console.log(cards);
          
          cards.forEach(card => {
            console.log(card.id);
            console.log(card.travelTitle);
            console.log(card.travelImg);
   

          const temp = `div "${card.travelTitle}" div`;
          console.log(temp);
        });

          cards.forEach(card => {
              const cardElement = document.createElement("div");
              cardElement.className = "card p-1 mt-2";
              console.log(card.travelImg.replace(/^"/, ''));
              
                    cardElement.innerHTML = `        
              <a href="/my-page/mytravel/` + card.id + `">
                  <img src="/display/042` + card.travelImg.replace(/^"/, '') + `" class="card-img-top img-fluid" alt="이미지를 클릭하면 해당 여행으로 이동합니다."/>
                </a>
                <div class="pt-2">
                  <h6 class="card-title">` + card.travelTitle +`</h6>
                </div>
                `;
                
              // cardElement.innerHTML = `
              //     <div class="card">
              //         <div class="card-body">
              //             <h5 class="card-title">` + card.travelTitle + `</h5>
              //             <p class="card-text">` + card.id + `</p>
              //         </div>
              //     </div>
              // `;
              console.log(cardElement.innerHTML);

              cardContainer.appendChild(cardElement);
          });
      }

      function displayPagination() {
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    const pageCount = Math.ceil(travelList.length / cardsPerPage);
    for (let i = 1; i <= pageCount; i++) {
        const pageItem = document.createElement("li");
        //pageItem.className = `page-item`+ i +`===`+ currentPage + `? "active" : ""`;

        if(i === currentPage){
          pageItem.className = `page-item active`;
        } else {
          pageItem.className = `page-item`;
        }

        console.log("pageItem.className");
        console.log(pageItem.className);
        
        pageItem.innerHTML = `<a class="page-link" href="#">`+i+`</a>`;

        pageItem.addEventListener("click", function(e) {
            e.preventDefault();
            currentPage = i;
            displayCards();
            displayPagination();
        });

        pagination.appendChild(pageItem);
    }
}
      // 초기 표시
      displayCards();
      displayPagination();
</script>


  </body>
</html>
