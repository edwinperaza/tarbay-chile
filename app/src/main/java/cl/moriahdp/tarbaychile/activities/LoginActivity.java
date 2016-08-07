package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

public class LoginActivity extends GeneralActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String FACEBOOK_PROFILE = "public_profile";
    public static final String FACEBOOK_EMAIL = "email";
    public static final String FACEBOOK_USER_FRIENDS = "user_friends";

    // UI Facebook references
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        mCallbackManager = CallbackManager.Factory.create();
        // Bind views.
        mLoginButton = (LoginButton) findViewById(R.id.login_button);

        //I have edited this sentence to launch Stories Fragment without login if you want to
        //test login please uncomment the following sentence
        if(!PreferencesManager.isUserLogged(getApplicationContext())) {

            //Set Facebook permissions
            mLoginButton.setReadPermissions(FACEBOOK_PROFILE, FACEBOOK_EMAIL, FACEBOOK_USER_FRIENDS);

            // Register Callback Facebook button
            mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // Now we can get facebookToken but is necessary request the email address
                            // from GraphRequest API
                            final String token = loginResult.getAccessToken().getToken();

                            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                                            String emailFacebook;
                                            try {
                                                emailFacebook = jsonObject.getString("email");
                                                PreferencesManager.setStringPref(getApplicationContext(),PreferencesManager.PREF_USER_EMAIL,emailFacebook);
                                                Log.d(TAG + " Email FB",emailFacebook);
                                                Log.d(TAG + " Token FB",token);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            startActivityClosingAllOthers(MainActivity.class);

                                        }
                                    });

                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,email");
                            request.setParameters(parameters);
                            request.executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(LoginActivity.this, "Cancelado por el usuario", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onError(FacebookException error) {
                            Log.d("error", error.getClass().toString());
                            Toast.makeText(LoginActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                        }
                    }
            );

        } else {
            startActivityClosingAllOthers(MainActivity.class);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
