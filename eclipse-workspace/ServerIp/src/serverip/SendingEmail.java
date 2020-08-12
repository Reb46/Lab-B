package serverip;
import java.util.Properties;


import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;

public class SendingEmail {
	public static String username = "";		
	public static String password = "";


	public SendingEmail(String username,String password,String to,String subject,String body) throws SendFailedException, MessagingException
	{

		String host = "smtp.gmail.com";
		String from= username;

		Properties props = System.getProperties();
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port",587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getInstance(props);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
		msg.setSubject(subject);
		msg.setText(body);

		Transport.send(msg,username,password);
		System.out.println("\nMail inviata con successo!");   
	}
}

