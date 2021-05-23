package main;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.JsonObject;


public class ApiServer {
	
	static String  ip_destino;
	static String  id_cabina;
	static String  username;
	static String  password;
	static String  token;
	static JsonObject bodyLogin; 
	
	// CONSTRUCTOR
	public ApiServer(String host_destino,String cabina_id,String api_username,String api_password){
		
		ip_destino = host_destino;
		id_cabina = cabina_id;
		username = api_username;
		password = api_password;
		bodyLogin = new JsonObject();
		bodyLogin.addProperty("username", username);
		bodyLogin.addProperty("password", password);
		
		login();
	}

	// LOGIN
	public static void login(){
		

        Call<LoginResponse> login = ApiAdapter.getApiService().login(bodyLogin);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            	LoginResponse respuesta = response.body();
                if (response.isSuccessful() && respuesta.getExito()) {
                	token = "Bearer " + respuesta.getValue();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) { }
        });
	}
	
	// ENVIA LECTURA DE UN TAG PREVIO LOGIN
	public static void enviarLecturaEnLogin(final JsonObject lecturaBody){
		
        Call<LoginResponse> login = ApiAdapter.getApiService().login(bodyLogin);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            	LoginResponse respuesta = response.body();
                if (response.isSuccessful()) {
                	token = "Bearer " + respuesta.getValue();
                	// REENVIA LECTURA EN LOGIN EXITOSO
                    Call<LecturaResponse> send = ApiAdapter.getApiService().sendLectura(token,lecturaBody);
                    send.enqueue(new Callback<LecturaResponse>() {
                        @Override
                        public void onResponse(Call<LecturaResponse> call, Response<LecturaResponse> response) {

                        }
                        @Override
                        public void onFailure(Call<LecturaResponse> call, Throwable t) {
                        	token = null;
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            	token = null;
            }
        });
	}
	
	// ENVIA LECTURA DE UN TAG
	public static void enviarLectura(final String epc,final String datetime){
		
		final JsonObject body = new JsonObject();
        body.addProperty("id_cabina", id_cabina);
        body.addProperty("epc", epc);
        body.addProperty("fecha_lectura", datetime);
        Call<LecturaResponse> send = ApiAdapter.getApiService().sendLectura(token,body);
        send.enqueue(new Callback<LecturaResponse>() {
            @Override
            public void onResponse(Call<LecturaResponse> call, Response<LecturaResponse> response) {
            	LecturaResponse respuesta = response.body();
                if (! response.isSuccessful() || ! respuesta.getExito() ) {
                	ApiServer.enviarLecturaEnLogin(body);
                }
            }

            @Override
            public void onFailure(Call<LecturaResponse> call, Throwable t) {
            	ApiServer.enviarLecturaEnLogin(body);
            }
        });
	}
	

	
}
