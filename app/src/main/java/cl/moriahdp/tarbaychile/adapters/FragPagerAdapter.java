package cl.moriahdp.tarbaychile.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.fragments.ProductsListFragment;

public class FragPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Home", "Search", "Wish", "Profile" };

    private int[] imageResId = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_search_black_24dp,
            R.drawable.ic_favorite_border_black_24dp,
            R.drawable.ic_perm_identity_black_24dp
    };

    private Context context;

    public FragPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return ProductsListFragment.newInstance("Productos");
        } else {
            return ProductsListFragment.newInstance("Productos");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
//        return tabTitles[position];
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
