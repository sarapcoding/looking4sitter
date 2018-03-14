package interactivecomments;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //habilita el manejo de mensajes de WebSocket apoyado por el broker de mensajes
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	/* Configura el broker que tiene WebSocketMessageBrokerConfigurer
	 * por defecto
	 * */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		/* Habilita un broker de mensajes en memoria 
		 * que envía los mensajes de vuelta al cliente
		 * con destinos con el prefijo /topic
		 * */ 
		config.enableSimpleBroker("/topic");
		/* Se designa el prefijo /app para los mensajes que están destinados
		 * a los métodos MessageMapping
		 * /app/hello es el endpoint que GreetingController.greeting()
		 * está destinado a manejar
		 * */
		config.setApplicationDestinationPrefixes("/app");
	}
	
	/* Registra el endpoint /gs-guide-websocket, habilitando las opciones
	 * de SockJS para que se puedan usar transportes alternativos en caso de
	 * que WebSocket no estuviera disponible. El cliente de SockJS intenta
	 * conectarse a /gs-guide-websocket y emplea el mejor transporte disponible
	 * */
	public void registerStompEndPoints(StompEndpointRegistry registry) {
		//registry.addEndpoint("/gs-guide-websocket/info?t=1521063553304").setAllowedOrigins("*").withSockJS();
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
	}

}
