package cl.moriahdp.tarbaychile.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class that handles SharedPreferences functionality. Has useful methods to save and retrieve
 * data from SharedPreferences.
 *
 * @author  Magnet SPA
 * Created 13/3/17
 */
public class PrefsManager {

    //Base names
    public final static String PACKAGE_NAME = "cl.magnet";
    public final static String APP_NAME = "vigia";
    private final static String PREFS_NAME = PACKAGE_NAME + "." + APP_NAME + ".preferences";

    //SharedPreferences names
    public final static String PREF_USER = PREFS_NAME + ".user";
    public final static String PREF_REPORT = PREFS_NAME + ".report";
    public final static String PREF_USER_EMAIL = PREF_USER + ".email";
    public final static String PREF_USER_PASSWORD = PREF_USER + ".password";
    public final static String PREF_USER_FULL_NAME = PREF_USER + ".full_name";
    public final static String PREF_USER_LAST_NAME = PREF_USER + ".lastName";
    public final static String PREF_USER_TELEPHONE = PREF_USER + ".telephone";
    public final static String PREF_USER_REGION = PREF_USER + ".regionId";
    public final static String PREF_USER_COMMUNE = PREF_USER + ".communeId";
    public final static String PREF_USER_AUTH_TOKEN = PREF_USER + ".auth_token";
    public final static String PREF_USER_COVERAGE_RADIUS = PREF_USER + ".coverage_radius";
    public final static String PREF_USER_LATITUDE = PREF_USER + ".latitude";
    public final static String PREF_USER_LONGITUDE = PREF_USER + ".longitude";
    public final static String PREF_USER_GROUP_ID = PREF_USER + ".groupId";
    public final static String PREF_USER_PROFILE_IMAGE_PATH = PREF_USER + ".profileImagePath";
    public final static String PREF_USER_PROFILE_IMAGE_URL = PREF_USER + ".profileImageUrl";
    public final static String PREF_USER_PROFILE_KIND_IMAGE_PATH = PREF_USER + ".profileKindImagePath";
    public final static String PREF_REPORT_TEMPORAL_IMAGE_PATH = PREF_REPORT + ".imagePath";
    public final static String PREF_REPORT_TEMPORAL_KIND_IMAGE_PATH = PREF_REPORT + ".kindImagePath";
    public final static String PREF_REPORT_TEMPORAL_TITLE = PREF_REPORT + ".title";
    public final static String PREF_REPORT_TEMPORAL_REFERENCE_LOCATION = PREF_REPORT + ".referenceLocation";
    public final static String PREF_REPORT_TEMPORAL_DESCRIPTION = PREF_REPORT + ".description";
    public final static String PREF_REPORT_TEMPORAL_CATEGORY = PREF_REPORT + ".categoryId";
    public final static String PREF_REPORT_TEMPORAL_LATITUDE = PREF_REPORT + ".latitude";
    public final static String PREF_REPORT_TEMPORAL_LONGITUDE = PREF_REPORT + ".longitude";
    public final static String PREF_EDIT_PROFILE = PREF_USER + ".editProfile";
    public final static String PREF_UPDATE_EDIT_PROFILE = PREF_USER + ".updateEditProfile";
    public final static String PREF_USER_FIRST_TIME = PREF_USER + ".isFirstTime";

    public static final String PREF_GCM = PREFS_NAME + ".gcm";
    public static final String PREF_GCM_TOKEN = PREF_GCM + ".token";
    public static final String PREF_GCM_SENT_TOKEN = PREF_GCM + ".sentToken";

    //Default values
    public final static int DEFAULT_INT = Integer.MIN_VALUE;
    public final static String DEFAULT_STRING = "";


    //Private constructor to prevent creating an instance of this class
    private PrefsManager() {}

    /**
     * Return application Shared Preferences
     *
     * @param context The context where the preferences get called
     * @return Application SharedPreferences
     */
    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Returns an Integer preference previously saved
     *
     * @param context: The application context
     * @return Integer preference
     */
    public static Integer getIntPref(Context context, String prefKey) {
        return getPrefs(context).getInt(prefKey, DEFAULT_INT);
    }

    /**
     * Returns a Long preference previously saved
     *
     * @param context: The context where the preferences get called
     * @return Long preference
     */
    public static Long getLongPref(Context context, String prefKey) {
        return getPrefs(context).getLong(prefKey, DEFAULT_INT);
    }

    /**
     * Returns a String preference previously saved
     *
     * @param context: The application context
     * @return String preference
     */
    public static String getStringPref(Context context, String prefKey) {
        return getPrefs(context).getString(prefKey, DEFAULT_STRING);
    }

    /**
     * Returns an Integer preference previously saved
     *
     * @param context: The application context
     * @return Integer preference
     */
    public static Boolean getBooleanPref(Context context, String prefKey) {
        return getPrefs(context).getBoolean(prefKey, true);
    }

    /**
     * Sets a Integer preference
     *
     * @param context: The application context
     * @param prefKey: The key identifier
     * @param value: The new preference value
     */
    public static void setIntPref(Context context, String prefKey, Integer value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(prefKey, value);
        editor.apply();
    }

    /**
     * Sets a Long preference
     *
     * @param context: The application context
     * @param prefKey: The key identifier
     * @param value: The new preference value
     */
    public static void setLongPref(Context context, String prefKey, Long value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putLong(prefKey, value);
        editor.apply();
    }

    /**
     * Sets a String preference
     *
     * @param context: The application context
     * @param prefKey: The key identifier
     * @param value: The new preference value
     */
    public static void setStringPref(Context context, String prefKey, String value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(prefKey, value);
        editor.apply();
    }

    /**
     * Saves the user mEmail and et_login_password for future logins
     *
     * @param context The context where the preferences get called
     * @param email The user mEmail
     * @param password The user et_login_password
     */
    public static void saveUserData(Context context, String email, String password, String fullName,
                                    int countyId, int coverageRadius, String phoneNumber,
                                    String avatar, String authToken, int group) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.putString(PREF_USER_PASSWORD, password);
        editor.putString(PREF_USER_FULL_NAME, fullName);
        editor.putInt(PREF_USER_COMMUNE, countyId);
        editor.putInt(PREF_USER_COVERAGE_RADIUS, coverageRadius);
        editor.putString(PREF_USER_TELEPHONE, phoneNumber);
        editor.putString(PREF_USER_PROFILE_IMAGE_URL, avatar);
        editor.putString(PREF_USER_AUTH_TOKEN, authToken);
        editor.putInt(PREF_USER_GROUP_ID, group);
        editor.apply();
    }

    /**
     * Saves the user mEmail and et_login_password for future logins
     *
     * @param context The context where the preferences get called
     * @param email The user mEmail
     * @param password The user et_login_password
     */
    public static void saveUserCredentials(Context context, String email, String password) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.putString(PREF_USER_PASSWORD, password);
        editor.apply();
    }

    /**
     * Saves the user mEmail and et_login_password for future logins
     *
     * @param context The context where the preferences get called
     * @param regionId The user mEmail
     */
    public static void saveUserRegionId(Context context, int regionId) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(PREF_USER_REGION, regionId);
        editor.apply();
    }

    /**
     * Saves the user mEmail and et_login_password for future logins
     *
     * @param context The context where the preferences get called
     */
    public static int getUserRegionId(Context context) {
        return getIntPref(context, PREF_USER_REGION);
    }


    /**
     * Saves kind of user
     *
     * @param context The context where the preferences get called
     * @param kind The user kind
     */
    public static void saveUserKind(Context context, int kind) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(PREF_USER_GROUP_ID, kind);
        editor.apply();
    }

    /**
     * get user kind
     *
     * @param context The context where the preferences get called
     */
    public static int getUserKind(Context context) {
        return getIntPref(context, PREF_USER_GROUP_ID);
    }

    /**
     * Saves the user commune
     *
     * @param context The context where the preferences get called
     * @param communeId The user commune
     */
    public static void saveUserCommuneId(Context context, int communeId) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(PREF_USER_COMMUNE, communeId);
        editor.apply();
    }

    /**
     * get user commune
     *
     * @param context The context where the preferences get called
     */
    public static int getUserCommuneId(Context context) {
        return getIntPref(context, PREF_USER_COMMUNE);
    }

    /**
     * Saves the user password
     *
     * @param context The context where the preferences get called
     * @param password The user password
     */
    public static void saveUserPassword(Context context, String password) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_PASSWORD, password);
        editor.apply();
    }

    /**
     * get user password
     *
     * @param context The context where the preferences get called
     */
    public static String getUserPassword(Context context) {
        return getStringPref(context, PREF_USER_PASSWORD);
    }

    /**
     * Saves the user latitude and longitude
     *
     * @param context The context where the preferences get called
     * @param latitude user latitude
     * @param longitude user longitude
     */
    public static void saveUserLatLng(Context context, String latitude , String longitude) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_LONGITUDE, longitude);
        editor.putString(PREF_USER_LATITUDE, latitude);
        editor.apply();
    }

    /**
     * get user longitude
     *
     * @param context The context where the preferences get called
     */
    public static String getUserLongitude(Context context) {
        return getStringPref(context, PREF_USER_LONGITUDE);
    }

    /**
     * get user longitude
     *
     * @param context The context where the preferences get called
     */
    public static String getUserLatitude(Context context) {
        return getStringPref(context, PREF_USER_LATITUDE);
    }

    /**
     * Saves user profile image path
     *
     * @param context The context where the preferences get called
     * @param currentProfilePath The user image path
     */
    public static void saveUserProfilePath(Context context, String currentProfilePath) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_PROFILE_IMAGE_PATH, currentProfilePath);
        editor.apply();
    }

    /**
     * get user profile path
     *
     * @param context The context where the preferences get called
     */
    public static String getUserProfilePath(Context context) {
        return getStringPref(context, PREF_USER_PROFILE_IMAGE_PATH);
    }

    /**
     * get user profile url
     *
     * @param context The context where the preferences get called
     */
    public static String getUserProfileUrl(Context context) {
        return getStringPref(context, PREF_USER_PROFILE_IMAGE_URL);
    }

    /**
     * Saves user kind image profile path
     *
     * @param context The context where the preferences get called
     * @param kind The user mEmail
     */
    public static void saveUserKindProfilePath(Context context, int kind) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(PREF_USER_PROFILE_KIND_IMAGE_PATH, kind);
        editor.apply();
    }

    /**
     * get user kind image profile path
     *
     * @param context The context where the preferences get called
     */
    public static int getUserKindProfilePath(Context context) {
        return getIntPref(context, PREF_USER_PROFILE_KIND_IMAGE_PATH);
    }

    /**
     * Clears all application preferences
     *
     * @param context The context where the preferences get called
     */
    public static void clearPrefs(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Clears all application preferences
     *
     * @param context The context where the preferences get called
     */
    public static void clearPrefsReport(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.remove(PREF_REPORT_TEMPORAL_IMAGE_PATH);
        editor.remove(PREF_REPORT_TEMPORAL_KIND_IMAGE_PATH);
        editor.remove(PREF_REPORT_TEMPORAL_TITLE);
        editor.remove(PREF_REPORT_TEMPORAL_REFERENCE_LOCATION);
        editor.remove(PREF_REPORT_TEMPORAL_DESCRIPTION);
        editor.remove(PREF_REPORT_TEMPORAL_CATEGORY);
        editor.remove(PREF_REPORT_TEMPORAL_LATITUDE);
        editor.remove(PREF_REPORT_TEMPORAL_LONGITUDE);
        editor.apply();
    }

    /**
     * Clears commune data
     *
     * @param context The context where the preferences get called
     */
    public static void clearCommuneId(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.remove(PREF_USER_COMMUNE);
        editor.apply();
    }

    /**
     * @param context The context where the preferences get called
     * @return True if the user is logged in
     */
    public static boolean isUserLogged(Context context) {
        return !PrefsManager.getStringPref(context, PREF_USER_EMAIL).equals(DEFAULT_STRING);
    }

    /**
     * Concatenates first and last name with a space in between
     *
     * @param context: The application context
     * @return Full name
     */
    public static String getFullName(Context context) {
        return getStringPref(context, PREF_USER_FULL_NAME);
    }

    /**
     * Saves the user name
     *
     * @param context The context where the preferences get called
     * @param username The user mEmail
     */
    public static void saveUserName(Context context, String username) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_FULL_NAME, username);
        editor.apply();
    }

    /**
     * Get user email from share preferences
     *
     * @param context: The application context
     * @return Full name
     */
    public static String getEmail(Context context) {
        return getStringPref(context, PREF_USER_EMAIL);
    }

    /**
     * Saves the user mEmail
     *
     * @param context The context where the preferences get called
     * @param email The user mEmail
     */
    public static void saveUserEmail(Context context, String email) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.apply();
    }

    /**
     * Get user telephone
     *
     * @param context: The application context
     * @return telephone
     */
    public static String getUserTelephone(Context context) {
        return getStringPref(context, PREF_USER_TELEPHONE);
    }

    /**
     * Saves the user telephone
     *
     * @param context The context where the preferences get called
     * @param telephone The user telephone
     */
    public static void saveUserTelephone(Context context, String telephone) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_TELEPHONE, telephone);
        editor.apply();
    }

    /**
     * Saves report data to be shown on detail
     *
     * @param context The context where the preferences get called
     * @param currentReportPath path to report image
     * @param reportTitle title of report
     * @param reportReferenceLocation location reference
     * @param reportDescription description of report
     * @param reportImageKind kind of image (Gallery or Photo)
     * @param reportCategory category of report
     * @param reportLatitude latitude of location
     * @param reportLongitude longitude of location
     */
    public static void saveReportData(Context context, String currentReportPath,
                                      String reportTitle, String reportReferenceLocation,
                                      String reportDescription , int reportImageKind,
                                      int reportCategory, String reportLatitude,
                                      String reportLongitude) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_REPORT_TEMPORAL_IMAGE_PATH, currentReportPath);
        editor.putString(PREF_REPORT_TEMPORAL_TITLE, reportTitle);
        editor.putString(PREF_REPORT_TEMPORAL_REFERENCE_LOCATION, reportReferenceLocation);
        editor.putString(PREF_REPORT_TEMPORAL_DESCRIPTION, reportDescription);
        editor.putInt(PREF_REPORT_TEMPORAL_KIND_IMAGE_PATH, reportImageKind);
        editor.putInt(PREF_REPORT_TEMPORAL_CATEGORY, reportCategory);
        editor.putString(PREF_REPORT_TEMPORAL_LATITUDE, reportLatitude);
        editor.putString(PREF_REPORT_TEMPORAL_LONGITUDE, reportLongitude);
        editor.apply();
    }

    
    public static void setIsEditProfile(Context context, boolean isEditProfile) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(PREF_EDIT_PROFILE, isEditProfile);
        editor.apply();
    }

    public static boolean getIsEditProfile(Context context) {

        SharedPreferences prefs = getPrefs(context);
        return prefs.getBoolean(PREF_EDIT_PROFILE, false);
    }

    public static void setIsUpdateEditProfile(Context context, boolean isEditProfile) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(PREF_UPDATE_EDIT_PROFILE, isEditProfile);
        editor.apply();
    }

    public static boolean getIsUpdateEditProfile(Context context) {

        SharedPreferences prefs = getPrefs(context);
        return prefs.getBoolean(PREF_UPDATE_EDIT_PROFILE, false);
    }

    /**
     * @param context The context where the preferences get called
     * @return True if is the first time that the app is used with this device
     */
    public static Boolean isFirstTime(Context context) {
        return PrefsManager.getBooleanPref(context, PREF_USER_FIRST_TIME);
    }

    /**
     * Save true when the user uses the app for first time
     *
     * @param context The context where the preferences get called
     * @param value The value of boolean
     */
    public static void saveFirstTime(Context context, Boolean value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(PREF_USER_FIRST_TIME, value);
        editor.apply();
    }

    // ################ GCM ###############

    public static void setSentTokenToServer(Context context, boolean isSent) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(PREF_GCM_SENT_TOKEN, isSent);
        editor.apply();
    }

    public static boolean getSentTokenToServer(Context context) {

        SharedPreferences prefs = getPrefs(context);
        return prefs.getBoolean(PREF_GCM_SENT_TOKEN, false);
    }

    public static void setGCMToken(Context context, String token) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_GCM_TOKEN, token);
        editor.apply();
    }

    public static String getGCMToken(Context context) {

        SharedPreferences prefs = getPrefs(context);
        return prefs.getString(PREF_GCM_TOKEN, null);
    }

}
