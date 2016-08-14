package cl.moriahdp.tarbaychile.network;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.net.HttpURLConnection;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public abstract class MoriahResponseErrorListener implements Response.ErrorListener {

    private static final String TAG = MoriahResponseErrorListener.class.getSimpleName();

    public static final int HTTP_UNAUTHORIZED = HttpURLConnection.HTTP_UNAUTHORIZED;
    public static final int HTTP_UPGRADE_REQUIRED = 426;

    private Context mContext;

    public MoriahResponseErrorListener(Context context) {
        // prevents an activity or broadcast receiver leak by getting the application context
        mContext = context.getApplicationContext();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        NetworkResponse networkResponse = error.networkResponse;

        if (networkResponse != null) {

            switch (networkResponse.statusCode) {
                case HTTP_UNAUTHORIZED:
                    onUnauthorizedError(error);
                    break;
                case HTTP_UPGRADE_REQUIRED:
                    onUpgradeRequiredError(error);
                    break;
                default:
                    onUnhandledError(error);
                    break;
            }

        } else {
            noInternetConnectionError();
        }

    }

    /**
     * Method called when there is no internet connection. This happens when there is
     * no response from the server
     */
    public abstract void noInternetConnectionError();

    /**
     * Method called when an unhandled error has been ocurred. This happens when the server
     * responds with a 4xx or 5xx status code, with the exception of 401 and
     * {@value #HTTP_UPGRADE_REQUIRED} codes,
     * that are handled by
     * {#onUnauthorizedError(com.android.volley.VolleyError)}
     * and {#onUpgradeRequiredError(com.android.volley.VolleyError)} respectively.
     *
     * @param volleyError The error with the provided error code.
     */
    public abstract void onUnhandledError(VolleyError volleyError);

    /**
     * Method called when an Unauthorized error has been ocurred. This happens when the
     * server responds with an 401 status code.
     *
     * @param volleyError The error with the provided error code.
     * @see <a href="http://tools.ietf.org/html/rfc2616#section-10.4.2">401 Unauthorized</a>
     */
    public abstract <T> void onUnauthorizedError(VolleyError volleyError);

    /**
     * Method called when an Upgrade Required error has been ocurred. This happens when the
     * server responds with a {@value #HTTP_UPGRADE_REQUIRED} status code.
     *
     * @param volleyError The error with the provided error code.
     */
    public abstract void onUpgradeRequiredError(VolleyError volleyError);

}