// /**
//  * STOMP 프로토콜
//  * STOMP(Simple Text Oriented Messaging Protocol)은 메시지 큐 시스템에서 널리 사용되는
//  * 텍스트 기반의 프로토콜임. 주로 웹소켓 위에서 메세지를 주고받기 위해 사용됨
//  *
//  * 웹소켓은 클라이언트와 서버 간의 양방향 통신을 가능하게 하는 프로토콜로, 실시간 메시징 시스템을 구현할 때 자주 사용됨
//  *
//  */
//
//
//
// /*<![CDATA[*/
// // '/websocket'은 서버와의 웹소켓 연결을 설정하는 엔드포인트를 의미한다. 즉 서버와의 실시간 연결을 시작하는 출발점
// let socket = new SockJS('/websocket');
//
// // SockJs를 통해 설정된 연결을 사용해 STOMP프로토콜로 메시지를 주고받을 수 있도록 stompClient 라는 객체를 반환함
// let stompClient = Stomp.over(socket)
//
// stompClient.connect({}, function (frame) {
//     console.log('Connected:' + frame);
//     // 구독 (Subscribe) 기능
//     /**
//      * - stompClient.subscribe() 메서드는 클라이언트가 특정 주제(topic)를 구독하는 기능을 수행함
//      * - 주제(topic)는 메세지가 게시 (publish)될 위치를 의미함, 서버가 해당 주제에 메세지를 전송하면 이를 구독하고 있는 모든 클라이언트가 그 메세지를 "모두" 수신하게 됨
//      *
//      */                                                         // 클라이언트가 메세지를 수신했을 때 호출되는 콜백 함수
//     stompClient.subscribe('/topic/petsitterfinder', function (message) {
//         console.log(message)
//         let notification = message.body;
//         showNotification(notification)
//     });
// });
//
// function showNotification(message) {
//     if (Notification.permission === "granted") {
//         console.log("permission granted?" + Notification.permission)
//         let notification = new Notification('🔔새로운 예약 알림🔔', {
//             body: message,
//             requireInteraction: true
//
//         })
//     } else ("permission granted? ㄴㄴ?" + Notification.permission)
// }
//
// /*]]>*/