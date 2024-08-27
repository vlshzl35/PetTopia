document.getElementById('comment-regist').addEventListener('submit', function (event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.

    const content = document.getElementById('comment').value;
    const postId = document.getElementById('commentPostId').value;

    $.ajax({
        url: '/community/postDetail',
        method: 'POST',
        data: {
            postId: postId,
            content: content
        },
        success: function (response) {
            alert('댓글 등록 완료!');

            // 댓글 등록 후 성공 응답을 받으면, 댓글 리스트에 새로운 댓글을 추가합니다.
            const commentList = document.querySelector('.comment-list');

            // 새로운 댓글 요소를 생성합니다.
            const newComment = document.createElement('li');

            // 새로운 댓글 요소에 comment 클래스를 추가합니다
            newComment.classList.add('comment');

            // 새로운 댓글의 HTML 내용을 설정
            newComment.innerHTML = `
                        <div class="comment-body">
                            <div class="post-comment-headers">
                                <span>${response.nickName}</span>
                                <span>|</span>
                                <span>${new Date(response.createdAt)
                .toLocaleString('ko-KR', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit',
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                })}
                                </span>
                            </div>
                            <div>${response.content}</div>
                            <div>
                                <form class="delete-comment-form" method="post" action="/community/comments/deleteComment">
                                    <input type="hidden" name="commentId" value="${response.id}">
                                    <input type="hidden" name="postId" value="${response.postId}">
                                    <button class="btn btn-danger btn-sm delete-comment" id = "deleteComment" type="button">삭제</button>
                                </form>
                            </div>
                        </div>
                    `;

            // 새로운 댓글 요소를 댓글 리스트에 추가합니다.
            commentList.appendChild(newComment);


            // 입력 필드를 초기화합니다.
            document.getElementById('comment').value = '';
        },
        error: function () {
            console.log('댓글 등록에 실패했습니다.');
        }
    });
});

document.addEventListener('input', function (event) {
    if (event.target.tagName.toLowerCase() === 'textarea') {
        event.target.style.height = 'auto';
        event.target.style.height = (event.target.scrollHeight) + 'px';
    }
}, false);

// 게시글 삭제 버튼 핸들링 하는 코드입니다.
document.getElementById('deleteButton').addEventListener('click', deletePost);

function deletePost() {
    console.log('deletePost 함수 호출');
    confirm('정말 이 게시글을 삭제하시겠습니까?') && document.deletePostFrm.submit();
}

// // 댓글 삭제 버튼 핸들링 하는 코드입니다.
function deleteCom() {
    console.log('deleteComment 함수 호출');
    if (confirm('댓글을 삭제하시겠습니까?')) {
        document.deleteCommentForm.submit();
        alert('✨댓글 삭제 완료✨');
    } else {
        console.log('댓글 삭제 안되ㅜㅜㅡㅜㅜ')
    }
}

// Java 코드에서 addFlashAttribute로 담은 message를 alert를 해주기 위한 코드입니다.
const message = /*[[${message != null ? '\'' + message + '\'' : 'null'}]]*/ null;
if (message !== null) {
    alert(message);
}