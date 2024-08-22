$(document).ready(function () {
    if (window.location.pathname !== '/auth/login') { // 로그인 페이지에서는 모달을 띄우지 않음
        $('#loginModal').modal('show');
    }
});