package entitiesip;
import java.io.Serializable;



public class UserIp implements Serializable { 
	private static final long serialVersionUID = 1L;
	private String surname;
	private String name;
	private String nickName;
	private String email;
	private String password;
	private String type;


	/**
	 * Crea l'oggetto UserIp
	 * @param surname Cognome
	 * @param name Nome
	 * @param nickName NickName 
	 * @param email Indirizzo email
	 * @param password Password
	 */


	public UserIp(String surname,String name,String nickName,String email,String password,String type) {
		
		this.surname = surname;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.type = type;

	}

	

	/**
	 * Ritorna il cognome dell'utente
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Setta il cognome dell'utente
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Ritorna il nome dell'utente
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setta il nome dell'utente
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ritorna il NickName dell'utente
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Setta il NickName dell'utente
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Ritorna email dell'utente
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setta email dell'utente
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Ritorna password dell'utente
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setta password dell'utente
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}