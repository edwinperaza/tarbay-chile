package cl.moriahdp.tarbaychile.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

import cl.moriahdp.tarbaychile.R;
import cl.moriahdp.tarbaychile.models.product.Product;
import cl.moriahdp.tarbaychile.utils.Constant;
import cl.moriahdp.tarbaychile.utils.ImageHelper;

/**
 * Created by edwinperaza on 11/25/17.
 */

public class CameraFragment extends Fragment {

    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_PICK_PHOTO = 2;

    public ImageView mCameraImage;
    public Context mContext;

    private String mCurrentPhotoPath;
    private String mCurrentFileName;
    private View mRootView;
    private static final String TAG = CameraFragment.class.getSimpleName();

    public CameraFragment() {

    }


    public static CameraFragment newInstance(String title) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_TITLE, title);
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_camera, container, false);
        mCameraImage = (ImageView) mRootView.findViewById(R.id.iv_camera);
        mContext = getContext();
        mCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission(mContext)) {
                    takePhoto();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(
                                new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.CAMERA
                                },
                                Constant.REQUEST_PERMISSION_CODE);
                    }
                }
            }
        });




//        mProductsListView = (ListView) view.findViewById(R.id.lvProductsList);
//        mProductsListView.setAdapter(mProductsListAdapter);
//        populateProducts();
//
//        mProductsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Product product = (Product) parent.getItemAtPosition(position);
//                mListener.onProductItemSelected(product);
//            }
//        });
        return mRootView;
    }
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final int REQUEST_PERMISSION_CODE = 1;

    /**
     * Manager of permission results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean LocationPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean CameraPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if (StoragePermission && LocationPermission && CameraPermission) {
                        Snackbar.make(mRootView, "Permisos Aceptados, continue el proceso", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(mRootView, "Vigia necesita permisos de Ubicación, Cámara y Almacenamiento", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }
                break;
        }
    }


    /**
     * Implement dispatch media
     */
    private void takePhoto() {
        dispatchMedia(
                MediaStore.ACTION_IMAGE_CAPTURE,
                REQUEST_TAKE_PHOTO,
                R.string.error_photo_app_no_available
        );
    }

    /**
     * Check device version and launch camera depends on it.
     *
     * @param action action to be implemented
     * @param requestCode code to know who launch the activity
     * @param errorToast message to be shown if an issue occur.
     */
    private void dispatchMedia(String action, int requestCode, int errorToast) {
        // Create Intent to dispatch media and return control to the calling application
        Intent intent = new Intent(action);

        // Create the File where the photo should go
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            File photoFile = null;
//            try {
//                photoFile = ImageHelper.createImageFile(mContext);
//                mCurrentPhotoPath = photoFile.getAbsolutePath();
//                mCurrentFileName = photoFile.getName();
//            } catch (IOException ex) {
//                //Crashlytics.logException(ex);
//                //Crashlytics.log("Error occurred while creating the File");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri fileUriCreated = FileProvider.getUriForFile(
//                        mContext,
//                        "cl.magnet.vigia.fileprovider",
//                        photoFile);
//
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUriCreated);
//            }
//
//        } else {
            mCurrentFileName = ImageHelper.getFileName();

            Uri fileUriCreated = ImageHelper.getExternalFileUri(
                    getContext(),
                    mCurrentFileName,
                    TAG);
            try {
                if (fileUriCreated != null) {
                    mCurrentPhotoPath = fileUriCreated.getPath();
                }
            } catch (Exception ex) {
                //Crashlytics.logException(ex);
                //Crashlytics.log("Error occurred while getting path");
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUriCreated);
//        }

        // If you call startActivityForResult() using an intent that no app can handle, your app
        // will crash. So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            // Start the dispatch media intent
            startActivityForResult(intent, requestCode);
        } else {
            //Crashlytics.log("No app can handle the media");
            // No app can handle the media
            //showLongToast(errorToast);
        }
    }

    public static boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int result2 = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    ImageHelper.saveResizedImage(mContext, mCurrentFileName, mCurrentPhotoPath);
                    ImageHelper.setImageBitMapToImageView(mContext, mCameraImage, mCurrentPhotoPath, Constant.REPORT_ROFILE_WIDTH, Constant.REPORT_PROFILE_HEIGHT);
                    break;
                }
                case REQUEST_PICK_PHOTO: {
                    Uri photoUri = data.getData();
                    mCurrentPhotoPath = ImageHelper.getPicturePathFromGalleryUri(mContext, photoUri);
                    ImageHelper.setImageBitMapToImageView(mContext, mCameraImage, mCurrentPhotoPath, Constant.REPORT_ROFILE_WIDTH, Constant.REPORT_PROFILE_HEIGHT);
                    break;
                }
            }

        } else {
            // Result was a failure
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    //Crashlytics.log("Foto not taken");
                    Snackbar.make(mRootView, "Foto no tomada", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    break;
                case REQUEST_PICK_PHOTO:
                    //Crashlytics.log("No image selected");
                    Snackbar.make(mRootView, "No seleccionó ninguna imagen", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    break;
            }
        }
    }
}
