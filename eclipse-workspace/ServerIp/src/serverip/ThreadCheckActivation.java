package serverip;

import java.sql.SQLException;

public class ThreadCheckActivation {

	ManagementServerDb sb = new ManagementServerDb("jdbc:postgresql://127.0.0.1:5432/dbip","postgres","pbkwsclc");

	public ThreadCheckActivation(String nickName) {

		Thread t = new Thread() {

			@Override
			public void run() {

				int i=600;
				while(i>0) {
					try {
						// parte il countdown di 10 minuti
						while(!sb.checkActivationUser(nickName)) { // controlla se l'utente si è registrato
							System.out.println(i--);
							System.out.println("non registrato " + nickName);

							try {
								Thread.sleep(1000); // controllo avviene ogni secondo
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
							if(sb.checkActivationUser(nickName)) {
								i=0;
								System.out.println("registrato adesso"); // se l'utente si è registrato, il countdown si blocca e il thread termina

							}

							else if(i==0 && !sb.checkActivationUser(nickName)) { // se allo scadere dei 10 minuti l'utente non si è attivato, il profilo viene eliminato dal db

								sb.deleteAfter(nickName);
								System.out.println(nickName + " eliminato, ci dispiace");
								System.exit(0);
							}
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}

				}

			}

		};
		t.start();
	}
}
