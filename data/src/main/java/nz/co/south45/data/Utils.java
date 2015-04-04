package nz.co.south45.data;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by codie on 4/04/15.
 */
public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    private static Context appContext;

    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager)getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch (Exception e) {
            return false;
        }
    }

}
