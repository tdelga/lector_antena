package main;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


    @POST("/auth")
    Call<LoginResponse> login(@Body JsonObject user);

    
    @POST ("/lecturas/new")
    Call<LecturaResponse> sendLectura(@Header("Authorization") String token, @Body JsonObject lectura);


}
