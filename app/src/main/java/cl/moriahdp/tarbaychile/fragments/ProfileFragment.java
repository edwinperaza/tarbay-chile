package cl.moriahdp.tarbaychile.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import cl.moriahdp.tarbaychile.R;


public class ProfileFragment extends Fragment {

    SimpleDraweeView draweeView;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        draweeView = (SimpleDraweeView) view.findViewById(R.id.user_profile_photo);
        Uri uri = Uri.parse("http://www.tarbay.com/media/wysiwyg/logo-tarbay-header.png");
        draweeView.setImageURI(uri);
        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getTag(), "Image Profile");
            }
        });
        return view;
    }

}