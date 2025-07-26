package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Login {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
