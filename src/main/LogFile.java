package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.MessageFormat;

public class LogFile {
	
	static String log_path;
	static int log_max_size;
	static FileWriter log_file;

		// CONSTRUCTOR
		public LogFile(String path,int max_size){
			
			log_max_size = max_size;
			log_path = path;		
		}
	
		// CARGA LECTURA DE UN TAG
		public static void guardarLectura(String epc,String datetime) throws IOException{
			
			String new_log = MessageFormat.format("FECHA: {0} EPC: {1}\n", datetime,epc);
			
			log_file = new FileWriter(log_path,true);
			log_file.write(new_log);
			log_file.close();
		}
		
		// REDUCE FILE A N BYTES
		public static void verificarTamanio() throws IOException{
			
			RandomAccessFile file = new RandomAccessFile (log_path,"rw");
			byte[] data = new byte[log_max_size];
			int file_length = (int) file.length();
			System.out.print(file_length);
			
			// SUPERA EL TAMANIO MAXIMO PERMITIDO
			if(file_length > log_max_size) {				
				// PUNTERO A ULTIMOS N BYTES
				file.seek(file_length - log_max_size);
				file.read(data, 0, log_max_size);
				
				//REESCRIBO FILE
				file.seek(0);
				file.write(data,0,log_max_size);
				file.setLength(log_max_size);
			}
			
		    file.close();
		    
		}

}
