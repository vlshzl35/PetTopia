function showContent(feature) {
    const description = document.getElementById('feature-description');
    const features = document.querySelectorAll('.features span');

    features.forEach(span => {
        span.classList.remove('active');
    });

    switch (feature) {
        case '펫시팅':
            description.textContent = '다양한 조건으로 일대일 맞춤 돌봄 서비스를 제공합니다.';
            break;
        case '병원 약국 미용':
            description.textContent = '위치 기반으로 펫의 건강과 미용을 위한 병원, 약국, 미용실 위치 모든 것을 제공합니다.';
            break;
        case '커뮤니티':
            description.textContent = '반려동물 주인들과의 커뮤니티를 통해 정보 공유와 소통을 할 수 있습니다.';
            break;
        default:
            description.textContent = '';
    }

    document.querySelector(`span[onclick="showContent('${feature}')"]`).classList.add('active');
}

window.onload = function () {
    const features = document.querySelectorAll('.features span');
    features.forEach(span => {
        span.addEventListener('click', function () {
            showContent(this.textContent);
        });
    });
}

// 스크롤 이벤트를 통해 요소들이 보이는 순간 class를 추가
document.addEventListener('DOMContentLoaded', function () {
    window.addEventListener('scroll', function () {
        const elements = document.querySelectorAll('.text-content,.trustworthy-title, .trustworthy-item, .trustworthy-content,.main-image, .service-title, .review-container, .trustworthy-container, .enterprise-button');

        elements.forEach(function (element) {
            const position = element.getBoundingClientRect().top;
            const screenPosition = window.innerHeight;

            if (position < screenPosition) {
                element.classList.add('show');
            }
        });
    });
});