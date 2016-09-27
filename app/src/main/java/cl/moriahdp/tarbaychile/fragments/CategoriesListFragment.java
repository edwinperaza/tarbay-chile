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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.adapters.CategoriesListExpandableAdapter;
import cl.moriahdp.tarbaychile.models.category.Category;
import cl.moriahdp.tarbaychile.models.subcategory.SubCategory;

public class CategoriesListFragment extends Fragment {

    private ArrayList<Category> mCategoriesArrayList;
    private ListView mCategoriesListView;
    private ExpandableListAdapter mAdapterView;
    List<Map<String, String>> mCategoriesListItem = new ArrayList<Map<String, String>>();
    List<List<Map<String, String>>> mSubCategoriesListItem = new ArrayList<List<Map<String, String>>>();

    private ExpandableListView expandableListView;
    private int lastExpandedPosition = -1;
    private List<Category> mCategoryList;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_list, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.lvCategoriesList);
        initData();

        expandableListView.setIndicatorBounds(5, 5);
        CategoriesListExpandableAdapter categoriesListExpandableAdapter =
                new CategoriesListExpandableAdapter(mCategoryList, getActivity());
        expandableListView.setIndicatorBounds(0, 20);
        expandableListView.setAdapter(categoriesListExpandableAdapter);
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

                Toast.makeText(getContext(), mCategoryList.get(groupPosition).getItemList().get(childPosition).getName(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }

    private void initData() {
        mCategoryList = new ArrayList<>();

        Category categoryJoyeria = createCategory("JOYERÍA", "", 1);
        categoryJoyeria.setItemList(createItems("joyeria"));

        Category categoryBolsos = createCategory("BOLSOS", "", 2);
        categoryBolsos.setItemList(createItems("bolsos"));

        Category categoryZapatos = createCategory("ZAPATOS", "", 3);
        categoryZapatos.setItemList(createItems("zapatos"));

        mCategoryList.add(categoryJoyeria);
        mCategoryList.add(categoryBolsos);
        mCategoryList.add(categoryZapatos);
    }

    private Category createCategory(String name, String descr, long id) {
        return new Category(id, name, descr);
    }

    private List<SubCategory> createItems(String type) {
        List<SubCategory> subCategoryList = new ArrayList<>();
        SubCategory item;
        switch (type){
            case "joyeria": {
                item = new SubCategory(1, 0, "Zarcillos", "");
                subCategoryList.add(item);
                item = new SubCategory(2, 0, "Anillos", "");
                subCategoryList.add(item);
                item = new SubCategory(3, 0, "Collares", "");
                subCategoryList.add(item);
                item = new SubCategory(4, 0, "Brazaletes", "");
                subCategoryList.add(item);
                item = new SubCategory(5, 0, "Todos", "");
                subCategoryList.add(item);
                break;
            }
            case "bolsos": {
                item = new SubCategory(6, 0, "Shopping Bag", "");
                subCategoryList.add(item);
                item = new SubCategory(7, 0, "Bauletto", "");
                subCategoryList.add(item);
                item = new SubCategory(8, 0, "Bandolera", "");
                subCategoryList.add(item);
                item = new SubCategory(9, 0, "Clutch", "");
                subCategoryList.add(item);
                item = new SubCategory(10, 0, "Tote", "");
                subCategoryList.add(item);
                item = new SubCategory(11, 0, "Mochila", "");
                subCategoryList.add(item);
                item = new SubCategory(12, 0, "Hobo", "");
                subCategoryList.add(item);
                item = new SubCategory(13, 0, "Cesta", "");
                subCategoryList.add(item);
                item = new SubCategory(14, 0, "Portafolio Bag", "");
                subCategoryList.add(item);
                item = new SubCategory(15, 0, "Llavero", "");
                subCategoryList.add(item);
                item = new SubCategory(16, 0, "Todos", "");
                subCategoryList.add(item);
                break;
            }
            case "zapatos": {
                item = new SubCategory(17, 0, "Flats", "");
                subCategoryList.add(item);
                item = new SubCategory(18, 0, "Sandalias", "");
                subCategoryList.add(item);
                item = new SubCategory(19, 0, "Stilettos", "");
                subCategoryList.add(item);
                item = new SubCategory(20, 0, "Plataformas", "");
                subCategoryList.add(item);
                item = new SubCategory(21, 0, "Cuñas", "");
                subCategoryList.add(item);
                item = new SubCategory(22, 0, "Botines", "");
                subCategoryList.add(item);
                item = new SubCategory(23, 0, "Tacón Bajo", "");
                subCategoryList.add(item);
                item = new SubCategory(24, 0, "Tacón Medio", "");
                subCategoryList.add(item);
                item = new SubCategory(25, 0, "Tacón Alto", "");
                subCategoryList.add(item);
                break;
            }
        }
        return subCategoryList;
    }
}