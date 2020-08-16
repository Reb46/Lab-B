package entitiesip;

import java.util.ArrayList;




public interface InterfaceServer {

	public String registerUser(UserIp userRdF);
	public String delete(String email);
	public String login(String email,String password,String type);
	public String loginAdmin(String uid,String password,String type);
	public String activeUser(String nick,String code);
	public String checkEmail(String email);
	public String checkNameGame(String nameGame);
	public String checkNick(String nickName);
	public String checkPassword(String email,String password);
	public String checkPasswordAdmin(String uid, String password);
	public String resetPassword(String email);
	public String editPasswordAdmin(String uid, String password);
	public String editName(String email,String name);
	public String editSurname(String email,String surname);
	public String editNick(String email,String nickName);
	public String editPassword(String email,String password);
	public String getName(String email);
	public String getSurname(String email);
	public String getNick(String email);
	public String getAdmin(String type);
	public String getRandomChar(String nameGame);
	public int getIscritti(String nameGame);
	public int getRichiesti(String nameGame);
	public String updateIscritti(String nameGame);
	public String createNewGame(Game newGame);
	public ArrayList<Game> getMatch();
	public ArrayList<UserIp> getUser();
	public String createMatch(Match match);
	public ArrayList<String> getNickMatch(String nome_Partita);
	public String checkAvvio(String nameGame);
	public String setAbbandono(String nameGame,String nick);
	public String deleteGame(String nameGame);
	public int getScores(String nameGame,String nick, String sessione);
	public String addWord(Word word);
	public ArrayList<String> getWord(String nameGame,String nick, int sessione);
	public String getHost();
	public String userPostGres();
	public String passwPostGres();
	
	
}


