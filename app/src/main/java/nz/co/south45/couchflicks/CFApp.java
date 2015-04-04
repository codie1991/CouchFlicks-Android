package nz.co.south45.couchflicks;

import android.app.Application;

import nz.co.south45.data.Utils;

/**
 * Created by codie on 4/04/15.
 */
public class CFApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.setAppContext(getApplicationContext());
    }
}
