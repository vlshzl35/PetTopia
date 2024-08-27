//package com.sh.pettopia.parktj.petsitterfinder.webSocket;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    // 웹사이트와 서버 사이에 실시간으로 메세지를 주고받기 위한 연결 지점을 만드는 작업을 하는 코드
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // 여기서부터 웹소켓 연결을 시작할거다.
//        // "StompEndpointRegistry" 는 만들고 싶은 연결 지점을 등록할 수 있는 도구라 생각
//        registry.addEndpoint("/websocket") // 실제로 웹소켓 연결 지점을 만들어 주는 역할, 웹사이트가 서버와 연결하고 싶을 때
//                // 이 지점("/websocket")으로 오세요~ 라고 하는 것과 같은 말, http://서버주소/websocket으로 요청을 보내면, 서버가 아 이 사람이 웹소켓을 통해 연결하고 싶구나 인식
//                .setAllowedOriginPatterns("http:/localhost:8080", "http:/223.130.146.203:8080") // 이 주소로 온 요청만 서버와 연결할 수 있도록 함
//                .withSockJS(); // 만약 웹소켓을 사용할 수 없는 환경이라면, 대체 방법으로 연결을 시도해라 라는 옵션
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic"); // "/topic"은 메시지가 갈 수 있는 주소라고 생각, 예를 들어, 서버에서 모두에게 알려줘야 할 메세지가 있다면, 이걸 "/topic"이라는 주소로 보낼 수 있음
//        config.setApplicationDestinationPrefixes("/app"); // 웹사이트가 서버로 메세지를 보낼 때, 어느 길로 가야할지를 정해주는 것
//        // 예를 들어, 웹사이트에서 서버에게 안녕하세요라는 메세지를 보내고 싶을 때, 이 메세지를 '/app' 으로 시작하는 주소로 보내야 하는 것임
//    }
//}
