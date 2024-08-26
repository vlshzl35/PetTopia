document.addEventListener('DOMContentLoaded', function() {
    const btnEnterpriseInfo = document.getElementById('btnEnterpriseInfo');
    const enterpriseInfoTab = document.getElementById('enterpriseInfoTab');
    const btnReviews = document.getElementById('btnReviews');
    const reviewTab = document.getElementById('reviewTab');

    // 리뷰 등록후 리다이렉트 시, 이전에 선택된 탭(리뷰탭) 활성화
    const activeTab = localStorage.getItem('activeTab');
    if (activeTab === 'reviews') {
        reviewTab.style.display = 'block';
        enterpriseInfoTab.style.display = 'none';
    } else {
        enterpriseInfoTab.style.display = 'block';
        reviewTab.style.display = 'none';
    }

    btnEnterpriseInfo.addEventListener('click', function() {
        // 업체 정보 탭이 열려있다면 아무것도 실행하지 않고, 닫혀있으면 실행
        if (enterpriseInfoTab.style.display !== 'block') {
            enterpriseInfoTab.style.display = 'block';
            reviewTab.style.display = 'none'; // 리뷰 탭 숨기기
            localStorage.setItem('activeTab', 'info'); // 리뷰탭이 활성화 될 때 상태 저장
        }
    });

    btnReviews.addEventListener('click', function() {
        // 업체 정보 탭이 열려있다면 아무것도 실행하지 않고, 닫혀있으면 실행
        if (reviewTab.style.display !== 'block') {
            reviewTab.style.display = 'block';
            enterpriseInfoTab.style.display = 'none'; // 업체정보 탭 숨기기
            localStorage.setItem('activeTab', 'reviews'); // 리뷰탭이 활성화 될 때 상태 저장
        }
    });

    /* 영수증 인증 폼 */

    // 모달 창을 열고 닫는 스크립트
    document.getElementById('writeReviewBtn').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'block';
    };

    document.querySelector('.closeBtn').onclick = function() {
        document.getElementById('uploadPopup').style.display = 'none';
    };

    // 전화번호 복사 기능 추가
    document.querySelector('a[type="button"][style*="margin-left: 10px;"]').onclick = function() {
        var copyText = document.getElementById("entPhone").innerText;

        var tempInput = document.createElement("input");
        tempInput.style = "position: absolute; left: -1000px; top: -1000px";
        tempInput.value = copyText;
        document.body.appendChild(tempInput);

        tempInput.select();
        document.execCommand("copy");

        document.body.removeChild(tempInput);

        alert("전화번호가 복사되었습니다");
    };

    // 리뷰 삭제 기능
    const deleteButtons = document.querySelectorAll('.deleteReview');
    const enterpriseTypeElement = document.getElementById('enterpriseType');
    let enterpriseType = '';

    if (enterpriseTypeElement) {
        enterpriseType = enterpriseTypeElement.value;
    }

    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            if (confirm('리뷰를 삭제하시겠습니까?')) {
                const reviewId = this.getAttribute('data-review-id'); // 삭제할 리뷰의 ID를 가져옴
                const entId = new URLSearchParams(window.location.search).get('id'); // 현재 URL에서 업체 ID(entId)를 가져옴

                const deleteUrl = `/enterprise/${enterpriseType}/deleteReview`;

                $.ajax({
                    url: deleteUrl, // 컨트롤러의 경로에 맞게 수정
                    method: 'POST',
                    data: {
                        id: entId, // 업체 ID를 전달
                        reviewId: reviewId // 리뷰 ID를 전달
                    },
                    success: function(response) {
                        alert('리뷰가 삭제되었습니다.');
                        location.reload(); // 페이지 새로고침하여 변경사항 반영
                    },
                    error: function(err) {
                        console.log(err);
                        alert('리뷰 삭제에 실패했습니다. 다시 시도해주세요.');
                    }
                });
            }
        });
    });

    // 클라이언트 측에서 OCR 데이터와 서버 제공 데이터 비교
    function validateOcrData() {
        // OCR에서 제공한 업체명과 사업자번호
        const ocrEntName = document.querySelector('.reviewForm input[name="entName"]').value.trim();
        const ocrBizNum = document.querySelector('.reviewForm input[name="bizNum"]').value.trim().replace(/-/g, '');

        // 서버에서 제공된 업체명과 사업자번호
        const actualEntName = document.getElementById('entName').innerText;
        const actualBizNum = document.getElementById('bizNum').value;

        console.log("ocrEntName:", ocrEntName, "actualEntName:", actualEntName);
        console.log("ocrBizNum:", ocrBizNum, "actualBizNum:", actualBizNum);
        console.log('EntName Comparison result:', ocrEntName.includes(actualEntName));
        console.log('BizNum Comparison result:', ocrBizNum === actualBizNum);

        // 실제 일치 여부를 확인하기 위한 변수
        const isEntNameMatch = ocrEntName.includes(actualEntName) || actualEntName.includes(ocrEntName);
        const isBizNumMatch = ocrBizNum === actualBizNum;

        // EntName 또는 BizNum 중 하나라도 일치하면 true 반환(OCR 데이터와 서버 제공 데이터 비교
        if (isEntNameMatch || isBizNumMatch){
            return true;
        }
        // 여기까지 왔다는 것은 EntName과 BizNum이 모두 일치하지 않는 경우임
        alert('영수증의 정보가 선택하신 업체와 일치하지 않습니다. 확인 후 다시 시도해 주세요.');
        return false;
    }

    // ajax로 영수증 네이버 OCR API에 전송하기
    document.fileUploadFrm.onsubmit = (e) => {
        e.preventDefault();

        // multipart/form-data 비동기 요청
        const frmData = new FormData(e.target);
        frmData.append('type', 'document'); // OCR type파라미터에 document(특화) value넣기

        console.log('type = ', frmData.get('type'));
        console.log('file = ', frmData.get('file'));

        const apiUrl = document.getElementById('apiUrl').dataset.url;

        $.ajax({
            url: apiUrl, // url: `[[@{/ocrUpload}]]`,
            method: 'post',
            data: frmData,
            contentType: false, // multipart/form-data 관련 필수 설정
            processData: false, // multipart/form-data 관련 필수 설정
            success(response) {
                const {message, data} = response;
                const _data = JSON.parse(data); // OCR로 읽어온 영수증 정보 출력
                console.log(_data); // OCR로 읽어온 영수증 정보 출력


                // 데이터 구조에서 필요한 정보를 추출
                const {
                    images: [
                        {
                            receipt: {
                                result: {
                                    storeInfo: { name, bizNum},
                                    paymentInfo: { date },
                                    totalPrice: { price }
                                }
                            }
                        }
                    ]
                } = _data;

                // Fill hidden form fields with OCR data
                // document.querySelector('.reviewForm input[name="entName"]').value = name.text;
                // document.querySelector('.reviewForm input[name="bizNum"]').value = bizNum.text;
                // document.querySelector('.reviewForm input[name="paymentDate"]').value = date.text;
                // document.querySelector('.reviewForm input[name="totalPrice"]').value = price.text;
                const entNameField = document.querySelector('.reviewForm input[name="entName"]');
                const bizNumField = document.querySelector('.reviewForm input[name="bizNum"]');
                const paymentDateField = document.querySelector('.reviewForm input[name="paymentDate"]');
                const totalPriceField = document.querySelector('.reviewForm input[name="totalPrice"]');

                console.log('entNameField:', entNameField);
                console.log('bizNumField:', bizNumField);
                console.log('paymentDateField:', paymentDateField);
                console.log('totalPriceField:', totalPriceField);

                if (entNameField) entNameField.value = name.text;
                if (bizNumField) bizNumField.value = bizNum.text;
                if (paymentDateField) paymentDateField.value = date.text;
                if (totalPriceField) totalPriceField.value = price.text;


                // // 리뷰 작성 폼 표시
                // document.getElementById('reviewPopup').style.display = 'block';

                // Validate OCR data with actual data before showing review form
                if (validateOcrData()) {
                    alert(message);

                    // 영수증 인증 섹션 숨기기 & 초기화
                    // 1. upload팝업을 숨깁니다.
                    document.getElementById('uploadPopup').style.display = 'none';
                    // 2. 파일 업로드 필드, 텍스트 입력 필드 등이 있다면, 이러한 필드들을 비우거나 초기 상태로 돌려놓기
                    document.getElementById('ocrVerificationSection').style.display = 'block';
                    // 3. 내부의 입력 필드나 선택 옵션을 초기화
                    document.querySelector('#uploadPopup input[type="file"]').value = '';
                    document.getElementById('reviewPopup').style.display = 'block';
                }
            },
            error(err) {
                console.log(err);
                alert('OCR 처리 중 오류가 발생했습니다.');
            },
            complete() {
                e.target.reset(); // 폼 초기화 (오류날수도 있음)
            }
        });
    };

    // // 리뷰 등록 폼 제출 시 데이터 검증
    // document.querySelector(".reviewForm").addEventListener('submit', (e) => {
    //     e.preventDefault();
    //
    //     if (validateOcrData()) {
    //         const form = e.target;
    //
    //         const reviewData = {
    //             entName: form.querySelector('input[name="entName"]').value,
    //             bizNum: form.querySelector('input[name="bizNum"]').value,
    //             paymentDate: form.querySelector('input[name="paymentDate"]').value,
    //             totalPrice: form.querySelector('input[name="totalPrice"]').value,
    //         };
    //
    //         console.log("등록한 리뷰 정보: ", reviewData); // 데이터가 제대로 변환되었는지 확인
    //         alert("리뷰가 등록되었습니다.");
    //
    //         form.submit(); // 서버로 폼 제출
    //     }
    // });
    document.querySelector(".reviewForm").addEventListener('submit', (e) => {
        // OCR로 읽어온 데이터를 가져오기
        const form = e.target;
        const reviewData = {
            entId: form.querySelector('input[name="entId"]').value,
            userId: form.querySelector('input[name="userId"]').value,
            rating: parseInt(form.querySelector('input[name="rating"]:checked').value),
            reviewContent: form.querySelector('textarea[name="reviewContent"]').value,
            bizNum: form.querySelector('input[name="bizNum"]').value.replace(/-/g, ""), // 사업자번호에서 "-" 제거
            entName: form.querySelector('input[name="entName"]').value,
            paymentDate: parseDate(form.querySelector('input[name="paymentDate"]').value), // 날짜 포맷 변환
            totalPrice: parseInt(form.querySelector('input[name="totalPrice"]').value.replace(/,/g, "")) // 쉼표 제거 후 정수로 변환
        };

        console.log("전처리된 리뷰 데이터: ", reviewData);

        // 중복 영수증 확인을 위한 Ajax 요청
        $.ajax({
            url: '/enterprise/hospital/checkReceipt',
            method: 'POST',
            data: JSON.stringify(reviewData),
            contentType: 'application/json',
            success: function (isDuplicate) {
                if (isDuplicate) {
                    alert('중복된 영수증입니다. 리뷰를 등록할 수 없습니다.');
                } else {
                    alert('리뷰가 등록되었습니다.');
                    form.submit(); // 중복이 아닌 경우 폼 제출
                }
            },
            error: function (err) {
                console.log(err);
                alert('영수증 중복 확인 중 오류가 발생했습니다.');
            }
        });
    });

// 날짜 포맷을 변환하는 함수 (Java에서의 전처리 로직과 동일)
    function parseDate(dateStr) {
        const formatter1 = /^\d{4}-\d{2}-\d{2}$/; // yyyy-MM-dd
        const formatter2 = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/; // yyyy-MM-dd HH:mm
        const formatter3 = /^\d{4}년 \d{1,2}월 \d{1,2}일$/; // yyyy년 M월 d일

        let date;

        if (formatter1.test(dateStr)) {
            date = new Date(dateStr);
        } else if (formatter2.test(dateStr)) {
            date = new Date(dateStr.replace(' ', 'T'));
        } else if (formatter3.test(dateStr)) {
            const parts = dateStr.match(/(\d{4})년 (\d{1,2})월 (\d{1,2})일/);
            date = new Date(parts[1], parts[2] - 1, parts[3]);
        } else {
            throw new Error("지원되지 않는 날짜 형식입니다: " + dateStr);
        }

        return date.toISOString().split('T')[0]; // yyyy-MM-dd 형식으로 반환
    }

    // 리뷰등록 모달 닫기 버튼 핸들링
    document.querySelector('#closeBtn2').onclick = function() {
        document.getElementById('reviewPopup').style.display = 'none';
    };
});
