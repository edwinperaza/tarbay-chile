package cl.moriahdp.tarbaychile.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.models.category.Category;

public class CategoriesListAdapter extends ArrayAdapter<Category> {

    List<Category> mCategoriesList;
    Typeface font;

    public CategoriesListAdapter(Context context, List<Category> objects) {
        super(context, 0, objects);
        mCategoriesList = objects;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Condensed.ttf");

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = mCategoriesList.get(position);

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_categories_item, parent, false);

            viewHolder = new ViewHolder(convertView, font);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.setCategory(convertView, category);
        return convertView;
    }


    private static class ViewHolder {
//        ImageView mCategoryImageView;
        TextView mCategoryTitleView;

        public ViewHolder(View view, Typeface font){
//            mCategoryImageView = (ImageView) view.findViewById(R.id.ivMainImageProductList);
            mCategoryTitleView = (TextView) view.findViewById(R.id.tv_category_title);
            mCategoryTitleView.setTypeface(font);

        }

        public void setCategory(View view, Category category){
            this.mCategoryTitleView.setText(category.getTitle());
         }

    }
}