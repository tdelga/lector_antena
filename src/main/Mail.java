package main;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail {

	static String  mail_cuenta;
	static String  mail_password;
	static String  mail_destino;
	static String  log_path;
	static String  id_Cabina;
	
	public Mail(String cuenta, String password, String destino, String log,String cabina) {
		mail_cuenta = cuenta;
		mail_password = password;
		mail_destino = destino;
		log_path = log;
		id_Cabina = cabina;
	}
	
	public void enviarLog() {
		
	      
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(mail_cuenta, mail_password);
	                    }
	                });    

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(mail_cuenta));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail_destino));
	         

	         // Set Subject: header field
	         String right_now_time = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	         message.setSubject(id_Cabina +" - " +right_now_time);

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText("Reporte al encender formacion");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(log_path);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(log_path);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }		
		
	}

}
