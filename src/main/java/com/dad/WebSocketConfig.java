package com.dad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //habilita el manejo de mensajes de WebSocket apoyado por el broker de mensajes
public abstract class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	/* Configura el broker que tiene WebSocketMessageBrokerConfigurer
	 * por defecto
	 * */
	/* Habilita un broker de mensajes en memoria 
	 * que envía los mensajes de vuelta al cliente
	 * con destinos con el prefijo /topic
	 * */ 
	/* Se designa el prefijo /app para los mensajes que están destinados
	 * a los métodos MessageMapping
	 * /app/hello es el endpoint que GreetingController.greeting()
	 * está destinado a manejar
	 * */
	/* Registra el endpoint /gs-guide-websocket, habilitando las opciones
	 * de SockJS para que se puedan usar transportes alternativos en caso de
	 * que WebSocket no estuviera disponible. El cliente de SockJS intenta
	 * conectarse a /gs-guide-websocket y emplea el mejor transporte disponible
	 * */
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/gs-guide-websocket").withSockJS();
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
    }
    
//    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//    	messages.simpDestMatchers("/user/*").authenticated();
//    	
//    }
	
	
	

}
