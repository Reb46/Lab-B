/**
 * 
 */
package entitiesip;

import java.io.Serializable;



/**
 * @author massa
 *
 */
public class Match  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomePartita;
	private String nick;
	public Match(String nomePartita,String nick) {

		this.nomePartita = nomePartita;
		this.nick = nick;
		
	}
	
	
	/**
	 * @return the nomePartita
	 */
	
	
	public String getNomePartita() {
		return nomePartita;
	}
	
	
	/**
	 * @param nomePartita the nomePartita to set
	 */
	public void setNomePartita(String nomePartita) {
		this.nomePartita = nomePartita;
	}
	
	
	/**
	 * @return the nick
	 */
	
	public String getNick() {
		return nick;
	}
	
	
	/**
	 * @param nick the nick to set
	 */
	
	public void setNick(String nick) {
		this.nick = nick;
	}

}