<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
        html {
            height: 100vh;
        }
        .container {
            display: flex;
            width: 90%;
            height: 90%;
            border: 2px solid skyblue;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
        #travel-info-container {
            width: 90%;
            height: 90%;
        }
        #travel-info {
            width: 30%;
            border: 1px solid skyblue;
        }
        #travel-name {
            background-color: #f693ff;
            padding: 5%;
            font-size: larger;
        }
        #map-info {
            width: 70%;
            border: 3px solid skyblue;
        }
        #travel-info-container {
            margin: 5%;
        }
        #travel-period h3 {
            display: block;
        }
        #calendar{

        }
    </style>
</head>
<html>
<body>

<%--<%@ include file="../header.jsp"%>--%>
<div class="container">
    <div id="travel-info">
        <div id="travel-info-container">
            <div id="travel-name">세계 여행</div>
            <div id="calendar"></div>
            <div id="travel-period">
                <h3>여행기간</h3>
                <p>2024년 10월 22일 ~ 2024년 10월 30일</p>
            </div>


        </div>
    </div>
    <div id="map-info">
        <div id="googleMap" style="width: 100%; height: 100%"></div>
    </div>
</div>
</body>


<script>


    let period =[];
    function myMap() {
        var mapOptions = {
            center: new google.maps.LatLng(51.508742, -0.12085),
            zoom: 5,
        };

        var map = new google.maps.Map(
            document.getElementById("googleMap"),
            mapOptions
        );

        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'ko',
                dateClick: function(info) {
                    alert('Clicked on: ' + info.dateStr);
                    alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
                    alert('Current view: ' + info.view.type);
                    // change the day's background color just for fun
                    info.dayEl.style.backgroundColor = 'red';
                }
            });

            calendar.render();
        });



    }
</script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script src="fullcalendar/lib/locales-all.js"></script>
<script src='fullcalendar/dist/index.global.js'></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBY7CGNgsIdVaut54UGlivQkiCYAyoS19I&callback=myMap"></script>


</html>

