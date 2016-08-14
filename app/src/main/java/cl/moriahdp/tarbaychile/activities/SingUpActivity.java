package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cl.moriahdp.tarbaychile.R;

public class SingUpActivity extends GeneralActivity {

    private TextView mLogInLinkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        mLogInLinkView = (TextView) findViewById(R.id.tv_link_login);
        mLogInLinkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
