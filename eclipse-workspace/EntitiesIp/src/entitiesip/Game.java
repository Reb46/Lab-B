package entitiesip;

import java.io.Serializable;


public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String date;
	private String hour;
	private int numberPlayer;
	private String randomChar;
	public Game(String name, String date, String hour,int numberPlayer,String randomChar) {

		this.name = name;
		this.date = date;
		this.hour = hour;
		this.numberPlayer=numberPlayer;
		this.randomChar = randomChar;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return the numberPlayer
	 */
	public int getNumberPlayer() {
		return numberPlayer;
	}
	/**
	 * @param numberPlayer the numberPlayer to set
	 */
	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}
	/**
	 * @return the randomChar
	 */
	public String getRandomChar() {
		return randomChar;
	}
	/**
	 * @param randomChar the randomChar to set
	 */
	public void setRandomChar(String randomChar) {
		this.randomChar = randomChar;
	}


}