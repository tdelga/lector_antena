package main;

import java.io.IOException;

public class ReadConfigMain {
	
	static GetPropertyValues properties = new GetPropertyValues();
	
	public static String getHost() throws IOException {

		String host = properties.getPropValues().getProperty("host");
		return host;
	}
	
	public static String getIdCabina() throws IOException {

		String id_cabina = properties.getPropValues().getProperty("id_cabina");
		return id_cabina;
	}
	
	public static ApiServer getIpDestino() throws IOException {

		String ip_destino = properties.getPropValues().getProperty("ip_destino");

		ApiServer api_server = new ApiServer(ip_destino);
		return api_server;
	}
	
	public static String getLogMaxSize() throws IOException {

		String log_max_size = properties.getPropValues().getProperty("log_max_size");
		return log_max_size;
	}
	
	public static LogFile getLogFile() throws IOException {

		// OBJETO LOG FILE
		String log_max_size = properties.getPropValues().getProperty("log_max_size");
		String log_path = properties.getPropValues().getProperty("log_path");
		LogFile log_file = new LogFile(log_path,Integer.parseInt(log_max_size));
		return log_file;
	}
	
	public static Mail getMail() throws IOException {

		// OBJETO LOG FILE
		String cuenta = properties.getPropValues().getProperty("mail_cuenta");
		String password = properties.getPropValues().getProperty("mail_password");
		String destino = properties.getPropValues().getProperty("mail_destino");
		String log_path = properties.getPropValues().getProperty("log_path");
		String id_cabina = properties.getPropValues().getProperty("id_cabina");
		Mail mail = new Mail(cuenta,password,destino,log_path,id_cabina);
		return mail;
	}


}
