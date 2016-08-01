package cl.moriahdp.tarbaychile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.adapters.ProductsListAdapter;
import cl.moriahdp.tarbaychile.models.product.Product;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class ProductsListFragment extends Fragment {

    private static final String ARG_TITLE = "TITLE";
    private ListView mProductsListView;
    private ProductsListAdapter mProductsListAdapter;
    private ArrayList<Product> mProductsArrayList;

    public ProductsListFragment() {

    }

    public static ProductsListFragment newInstance(String title) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsArrayList = new ArrayList<>();
        mProductsListAdapter = new ProductsListAdapter(getActivity(), mProductsArrayList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        mProductsListView = (ListView) view.findViewById(R.id.lvProductsList);
        mProductsListView.setAdapter(mProductsListAdapter);
        setProducts();
        return view;
    }

    public void setProducts(){
                       for (int i = 0; i < 10; i++) {

                       Product product = new Product("Collar Nro: "+i, 100 , "");
                           mProductsListAdapter.add(product);

                    }

          }
}
