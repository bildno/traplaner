<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .container {
        margin-top: 150px;
        margin-bottom: 100px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #join_h1 {
        margin-top: 100px;
        font-size: 50px;
        display: flex;
        flex-flow: row nowrap;
        margin-bottom: 20px;
    }

    #join_exp {
        font-weight: 300;
        margin-left: 220px;
        font-size: 20px;
        color: rgb(37, 49, 109);
    }

    #space_icon {
        width: 30px;
    }

    .contents {
        border: 1px solid rgb(238, 238, 238);
        width: 40%;
        margin: 40px 20% 40px 20%;
        padding: 40px 0 40px 0;
        border-radius: 5px;
    }

    #insert input[type="text"],
    input[type="number"],
    input[type="text"],
    input[type="email"],
    input[type="password"] {
        border: 1px solid rgb(238, 238, 238);
        padding: 10px;
        border-radius: 5px;
        width: 60%;
        margin-bottom: 15px;
        margin-left: 20%;
    }

    #joinbtn {
        padding: 10px;
        border: 1px solid rgb(238, 238, 238);
        border-radius: 5px;
        width: 60%;
        background-color: white;
        color: gray;
        margin-left: 20%;
        cursor: pointer;
    }


    .id_check {
        padding: 5px;
        border: 1px solid rgb(238, 238, 238);
        border-radius: 5px;
        font-size: 10px;
        margin-left: 5px;
        background-color: white;
        color: gray;
        cursor: pointer;
    }
    #profile_img{
        width: 300px;
    }
</style>
<div class="container">
    <%@ include file="../header.jsp"%>
    <h1 id="join_h1">비밀번호 변경</h1>
    <h2 id="join_exp"></h2>
    <img id="profile_img" src="/assets/img/basicProfile.png" alt="업따" />
    <div class="contents">
        <form name="join" method="post">
            <div id="insert">
                <div>
                    <input type="email" placeholder="이메일" name="email" class="email_input" />
                    <input
                            id="email"
                            class="id_check"
                            type="button"
                            value="이메일인증"
                            formaction=""
                    />
                </div>
                <div>
                    <input type="number" placeholder="인증 번호를 입력해주세요" name="email" class="email_input" />
                    <input
                            id= "check-num"
                            class="id_check"
                            type="button"
                            value="확인"
                            formaction=""
                    />
                </div>
                <div>
                    <input
                            type="password"
                            placeholder="새 비밀번호"
                            name="password"
                            id="userPs"
                    />
                </div>
                <div>
                    <input
                            type="password"
                            placeholder="비밀번호 확인"
                            name="password_check"
                            id="userPc"
                    />
                </div>

                <br />
                <input
                        type="submit"
                        value="비밀번호 변경"
                        id="joinbtn"
                        formaction="/members/pw-change"
                />
            </div>
            <br />
        </form>
    </div>
</div>
<script>

    let code = ''; // 이메일 전송 인증번호를 전역변수로 선언.

    // 이메일 인증버튼 클릭 이벤트
    document.getElementById('id_check').onclick = () => {
        // 우선 올바른 이메일 형식인지, 중복이 발생하지 않았는지 먼저 체크하기.
        // 여기에서는 따로 진행하지 않겠습니다. (sign-up.jsp에는 이미 되어있음)
        const email = document.getElementById('userEmail').value.trim();
        console.log('완성된 email: ', email);

        fetch('/members/email', {
            method: 'post',
            headers: {
                'Content-type': 'text/plain',
            },
            body: email,
        })
            .then((res) => {
                if (res.status === 200) {
                    return res.text();
                } else {
                    alert('존재하지 않는 이메일 주소인거 같아요!');
                    return;
                }
            })
            .then((data) => {
                console.log('인증번호: ', data);
                alert('인증번호가 전송되었습니다. 입력란에 정확히 입력해 주세요!');
                document.getElementById('mail-check-input').disabled = false;
                code = data; // 서버가 전달한 인증번호를 전역변수에 저장.
            });
    };

    // 인증번호 검증
    // blur -> focus가 빠지는 경우 발생.
    document.getElementById('mail-check-input').onblur = (e) => {
        console.log('blur 이벤트 발생!');
        const inputCode = e.target.value;

        if (inputCode === code) {
            document.getElementById('mailCheckMsg').textContent =
                '인증번호가 일치합니다!';
            document.getElementById('mailCheckMsg').style.color = 'skyblue';
            e.target.style.display = 'none';
        } else {
            document.getElementById('mailCheckMsg').textContent =
                '인증번호를 다시 확인하세요!';
            document.getElementById('mailCheckMsg').style.color = 'red';
            e.target.focus();
        }
    };

</script>
