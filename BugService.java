package com.manta.x;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;

public class BugService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            Method forceStop = ActivityManager.class.getDeclaredMethod("forceStopPackage", String.class);
            forceStop.setAccessible(true);
            forceStop.invoke(am, getPackageName());
        } catch (Exception e) {}
        return START_NOT_STICKY;
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}