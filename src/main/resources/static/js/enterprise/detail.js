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
    console.log('type = ', frmData.get('type'));
    console.log('file = ', frmData.get('file'));

    console.log('[[@{/ocrUpload}]]');

    $.ajax({
        url: `[[@{/ocrUpload}]]`,
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

            // // 업로드 성공 시 리뷰 작성 폼 보이기
            // document.getElementById('reviewForm').style.display = 'block';
            // closePopup(); // 팝업 닫기
        },
        error: console.log,
        complete() {
            e.target.reset(); // 폼 초기화
        }
    })
};



