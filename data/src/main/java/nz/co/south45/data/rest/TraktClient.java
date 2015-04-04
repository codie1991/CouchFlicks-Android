package nz.co.south45.data.rest;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.Util;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;

import nz.co.south45.data.Constants;
import nz.co.south45.data.Utils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by codie on 4/04/15.
 */
public class TraktClient {
    private final String TAG = TraktClient.class.getSimpleName();
    private static TraktClient mInstance = null;
    private static TraktApiInterface mTraktService;
    //    private static final String BASE_URL = "http://172.17.1.27:3000";
    private static String mCookies;
    private Context context;
    private static final long SIZE_OF_CACHE = 10 * 1024 * 1024;

    public TraktClient() {
        context = Utils.getAppContext();

        File cacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        Cache cache = null;
        try {
            cache = new Cache(cacheDirectory, SIZE_OF_CACHE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpClient ok = new OkHttpClient();
        ok.setCache(cache);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setEndpoint("https://api-v2launch.trakt.tv")
                .setClient(new OkClient(ok))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        // Setup Trakt API detail headers
                        request.addHeader("Content-type","application/json");
                        request.addHeader("trakt-api-key", Constants.API_KEY);
                        request.addHeader("trakt-api-version","2");
                        // Cache
//                        if (Utils.isOnline()) {
//                            int maxAge = 60; // read from cache for 1 minute
//                            request.addHeader("Cache-Control", "public, max-age=" + maxAge);
//                        } else {
//                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//                            request.addHeader("Cache-Control",
//                                    "public, only-if-cached, max-stale=" + maxStale);
//                        }
                    }
                })
                .build();
        mTraktService = restAdapter.create(TraktApiInterface.class);
    }

    public FilterInputStream getFromCache(String url) throws Exception {
        DiskLruCache cache = DiskLruCache.open(context.getCacheDir(), 201105, 2, SIZE_OF_CACHE);
        cache.flush();
        String key = Util.md5Hex(url);
        final DiskLruCache.Snapshot snapshot;
        try {
            snapshot = cache.get(key);
            if (snapshot == null) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        FilterInputStream bodyIn = new FilterInputStream((java.io.InputStream) snapshot.getSource(1)) {
            @Override
            public void close() throws IOException {
                snapshot.close();
                super.close();
            }
        };

        return bodyIn;
    }

    public static synchronized TraktClient getInstance() {
        if (mInstance == null) mInstance = new TraktClient();
        return mInstance;
    }

    public static TraktApiInterface getApiInterface() {

        return getInstance().mTraktService;
    }
}
