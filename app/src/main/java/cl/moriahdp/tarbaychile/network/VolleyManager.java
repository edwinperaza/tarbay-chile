package cl.moriahdp.tarbaychile.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by edwinmperazaduran on 7/31/16.
 */
public class VolleyManager {

    public static final String TAG = VolleyManager.class.getSimpleName();

    private static VolleyManager sInstance; // singleton instance of Volley Manager

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mContext;
    private String mUserAgent; // user agent known at runtime

    /**
     * Private constructor to prevent that VolleyManager is instanciated outside this class.
     *
     * @param context The context of the controller
     */
    private VolleyManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
//
//        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(mContext),
//                CookiePolicy.ACCEPT_ORIGINAL_SERVER);
//        CookieHandler.setDefault(cookieManager);
//        mRequestQueue = getRequestQueue();
//        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(LruBitmapCache
//                .getCacheSize(context)));
//
//        // set USER AGENT
//        mUserAgent = UserAgentUtils.getUserAgent(mContext);
    }

    /**
     * Returns the singleton instance of VolleyManager. If there is no instance,
     * then it creates a new one, else it returns the existing one.
     * <p/>
     * A key concept is that context <b>must</b> be the Application context,
     * <b>not</b> an Activity context. This  ensures that the RequestQueue will last for the
     * lifetime of your app, instead of being recreated every time the activity is recreated (for
     * example, when the user rotates the device).
     *
     * @param context The context where the method is called. This context <b>MUST</b> be
     *                Application context.
     * @return The instance of VolleyManager
     */
    public static synchronized VolleyManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyManager(context);
        }

        return sInstance;
    }


    /**
     * Returns the singleton instance of RequestQueue that last the lifetime of the app. If there
     * is no instance of RequestQueue, then a new one is created.
     * <p/>
     * @return RequestQueue instance
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * @return The image loader
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


    /**
     * Adds a request to the RequestQueue with the default tag.
     *
     * @param req The request that will be added to the queue.
     * @param <T>     The type of the request
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Adds a request to the RequestQueue with a specific tag.
     *
     * @param request The request that will be added to the queue.
     * @param tag     The tag to be added to the request
     * @param <T>     The type of the request
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    /**
     * Cancels all pending requests by the specified tag. It is important to specify a tag so
     * that pending/ongoing requests can be cancelled.
     *
     * @param tag The tag of the requests that are going to be cancelled
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}