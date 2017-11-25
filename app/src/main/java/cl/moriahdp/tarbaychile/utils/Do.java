package cl.moriahdp.tarbaychile.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

//import cl.magnet.vigia.R;

/**
 * Class to implement general methods
 *
 * @author  Magnet SPA
 * Created 13/3/17
 */
public class Do {

    // Date Patterns
    public static final String DAY_FIRST = "dd-MM-yyyy";
    public static final String DAY_FIRST_NO_YEAR = "dd-MM";
    public static final String HOUR_MINUTE = "HH:mm";
    public static final String FIRST_TIME_OF_DAY = " 00:00:00";
    public static final String LAST_TIME_OF_DAY = " 23:59:59";
    public static final int WEEK = 7;
    public static final int HOURS = 24;
    public static final int MINUTES = 60;
    public static final int SECONDS = 60;
    public static final int MILLIS = 1000;


    // Android helper functions

    /**
     * Changes the actual activity to a new activity. The old activity remains on queue.
     *
     * @author Tito_Leiva
     * @param context The activity or application context
     * @param name The class name of the activity
     */
    public static void changeActivity(Context context, Class<?> name){
        Intent intent = new Intent(context, name);
        context.startActivity(intent);
    }

    /**
     * Changes the actual activity to a new activity. The old activity is closed.
     *
     * @author Tito_Leiva
     * @param context The activity or application context
     * @param name The class name of the activity
     * @param closed The activity that will be closed
     */
    public static void changeActivity(Context context, Class<?> name, Activity closed){
        Intent intent = new Intent(context, name);
        context.startActivity(intent);
        closed.finish();
    }

    /**
     * Changes the actual activity to a new activity. The activities behavior will depend on the
     * setted flag.
     *
     * @author Tito_Leiva
     * @param context The activity or application context
     * @param name The class name of the activity
     * @param flag The setted flag for the intent
     */
    public static void changeActivity(Context context, Class<?> name, int flag) {
        Intent intent = new Intent(context, name);
        intent.addFlags(flag);
        context.startActivity(intent);
    }

    /**
     * Changes the actual activity to a new activity. The old activity remains is closed.
     * The new activity's behavior will depend on the setted flag.
     *
     * @author Tito_Leiva
     * @param context The activity or application context
     * @param name The class name of the activity
     * @param closed The activity that will be closed
     * @param flag The setted flag for the intent
     */
    public static void changeActivity(Context context, Class<?> name, Activity closed, int flag) {
        Intent intent = new Intent(context, name);
        intent.addFlags(flag);
        context.startActivity(intent);
        closed.finish();
    }

    /**
     * Changes the actual fragment for a new fragment on an activity.
     *
     * @param removed The fragment to be removed.  This fragment must already
     * be added to the activity.
     * @param added The fragment to be added.  This fragment must not already
     * be added to the activity.
     * @param container Optional identifier of the container this fragment is
     * to be placed in. If 0, it will not be placed in a container.
     * @param tag Optional tag name for the fragment, to later retrieve the
     * fragment with {@link FragmentManager#findFragmentByTag(String)
     * FragmentManager.findFragmentByTag(String)}.
     */
    public static void changeFragment(Fragment removed, Fragment added, int container, String tag) {

        Activity activity = removed.getActivity();

        activity.getFragmentManager().beginTransaction().remove(removed).commit();
        activity.getFragmentManager().beginTransaction().add(container, added, tag).commit();
    }

    /**
     * Shows a short toast message with the text from a string resource
     *
     * @param context The activity or application context
     * @param message The resource id of the string resource to use
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a long toast message with the text from a string resource
     *
     * @param context The activity or application context
     * @param message The resource id of the string resource to use
     */
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows a short toast message with the text from a string resource
     *
     * @param context The activity or application context
     * @param resId The resource id of the string resource to use
     */
    public static void showShortToast(Context context, int resId) {

        String message = getRString(context, resId);
        showShortToast(context, message);
    }


    /**
     * Shows a long toast message with the text from a string resource
     *
     * @param context The activity or application context
     * @param resId The resource id of the string resource to use
     */
    public static void showLongToast(Context context, int resId) {

        String message = getRString(context, resId);
        showLongToast(context, message);
    }

    /**
     * Creates a simple AlertDialog with a title, a body message and an OK button. Then,
     * start the dialog and display it on screen.  The window is placed in the
     * application layer and opaque.
     *
     * @param context The activity context
     * @param title The title of the dialog
     * @param message The body message of the dialog
     */
    public static void showAlertDialog(Context context, String title, String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting OK Button
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    /**
     * Shows an alert dialog with a message and two buttons (a positive and a negative button) with
     * the text from a resource.
     *
     * @param msgResId The resource id of the text the dialog will display
     * @param positiveBtnTextResId The resource id of the text the positive button will display
     * @param negativeBtnTextResId The resource id of the text the negative button will display
     * @param positiveBtnClickListener The positive button click listener that will handle the
     *                                 click event
     * @param negativeBtnClickListener The negative button click listener that will handle the
     *                                 click event
     */
    public void showAlertDialog(Context context,
                                int msgResId,
                                int positiveBtnTextResId,
                                int negativeBtnTextResId,
                                DialogInterface.OnClickListener positiveBtnClickListener,
                                DialogInterface.OnClickListener negativeBtnClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msgResId)
                .setPositiveButton(positiveBtnTextResId, positiveBtnClickListener)
                .setNegativeButton(negativeBtnTextResId, negativeBtnClickListener);

        builder.show();
    }

    /**
     * Return the string value associated with a particular resource ID.  It
     * will be stripped of any styled text information.
     *
     * @param context The activity or application context
     * @param resId The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     *
     * @return String The string data associated with the resource,
     * stripped of styled text information.
     */
    public static String getRString(Context context, int resId) {

        return context.getResources().getString(resId);
    }

    /**
     * Return a color integer associated with a particular resource ID.
     * If the resource holds a complex
     * {@link android.content.res.ColorStateList}, then the default color from
     * the set is returned.
     *
     * @param context The activity or application context
     * @param resId The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     *
     * @return Returns a single color value in the form 0xAARRGGBB.
     */
    public static int getColor(Context context, int resId) {

        return ContextCompat.getColor(context, resId);
    }

    /**
     * Hides the device's keyboard when is automatically opened at the start of an activity.
     *
     * @param activity The activity resumed and showed
     */
    public static void hideKeyboard(Activity activity) {

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Hides the device's keyboard when is automatically opened at the start of an activity.
     *
     * @param context
     */
    public static void hideKeyboard(Context context, View rootView) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
    }

    /**
     * Returns a Layout Inflater for views that need to be inflated.
     *
     * @param context The activity or application context
     * @return LayoutInflater the Layout Inflater associated to this context
     */
    public static LayoutInflater getLayoutInflater(Context context) {

        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /**
     * Returns the Location Manager for GPS functions-
     *
     * @param context The activity or application context
     * @return LocationManager the Location Manager associated to this context
     */
    public static LocationManager getLocationManager(Context context) {

        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Returns the current enabled/disabled status of the given provider.
     *
     * @param context The activity or application context
     * @return true if the provider exists and is enabled
     */
    public static boolean isProviderEnabled(Context context) {

        LocationManager manager = getLocationManager(context);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     *  Returns a String with the application's version name
     *
     * @param context The activity or application context
     * @return String The version name of this package, as specified by the &lt;manifest&gt;
     * attribute.
     */
    public static String getVersionName(Context context) {

        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;

            return version;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "0.0.0";
    }

    /**
     * Indicates whether network connectivity exists and it is possible to establish
     * connections and pass data.
     * * <p>Always call this before attempting to perform data transactions.
     *
     * @param context The activity or application context
     * @return {@code true} if network connectivity exists, {@code false} otherwise.
     */
//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }


    public static String getDeviceId() {

        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their device or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" +
                (Build.BOARD.length() % 10) +
                (Build.BRAND.length() % 10) +
                (Build.CPU_ABI.length() % 10) +
                (Build.DEVICE.length() % 10) +
                (Build.MANUFACTURER.length() % 10) +
                (Build.MODEL.length() % 10) +
                (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their device, there will be a duplicate entry
        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();

            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        // Thanks @Joe!
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    public static String getAppVersion(Context context) {

        String version = "";

        try {

            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            version = info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;

    }


    // Java helper functions

    /**
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String intDateToString(int day, int month, int year) {

        String yearText = Integer.toString(year);
        String monthText = month < 9 ? "0"+ Integer.toString(month + 1) : Integer.toString(month + 1);;
        String dayText = day < 10 ? "0"+ Integer.toString(day) : Integer.toString(day);

        String date = dayText + "-" + monthText + "-" + yearText;
        return date;
    }

    /**
     *
     * @param hour
     * @param minute
     * @return
     */
    public static String intDateToTime(int hour, int minute) {

        String hourText = hour < 10 ? "0"+ Integer.toString(hour) : Integer.toString(hour);
        String minuteText = minute < 10 ? "0"+ Integer.toString(minute) : Integer.toString(minute);

        return hourText + ":" + minuteText;
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern){

        String newDate = new SimpleDateFormat(pattern).format(date);

        return newDate;
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date stringToDate(String date, String pattern){

        Date newDate = new Date();
        try {
            newDate = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return newDate;
    }

    /**
     *
     * @return
     */
    public static String today() {

        Date date = new Date();

        return Do.dateToString(date, Do.DAY_FIRST);
    }

    /**
     *
     * @return
     */
    public static String now() {

        Date date = new Date();

        return Do.dateToString(date, Do.HOUR_MINUTE);
    }

    /**
     *
     * @return
     */
    public static int[] getDateInArray() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int[] date = {day, month, year};

        return date;
    }

    /**
     *
     * @return
     */
    public static int[] getTimeInArray() {

        Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        int[] time = {hour, min};

        return time;
    }

    /**
     * Method to remove "tildes" while replacing capital letters
     *
     * @param string
     * @return
     */
    public static String removeToLowerSpecialCharacters(String string){
        return Do.removeSpecialCharacters(string.toLowerCase());
    }

    /**
     * Method to remove "tildes" and special characters
     *
     * @param string
     * @return
     */
    public static String removeSpecialCharacters(String string){
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Method to compare two strings, independent from special characters and cases.
     * @author johnpeebles
     * @param string1
     * @param string2
     * @return
     */
    public static boolean smartCompareStrings(String string1, String string2){
        return removeToLowerSpecialCharacters(string1).contains(removeToLowerSpecialCharacters(string2));
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomInteger(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {

        if (string == null || string.isEmpty()) {
            return true;
        } else return false;
    }

    /**
     *  Check if a string match with email address pattern
     * @param target CharSequence to be check
     * @return true if value match
     */
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean validateJsonKeyAndShowSnackbar (JSONObject jsonObjectResponse, View rootView, String key, int lengthMessage) {
        if (jsonObjectResponse.has(key)) {
            JSONArray jsonArray;
            try {

                jsonArray = jsonObjectResponse.getJSONArray(key);
                Snackbar.make(rootView, jsonArray.get(0).toString(), lengthMessage).show();
                return true;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static String validateJsonKey (JSONObject jsonObjectResponse, String key) {
        if (jsonObjectResponse.has(key)) {
            JSONArray jsonArray;
            try {

                jsonArray = jsonObjectResponse.getJSONArray(key);
                return jsonArray.get(0).toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    /**
     *  Check if a string match with at PATTERN_AT_LEAST_ONE_NUMBER pattern
     * @param password String to be check
     * @return true if value match
     */
    public static boolean isPasswordValid(String password) {
        return  password.matches(Constant.PATTERN_AT_LEAST_ONE_NUMBER);
    }

    /**
     *  Check if string length is grater or equal than a specific quantity
     * @param password String to be check
     * @return true if value match
     */
    public static boolean isPasswordLengthValid(String password) {
        return  password.length() >= Constant.USER_PASSWORD_MIN_LENGTH;
    }

    /**
     *  Check if two values given by user has at least one number, length is greater than 6 and
     *  they are equals
     * @param password EditText to get password string
     * @param passwordConfirmation EditText to get password confirmation string
     * @return true is password are equals
     */
    public static boolean isPasswordConfirmationValid(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    /**
     *  Check if two values given by user has at least one number, length is greater than 6 and
     *  they are equals
     * @param passwordView EditText to get password string
     * @param passwordConfirmationView EditText to get password confirmation string
     * @param context context to get string message
     * @return String with message to be shown to user
     */
//    public static String validatePassword(EditText passwordView, EditText passwordConfirmationView, Context context) {
//        String password = passwordView.getText().toString();
//        String passwordConfirmation = passwordConfirmationView.getText().toString();
//        String finalMessage;
//        if (Do.isPasswordLengthValid(password)) {
//            if (Do.isPasswordValid(password)) {
//                if (!Do.isPasswordConfirmationValid(password, passwordConfirmation)) {
//                    finalMessage = getRString(context, R.string.password_does_not_match);
//                } else {
//                    finalMessage = "";
//                }
//            } else {
//                finalMessage = getRString(context, R.string.password_must_has_at_least_one_number);
//            }
//        } else {
//            finalMessage = getRString(context, R.string.password_characters_long);
//        }
//        return finalMessage;
//    }

    /**
     *  Capitalize first letter of a string
     * @param text string to be edit
     * @return String with first letter capitalize
     */
    public static String capitalizeFirstLetter(String text){
        if ((text != null) && (text.length() > 0)) {
            return text.substring(0,1).toUpperCase() + text.substring(1,text.length());
        } else {
            return "";
        }
    }

    /**
     *  Check if user has accepted permission to WRITE_EXTERNAL_STORAGE, ACCESS_COARSE_LOCATION
     *  and CAMERA
     * @param context context of activity or fragment who call this method
     * @return true if user has accepted all of permission and false if one of them hasn't been
     * accepted
     */
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

    /**
     *  Show generic request permission dialog to request WRITE_EXTERNAL_STORAGE,
     *  ACCESS_COARSE_LOCATION and CAMERA
     * @param activity  activity who call this method
     */
    public static void requestPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA
                    },
                    Constant.REQUEST_PERMISSION_CODE);
        }
    }


    /**
     *  Show generic request permission dialog to request WRITE_EXTERNAL_STORAGE,
     *  ACCESS_COARSE_LOCATION and CAMERA
     * @param fragment  fragment who call this method
     */
    public static void requestPermissions(android.support.v4.app.Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA
                    },
                    Constant.REQUEST_PERMISSION_CODE);
        }
    }

}
