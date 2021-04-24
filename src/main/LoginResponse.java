package main;


public class LoginResponse {

    private String access_token;

    public LoginResponse( String token) {
        this.access_token = token;
    }

    public String getValue() {
        return access_token;
    }

    public void setValue(String value) {
        this.access_token = value;
    }

}