package cl.moriahdp.tarbaychile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import cl.moriahdp.tarbaychile.fragments.ProductsListFragment;
import cl.moriahdp.tarbaychile.models.product.Product;

public class GeneralActivity extends AppCompatActivity implements ProductsListFragment.onItemSelectedListener {

    protected void startActivityClosingAllOthers(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onProductItemSelected(Product product) {
        if (product != null) {

            Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);

        }
    }
}
