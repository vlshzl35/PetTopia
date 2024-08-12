// 병원정보/후기 열고 닫기
document.addEventListener('DOMContentLoaded', function() {
    var btnHospitalInfo = document.getElementById('btnHospitalInfo');
    var hospitalInfoTab = document.getElementById('hospitalInfoTab')
    var btnReviews = document.getElementById('btnReviews');
    var reviewTab = document.getElementById('reviewTab')

    btnHospitalInfo.addEventListener('click', function() {
        if (hospitalInfoTab.style.display === 'block') {
            hospitalInfoTab.style.display = 'none';
        } else {
            hospitalInfoTab.style.display = 'block';
            reviewTab.style.display = 'none'; // 후기 탭을 숨깁니다.
        }
    });

    btnReviews.addEventListener('click', function() {
        if (reviewTab.style.display === 'block') {
            reviewTab.style.display = 'none';
        } else {
            reviewTab.style.display = 'block';
            hospitalInfoTab.style.display = 'none'; // 병원 정보 탭을 숨깁니다.
        }
    });

    // 영수증 인증 폼

    // 모달 창을 열고 닫는 스크립트
    document.getElementById('writeReviewBtn').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'block';
    };

    // 닫기 버튼을 누르면 모달 숨기기
    document.querySelector('.closeBtn').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'none';
    };
});



// ajax로 영수증 네이버 OCR API에 전송하기
document.fileUploadFrm.onsubmit = (e) => {
    e.preventDefault();

    // multipart/form-data 비동기 요청
    const frmData = new FormData(e.target);
    frmData.append('type', 'general');  // type파라미터에 general(일반) value넣기

    console.log('type = ', frmData.get('type'));
    console.log('file = ', frmData.get('file'));

    const apiUrl = document.getElementById('apiUrl').dataset.url;

    $.ajax({
        url: apiUrl, // url: `[[@{/ocrUpload}]]`,
        method: 'post',
        data: frmData,
        contentType: false, // multipart/form-data 설정
        processData: false, // multipart/form-data 설정
        success(response) {
            console.log(response);
            const {message, data} = response;
            alert(message);
            const _data = JSON.parse(data);
            console.log(_data);

            // 리뷰등록 팝업 열기
            // 영수증 인증 섹션 숨기기 & 초기화
            // 1. upload팝업을 숨깁니다.
            document.getElementById('uploadPopup').style.display = 'none';
            // 2. 파일 업로드 필드, 텍스트 입력 필드 등이 있다면, 이러한 필드들을 비우거나 초기 상태로 돌려놓기
            document.getElementById('ocrVerificationSection').style.display = 'block';
            // 3. 내부의 입력 필드나 선택 옵션을 초기화
            document.querySelector('#uploadPopup input[type="file"]').value = '';
            // 리뷰 작성 폼 표시
            document.getElementById('reviewPopup').style.display = 'block';
        },
        error: function(err) {
            console.log(err);
        },
        complete: function() {
            e.target.reset(); // 폼 초기화
        }
    })
};

// 리뷰 등록 폼
// 별점
document.querySelectorAll('.star-rating input').forEach(input => {
    input.addEventListener('change', function() {
        console.log(`Selected rating: ${this.value}`); // 선택된 별점을 콘솔에 출력
    });
});

// 닫기 버튼 핸들링
document.querySelector('#closeBtn2').onclick = function() {
    document.getElementById('reviewPopup').style.display = 'none';
};

