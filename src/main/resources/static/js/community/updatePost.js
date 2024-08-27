// 게시글 등록되고 나서 alert 해줍니다.
function isValid() {
    if (true) {
        alert('✏️게시글 수정이 완료되었습니다!✏️')
    }
}

function filterPosts(category) {
    var posts = document.getElementsByClassName('post-item');
    for (var i = 0; i < posts.length; i++) {
        if (category === 'all') {
            posts[i].style.display = 'flex';
        } else {
            if (posts[i].getAttribute('data-category') === category) {
                posts[i].style.display = 'flex';
            } else {
                posts[i].style.display = 'none';
            }
        }
    }
}