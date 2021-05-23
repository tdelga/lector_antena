package main;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

	@SerializedName("access_token")
    private String access_token;

	@SerializedName("exito")
    private String exito;
	
    public LoginResponse( String access_token, String exito) {
        this.access_token = access_token;
        this.exito = exito;
    }

    public String getValue() {
        return access_token;
    }
    
    public boolean getExito() {
    	if(exito.equals("true")){
    		return true;
    	}
        return false;
    }

    public void setAccess_Token(String value) {
        this.access_token = value;
    }
    
    public void setAccessToken(String value) {
        this.access_token = value;
    }

}