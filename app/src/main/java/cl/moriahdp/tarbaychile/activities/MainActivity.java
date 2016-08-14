package cl.moriahdp.tarbaychile.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.login.LoginManager;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.adapters.FragPagerAdapter;
import cl.moriahdp.tarbaychile.fragments.ProductsListFragment;
import cl.moriahdp.tarbaychile.helpers.NonSwipeableViewPager;
import cl.moriahdp.tarbaychile.models.product.Product;
import cl.moriahdp.tarbaychile.utils.PreferencesManager;

public class MainActivity extends GeneralActivity implements ProductsListFragment.onItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //I have edited this sentence to launch Stories Fragment without login if you want to
        //test login please uncomment the following sentence
        if(!PreferencesManager.isUserLogged(context)) {

            startActivityClosingAllOthers(LoginActivity.class);

        } else {

            // Get the ViewPager and set it's PagerAdapter so that it can display items
            NonSwipeableViewPager viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
            viewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager(),MainActivity.this));

            // Give the TabLayout the ViewPager
            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

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

    @Override
    public void onProductItemSelected(Product product) {
        //TODO IMPLEMENT METHOD
    }
}