package entitiesip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// questa classe si occupa della generazione delle 16 lettere casuali del paroliere 



public class RandomChar {

	List<String> facceDadi = new ArrayList<String>(); // arraylist contenente i 16 dadi con le rispettive facce

	String[] arrayDadi; // array che andrà a contenere i 16 dadi con le rispettive facce

	String[] sediciLettere = new String[16]; // array che andrà a contenere le 16 lettere finali generate casualmente

	int index; // uso la variabile index di tipo int per salvare il valore casuale compreso tra 0 e la lunghezza della stringa (le 6 facce di ogni dado)

	Random random = new Random();// Un'istanza di questa classe viene utilizzata per generare un flusso di numeri casuali.


	String letteraDado; // variabile utilizzata per memorizzare la singola lettera scelta casualmente tra ognuna delle 6 facce dei 16 dadi

	StringBuilder sb = new StringBuilder();

	String stringaFinale; // variabile contenente le 16 lettere utili per creazione dello scacchiere


	public RandomChar() {
	}

	/**
	 * Metodo che ritorna una stringa contenente le 16 lettere generate casualmente
	 * @return stringaFinale
	 */
	public String setChar(){

		facceDadi.add("BAOOQM");
		facceDadi.add("UTESLP");
		facceDadi.add("IGENVT");
		facceDadi.add("OULIER");
		facceDadi.add("ACESLR");
		facceDadi.add("RATIBL");
		facceDadi.add("SMIROA");
		facceDadi.add("ISEEFH");
		facceDadi.add("SOTEND");
		facceDadi.add("AICOFR");
		facceDadi.add("VNZDAE");
		facceDadi.add("IEATAO");
		facceDadi.add("OTUCEN");
		facceDadi.add("NOLGUE");
		facceDadi.add("DCMPAE");
		facceDadi.add("ERINSH");

		// converto l'arraylist "faccedadi" in un array(arrayDadi) contenente i 16 dadi con le rispettive facce
		arrayDadi = facceDadi.toArray(new String[facceDadi.size()]); 

		// con il metodo statico shuffle, permuto in modo casuale l'array paroliere passato come parametro
		Collections.shuffle(Arrays.asList(arrayDadi)); 


		for(int i=0;i<arrayDadi.length;i++) {

			// uso la variabile index di tipo int per salvare il valore casuale compreso tra 0 e la lunghezza della i-esima faccia del dado
			//(ogni faccia di un dado è una stringa)
			index = random.nextInt(arrayDadi[i].length());

			// memorizzo la lettera scelta casualmente dalla i-esima faccia/stringa. index rapprenta la posizione casuale salvata nell'istruzione precedente
			letteraDado = Character.toString(arrayDadi[i].charAt(index));

			// array contenente le 16 lettere finali
			sediciLettere[i] = letteraDado;
		}


		// ognuna delle sedici lettere scelte casualmente vengono aggiunte alla variabile sb, che verra assegnata a stringaFinale
		for(String x:sediciLettere)
			sb.append(x); 
		stringaFinale =sb.toString();
		return stringaFinale;
	}



}
