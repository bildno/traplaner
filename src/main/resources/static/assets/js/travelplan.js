
//JSON으로 포장할 객체
let data ={
    travel :{
        title : "",
        startDate : Date.now(),
        endDate : Date.now(),
        share : false
    },
    journeys : []
}


// day 1,2,3 부분 구현 함수
function addOpt(k) {
    const $select = document.querySelector("#day-select");
    $select.style.display = "block";
    $select.innerHTML.replace(/<[^>]*>?/g, "");
    for (let i = 1; i <= k; i++) {
        $select.innerHTML += `<option value="javascript">Day` + i + "</option>";
    }
}

// 캘린더 구현 부분

let start;
let end;
let period = start + "~" + end;
async function calendarRender(){
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        selectable: true,
        select: await function (info) {
            start = info.start;
            end = info.end;
            //여행 시작 끝 데이터 객체에 넣어두기
            data.travel.startDate = start;
            data.travel.endDate = end;
            end.setDate(end.getDate() - 1);
            const diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1;
            period = start.getFullYear() + '년 ' + (start.getMonth() + 1) + '월 ' + start.getDate() + '일 ' + "~ " +
                end.getFullYear() + '년 ' + (end.getMonth()+1) + '월 ' + end.getDate() + '일';
            const $period = document.querySelector("#travel-info p");
            $period.innerHTML = period;
            calendarEl.style.display = "none";
            document.querySelector(".form-container").style.display = "block";
            document.querySelector("#save-journey").style.display = "flex";
            document.querySelector("#journey-display").style.display = "block";
            document.querySelector("#save-travel").style.display = "block";
            addOpt(diffDays);
        }
    });
    calendar.render();
}

document.addEventListener('DOMContentLoaded',calendarRender);
// 여정 프론트엔드 등록 처리
let journeyId = 0; // 삭제 로직을 위한 고유 id 선언
function addJourney() {
    const $day = document.querySelector('#day-select');
    const day = parseInt($day.options[$day.selectedIndex].text.replace(/\D/g, ''))
    const date = data.travel.startDate;
    date.setDate(date.getDate()+day-1);
    const time = document.getElementById('time').value;
    const title = document.getElementById('schedule').value;
    const location = document.getElementById('location').value;
    const locationId = document.getElementById('location').dataset.placeid;
    const address = document.getElementById('location').dataset.address;
    const budget = document.getElementById('budget').value;
    const reservation = document.getElementById('reservation').files[0] ? document.getElementById('reservation').files[0].name : '없음';

    const newJourney = {
        id: journeyId++, // 고유 ID 부여
        day,
        date,
        time,
        title,
        location,
        locationId,
        address,
        budget,
        reservation
    };
    data.journeys.push(newJourney);
    updateJourneyView();
    clearForm();
}

function updateJourneyView() {
    const $day = document.querySelector('#day-select');
    const day = parseInt($day.options[$day.selectedIndex].text.replace(/\D/g, ''))
    const journeyDisplay = document.getElementById('journey-display');
    journeyDisplay.innerHTML = '';
    console.log(data.journeys);
    if (data.journeys.length !== 0) {
        data.journeys.filter((journey) => journey.day === day)
            .forEach(journey => {
            const journeyItem = document.createElement('div');
            journeyItem.innerHTML = `
        <p><strong>시간:</strong> ${journey.time}</p>
        <p><strong>일정 제목:</strong> ${journey.title}</p>
        <p><strong>장소:</strong> ${journey.location}</p>
        <p><strong>예산:</strong> ₩${journey.budget}</p>
        <p><strong>예약:</strong> ${journey.reservation}</p>
        <button data-id = ${journey.id}>x</button>
      `;
            journeyItem.style.borderBottom = "1px solid black";
            journeyItem.style.marginBottom = '10px';
            journeyDisplay.appendChild(journeyItem);
        });
    } else {
        journeyDisplay.innerHTML = '<p>일정이 없습니다.</p>';
    }
}

function clearForm() {
    document.getElementById('time').value = '';
    document.getElementById('schedule').value = '';
    document.getElementById('location').value = '';
    document.getElementById('budget').value = '';
    document.getElementById('reservation').value = '';
}

//여정 추가 부분 이벤트리스너
document.querySelector("#save-journey").addEventListener(
    "click", (e) => {
       addJourney();
    }
)
//select 동적반응 로직
document.querySelector("#day-select").addEventListener(
    "change", updateJourneyView)

document.querySelector("#journey-display").addEventListener(
    "click",e=>{
        if(e.target && e.target.tagName === 'BUTTON'){
            for(let i = 0; i < data.journeys.length; i++) {
                if (data.journeys[i].id === parseInt(e.target.dataset.id)) {
                    data.journeys.splice(i, 1);
                }
            }
        }
        updateJourneyView();
    }
)
document.querySelector(".fas").addEventListener("click",()=>{
    document.getElementById('calendar').style.display="block";
    calendarRender().then(r => {});
})

document.getElementById("save-travel").addEventListener("click",()=>{
    data.travel.title = document.getElementById("travel-name").value;
    const json = JSON.stringify(data);
    fetch("travelplan", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        body: {json},

    }).then(r => {})
})






