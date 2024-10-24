// 유효성 검증 관련 함수들 임포트
// 디스트럭처링을 통해 특정 js 파일에서 원하는 함수만 가져와서 쓸라고.
import { validateInput } from './validation.js';
import { debounce } from './util.js';

// 폼과 회원가입 버튼 요소를 가져옴
const form = document.getElementById('signUpForm'); // 회원가입 폼
const signupButton = document.getElementById('signup-btn'); // 회원가입 버튼
const EmailCheckButton = document.getElementById('id_check');
const emailValue = document.getElementById('emailValue');
const NicknameCheckButton = document.getElementById('id_nickname');

// 각 필드에 대한 정보 배열 (id, 유효성 검증 함수, 에러 메시지 표시 요소, 초기 유효 상태)
const fields = [
  {
    id: 'password',
    validator: validateInput.password,
    errorElement: 'pwChk',
    valid: false,
  },
  {
    id: 'password_check',
    validator: (value) =>
      validateInput.passwordCheck(
        value,
        document.getElementById('password').value
      ),
    errorElement: 'pwChk2',
    valid: false,
  },
  {
    id: 'user_name',
    validator: validateInput.name,
    errorElement: 'nameChk',
    valid: false,
  },
  {
    id: 'user_email',
    validator: validateInput.email,
    errorElement: 'emailChk',
    valid: false,
  },
];

// 버튼 상태를 업데이트하는 함수
const updateButtonState = () => {
  // 모든 valid가 true인지 확인
  // every 배열 고차 함수: 배열 내의 데이터를 순회하면서 특정 값이 모두 true인지를 확인.
  const isFormValid = fields.every((field) => field.valid);

};


// 이메일 유효성검사 버튼 이벤트 리스터 추가
EmailCheckButton.addEventListener('click', (e) => {
  fields.email = validateInput.email(e.target.value);
})
//닉네임 유효성 검사 버튼 이벤트 리스너 추가
NicknameCheckButton.addEventListener('click', (e) => {

})
// 회원가입 버튼 클릭 이벤트 리스너 추가
signupButton.addEventListener('click', (e) => {
  e.preventDefault();
  //비밀 번호 유효성 확인 로직

  // 모든 필드가 유효한지 확인
  const isFormValid = fields.every((result) => result.valid);
  if (isFormValid) {
    // 모든 필드가 유효한 경우
    form.submit(); // 폼 제출
  } else {
    // 유효하지 않은 필드가 있는 경우
    alert('입력란을 다시 확인하세요!'); // 경고 메시지 표시
  }
});
