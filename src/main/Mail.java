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
		
	      	// CONFIG SMPT
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        // LOGIN USER
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(mail_cuenta, mail_password);
	                    }
	                });    

	      try {
	         // SESION
	         MimeMessage message = new MimeMessage(session);

	         // DESDE
	         message.setFrom(new InternetAddress(mail_cuenta));
	         // HASTA
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail_destino));
	         
	         // TITULO
	         String right_now_time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	         message.setSubject(id_Cabina +" - " +right_now_time);
	         
	         // SETEO FILE TYPE
	         Multipart multipart = new MimeMultipart();

	         // BODY MAIL
	         BodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText("Reporte al encender formacion");
	         multipart.addBodyPart(messageBodyPart);

	         // ADJUNTA FILE
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(log_path);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(id_Cabina +" - " +right_now_time + ".txt");
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart );

	         // ENVIO MENSAJE
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }		
		
	}

}
