package cl.moriahdp.tarbaychile.network;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cl.moriahdp.tarbaychile.models.user.UserRequestManager;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class AppResponseListener<T> extends MoriahResponseErrorListener implements Response.Listener<T>{

    public static final String ACTION_UNAUTHORIZED = "AppErrorListener.Unauthorized";
    public static final String ACTION_UPGRADE_REQUIRED = "AppErrorListener.UpgradeRequired";

    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_UPGRADE_REQUIRED = 426;
    public static final int HTTP_UNKNOW = 404;

    private Context mContext;

    public AppResponseListener(Context context) {
        super(context);
        // prevents an activity or broadcast receiver leak by getting the application context
        mContext = context.getApplicationContext();
    }

    @Override
    public void onResponse(T response) {
        onPostResponse();
    }

    /**
     * This method manages every HTTP error and then calls corresponding error method
     * If new implementation is needed, override and call super.onErrorResponse(error) in the Error Listener
     */
    @Override
    public void onErrorResponse(VolleyError error) {

        NetworkResponse networkResponse = error.networkResponse;

        if(networkResponse != null){

            switch(networkResponse.statusCode){
                case HTTP_UNAUTHORIZED: onUnauthorizedError(error); break;
                case HTTP_UPGRADE_REQUIRED: onUpgradeRequiredError(error); break;
                case HTTP_BAD_REQUEST: onBadRequest(error); break;
                case HTTP_UNKNOW: noResourceFound(); break;
                default: onUnhandledError(error); break;
            }

        }
        else{
            noInternetError();
        }

        onPostResponse();
    }


    public void noResourceFound() {
        Toast.makeText(mContext, "Recurso no encontrado", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void noInternetConnectionError() {

    }

    /**
     * This must be called whenever we need a common behaviour after either success or failure.
     * It must be overridden on the AppResponseListener instance
     */
    public void onPostResponse(){}

    /**
     * If we get a 401 we try to relogin using the Shared Preferences.
     * If we fail we log out the user and call Login Activity.
     * This method must be overridden only in the LoginActivity request. Nowhere else.
     */
    @Override
    public <T> void onUnauthorizedError(VolleyError volleyError) {

        final String email = PreferencesManager.getStringPref(mContext, PreferencesManager.PREF_USER_EMAIL);
        final String password = PreferencesManager.getStringPref(mContext, PreferencesManager.PREF_USER_PASSWORD);

        //We set the response listener with corresponding overridden methods
        AppResponseListener<JSONObject> responseListener = new AppResponseListener<JSONObject>(mContext){

            //On success we update the Shared Preferences of the user
            @Override
            public void onResponse(JSONObject response) {

                String username = null;
                String token = null;
                try {
                    username = response.getString(PreferencesManager.PREF_USER_USERNAME);
                    token = response.getString(PreferencesManager.PREF_USER_TOKEN);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //TODO: Use method UserManager.logInUser loading user from database
                PreferencesManager.saveUserCredentials(mContext, email, password, username, token);
            }

            //On failure we clear the Preferences and send local broadcast to log out user
            @Override
            public void onErrorResponse(VolleyError error) {
                PreferencesManager.clearPrefs(mContext);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(ACTION_UNAUTHORIZED));
            }

        };

        //We add the request
        JsonObjectRequest reloginRequest = UserRequestManager.userLogInRequest(email, password, responseListener);
        VolleyManager.getInstance(mContext).addToRequestQueue(reloginRequest);

    }

    /**
     * If we get a 426 we close all activities and go to the Upgrade required Activity
     */
    @Override
    public void onUpgradeRequiredError(VolleyError volleyError) {
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(ACTION_UPGRADE_REQUIRED));
    }

    /**
     * If we get a 400 we send and appropriate message (Override for each case).
     */
    public void onBadRequest(VolleyError volleyError) {
    }

    /**
     * If there is no network response we notify there is no internet access
     */
    public void noInternetError() {
        Toast.makeText(mContext, "No tiene conexión a Internet", Toast.LENGTH_SHORT).show();
    }

    /**
     * For other errors we notify there has been an unknown error
     */
    @Override
    public void onUnhandledError(VolleyError volleyError) {
        String error = VolleyHelper.getMessage(volleyError, mContext);
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
        Log.d("error", error);
    }

}