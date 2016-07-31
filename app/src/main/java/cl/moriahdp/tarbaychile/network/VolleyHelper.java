package cl.moriahdp.tarbaychile.network;

import android.content.Context;
import android.content.res.Resources;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class VolleyHelper {

    // Prevent the instantiation of this class
    private VolleyHelper() {
    }

    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error   The VolleyError received
     * @param context The context of the application
     * @return A string with the error feedback.
     */
    public static String getMessage(VolleyError error, Context context) {
        Resources resources = context.getResources();

        if (isNetworkProblem(error)) {
            // Handles network error
            return "No hay conexión a internet";
        } else if (isAuthError(error)) {
            // Handles authentication errors
            return "Error de autenticación";
        }

        // Any other error codes, return a message with the code.
        if (error.networkResponse != null) {
//            return String.format(resources.getString(R.string.unhandled_error),
//                    error.networkResponse.statusCode);
            return "Error desconocido de red";
        }

        // if there is no networkResponse, then the connection couldn't established, so there is an
        // internet error
        return "No hay conexión a internet";
    }


    /**
     * Check if the error is an authentication error.
     *
     * @param error The VolleyError received.
     * @return True if the error is an authentication error.
     */
    public static boolean isAuthError(VolleyError error) {
        return error.networkResponse != null && error.networkResponse.statusCode == 401;
    }

    /**
     * Determines whether the error is related to a network problem.
     *
     * @param error The VolleyError returned
     * @return True if the error is a network problem
     */
    public static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NoConnectionError) || (error instanceof NetworkError);
    }
}