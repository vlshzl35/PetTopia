// 펫 등록되고 나서 alert 해주기 위함입니다.
function isValid() {
    if (true) {
        alert('🐶펫 등록이 완료되었습니다!🐶')
    }
}

function previewImage(event) {
    const reader = new FileReader();  // FileReader 객체 생성
    reader.onload = function() {
        const output = document.getElementById('preview');  // 미리보기 이미지 태그
        output.src = reader.result;  // 이미지 태그의 src 속성에 데이터 URL 설정
    };
    if (event.target.files && event.target.files[0]) {
        reader.readAsDataURL(event.target.files[0]);  // 파일 내용을 읽어 데이터 URL로 변환
    }
}