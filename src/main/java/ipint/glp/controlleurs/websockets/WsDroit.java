package ipint.glp.controlleurs.websockets;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WsDroit extends TextWebSocketHandler {
	private static ArrayList<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession newSession)
			throws Exception {
		super.afterConnectionEstablished(newSession);
		try {
			//donner la nouvelle personne connectée aux personnes déjà connectées
			JSONObject loginNewSession = new JSONObject();
			loginNewSession.put("type", "login");
			if (newSession.getPrincipal() != null) {
				loginNewSession.put("expediteur", newSession.getPrincipal().getName());
			}
			for(WebSocketSession session : (ArrayList<WebSocketSession>)sessions.clone()) {
				if (session.isOpen()) {
					session.sendMessage(new TextMessage(loginNewSession.toString()));
				} else {
					sessions.remove(session);
				}
			}
			
			//donner les personnes déjà connectées à la nouvelle personne connectée
			if (!sessions.isEmpty()) {
				JSONObject logins = new JSONObject();
				logins.put("type", "logins");
				for(WebSocketSession session : sessions) {
					if (session.getPrincipal() != null) {
						logins.append("logins",session.getPrincipal().getName());
					}
				}
				newSession.sendMessage(new TextMessage(logins.toString()));
			}
			
			//mémorisation de la session de la personne connectée
			sessions.add(newSession);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionClosed(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.CloseStatus)
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession sessionClose,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(sessionClose, status);
		try {
			JSONObject jSONObject = new JSONObject();
			jSONObject.put("type", "logout");
			if (sessionClose.getPrincipal() != null) {
				jSONObject.put("expediteur", sessionClose.getPrincipal().getName());
			}
			sessions.remove(sessionClose);
			for(WebSocketSession session : (ArrayList<WebSocketSession>)sessions.clone()) {
				if (session.isOpen()) {
					session.sendMessage(new TextMessage(jSONObject.toString()));
				} else {
					sessions.remove(session);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTextMessage(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.TextMessage)
	 */
	@Override
	protected void handleTextMessage(WebSocketSession wss,
			TextMessage message) throws Exception {
		super.handleTextMessage(wss, message);
		try {
			for(WebSocketSession session : (ArrayList<WebSocketSession>)sessions.clone()) {
				if (!session.equals(wss)) {
					if (session.isOpen()) {
						session.sendMessage(message);
					} else {
						sessions.remove(session);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}