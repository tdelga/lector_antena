package main;

public class ApiServer {
	
	static String  ip_destino;
	static String  id_cabina;
	
	// CONSTRUCTOR
	public ApiServer(String host_destino){
		
		ip_destino = host_destino;
	}

	// ENVIA LECTURA DE UN TAG
	public static void enviarLectura(String epc,String datetime){
		
		System.out.println("EPC " + epc + " Cabina " + id_cabina + " at time:" + datetime );
	}

	// SETEO DE ID CABINA
	public static void setIdCabina(String cabina_id) {
		
		id_cabina = cabina_id;
	}
	
}
