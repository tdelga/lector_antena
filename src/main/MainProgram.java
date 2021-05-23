package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.jietong.rfid.uhf.dao.impl.Reader;
import com.jietong.rfid.uhf.service.CallBack;
import com.jietong.rfid.uhf.service.ReaderService;
import com.jietong.rfid.uhf.service.impl.ReaderServiceImpl;

public class MainProgram{
	
	static String host;
	static LogFile log_file;
	static Mail mail;
	static ApiServer api_server;
	static String last_epc_processed = "";
	
	// MAIN PROGRAM
	public static void main(String [] args) throws IOException, Throwable{		
		
		// SERVER CONFIG
		api_server = ReadConfigMain.getServer();
		
		// LOG FILE CONFIG
		log_file = ReadConfigMain.getLogFile();
		LogFile.verificarTamanio();
		
		// SERVICIO LECTOR EN ATENA
		host = ReadConfigMain.getHost();
		ReaderService readerService = new ReaderServiceImpl();
		Reader reader = new Reader();		
		
		//CONEXION SERVICIO
		reader = readerService.connect(host, 0);
		readerService.beginInvV2(reader, new CallBackData());

	    //readerService.disconnect(reader);
	}
	
	// CALLBACK ASIGNADO A LECTURA DE ANTENA
	public static class CallBackData implements CallBack {
		
		@Override
		public void readData(String data, String rssi, String antNo,String deviceNo, String direction) {

			if( ! last_epc_processed.equals(data)) {
				
				//ACTUALIZA ULTIMO PROCESADO
				last_epc_processed = data;
				
				// TIME
				String right_now_time = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date());
				
				//SERVER SEND
				try {
					ApiServer.enviarLectura(last_epc_processed, right_now_time);
				} catch (Exception e) {
					System.out.println("Error al enviar la lectura al servidor");
					e.printStackTrace();
				}
				
				//GUARDA EN LOG
				try {
					LogFile.guardarLectura(last_epc_processed, right_now_time);
				} catch (IOException e) {
					System.out.println("Error al guardar la lectura en el log local");
					e.printStackTrace();
				}
				

			}
		}
	}

}



