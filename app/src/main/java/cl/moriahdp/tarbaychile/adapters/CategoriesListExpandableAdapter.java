package cl.moriahdp.tarbaychile.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.models.category.Category;
import cl.moriahdp.tarbaychile.models.subcategory.SubCategory;

public class CategoriesListExpandableAdapter extends BaseExpandableListAdapter {

    private List<Category> mCategoryList;
    private int mItemLayoutId;
    private int mGroupLayoutId;
    private Context mContext;
    Typeface font;

    public CategoriesListExpandableAdapter(List<Category> categoryList, Context context) {

        this.mItemLayoutId = R.layout.fragment_subcategory_item;
        this.mGroupLayoutId = R.layout.fragment_category_item;
        this.mCategoryList = categoryList;
        this.mContext = context;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Condensed.ttf");
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCategoryList.get(groupPosition).getItemList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mCategoryList.get(groupPosition).getItemList().get(childPosition).hashCode();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        SubCategory subCategory = mCategoryList.get(groupPosition).getItemList().get(childPosition);
        ViewHolderSubCategory viewHolder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_subcategory_item, parent, false);

            viewHolder = new ViewHolderSubCategory(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolderSubCategory) convertView.getTag();
        }

        viewHolder.setSubCategory(subCategory);
        return convertView;

    }

    // ViewHolder Pattern in SubCategory Item (View)
    private static class ViewHolderSubCategory {
        TextView mSubCategoryTitleView;

        public ViewHolderSubCategory(View view){
            mSubCategoryTitleView = (TextView) view.findViewById(R.id.tv_subcategory_name);

        }

        public void setSubCategory(SubCategory subCategory){
            this.mSubCategoryTitleView.setText(subCategory.getName());
        }

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = mCategoryList.get(groupPosition).getItemList().size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCategoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mCategoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mCategoryList.get(groupPosition).hashCode();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        Category category = mCategoryList.get(groupPosition);

        ViewHolder viewHolder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_category_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.setCategory(category);
        return convertView;

    }

    // ViewHolder Pattern in Category Item (View)
    private static class ViewHolder {
        TextView mCategoryTitleView;

        public ViewHolder(View view){
            mCategoryTitleView = (TextView) view.findViewById(R.id.tv_category_name);

        }

        public void setCategory(Category category){
            this.mCategoryTitleView.setText(category.getName());
        }

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
