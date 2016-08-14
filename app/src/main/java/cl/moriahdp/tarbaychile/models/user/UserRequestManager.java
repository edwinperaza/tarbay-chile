package cl.moriahdp.tarbaychile.models.user;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cl.moriahdp.tarbaychile.network.AppRequestManager;
import cl.moriahdp.tarbaychile.network.AppResponseListener;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class UserRequestManager extends AppRequestManager {

    private static final String USER_API_URL = BASE_URL + API_URL + "user/";
    private static final String LOGIN_API_URL = USER_API_URL + "login/";
    private static final String LOGOUT_API_URL = USER_API_URL + "logout/";
    private static final String RECOVER_PASSWORD_API_URL = USER_API_URL + "recover_password/";

    public static String FIRST_NAME = "first_name";
    public static String LAST_NAME = "last_name";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";

    /**
     * Creates a new JsonObjectRequest for creating a new user.
     *
     * @param firstName The first name of the user to create
     * @param lastName The last name of the user to create
     * @param email The email of the user to create
     * @param password The password of the user to create
     * @param responseListener The listener for on success and error callbacks
     * @return The created JsonObjectRequest for create user webservice
     */
    public static JsonObjectRequest createUserRequest(String firstName, String lastName,
                                                      String email, String password,
                                                      AppResponseListener<JSONObject> responseListener) {

        JSONObject params = new JSONObject();
        try {
            params.put(FIRST_NAME, firstName);
            params.put(LAST_NAME, lastName);
            params.put(EMAIL, email);
            params.put(PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonObjectRequest(Request.Method.POST, USER_API_URL, params, responseListener, responseListener);
    }

    /**
     * Creates a new JsonObjectRequest for logging in a user.
     *
     * @param email The email of the user to log in
     * @param password The password of the user to log in
     * @param responseListener The listener for on success and error callbacks
     * @return The created JsonObjectRequest for logging in the user webservice
     */
    public static JsonObjectRequest userLogInRequest(String email,
                                                     String password,
                                                     AppResponseListener<JSONObject> responseListener) {

        JSONObject params = new JSONObject();

        try {
            params.put(EMAIL, email);
            params.put(PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonObjectRequest(Request.Method.POST, LOGIN_API_URL, params, responseListener, responseListener);

    }

    /**
     * Logs out the user from the server session
     *
     * @param responseListener The listener for on success and error callbacks
     * @return The created JsonObjectRequest for logging out the user webservice
     */
    public static JsonObjectRequest userLogOutRequest(AppResponseListener<JSONObject> responseListener) {

        JSONObject jsonObject = new JSONObject();

        return new JsonObjectRequest(Request.Method.DELETE, LOGOUT_API_URL, jsonObject, responseListener, responseListener);

    }

    /**
     * Creates a new JsonObjectRequest for recovering the user's password.
     *
     * @param email The email of the account to recover
     * @return The Request created
     */
    public static JsonObjectRequest userRecoverPassword(String email, AppResponseListener<JSONObject> responseListener) {

        JSONObject params = new JSONObject();

        try {
            params.put(EMAIL, email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonObjectRequest(Request.Method.POST, RECOVER_PASSWORD_API_URL, params, responseListener, responseListener);

    }
}