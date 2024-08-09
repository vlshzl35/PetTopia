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
    document.querySelector('.closeBtn1').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'none';
    };

    document.querySelector('.closeBtn2').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'none';
    };




});

// ajax로 영수증 네이버 OCR API에 전송하기
function closePopup() {
    document.getElementById('uploadPopup').style.display = 'none';
}

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

            // uploadPopup에서 ocr 인증에 썻던 form 안보이도록 숨기기
            const uploadPopupContent = document.getElementById('uploadPopup').innerHTML;
            document.getElementById('uploadPopup').innerHTML = '';

            // Display the review form inside the same modal
            const reviewForm = document.getElementById('reviewForm');
            document.getElementById('uploadPopup').appendChild(reviewForm);
            reviewForm.style.display = 'block'; // Show review form in the same modal

            // 리뷰 작성까지 마치면 팝업 닫기
            // closePopup();
        },
        error: console.log,
        complete() {
            e.target.reset(); // 폼 초기화
        }
    })
};

// 리뷰 등록 폼
document.querySelectorAll('.star-rating input').forEach(input => {
    input.addEventListener('change', function() {
        console.log(`Selected rating: ${this.value}`); // 선택된 별점을 콘솔에 출력
    });
});






