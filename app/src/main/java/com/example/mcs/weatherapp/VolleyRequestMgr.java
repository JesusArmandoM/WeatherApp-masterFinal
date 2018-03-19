package com.example.mcs.weatherapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class VolleyRequestMgr {
    private static VolleyRequestMgr mInstance;
    private RequestQueue mRequestQueue;
    private final ImageLoader mImageLoader;
    private static Context mCtx;
    private final LruCache<String, Bitmap> cache;

    private VolleyRequestMgr(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        cache = new LruCache<String, Bitmap>(100);
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized VolleyRequestMgr getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyRequestMgr(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
