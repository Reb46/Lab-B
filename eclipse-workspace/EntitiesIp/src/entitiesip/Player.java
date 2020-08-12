/**
 * 
 */
package entitiesip;

import java.net.Socket;

/**
 * @author massaro
 *
 */
public class Player {

	String nickName;
	Socket socket;

	public Player(String nickName,Socket socket) {

		this.nickName = nickName;
		this.socket = socket;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}



}
