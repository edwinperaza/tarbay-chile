package cl.moriahdp.tarbaychile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.adapters.CategoriesListAdapter;
import cl.moriahdp.tarbaychile.models.category.Category;

public class CategoriesListFragment extends Fragment {

    private CategoriesListAdapter mCategoriesListAdapter;
    private ArrayList<Category> mCategoriesArrayList;
    private ListView mCategoriesListView;

    public CategoriesListFragment() {
    }

    public static CategoriesListFragment newInstance() {
        CategoriesListFragment fragment = new CategoriesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoriesArrayList = new ArrayList<>();
        mCategoriesListAdapter = new CategoriesListAdapter(getActivity(), mCategoriesArrayList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_list, container, false);

        mCategoriesListView = (ListView) view.findViewById(R.id.lvCategoriesList);
        mCategoriesListView.setAdapter(mCategoriesListAdapter);
        populateCategories();

        mCategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //TODO
            }
        });
        return view;
    }

    private void populateCategories() {

        mCategoriesListAdapter.add(new Category("ZARCILLOS",""));
        mCategoriesListAdapter.add(new Category("ANILLOS",""));
        mCategoriesListAdapter.add(new Category("COLLARES",""));
        mCategoriesListAdapter.add(new Category("BRAZALETES",""));
//        mCategoriesListAdapter.add(new Category("Zarcillos",""));
    }
}
