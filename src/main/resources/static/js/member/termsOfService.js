document.getElementById('agree-all').addEventListener('change', function () {
    const isChecked = this.checked;
    document.getElementById('agree-terms').checked = isChecked;
    document.getElementById('agree-privacy').checked = isChecked;
    document.getElementById('agree-location').checked = isChecked;
});

document.querySelectorAll('.form-check-input').forEach(item => {
    item.addEventListener('change', updateAgreeAllCheckbox);
});

function updateAgreeAllCheckbox() {
    const agreeAllCheckbox = document.getElementById('agree-all');
    const agreeTerms = document.getElementById('agree-terms').checked;
    const agreePrivacy = document.getElementById('agree-privacy').checked;
    const agreeLocation = document.getElementById('agree-location').checked;

    if (!agreeTerms || !agreePrivacy || !agreeLocation) {
        agreeAllCheckbox.checked = false;
    } else {
        agreeAllCheckbox.checked = true;
    }
}

function validateForm() {
    const agreeTerms = document.getElementById('agree-terms').checked;
    const agreePrivacy = document.getElementById('agree-privacy').checked;
    const agreeLocation = document.getElementById('agree-location').checked;

    if (agreeTerms && agreePrivacy && agreeLocation) {
        return true;  // 회원 등록 폼으로 넘어갑니다.
    } else {
        alert('모든 이용 약관에 동의해주세요!');
        return false;  // 모든 체크박스가 체크되어있지 않으면, 다음으로 넘어가지 않습니다.
    }
}