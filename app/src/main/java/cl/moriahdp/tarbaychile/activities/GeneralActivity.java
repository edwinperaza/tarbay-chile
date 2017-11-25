package cl.moriahdp.tarbaychile.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

//import com.facebook.login.LoginManager;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

public class GeneralActivity extends AppCompatActivity {

    protected void startActivityClosingAllOthers(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    protected void showLogOutAlertDialogLogOut() {

        DialogInterface.OnClickListener positiveButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = getApplicationContext();
                PreferencesManager.clearPrefs(context);
                //LoginManager.getInstance().logOut();
                startActivityClosingAllOthers(LoginActivity.class);
            }
        };

        DialogInterface.OnClickListener negativeButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.logout_confirmation)
                .setPositiveButton(android.R.string.ok, positiveButtonClickListener)
                .setNegativeButton(android.R.string.cancel, negativeButtonClickListener);

        builder.show();
    }

}