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
	
	// CONSTRUCTOR
	public ApiServer(String host_destino,String cabina_id,String api_username,String api_password){
		
		ip_destino = host_destino;
		id_cabina = cabina_id;
		username = api_username;
		password = api_password;
	}

	// LOGIN
	public static void login(){
		
		JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("password", password);
        Call<LoginResponse> login = ApiAdapter.getApiService().login(body);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse respuesta = response.body();
                if (response.isSuccessful()) {
                	token = respuesta.getValue();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
	}
	
	// ENVIA LECTURA DE UN TAG PREVIO LOGIN
	public static void enviarLecturaEnLogin(final String epc,final String datetime){
		
		JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("password", password);
        Call<LoginResponse> login = ApiAdapter.getApiService().login(body);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse respuesta = response.body();
                if (response.isSuccessful()) {
                	token = respuesta.getValue();
                	// REENVIA LECTURA EN LOGIN EXITOSO
                	JsonObject body = new JsonObject();
                    body.addProperty("id_cabina", id_cabina);
                    body.addProperty("epc", epc);
                    body.addProperty("fecha_lectura", datetime);
                    Call<String> login = ApiAdapter.getApiService().sendLectura(token,body);
                    login.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                        	String respuesta = response.body();
                            if (! response.isSuccessful()) {
                            	ApiServer.login();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
	}
	
	
	// ENVIA LECTURA DE UN TAG
	public static void enviarLectura(final String epc,final String datetime){
		
		JsonObject body = new JsonObject();
        body.addProperty("id_cabina", id_cabina);
        body.addProperty("epc", epc);
        body.addProperty("fecha_lectura", datetime);
        Call<String> login = ApiAdapter.getApiService().sendLectura(token,body);
        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            	String respuesta = response.body();
                if (! response.isSuccessful()) {
                	ApiServer.login();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            	ApiServer.enviarLecturaEnLogin(epc,datetime);
            }
        });
	}
	

	
}
