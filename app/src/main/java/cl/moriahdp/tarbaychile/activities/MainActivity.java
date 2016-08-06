package cl.moriahdp.tarbaychile.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.fragments.ProductsListFragment;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

public class MainActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment mProductsListFragment = ProductsListFragment.newInstance("Productos");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, mProductsListFragment);
        ft.commit();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.myFeed: break;
            case R.id.mySearch: break;
            case R.id.myWish: break;
            case R.id.logOut: showAlertDialogLogOut(); break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialogLogOut(){

        DialogInterface.OnClickListener positiveButtonClickListener =  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = getApplicationContext();
                PreferencesManager.clearPrefs(context);
                LoginManager.getInstance().logOut();
                startActivityClosingAllOthers(LoginActivity.class);
            }
        };

        DialogInterface.OnClickListener negativeButtonClickListener =  new DialogInterface.OnClickListener() {
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
