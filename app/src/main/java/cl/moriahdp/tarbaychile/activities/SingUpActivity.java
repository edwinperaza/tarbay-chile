package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.models.user.UserRequestManager;
import cl.moriahdp.tarbaychile.network.AppResponseListener;
import cl.moriahdp.tarbaychile.network.VolleyManager;

public class SingUpActivity extends GeneralActivity {

    private TextView mLogInLinkView;
    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mSingUpView;
    private View focusView;
    private boolean cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        setupRegisterFieldsForm();
        mLogInLinkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSingUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();
            }
        });

    }

    private void setupRegisterFieldsForm(){
        mUsernameView = (EditText) findViewById(R.id.et_register_username);
        mEmailView = (EditText) findViewById(R.id.et_register_email);
        mPasswordView = (EditText) findViewById(R.id.et_register_password);
        mLogInLinkView = (TextView) findViewById(R.id.tv_link_login);
        mSingUpView = (Button) findViewById(R.id.btn_register_signup);

    }

    /**
     * Attempts to sign register a new user.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual sign up attempt is made.
     */
    private void attemptSignUp() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the register attempt.
        String email = mEmailView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        cancel = false;
        focusView = null;

        // This method validate each field of the form and also update boolean variable called
        // "cancel", also assign focusView to the first field with errors.
//        validateRegisterForm(email, firstName, lastName, age, gender, address, zone, region, study,
//                password, passwordConfirmation);

//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {

            //We show the loader and hide the form
//            showHideView(mProgressView, mLoginFormView, true);

            //We set the response listener with corresponding overridden methods
            AppResponseListener<JSONObject> appResponseListener = new AppResponseListener<JSONObject>(getApplicationContext()){
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Registro Satisfactorio", Toast.LENGTH_SHORT).show();
                    startActivityClosingAllOthers(MainActivity.class);
                }

                @Override
                public void noResourceFound() {
                    Toast.makeText(getApplicationContext(), "Tenemos un problema, por favor contacte a soporte técnico", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPostResponse(){
//                    showHideView(mLoginFormView, mProgressView, true);
                }
            };

            //We add the request
            JsonObjectRequest request = UserRequestManager.signUpUserRequest(username, email, password, appResponseListener);
            VolleyManager.getInstance(getApplicationContext()).addToRequestQueue(request);
//        }

    }

}
