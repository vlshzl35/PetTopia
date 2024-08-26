$(document).ready(function () {
    // 페이지 로드 시 모달 창을 띄웁니다.
    $('#loginModal').modal('show');

    // "확인" 버튼 클릭 시 로그인 페이지로 이동합니다.
    $('#confirmLogin').click(function () {
        window.location.href = '/auth/login';  // 실제 로그인 페이지 경로로 변경
    });

    // 플래시 메시지가 있으면 show 클래스를 추가하여 표시
    const flashMessage = $('#flashMessage');
    if (flashMessage.length) {
        flashMessage.addClass('show');
        setTimeout(function () {
            flashMessage.addClass('hide');
            setTimeout(function () {
                flashMessage.removeClass('show').removeClass('hide');
            }, 500);
        }, 5000); // 5초 동안 표시
    }
});