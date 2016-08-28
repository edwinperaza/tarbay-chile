package cl.moriahdp.tarbaychile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.adapters.CategoriesListAdapter;
import cl.moriahdp.tarbaychile.models.category.Category;

public class CategoriesListFragment extends Fragment {

    private CategoriesListAdapter mCategoriesListAdapter;
    private ArrayList<Category> mCategoriesArrayList;
    private ListView mCategoriesListView;

    private ExpandableListAdapter mAdapterView;
    private ExpandableListView expandableListView;
    private int lastExpandedPosition = -1;

    List<Map<String, String>> mCategoriesListItem = new ArrayList<Map<String, String>>();
    List<List<Map<String, String>>> mSubCategoriesListItem = new ArrayList<List<Map<String, String>>>();

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

        populateCategories();

        mAdapterView = new SimpleExpandableListAdapter(
                getContext(),
                mCategoriesListItem,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"parentItem"},
                new int[]{android.R.id.text1, android.R.id.text2},
                mSubCategoriesListItem,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"childItem"},
                new int[]{android.R.id.text1}
        );
        expandableListView = (ExpandableListView) view.findViewById(R.id.lvCategoriesList);
        expandableListView.setAdapter(mAdapterView);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        expandableListView.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getContext(), "ListView Example",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getContext(), "ListView Tutorial",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getContext(), "Android ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getContext(), "Android ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getContext(), "ListView Example",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                }
                return false;
            }
        });
        return view;
    }

    private void populateCategories() {

        //TODO CREATE REQUEST TO GET CATEGORIES AND SUBCATEGORIES
        /* ******************** Group item 1 ********************* */
        Map<String, String> category1 = new HashMap<String, String>();
        mCategoriesListItem.add(category1);
        category1.put("parentItem", "JOYERÍA");

        List<Map<String, String>> mSubCategories1 = new ArrayList<Map<String, String>>();
        Map<String, String> childrenitem1 = new HashMap<String, String>();
        mSubCategories1.add(childrenitem1);
        childrenitem1.put("childItem", "Zarcillos");

        Map<String, String> childrenitem2 = new HashMap<String, String>();
        mSubCategories1.add(childrenitem2);
        childrenitem2.put("childItem", "Anillos");

        Map<String, String> childrenitem3 = new HashMap<String, String>();
        mSubCategories1.add(childrenitem3);
        childrenitem3.put("childItem", "Collares");

        Map<String, String> childrenitem4 = new HashMap<String, String>();
        mSubCategories1.add(childrenitem4);
        childrenitem4.put("childItem", "Brazaletes");

        Map<String, String> childrenitem5 = new HashMap<String, String>();
        mSubCategories1.add(childrenitem5);
        childrenitem5.put("childItem", "Todos");
        mSubCategoriesListItem.add(mSubCategories1);


	    /* ******************** Group Item 2  ********************* */
        Map<String, String> category2 = new HashMap<String, String>();
        mCategoriesListItem.add(category2);
        category2.put("parentItem", "BOLSOS");

        List<Map<String, String>> mSubCategories2 = new ArrayList<Map<String, String>>();
        Map<String, String> childrenitem7 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem7);
        childrenitem7.put("childItem", "Shopping Bag");

        Map<String, String> childrenitem8 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem8);
        childrenitem8.put("childItem", "Bauletto");

        Map<String, String> childrenitem9 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem9);
        childrenitem9.put("childItem", "Bandolera");

        Map<String, String> childrenitem10 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem10);
        childrenitem10.put("childItem", "Clutch");

        Map<String, String> childrenitem11 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem11);
        childrenitem11.put("childItem", "Tote");

        Map<String, String> childrenitem12 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem12);
        childrenitem12.put("childItem", "Mochila");

        Map<String, String> childrenitem13 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem13);
        childrenitem13.put("childItem", "Hobo");

        Map<String, String> childrenitem14 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem14);
        childrenitem14.put("childItem", "Cesta");

        Map<String, String> childrenitem15 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem15);
        childrenitem15.put("childItem", "Portafolio Bag");

        Map<String, String> childrenitem16 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem16);
        childrenitem16.put("childItem", "Llavero");

        Map<String, String> childrenitem17 = new HashMap<String, String>();
        mSubCategories2.add(childrenitem17);
        childrenitem17.put("childItem", "Todos");

        mSubCategoriesListItem.add(mSubCategories2);

        /* ******************** Group Item 3  ********************* */
        Map<String, String> category3 = new HashMap<String, String>();
        mCategoriesListItem.add(category3);
        category3.put("parentItem", "ZAPATOS");

        List<Map<String, String>> mSubCategories3 = new ArrayList<Map<String, String>>();
        Map<String, String> childrenitem18 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem18);
        childrenitem18.put("childItem", "Flats");

        Map<String, String> childrenitem19 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem19);
        childrenitem19.put("childItem", "Sandalias");

        Map<String, String> childrenitem20 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem20);
        childrenitem20.put("childItem", "Stilettos");

        Map<String, String> childrenitem21 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem21);
        childrenitem21.put("childItem", "Plataformas");

        Map<String, String> childrenitem22 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem22);
        childrenitem22.put("childItem", "Cuñas");

        Map<String, String> childrenitem23 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem23);
        childrenitem23.put("childItem", "Botines");

        Map<String, String> childrenitem24 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem24);
        childrenitem24.put("childItem", "Tacón Bajo");

        Map<String, String> childrenitem25 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem25);
        childrenitem25.put("childItem", "Tacón Medio");

        Map<String, String> childrenitem26 = new HashMap<String, String>();
        mSubCategories3.add(childrenitem26);
        childrenitem26.put("childItem", "Tacón Alto");
        mSubCategoriesListItem.add(mSubCategories3);
    }
}
