function setCategory(category, element) {
    document.getElementById('categoryInput').value = category;
    filterPosts();

    // 모든 카테고리 버튼에서 'active' 클래스 제거
    const buttons = document.querySelectorAll('.nav-link');
    buttons.forEach(function (btn) {
        btn.classList.remove('active');
    });

    // 선택된 버튼에 'active' 클래스 추가
    element.classList.add('active');
}

function filterPosts() {
    var category = document.getElementById('categoryInput').value;
    var posts = document.getElementById('postList').getElementsByClassName('post-item');
    for (var i = 0; i < posts.length; i++) {
        var postCategory = posts[i].getAttribute('data-category');
        if (category === "ALL" || category === postCategory) {
            posts[i].style.display = "";
        } else {
            posts[i].style.display = "none";
        }
    }
}

document.addEventListener("DOMContentLoaded", function () {
    filterPosts(); // 페이지 로드 시 초기화
});

// Java 코드에서 addFlashAttribute로 담은 message를 alert를 해주기 위한 코드입니다.
const message = /*[[${message != null ? '\'' + message + '\'' : 'null'}]]*/ null;
if (message !== null) {
    alert(message);
}