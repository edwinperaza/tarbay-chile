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

/**
 * Created by edwinperaza on 8/29/16.
 */
public class ContactUsFragment extends Fragment {

    private SimpleDraweeView mContactLogoView;

    public ContactUsFragment() {
    }

    public static ContactUsFragment newInstance() {
        ContactUsFragment fragment = new ContactUsFragment();
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

        View view = inflater.inflate(R.layout.fragment_contact_form, container, false);
        mContactLogoView = (SimpleDraweeView) view.findViewById(R.id.sdv_contact_logo);
        Uri uri = Uri.parse("http://www.tarbay.com/media/wysiwyg/logo-tarbay-header.png");
        mContactLogoView.setImageURI(uri);

        return view;

    }
}