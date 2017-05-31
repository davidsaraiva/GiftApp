package pt.ds.giftApp;

import android.app.Application;
import android.content.Context;

/**
 * Created by DS on 24/05/2017.
 */

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }

}
