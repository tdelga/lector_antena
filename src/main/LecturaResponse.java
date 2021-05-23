package main;

import com.google.gson.annotations.SerializedName;

public class LecturaResponse {

	@SerializedName("exito")
    private String exito;
	
    public LecturaResponse( String exito) {
        this.exito = exito;
    }

    public boolean getExito() {
    	if(exito.equals("true")){
    		return true;
    	}
        return false;
    }


}