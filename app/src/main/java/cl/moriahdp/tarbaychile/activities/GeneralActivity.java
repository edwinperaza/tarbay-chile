package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class GeneralActivity extends AppCompatActivity {

    protected void startActivityClosingAllOthers(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
