package entitiesip;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author massa
 *
 */
public class Validator {

	// espressione regolare per uid: caratteri minuscoli,numeri,underscore,lunghezza tra 5 e 12
	private final String uidPattern= "^[a-z0-9_]{5,12}$";
	
	// espressione regolare per verificare la correttezza di un indirizzo email
	private final String emailPattern = "^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@"
			+ "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

	
	//espressione regolare per verificare la correttezza di un nome/cognome
	private final String surnameName = "[a-z]+([ '-][a-z]+)*";


	/* La password deve rispettare le seguenti regole:almeno un numero,
	 almeno una lettere minuscola, almeno una lettera maiuscola
	 almeno un carattere speciale(.#$%^&+=),nessuno spazio,almeno 8 caratteri");*/

	private final String password = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.#$%^&+=])(?=\\S+$).{8,}";

	private final String nickName =  "[a-zA-Z0-9\\._\\-]{3,}";
	private Pattern pattern;
	private Matcher matcher;


	public Validator(){

	}
	
	
	/**
 	 Metodo che permette di convalidare il formato di un uid.
 	 @param uid Stringa che corrisponde all'uid da controllare
 	 @return Restituisce True o False a seconda che l'uid sia valido o meno 
	 */
	public boolean isUidValid(String uid){
		pattern = Pattern.compile(uidPattern);
		matcher = pattern.matcher(uid);
		if(matcher.matches())
			return true;

		return false;
	}
	
	
	/**
  	 Metodo che permette di convalidare il formato di un indirizzo di posta elettronica.
  	 @param email Stringa che corrisponde all'email da controllare
  	 @return Restituisce True o False a seconda che l'email sia valida o meno 
	 */

	
	
	
	public boolean isEmailValid(String email){
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		if(matcher.matches())
			return true;

		return false;

	}
	/**
 	 Metodo che permette di convalidare il formato del nickName.
 	 @param nick Stringa che corrisponde al nick da controllare
 	 @return Restituisce True o False a seconda che il nick sia valido o meno 
	 */
	public boolean isNickName(String nick){
		pattern = Pattern.compile(nickName);
		matcher = pattern.matcher(nick);
		if(matcher.matches())
			return true;

		return false;

	}
	/**
  	 Metodo che permette di convalidare il formato di un nome o di un cognome.
  	 @param name_surname Stringa che corrisponde al nome o al cognome da convalidare
  	 @return Restituisce True o False a seconda che il nome o il cognome siano validi o meno 
	 */

	public boolean isNameSurname(String name_surname){
		pattern = Pattern.compile(surnameName);
		matcher = pattern.matcher(name_surname);
		if(matcher.matches())
			return true;

		return false;


	}
	/**
 	 Metodo che permette di convalidare il formato di una password.
 	 @param passw Stringa che corrisponde alla password da convalidare
 	 @return Restituisce True o False a seconda che la password sia valida o meno 
	 */

	public boolean isPassword(String pw){
		pattern = Pattern.compile(password);
		matcher = pattern.matcher(pw);
		if(matcher.matches())
			return true;

		return false;

	}

	/**
	 * La password viene crittografata in sha-1
	 * @param pw
	 * @return
	 */
	public String sha1(String pw) {

		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			byte[] result = mDigest.digest(pw.getBytes());

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toHexString((result[i] & 0xff)));
			}

			return sb.toString();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
/**
 * Metodo che genera un codice casuale per l'attivazione dell'utente in fase di registrazione o per il reset della password
 * @return
 */
	public String codeGenerator() {
		int n = 8;
		SecureRandom secure = new SecureRandom();
		StringBuilder sBuilder = new StringBuilder(n);
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


		for(int i=0;i<n;i++) 
			sBuilder.append(chars.charAt(secure.nextInt(chars.length())));
		return sBuilder.toString();



	}

}