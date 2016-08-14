package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

public class LoginActivity extends GeneralActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String FACEBOOK_PROFILE = "public_profile";
    public static final String FACEBOOK_EMAIL = "email";
    public static final String FACEBOOK_USER_FRIENDS = "user_friends";

    // UI Facebook references
    private LoginButton mLoginFacebookButton;
    private CallbackManager mCallbackManager;

    //UI Login references
    private Button mLoginButton;
    private TextView mSingUpLinkView;
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        mCallbackManager = CallbackManager.Factory.create();
        // Bind views.
        mLoginFacebookButton = (LoginButton) findViewById(R.id.login_button);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mSingUpLinkView = (TextView) findViewById(R.id.tv_link_signup);
        mEmailView = (EditText) findViewById(R.id.et_input_email);
        mPasswordView = (EditText) findViewById(R.id.et_input_password);

        if(!PreferencesManager.isUserLogged(getApplicationContext())) {

            //Set Facebook permissions
            mLoginFacebookButton.setReadPermissions(FACEBOOK_PROFILE, FACEBOOK_EMAIL, FACEBOOK_USER_FRIENDS);

            // Register Callback Facebook button
            mLoginFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
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

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


        mSingUpLinkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailView.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordView.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPasswordView.setError(null);
        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}