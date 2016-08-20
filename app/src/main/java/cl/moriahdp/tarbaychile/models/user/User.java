package cl.moriahdp.tarbaychile.models.user;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class User {

    private String email;
    private String username;
    private String password;
    private String token;

    public User() {
    }

    public User(String email, String username, String password, String token) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public static User jsonObjectToUser (JSONObject jsonObjectUser) {
        User user = new User();

        try {
            user.setUsername(jsonObjectUser.getString(UserRequestManager.USERNAME));
            user.setEmail(jsonObjectUser.getString(UserRequestManager.EMAIL));
            user.setPassword(jsonObjectUser.getString(UserRequestManager.PASSWORD));
            user.setToken(jsonObjectUser.getString(UserRequestManager.TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
