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
	
	public static String getIpDestino() throws IOException {

		String ip_destino = properties.getPropValues().getProperty("ip_destino");
		return ip_destino;
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


}
