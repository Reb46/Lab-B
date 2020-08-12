package entitiesip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author massa
 *
 */

// questa classe si occupa della generazione delle 16 lettere casuali del paroliere 



public class RandomChar {
	private String numeri = ""; // variabile usata per memorizzare il numero corrispondente al dado

	private String lettere = ""; // varaibile usata per memorizzare le lettere di ogni dado

	private int index; // uso la variabile index di tipo int per salvare il valore casuale compreso tra 0 e la lunghezza della stringa(6 nel nostro caso)

	private Random random = new Random();

	private String letteraDado= ""; //variabile usata per memorizzare le lettere del dado

	private String stringaLettere = ""; // variabile usata per memorizzare le 16 lettere random 

	private StringBuffer sb = new StringBuffer();

	//Hashmap dei 16 dadi. Il numero del dado corrisponde alla chiave; le 6 facce del dado al valore
	private HashMap<Integer,String> dadi = new HashMap<Integer,String>();

	// Arraylist contenente il numero del dado con tutte le rispettive lettere 
	private ArrayList<String> listaCompleta = new ArrayList<String>();

	// Arraylist contente le lettere casuali generate dai dadi
	ArrayList<String> lettereCasuali = new  ArrayList<String>(); 

	// Arraylist contente il numero del dado che ha generato la corrispettiva lettera
	ArrayList<String> numeriDadi = new  ArrayList<String>(); 

	public RandomChar() {

	}

	public String setChar(){

		dadi.put(1,"BAOOQM");
		dadi.put(2,"UTESLP");
		dadi.put(3,"IGENVT");
		dadi.put(4,"OULIER");
		dadi.put(5,"ACESLR");
		dadi.put(6,"RATIBL");
		dadi.put(7,"SMIROA");
		dadi.put(8,"ISEEFH");
		dadi.put(9,"SOTEND");
		dadi.put(10,"AICOFR");
		dadi.put(11,"VNZDAE");
		dadi.put(12,"IEATAO");
		dadi.put(13,"OTUCEN");
		dadi.put(14,"NOLGUE");
		dadi.put(15,"DCMPAE");
		dadi.put(16,"ERINSH");

		// Map.Entry permette di stampare la coppia (chiave-valore) attaverso i metodi getKey e getValue.
		// tutte le coppie presenti verranno aggiunte all'arraylist 'completi'
		for(Map.Entry<Integer, String> entry: dadi.entrySet()) {
			listaCompleta.add(entry.getKey() +"," + entry.getValue());

		} 

		// tramite il metodo statico shuffle, della classe Collections, permuto in modo casuale la lista(listaCompleta) passata come parametro
		Collections.shuffle(listaCompleta); 


		//scorro tutti gli elementi dell'arraylist

		for(int i=0; i<listaCompleta.size();i++) {

			// divido ogni stringa presente nell'array tramite il metodo split della classe String.
			// la parte numerica e quella letterale vengono salvate in due variali distinte di tipo string

			String[] split = listaCompleta.get(i).split(","); 
			numeri = split[0]; // numeri de dadi
			lettere = split[1]; // le lettere dei dadi

			// uso la variabile index di tipo int per salvare il valore casuale compreso tra 0 e la lunghezza della stringa(6 nel nostro caso)

			index = random.nextInt(lettere.length());

			// memorizzo la lettera del dado. index rapprenta la posizione della lettera passata come parametro
			letteraDado = Character.toString(lettere.charAt(index));

			// ogni numero dei 16 dadi viene aggiunto all'arraylist
			numeriDadi.add(numeri);

			// ogni lettera casuale dei 16 dadi viene aggiunta all'arraylist
			lettereCasuali.add(letteraDado);

		}

		for(String x:lettereCasuali)
			sb.append(x);
		stringaLettere =sb.toString();
		return stringaLettere;
	}



}
