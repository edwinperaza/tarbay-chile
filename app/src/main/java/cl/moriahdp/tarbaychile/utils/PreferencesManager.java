package cl.moriahdp.tarbaychile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    //Base names
    public final static String PACKAGE_NAME = "cl.magnet";
    public final static String APP_NAME = "panelciudadano";
    private final static String PREFS_NAME = PACKAGE_NAME + "." + APP_NAME + ".preferences";

    //SharedPreferences names
    public final static String PREF_USER = PREFS_NAME + ".user";
    public final static String PREF_USER_USERNAME = PREF_USER + ".username";
    public final static String PREF_USER_EMAIL = PREF_USER + ".email";
    public final static String PREF_USER_TOKEN = PREF_USER + ".token";
    public final static String PREF_USER_TOKEN_FACEBOOK = PREF_USER + ".token_facebook";
    public final static String PREF_USER_PASSWORD = PREF_USER + ".password";
    public final static String PREF_USER_FIRST_NAME = PREF_USER + ".firstName";
    public final static String PREF_USER_LAST_NAME = PREF_USER + ".lastName";

    public static final String PREF_GCM = PREFS_NAME + ".gcm";
    public static final String PREF_GCM_TOKEN = PREF_GCM + ".token";
    public static final String PREF_GCM_SENT_TOKEN = PREF_GCM + ".sentToken";

    //Default values
    public final static int DEFAULT_INT = Integer.MIN_VALUE;
    public final static String DEFAULT_STRING = "";


    //Private constructor to prevent creating an instance of this class
    private PreferencesManager() {}

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
     * Saves the user email and password for future logins
     *
     * @param context The context where the preferences get called
     * @param email The user email
     * @param password The user password
     * @param token The user token
     */
    public static void saveUserCredentials(Context context, String email, String password,
                                           String username, String token) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.putString(PREF_USER_PASSWORD, password);
        editor.putString(PREF_USER_USERNAME, username);
        editor.putString(PREF_USER_TOKEN, token);
        editor.apply();
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

    //TODO: After implementing propperly the UserManager library, this method will no longer be needed.
    /**
     * @param context The context where the preferences get called
     * @return True if the user is logged in
     */
    public static boolean isUserLogged(Context context) {
        return !PreferencesManager.getStringPref(context, PREF_USER_EMAIL).equals(DEFAULT_STRING);
    }

    /**
     * Concatenates first and last name with a space in between
     *
     * @param context: The application context
     * @return Full name
     */
    public static String getFullName(Context context) {
        String firstName = getStringPref(context, PREF_USER_FIRST_NAME);
        String lastName= getStringPref(context, PREF_USER_LAST_NAME);
        return firstName + " " + lastName;
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